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
import lambdacalc.STL;
import lambdacalc.Type;
import lambdacalc.Types;
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
import semante.pipeline.HigherOrderImplication.HOIException;
import semante.pipeline.Pair;
import semante.pipeline.Pipeline;
import semante.pipeline.PreparedFormulae;
import semante.pipeline.PreparedRunnableFormula;
import semante.pipeline.PreparedRunnableFormulae;
import semante.pipeline.PreparedTree;
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
	
	public final <ID> Set<Type> getTypeOfSub(
			final BinaryTree<ID, Annotation<ID>> text,
			final BinaryTree<ID, Annotation<ID>> hypo,
			final Pair<ID,ID> subsumption,
			Smasher stl2p) throws FileNotFoundException {

		PreparedFormulae<ID> preparedFormulae = prepareFormulae(text, hypo, ImmutableList.of(subsumption), stl2p);
		
		val nubTexts = preparedFormulae.getTextDesc();
		val nubHypos = preparedFormulae.getHypoDesc();
		
		Set<Type> matchedTypes = Sets.newHashSet();
		
		for (val nubText : nubTexts.keySet()) {
			for (val nubHypo : nubHypos.keySet()) {
				val nubTestSubs =nubTexts.get(nubText);
				val nubHypoSubs =nubHypos.get(nubHypo);
				Type typeText = stl.typeOf(nubTestSubs.get(0));
				Type typeHypo = stl.typeOf(nubHypoSubs.get(0));
				if (typeText.equals(typeHypo)) {
					matchedTypes.add(typeText);
				}
			}
		}

		return matchedTypes;
	}
	
	
	// add field for subsumptions to prepared tree, to later go into pragmatics
	public <ID> PreparedTree<ID> prepareTree(
			final AnnotationTreePrinter<ID,Annotation<ID>> printer,
			final FlattenTree<ID> flattener,
			final BinaryTreeBuilder<ID,UnambiguousAnnotation<ID>> treebuilder,
			final Disambiguator<ID> disambiguator, 
			final BinaryTree<ID, Annotation<ID>> tree,
			final List<ID> subIds,
			final ETHType thType) {
		
		// DISAMBIGUATE: infer denotations and function application structure
		val disambTextM = disambiguator.disambiguate(tree);
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

		System.err.println("Subsumed elements: " + subIds.size() + " reduced unambiguous trees: " + redTextsMap.size());
		// map between reduced texts and the sub-trees that the subsumptions refer to
		for (int i : redTextsMap.keySet()) {
			val redText = redTextsMap.get(i);
			val binaryTree = validTexts.get(i);
			treesMap.put(redText,new ArrayList<DeBruijn>());
			for (ID id : subIds) {
				System.err.println("tree: " +i + "; sub: " + id);

				// Extract the sub-tree that needs to be subsumed (either source/target) 
				val subTree = ITreeTools.extractElement(binaryTree, id);

				// FLATTEN and REDUCE the sub-tree
				val redSubTree = reducer.apply(flattener.flatten(subTree));

				System.err.println(ITreeTools.printReadable(subTree, false));
				System.err.println("type: " + stl.format(stl.typeOf(redSubTree)));

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
			
		val textSubIds = Lists.transform(subsumptions, getFirst);
		val hypoSubIds = Lists.transform(subsumptions, getSecond);
		
		// prepare the text tree
		val textPreparedTree = prepareTree(printer,flattener,treebuilder,disambiguator,text,textSubIds,ETHType.ETextTree);
		if (textPreparedTree.isResultSet()) {
			return new IPreparedFormulae<ID>(textPreparedTree.getResult(), ETHType.ETextTree) ;
		}
		val tempNubTexts = textPreparedTree.getInterpretations();

		// prepare the hypo tree
		val hypoPreparedTree = prepareTree(printer,flattener,treebuilder,disambiguator,hypo,hypoSubIds,ETHType.EHypoTree);
		if (hypoPreparedTree.isResultSet()) {
			return new IPreparedFormulae<ID>(hypoPreparedTree.getResult(), ETHType.EHypoTree) ;
		}
		val tempNubHypos = hypoPreparedTree.getInterpretations();
		
		
		System.err.println("Unambiguous formulas: T: " + tempNubTexts.size() + ", H: " + tempNubHypos.size());
		
		/* commented out because already printed in the IOTA collector
		for (val nubText: tempNubTexts) {
			System.err.println("T: "+stl.format(stl.fromDeBruijn(nubText)));
		}
		for (val nubHypo: tempNubHypos) {
			System.err.println("H: "+stl.format(stl.fromDeBruijn(nubHypo)));
		}*/

		
		// COLLECT and FRONT IOTAs
		Map<DeBruijn,List<DeBruijn>> nubTexts = new HashMap<DeBruijn, List<DeBruijn>>();
		for (val nubText: tempNubTexts.keySet()) {
			DeBruijn t = stl.toDeBruijn(IIotaCollector.collect(stl, stl.fromDeBruijn(nubText)));
			List<DeBruijn> sList = Lists.newArrayList();
			for (val sub : tempNubTexts.get(nubText)) {
				sList.add(stl.toDeBruijn(IIotaCollector.collect(stl, stl.fromDeBruijn(sub))));
			}
			nubTexts.put(t, sList);
		}


		Map<DeBruijn,List<DeBruijn>> nubHypos = new HashMap<DeBruijn, List<DeBruijn>>();
		for (val nubHypo: tempNubHypos.keySet()) {
			DeBruijn h = stl.toDeBruijn(IIotaCollector.collect(stl, stl.fromDeBruijn(nubHypo)));
			List<DeBruijn> sList = Lists.newArrayList();
			for (val sub : tempNubHypos.get(nubHypo)) {
				sList.add(stl.toDeBruijn(IIotaCollector.collect(stl, stl.fromDeBruijn(sub))));
			}
			nubHypos.put(h, sList);
			
		}
		
		return new IPreparedFormulae<ID>(nubTexts, nubHypos); 
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

		for (val nubText : nubTexts.keySet()) {
			for (val nubHypo : nubHypos.keySet()) {
				val hoi  = new IHigherOrderImplication(stl);
				val ctxt = ImmutableList.of(nubText,nubHypo);
				val subs = ImmutableList.<DeBruijn> builder();

				val nubTestSubs =nubTexts.get(nubText);
				val nubHypoSubs =nubHypos.get(nubHypo);
				
				/*
				System.err.println("interpretations: " + textPreparedTree.getInterpretations().size());
				for (DeBruijn d : textPreparedTree.getInterpretations().keySet()) {
					System.err.println("int: " + stl.format(stl.fromDeBruijn(d)));
				}*/
				
				System.err.println("nub: " + stl.format(stl.fromDeBruijn(nubText)));
				
				for (int subId=0 ; subId<nubTestSubs.size() ; subId++) {
					val textTree = nubTestSubs.get(subId);
					val hypoTree = nubHypoSubs.get(subId);
					
					try {
						System.out.println("Adding/Checking " + stl.format(stl.fromDeBruijn(textTree)) + " and " + stl.format(stl.fromDeBruijn(hypoTree)));
						subs.add(hoi.higherOrderImplication(textTree, hypoTree, ctxt));
					}
					catch (HOIException e) {
						System.err.println("Warning: " + e.getMessage());
					}

					
				}

				final DeBruijn withSubsText;
				
				// flatten with conjunction.
				val bld = stl.getDeBruijnBuilder();
				val subsList = subs.build();
				if (subsList.isEmpty()) {
					withSubsText = nubText;
				}
				else {
					DeBruijn subsExpr = subsList.get(0);
					for (int i = 1; i < subsList.size(); i++) {
						subsExpr =
							bld.application(
								bld.constant("AND",Types.TTT), subsExpr, subsList.get(i));
					}
					
					withSubsText = bld.application(
							bld.constant("AND",Types.TTT),nubText,subsExpr);
				}
				
				System.err.println("After adding subsumption relations:");
				System.err.println("T: "+stl.format(stl.fromDeBruijn(withSubsText)));
				
				// LOWER: convert normal form terms to first order logic
				
				val tText = stl.format(stl.fromDeBruijn(withSubsText));
				val tFormula = stl2p.smash(stl.fromDeBruijn(withSubsText));			

				val hText = stl.format(stl.fromDeBruijn(nubHypo));				
				val hFormula = stl2p.smash(stl.fromDeBruijn(nubHypo));
				runnableFormulas.add(new IPreparedRunnableFormula(tText,hText,tFormula,hFormula));
			}
		}
		
		return new IPreparedRunnableFormulae<ID>(runnableFormulas);
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
