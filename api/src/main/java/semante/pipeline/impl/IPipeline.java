package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

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
import semante.pipeline.Either;
import semante.pipeline.FlattenTree;
import semante.pipeline.Pipeline;
import semante.pipeline.Result;
import semante.pipeline.TestCaseCreator;
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
		
		final FlattenTree<ID>
			flattener = new IFlattenTree<ID>(stl,lexicon);
		final Either<Result<ID>,List<DeBruijn>>
			flatTextM = flattener.flatten(text);
		if (flatTextM.isLeft()) return flatTextM.getLeft();
		final Either<Result<ID>,List<DeBruijn>>
			flatHypoM = flattener.flatten(hypo);
		if (flatHypoM.isLeft()) return flatHypoM.getLeft();
		final List<DeBruijn>
			flatTexts = flatTextM.getRight();
		final List<DeBruijn>
			flatHypos = flatTextM.getRight();
		
		for (val flatText: flatTexts) {
			System.err.println(stl.format(stl.fromDeBruijn(flatText)));
		}
		
		final Function<DeBruijn,DeBruijn>
			reducer = new Function<DeBruijn,DeBruijn>() {
				@Override
				public final DeBruijn apply(final DeBruijn expr) {
					return stl.betaReduce(expr);
				}
			};
		final List<DeBruijn>
			redTexts = Lists.transform(flatTexts, reducer);
		final List<DeBruijn>
			redHypos = Lists.transform(flatHypos, reducer);
		
		final Set<DeBruijn>
			nubTexts = ImmutableSet.copyOf(redTexts);
		final Set<DeBruijn>
			nubHypos = ImmutableSet.copyOf(redHypos);
		
		for (val nubText: nubTexts) {
			System.err.println(stl.format(nubText));
			System.err.println(stl.format(stl.fromDeBruijn(nubText)));
		}
		for (val nubHypo: nubHypos) {
			System.err.println(stl.format(nubHypo));
			System.err.println(stl.format(stl.fromDeBruijn(nubHypo)));
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
