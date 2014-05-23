package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;
import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lambdacalc.Types;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import predcalc.impl.ILambda2Pred;
import predcalc.impl.IPredCalc;
import semante.abusechecker.ICollectivityAndIotaChecker;
import semante.disamb.UnambiguousAnnotation;
import semante.disamb.impl.IAnnotationTreePrinter;
import semante.disamb.impl.IDisambiguator;
import semante.disamb.impl.IFlattenTree;
import semante.lexicon.RichLexicon;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Category;
import semante.pipeline.HigherOrderImplication.HOIException;
import semante.pipeline.Pair;
import semante.pipeline.Pipeline;
import semante.pipeline.Result;
import semante.pipeline.TestCaseCreator;
import semante.prover.impl.IProver;
import semante.settings.Settings;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IPipeline implements Pipeline {
	
	@Delegate
	TestCaseCreator	testcase = new ITestCaseCreator();
	Settings		settings;
	STL				stl;
	RichLexicon		lexicon;
	
	// REDUCER: simple function wrapper around the STL beta reducer
	Function<DeBruijn,DeBruijn> reducer
		= new Function<DeBruijn,DeBruijn>() {
			@Override
			public final DeBruijn apply(final DeBruijn expr) {
				return stl.betaReduce(expr);
			}
		};
	
	@Override
	public final <ID> Result<ID> prove(
		final BinaryTree<ID, Annotation> text,
		final BinaryTree<ID, Annotation> hypo,
		final Iterable<Pair<BinaryTree<ID, Annotation>, BinaryTree<ID, Annotation>>> subsumptions) throws FileNotFoundException {
		
		// DISAMBIGUATE: infer denotations and function application structure
		val printer       = new IAnnotationTreePrinter<ID>();
		val flattener     = new IFlattenTree<ID>(stl.getDeBruijnBuilder());
		val treebuilder   = new IBinaryTreeBuilder<ID,UnambiguousAnnotation>();
		val disambiguator = new IDisambiguator<ID>(stl,lexicon,flattener,printer,treebuilder);
		val disambTextM = disambiguator.disambiguate(text);
		if (disambTextM.isLeft()) return disambTextM.getLeft().toResult();
		val disambHypoM = disambiguator.disambiguate(hypo);
		if (disambHypoM.isLeft()) return disambHypoM.getLeft().toResult();
		val disambTexts = disambTextM.getRight();
		val disambHypos = disambHypoM.getRight();
		
		// CHECK: perform a series of checks on the unambiguous annotation trees
		val collectivityAndIota = new ICollectivityAndIotaChecker(reducer,treebuilder);
		
		// FLATTEN: convert unambiguous trees to lambda terms
		val flatTexts = flattener.flattenAll(disambTexts);
		val flatHypos = flattener.flattenAll(disambHypos);
		
		for (val flatText: flatTexts) {
			System.err.println(stl.format(stl.fromDeBruijn(flatText)));
		}
		
		
		// REDUCE: convert lambda terms to normal form
		val redTexts = Lists.transform(flatTexts, reducer);
		val redHypos = Lists.transform(flatHypos, reducer);
		val nubTexts = ImmutableSet.copyOf(redTexts);
		val nubHypos = ImmutableSet.copyOf(redHypos);

		for (val nubText: nubTexts) {
			System.err.println("T: "+stl.format(stl.fromDeBruijn(nubText)));
		}
		for (val nubHypo: nubHypos) {
			System.err.println("H: "+stl.format(stl.fromDeBruijn(nubHypo)));
		}
		
		
		// SUBSUMPTIONS: convert the subsumption relations to lambda terms
		//               and add them to the term representing the text
		val hoi  = new IHigherOrderImplication(stl);
		val ctxt = Iterables.concat(nubTexts,nubHypos);
		val subs = ImmutableList.<DeBruijn> builder();
		for (val subsPair: subsumptions) {
			val subsRawText = subsPair.getFirst();
			val subsRawHypo = subsPair.getSecond();
			
			val subsMaybeFlatText = disambiguator.disambiguateAndFlatten(subsRawText);
			if (subsMaybeFlatText.isLeft())
				return subsMaybeFlatText.getLeft();
			val subsFlatTexts = subsMaybeFlatText.getRight();
			val subsRedTexts  = Lists.transform(subsFlatTexts, reducer);
			val subsNubTexts  = ImmutableSet.copyOf(subsRedTexts); 
			
			val subsMaybeFlatHypo = disambiguator.disambiguateAndFlatten(subsRawHypo);
			if (subsMaybeFlatHypo.isLeft())
				return subsMaybeFlatHypo.getLeft();
			val subsFlatHypos = subsMaybeFlatHypo.getRight();
			val subsRedHypos  = Lists.transform(subsFlatHypos, reducer);
			val subsNubHypos  = ImmutableSet.copyOf(subsRedHypos); 
			
			for (val subsText: subsNubTexts) {
				for (val subsHypo: subsNubHypos) {
					try {
						subs.add(hoi.higherOrderImplication(subsText, subsHypo, ctxt));
					}
					catch (HOIException e) {
						System.err.println("Warning: " + e.getMessage());
					}
				}
			}
		}
		
		// flatten with conjunction.
		val bld = stl.getDeBruijnBuilder();
		val subsList = subs.build();
		final Iterable<DeBruijn> withSubsTexts;
		if (subsList.isEmpty()) {
			withSubsTexts = nubTexts;
		}
		else {
			DeBruijn subsExpr = subsList.get(0);
			for (int i = 1; i < subsList.size(); i++) {
				subsExpr =
					bld.application(
						bld.constant("AND",Types.TTT), subsExpr, subsList.get(i));
			}
			val listbuilder = ImmutableList.<DeBruijn> builder();
			for (val nubText: nubTexts) {
				listbuilder.add(bld.application(bld.constant("AND",Types.TTT), nubText, subsExpr));
			}
			withSubsTexts = listbuilder.build();
		}
		
		for (val nubText: withSubsTexts) {
			System.err.println("T: "+stl.format(stl.fromDeBruijn(nubText)));
		}
		
		// LOWER: convert normal form terms to first order logic
		val pcalc  = new IPredCalc();
		val stl2p  = new ILambda2Pred(pcalc, stl);
		val prover = new IProver(settings, pcalc);
		
		for (val nubText: withSubsTexts) {
			val t = stl2p.smash(stl.fromDeBruijn(nubText));
			
			for (val nubHypo: nubHypos) {
				val h       = stl2p.smash(stl.fromDeBruijn(nubHypo));
				
				// PROVE: attempt to prove the combination of text and hypothesis
				val res     = prover.prove(t, h);
				val resType = res.getProverOutputPF().getResultType();
				switch (resType) {
				case ProofFound:
					return new IResult$Proof<ID>();
				case NoProofCanBeFound:
					break;
				case Error:
				case Interuppted:
				case NotRun:
				case Unset:
				default:
					// TODO convert this to returning IResult$Error()
					System.err.println("Unexpected error in prover execution: " + res.getProverOutputPF().getOutput());					
				}
			}
		}
		return new IResult$Unknown<ID>();
	}
	
	@Override
	public final List<Category> getCategories() {
		return Lists.transform(lexicon.getEntries(), new Function<String,Category>() {
			@Override
			public final Category apply(final String input) {
				return new ICategory(input);
			}
		});
	}
}
