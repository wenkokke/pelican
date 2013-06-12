package semante.pipeline.impl;

import static com.google.common.collect.Lists.transform;
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
import semante.lexicon.Word;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Category;
import semante.pipeline.Pipeline;
import semante.pipeline.Result;
import semante.pipeline.TestCaseCreator;
import semante.settings.Settings;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
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
		final BinaryTree<ID, Annotation> txt,
		final BinaryTree<ID, Annotation> hyp,
		final String subsumptions) throws FileNotFoundException {
		
		// lookup annotations in the lexicon.
		val lookup = IBinaryTree.functor(
			Functions.<ID> identity(), 
			new Function<Annotation,Word>() {
				
				@Override
				public final Word apply(final Annotation a) {
					return a.accept(new Annotation.Visitor<Word>() {

						@Override
						public final Word annotation(
								final String text,
								final String annotation) {
							return lexicon.getEntry(text, annotation);
						}
					});
				}
			});
		val anntxt = txt.accept(lookup);
		val annhyp = hyp.accept(lookup);
		
		// flatten trees into lambda expressions.
		val flatten = new IFlattenTree<ID>(stl);
		val exprTxt = flatten.flatten(anntxt);
		if (exprTxt.isLeft()) { return exprTxt.getLeft(); }
		val exprHyp = flatten.flatten(annhyp);
		if (exprHyp.isLeft()) { return exprHyp.getLeft(); }
		
		// beta-reduce the expressions.
		val betaReduce =
			new Function<DeBruijn, DeBruijn>() {
				@Override
				public final DeBruijn apply(DeBruijn input) {
					return stl.betaReduce(input);
				}
			};
		val reducedTxt = transform(exprTxt.getRight(),betaReduce);
		val reducedHyp = transform(exprHyp.getRight(),betaReduce);
		
		val uniqueTxt = ImmutableList.copyOf(ImmutableSet.copyOf(reducedTxt));
		val uniqueHyp = ImmutableList.copyOf(ImmutableSet.copyOf(reducedHyp));
		
		for (val nub: uniqueTxt) {
			System.err.println("txt:"+stl.format(nub));
		}
		for (val nub: uniqueHyp) {
			System.err.println("hyp:"+stl.format(nub));
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
