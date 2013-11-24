package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;
import java.util.List;

import predcalc.impl.IPredCalc;
import predcalc.impl.ILambda2Pred;
import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.flattener.impl.ITreePrinter;
import semante.flattener.impl.IFlattenTree;
import semante.lexicon.RichLexicon;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Category;
import semante.pipeline.Pipeline;
import semante.pipeline.Result;
import semante.pipeline.TestCaseCreator;
import semante.prover.impl.IProver;
import semante.settings.Settings;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IPipeline implements Pipeline {
	
	@Delegate
	TestCaseCreator	testcase = new ITestCaseCreator();
	Settings		settings;
	STL				stl;
	RichLexicon		lexicon;
	
	@Override
	public final <ID> Result<ID> prove(
		final BinaryTree<ID, Annotation> text,
		final BinaryTree<ID, Annotation> hypo,
		final String subsumptions) throws FileNotFoundException {
		
		val printer = new ITreePrinter<ID>();
		val flattener = new IFlattenTree<ID>(stl,lexicon,printer);
		val flatTextM = flattener.flatten(text);
		if (flatTextM.isLeft()) return flatTextM.getLeft();
		val flatHypoM = flattener.flatten(hypo);
		if (flatHypoM.isLeft()) return flatHypoM.getLeft();
		val flatTexts = flatTextM.getRight();
		val flatHypos = flatHypoM.getRight();
		
		for (val flatText: flatTexts) {
			System.err.println(stl.format(stl.fromDeBruijn(flatText)));
		}
		
		val reducer = new Function<DeBruijn,DeBruijn>() {
			@Override
			public final DeBruijn apply(final DeBruijn expr) {
				return stl.betaReduce(expr);
			}
		};
		val redTexts = Lists.transform(flatTexts, reducer);
		val redHypos = Lists.transform(flatHypos, reducer);
		
		val nubTexts = ImmutableSet.copyOf(redTexts);
		val nubHypos = ImmutableSet.copyOf(redHypos);
		
		val pcalc = new IPredCalc();
		val stl2p = new ILambda2Pred(pcalc, stl);
		val prover = new IProver(settings, pcalc);
		
		for (val nubText: nubTexts) {
			System.err.println(stl.format(stl.fromDeBruijn(nubText)));
		}
		for (val nubHypo: nubHypos) {
			System.err.println(stl.format(stl.fromDeBruijn(nubHypo)));
		}
		
		for (val nubText: nubTexts) {
			val t = stl2p.smash(stl.fromDeBruijn(nubText));
			for (val nubHypo: nubHypos) {
				val h = stl2p.smash(stl.fromDeBruijn(nubHypo));
				val res = prover.prove(t, h, subsumptions);
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
