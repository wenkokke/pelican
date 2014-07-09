package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lambdacalc.DeBruijn;
import lambdacalc.Expr;
import lambdacalc.STL;
import lambdacalc.Type;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.checker.ICollectivityAndIotaChecker;
import semante.checker.IllegalAnnotationException;
import semante.disamb.AnnotationTreePrinter;
import semante.disamb.Disambiguator;
import semante.disamb.FlattenTree;
import semante.disamb.UnambiguousAnnotation;
import semante.disamb.impl.IAnnotationTreePrinter;
import semante.disamb.impl.IDisambiguator;
import semante.disamb.impl.IFlattenTree;
import semante.lexicon.RichLexicon;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.BinaryTreeBuilder;
import semante.pipeline.Category;
import semante.pipeline.ImplicationGenerator;
import semante.pipeline.ImplicationGenerator.UnmatchedTypesException;
import semante.pipeline.ImplicationGenerator.UnsupportedImplication;
import semante.pipeline.IotaExtractor;
import semante.pipeline.Pair;
import semante.pipeline.Pipeline;
import semante.pipeline.PreparedFormulae;
import semante.pipeline.PreparedRunnableFormula;
import semante.pipeline.PreparedRunnableFormulae;
import semante.pipeline.PreparedTree;
import semante.pipeline.PreparedTypeSet;
import semante.pipeline.Result;
import semante.pipeline.TestCaseCreator;
import semante.predcalc.Smasher;
import semante.predcalc.impl.IPredCalc;
import semante.predcalc.impl.ISmasher;
import semante.prover.impl.IProver;
import semante.settings.Settings;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

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
		final BinaryTree<ID, Annotation<ID>> text,
		final BinaryTree<ID, Annotation<ID>> hypo,
		final List<Pair<ID, ID>> subsumptions) throws FileNotFoundException {

		// prepare interfaces
		val pcalc  = new IPredCalc(settings);
		val prover = new IProver(settings, pcalc);
		val stl2p  = new ISmasher(pcalc, stl);
			
		// prepare the formuals to try to prove (including disambiguation, flattening, etc.)
		val preparedFormulae = prepare(text, hypo, subsumptions, stl2p);
			
		// if a result is already set then it's an error and we report it to the caller directly
		// otherwise, we start the proving stage (prover loop).
		if (preparedFormulae.isResultSet()) {
				
			return preparedFormulae.getResult() ;
				
		} else {
				
			// PROVE: attempt to prove the combinations of text and hypothesis
			val formulas      = preparedFormulae.getFormulae();
			val resultHandler = new ISimpleResultHandler<ID>();
				
			for (int index = 0; index<formulas.size() && !resultHandler.isFinalResultSet(); index++) {
				val formula = formulas.get(index);
				resultHandler.handle(prover.prove(formula.getTFormula(), formula.getHFormula()));
			}
			return resultHandler.getFinalResult();
		}
	}
	
	
	
	// add field for subsumptions to prepared tree, to later go into pragmatics
	public <ID> PreparedTree<ID> prepareTree(
			final AnnotationTreePrinter<ID,Annotation<ID>> printer,
			final FlattenTree<ID> flattener,
			final BinaryTreeBuilder<ID,UnambiguousAnnotation<ID>> treebuilder,
			final Disambiguator<ID> disambiguator, 
			final BinaryTree<ID, Annotation<ID>> tree,
			final List<ID> subIds,
			final ETHType thType,
			final String treeName) {
		
		val lowerCaseTree = new ILowerCase().apply(tree);
		
		// DISAMBIGUATE: infer denotations and function application structure
		val disambTextM = disambiguator.disambiguate(lowerCaseTree);
		if (disambTextM.isLeft()) return new IPreparedTree<ID>(disambTextM.getLeft().<ID>toResult(),thType);

		val disambTexts = disambTextM.getRight();
		
		// CHECK: perform a series of checks on the unambiguous annotation trees
		val collectivityAndIota = new ICollectivityAndIotaChecker<ID>(stl,flattener,treebuilder);
		val validTextsBuilder = ImmutableList.<BinaryTree<ID, UnambiguousAnnotation<ID>>> builder();
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
				return new IPreparedTree<ID>(invalidText.<ID>toResult(),thType);
			}
		}
		
		// FLATTEN: convert unambiguous trees to lambda terms
		val flatTexts = flattener.flattenAll(validTexts);
		
		// REDUCE: convert lambda terms to normal form
		val redTexts = Lists.transform(flatTexts, reducer);

		
		// CLEAN DUPLICATES (map is not multi-key)
		//val tempNubTexts = ImmutableSet.copyOf(redTexts);
		Map<Integer,DeBruijn> redTextsMap = new HashMap<Integer,DeBruijn>();
		Set<DeBruijn> usedFormulas = new HashSet<DeBruijn>();
		for (int i=0 ; i<redTexts.size() ; i++) {
			val formula = redTexts.get(i);
			if (!usedFormulas.contains(formula)) {
				redTextsMap.put(i,redTexts.get(i));
				usedFormulas.add(formula);
			}
		}
		
		
		// SUBSUMPTIONS: extract subtrees that correspond to the specified ids

		Map<DeBruijn,List<DeBruijn>> treesMap = new HashMap<DeBruijn, List<DeBruijn>>();

		System.err.println(treeName + ": reduced unambiguous trees: " + redTextsMap.size() + "; subsumed elements: " + subIds.toString());
		// map between reduced texts and the sub-trees that the subsumptions refer to
		for (int i : redTextsMap.keySet()) {
			val redText = redTextsMap.get(i);
			val binaryTree = validTexts.get(i);
			treesMap.put(redText,new ArrayList<DeBruijn>());
			for (ID id : subIds) {
				//System.err.println("tree: " +i + "; sub: " + id);

				// Extract the sub-tree that needs to be subsumed (either source/target) 
				val subTree = ITreeTools.extractElement(binaryTree, id);

				if (subTree==null) {
					return new IPreparedTree<ID>(new IResult$Error<ID>(id,"failed to extract subtree from the specified id"),thType); 
				}
				
				// FLATTEN and REDUCE the sub-tree
				val redSubTree = reducer.apply(flattener.flatten(subTree));

				// Store the reduced sub-tree in a list associated with the reduced tree that
				// it is extracted from.
				treesMap.get(redText).add(redSubTree);
			}
		}
		
		return new IPreparedTree<ID>(ImmutableMap.copyOf(treesMap),thType);
	}
	
	public final <ID> PreparedFormulae<ID> prepareFormulae(
			final BinaryTree<ID, Annotation<ID>> text,
			final BinaryTree<ID, Annotation<ID>> hypo,
			final List<Pair<ID,ID>> subsumptions,
			Smasher stl2p) throws FileNotFoundException {
			
		val printer       = new IAnnotationTreePrinter<ID,Annotation<ID>>();
		val flattener     = new IFlattenTree<ID>(stl.getDeBruijnBuilder());
		val treebuilder   = new IBinaryTreeBuilder<ID,UnambiguousAnnotation<ID>>();
		val disambiguator = new IDisambiguator<ID>(stl,lexicon,flattener,printer,treebuilder);

		// getFirst: simple function wrapper around a pair
		Function<Pair<ID,ID>,ID> getFirst
		= new Function<Pair<ID,ID>,ID>() {
			@Override
			public final ID apply(final Pair<ID,ID> pair) {
				return pair.getFirst();
			}
		};	

		// getSecond: simple function wrapper around a pair
		Function<Pair<ID,ID>,ID> getSecond
		= new Function<Pair<ID,ID>,ID>() {
			@Override
			public final ID apply(final Pair<ID,ID> pair) {
				return pair.getSecond();
			}
		};	

		val nameGetter = ImmutableMap.of(ETHType.ETextTree,"T",
										 ETHType.EHypoTree,"H");
		
		val subGetter = ImmutableMap.of(ETHType.ETextTree,getFirst,
										ETHType.EHypoTree,getSecond);
		
		val treeGetter = ImmutableMap.of(ETHType.ETextTree,text,
										 ETHType.EHypoTree,hypo);

		val nubsResultBuilder = ImmutableMap.<ETHType, Map<DeBruijn,List<DeBruijn>>>builder();
		
		for (ETHType thType : treeGetter.keySet()) {
			val subs = subGetter.get(thType);
			val tree = treeGetter.get(thType);
			
			// get the subsumption ids 
			val subIds = Lists.transform(subsumptions, subs);
			
			// prepare the tree
			val preparedTree = prepareTree(printer,flattener,treebuilder,disambiguator,tree,subIds,thType,nameGetter.get(thType));
			if (preparedTree.isResultSet()) {
				return new IPreparedFormulae<ID>(preparedTree.getResult(), preparedTree.getETHType()) ;
			}
			val tempNubs = preparedTree.getInterpretations();

			nubsResultBuilder.put(thType,tempNubs);
		}
		
		val nubsResult = nubsResultBuilder.build();
		
		return new IPreparedFormulae<ID>(nubsResult.get(ETHType.ETextTree), 
										 nubsResult.get(ETHType.EHypoTree)); 
	}
	
	public final <ID> PreparedRunnableFormulae<ID> prepare(
		final BinaryTree<ID, Annotation<ID>> text,
		final BinaryTree<ID, Annotation<ID>> hypo,
		final List<Pair<ID,ID>> subsumptions,
		Smasher stl2p) throws FileNotFoundException {
		
		val preparedFormulae = prepareFormulae(text, hypo, subsumptions, stl2p);
		
		if (preparedFormulae.isResultSet()) {
			return new IPreparedRunnableFormulae<ID>(
					preparedFormulae.getResult(),
					preparedFormulae.getETHType());
		}
		
		val nubTexts = preparedFormulae.getTextDesc();
		val nubHypos = preparedFormulae.getHypoDesc();
		
		// SUBSUMPTIONS: convert the subsumption relations to lambda terms
		//               and add them to the term representing the text
		List<PreparedRunnableFormula> runnableFormulas = new ArrayList<PreparedRunnableFormula>();

		IotaExtractor iotaExtractor = new IIotaExtractor();
		
		ImplicationGenerator impGenerator = new IImplicationGenerator(stl,iotaExtractor);
		
		val unsupportedImplicationsB = ImmutableSet.<String>builder();
		val unmatchedImplicationsB = ImmutableSet.<String>builder();
		
		int combinationsWithFailedSub = 0;
		
		for (val nubText : nubTexts.keySet()) {
			val context = nubText;
			val nubTestSubs =nubTexts.get(nubText);
			val textIotaResult = iotaExtractor.extract(stl.fromDeBruijn(nubText), stl);
			
			for (val nubHypo : nubHypos.keySet()) {
				val uniquenessConditionsB = ImmutableSet.<Expr>builder();
				val nubHypoSubs =nubHypos.get(nubHypo);
				val hypoIotaResult = iotaExtractor.extract(stl.fromDeBruijn(nubHypo), stl);
				
				boolean failedSub = false;
				
				val subs = ImmutableSet.<Expr> builder();
				for (int subId=0 ; subId<nubTestSubs.size() ; subId++) {
					val textTree = stl.fromDeBruijn(nubTestSubs.get(subId));
					val hypoTree = stl.fromDeBruijn(nubHypoSubs.get(subId));
					try {
						subs.addAll(impGenerator.process(textTree, hypoTree,context,uniquenessConditionsB));
					} catch (UnsupportedImplication e) {
						System.err.println("Warning: unsupported type for implications: " + e.getMessage());
						unsupportedImplicationsB.add(e.getMessage());
						failedSub = true;
					} catch (UnmatchedTypesException e) {
						System.err.println("Warning: unmatched types for implications: " + e.getMessage());
						unmatchedImplicationsB.add(e.getMessage());
						failedSub = true;
					}
					
				}
				if (failedSub) {
					combinationsWithFailedSub++;
				}
				
				uniquenessConditionsB.addAll(textIotaResult.getUniquenessConditions());
				uniquenessConditionsB.addAll(hypoIotaResult.getUniquenessConditions());

				val uniquenessConditions = uniquenessConditionsB.build();
				
				// LOWER: convert normal form terms to first order logic
				val tText = stl.format(textIotaResult.getAssertion());
				val tFormula = stl2p.smash(textIotaResult.getAssertion(),uniquenessConditions,subs.build());			

				val hText = stl.format(hypoIotaResult.getAssertion());				
				val hFormula = stl2p.smash(hypoIotaResult.getAssertion(),null,null);
				
				runnableFormulas.add(new IPreparedRunnableFormula(tText,hText,tFormula,hFormula));
			}
		}

		val unsupportedErrors = unsupportedImplicationsB.build();
		val unmatchedErrors = unmatchedImplicationsB.build();
		
		// in case that all combinations of text/hypo had problems to generate
		// a subsumption relation we conclude that there is a general error and
		// report all the types for which implications could be generated.
		if (runnableFormulas.size()==0) { 
			String msg = "failed to generate any runnable combination of text and hypothesis";
			return new IPreparedRunnableFormulae<ID>(
					new IResult$Error<ID>(ITreeTools.getId(text),msg),
					ETHType.ETextTree); 
		} else if (runnableFormulas.size()==combinationsWithFailedSub) {
			StringBuilder buff = new StringBuilder();
			if (unsupportedErrors.size()>0) {
				buff.append("unsupported type" + (unsupportedErrors.size()>1 ? "s" : "") + " for implications: " + unsupportedErrors.toString());
			}
			
			if (unmatchedErrors.size()>0) {
				if (buff.length()>0) {
					buff.append("; ");
				}
				buff.append("unmatched types for implications: " + unmatchedErrors.toString());
			}
			return new IPreparedRunnableFormulae<ID>(
					new IResult$Error<ID>(ITreeTools.getId(text),buff.toString()),
					ETHType.ETextTree); 
		}
		
		return new IPreparedRunnableFormulae<ID>(runnableFormulas);
	}

	public final <ID> PreparedTypeSet<ID> getTypeOfSub(
			final BinaryTree<ID, Annotation<ID>> text,
			final BinaryTree<ID, Annotation<ID>> hypo,
			final Pair<ID,ID> subsumption,
			Smasher stl2p) throws FileNotFoundException {

		PreparedFormulae<ID> preparedFormulae = prepareFormulae(text, hypo, ImmutableList.of(subsumption), stl2p);
		if (preparedFormulae.isResultSet()) {
			return new IPreparedTypeSet<ID>(
					preparedFormulae.getResult(),
					preparedFormulae.getETHType());
		}
		
		val nubTexts = preparedFormulae.getTextDesc();
		val nubHypos = preparedFormulae.getHypoDesc();
		
		Set<Type> matchedTypes = Sets.newHashSet();
		
		for (val nubText : nubTexts.keySet()) {
			for (val nubHypo : nubHypos.keySet()) {
				val nubTestSubs =nubTexts.get(nubText);
				val nubHypoSubs =nubHypos.get(nubHypo);
				for (int i=0 ; i<nubTestSubs.size() ; i++) {
					Type typeText = stl.typeOf(nubTestSubs.get(i));
					Type typeHypo = stl.typeOf(nubHypoSubs.get(i));
					if (typeText.equals(typeHypo)) {
						matchedTypes.add(typeText);
					}
				}
			}
		}

		return new IPreparedTypeSet<ID>(matchedTypes);
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
