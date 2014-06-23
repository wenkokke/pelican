package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lambdacalc.Types;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.checker.ICollectivityAndIotaChecker;
import semante.checker.IllegalAnnotationException;
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
import semante.predcalc.ExprForm;
import semante.predcalc.FOLExpr.Formula;
import semante.predcalc.Smasher;
import semante.predcalc.impl.IPredCalc;
import semante.predcalc.impl.ISmasher;
import semante.prover.ProverResult;
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
	
	public enum ETHType {ETextTree, EHypoTree};	
		
	public class PreparedFormulae<ID> {
		
		List<PreparedFormula> formulae;
		Result<ID> result;
		ETHType thType;
		
		public PreparedFormulae(List<PreparedFormula> formulae) {
			result = null;
			this.formulae = new ArrayList<PreparedFormula>(formulae);
		}
		
		public PreparedFormulae(Result<ID> result, ETHType thType) {
			this.result = result;
			this.thType = thType;
			this.formulae = null;
		}
		
		public boolean isResultSet() {
			return result!=null;
		}
		
		public Result<ID> getResult() {
			return result;
		}

		public ETHType getTHType() {
			return thType;
		}
		
		public List<PreparedFormula> getFormulae() {
			return formulae;
		}
	
	}
		
		
	public class PreparedFormula {
		final String tText, hText;
		final ExprForm<Formula> tFormula, hFormula;
		
		public PreparedFormula(String tText, ExprForm<Formula> tFormula,
				String hText, ExprForm<Formula> hFormula) {
			super();
			this.tText = tText;
			this.hText = hText;
			this.tFormula = tFormula;
			this.hFormula = hFormula;
		}

		public String getTText() {
			return tText;
		}
		
		public String getHText() {
			return hText;
		}
		
		public ExprForm<Formula> getTFormula() {
			return tFormula;
		}
		
		public ExprForm<Formula> getHFormula() {
			return hFormula;
		}
		
	}
	
	
	public final <ID> PreparedFormulae<ID> prepare(
		final BinaryTree<ID, Annotation> text,
		final BinaryTree<ID, Annotation> hypo,
		final Iterable<Pair<BinaryTree<ID, Annotation>, BinaryTree<ID, Annotation>>> subsumptions,
		Smasher stl2p) throws FileNotFoundException {
		
		// DISAMBIGUATE: infer denotations and function application structure
		val printer       = new IAnnotationTreePrinter<ID>();
		val flattener     = new IFlattenTree<ID>(stl.getDeBruijnBuilder());
		val treebuilder   = new IBinaryTreeBuilder<ID,UnambiguousAnnotation>();
		val disambiguator = new IDisambiguator<ID>(stl,lexicon,flattener,printer,treebuilder);
		val disambTextM = disambiguator.disambiguate(text);
		if (disambTextM.isLeft()) return new PreparedFormulae<ID>(disambTextM.getLeft().<ID>toResult(),ETHType.ETextTree);
		val disambHypoM = disambiguator.disambiguate(hypo);
		if (disambHypoM.isLeft()) return new PreparedFormulae<ID>(disambHypoM.getLeft().<ID>toResult(),ETHType.EHypoTree);
		val disambTexts = disambTextM.getRight();
		val disambHypos = disambHypoM.getRight();
		
		// CHECK: perform a series of checks on the unambiguous annotation trees
		val collectivityAndIota = new ICollectivityAndIotaChecker<ID>(stl,flattener,treebuilder);
		val validTextsBuilder = ImmutableList.<BinaryTree<ID, UnambiguousAnnotation>> builder();
		val invalidTextsBuilder = ImmutableList.<IllegalAnnotationException> builder();
		for (val disambText: disambTexts) {
			try {
				collectivityAndIota.check(disambText);
				validTextsBuilder.add(disambText);
			}
			catch (IllegalAnnotationException e) {
				invalidTextsBuilder.add(e);
			}
		}
		val validTexts = validTextsBuilder.build();
		if (validTexts.isEmpty()) {
			val invalidTexts = invalidTextsBuilder.build();
			for(val invalidText : invalidTexts) {
				return new PreparedFormulae<ID>(invalidText.<ID>toResult(),ETHType.ETextTree);
			}
		}
		
		
		// FLATTEN: convert unambiguous trees to lambda terms
		val flatTexts = flattener.flattenAll(validTexts);
		val flatHypos = flattener.flattenAll(disambHypos);
		
		System.err.println("Unambiguous derivations of flat text: " + flatTexts.size()); 
		int flatId = 0;
		for (val flatText: flatTexts) {
			System.err.println("Flat text " + flatId + ": "+ stl.format(stl.fromDeBruijn(flatText)));
			flatId++;
		}
		
		
		// REDUCE: convert lambda terms to normal form
		val redTexts = Lists.transform(flatTexts, reducer);
		val redHypos = Lists.transform(flatHypos, reducer);

		/*
		System.err.println("Unambiguous derivations of reduced flat text: " + flatTexts.size()); 
		flatId = 0;
		for (val redText: redTexts) {
			System.err.println("Reduced Flat text " + flatId + ": "+ stl.format(stl.fromDeBruijn(redText)));
			flatId++;
		}
		*/
		
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
				return new PreparedFormulae<ID>(subsMaybeFlatText.getLeft(),ETHType.ETextTree);
			val subsFlatTexts = subsMaybeFlatText.getRight();
			val subsRedTexts  = Lists.transform(subsFlatTexts, reducer);
			val subsNubTexts  = ImmutableSet.copyOf(subsRedTexts); 
			
			val subsMaybeFlatHypo = disambiguator.disambiguateAndFlatten(subsRawHypo);
			if (subsMaybeFlatHypo.isLeft())
				return new PreparedFormulae<ID>(subsMaybeFlatHypo.getLeft(),ETHType.EHypoTree);
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
		List<PreparedFormula> runnableFormulas = new ArrayList<PreparedFormula>();
		
		for (val nubText: withSubsTexts) {
			val tText = stl.format(stl.fromDeBruijn(nubText));
			val tFormula = stl2p.smash(stl.fromDeBruijn(nubText));			
			for (val nubHypo: nubHypos) {
				val hText = stl.format(stl.fromDeBruijn(nubHypo));				
				val hFormula = stl2p.smash(stl.fromDeBruijn(nubHypo));
				runnableFormulas.add(new PreparedFormula(tText,tFormula,hText,hFormula));
			}
		}
		return new PreparedFormulae<ID>(runnableFormulas);
	}

	public interface ResultHandler<ID> {
		Result<ID> getFinalResult();
		boolean isFinalResultSet();
		void handle(ProverResult result);
	}
	
	private class SimpleResultHandler<ID> implements ResultHandler<ID> {
		boolean finalResultSet = false;
		Result<ID> finalResult = null;

		public void setFinalResult(Result<ID> result) {
			if (!finalResultSet) {
				finalResult = result;
				finalResultSet = true;
			}
		}
		
		@Override
		public void handle(ProverResult result) {
			switch (result.getProverOutputPF().getResultType()) {
			case ProofFound:
				setFinalResult(new IResult$Proof<ID>());
				break;
			case NoProofCanBeFound:
				break;
			case Error:
			case Interuppted:
			case NotRun:
			case Unset:
			default:
				// TODO convert this to returning IResult$Error()
				System.err.println("Unexpected error in prover execution: " + result.getProverOutputPF().getOutput());
			}
		}

		@Override
		public Result<ID> getFinalResult() {
			return finalResultSet ? finalResult : new IResult$Unknown<ID>();  
		}
		
		@Override
		public boolean isFinalResultSet() {
			return finalResultSet;  
		}
		
		
	}
		
	@Override
	public final <ID> Result<ID> prove(
		final BinaryTree<ID, Annotation> text,
		final BinaryTree<ID, Annotation> hypo,
		final Iterable<Pair<BinaryTree<ID, Annotation>, BinaryTree<ID, Annotation>>> subsumptions) throws FileNotFoundException {

		// prepare interfaces
		val pcalc  = new IPredCalc(settings);
		val prover = new IProver(settings, pcalc);
		val stl2p  = new ISmasher(pcalc, stl);
		
		// prepare the formuals to try to prove (including disambiguation, flattening, etc.)
		PreparedFormulae<ID> preparedFormulae = prepare(text, hypo, subsumptions, stl2p);
		
		// if a result is already set then it's an error and we report it to the caller directly
		// otherwise, we start the proving stage (prover loop).
		if (preparedFormulae.isResultSet()) {
			return preparedFormulae.getResult() ;
		} else {
			// PROVE: attempt to prove the combinations of text and hypothesis
			List<PreparedFormula> formulas = preparedFormulae.getFormulae();
			ResultHandler<ID> resultHandler = new SimpleResultHandler<ID>();
			for (int index=0 ; index<formulas.size() && !resultHandler.isFinalResultSet() ; index++) {
				PreparedFormula formula = formulas.get(index);
				resultHandler.handle(prover.prove(formula.getTFormula(), formula.getHFormula()));
			}
			return resultHandler.getFinalResult();
		}
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
