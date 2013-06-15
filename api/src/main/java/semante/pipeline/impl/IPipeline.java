package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;
import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lexicon.RichLexicon;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Category;
import semante.pipeline.Pipeline;
import semante.pipeline.Result;
import semante.pipeline.TestCaseCreator;
import semante.settings.Settings;

import com.google.common.base.Function;
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
		
		val flattener = new IFlattenTree<ID>(stl,lexicon);
		val flatTextM = flattener.flatten(text);
		if (flatTextM.isLeft()) return flatTextM.getLeft();
		val flatHypoM = flattener.flatten(hypo);
		if (flatHypoM.isLeft()) return flatHypoM.getLeft();
		val flatTexts = flatTextM.getRight();
		val flatHypos = flatTextM.getRight();
		
		val reducer = new Function<DeBruijn,DeBruijn>() {
			@Override
			public final DeBruijn apply(final DeBruijn expr) {
				// TODO apparently there is a small bug in beta reduction
				// 		as we need two reductions in the case of jan01;
				return stl.etaReduce(stl.betaReduce(stl.betaReduce(expr)));
			}
		};
		val redTexts = Lists.transform(flatTexts, reducer);
		val redHypos = Lists.transform(flatHypos, reducer);
		
		for (val redText: redTexts) {
			System.out.println(stl.format(stl.fromDeBruijn(redText)));
		}
		for (val redHypo: redHypos) {
			System.out.println(stl.format(stl.fromDeBruijn(redHypo)));
		}
		
		// TODO implement smasher and prover9 parts of pipeline
		
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
