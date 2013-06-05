package semante.pipeline.impl;

import static com.google.common.collect.Lists.transform;
import static semante.pipeline.util.impl.IBinaryTree.functor;

import java.io.FileNotFoundException;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.val;
import lombok.experimental.Value;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;
import semante.lexicon.RichLexicon;
import semante.lexicon.Word;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Category;
import semante.pipeline.Pipeline;
import semante.pipeline.Result;
import semante.pipeline.util.impl.ICategory;
import semante.predcalc.impl.IPredCalc;
import semante.predcalc.util.ILambda2Pred;
import semante.prover.ProverException;
import semante.settings.Settings;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

@Value
@EqualsAndHashCode(callSuper = false)
public final class IPipeline implements Pipeline {
	
	Settings				settings;
	TLambdaCalc<TSymbol>	lambdacalc;
	RichLexicon				lexicon;
	
	@Override
	public final List<Category> getCategories() {
		return Lists.transform(lexicon.getEntries(), new Function<String,Category>() {
			
			@Override
			public final Category apply(final String input) {
				return new ICategory(input);
			}
		});
	}
	
	@Override
	public final <ID> Result<ID> prove(
		final BinaryTree<ID, Annotation> txt,
		final BinaryTree<ID, Annotation> hyp,
		final String subsumptions) throws FileNotFoundException {
		
		// lookup annotations in the lexicon.
		val lookup = functor(
			Functions.<ID> identity(), 
			new Function<Annotation,Word<TSymbol>>() {
				
				@Override
				public final Word<TSymbol> apply(final Annotation a) {
					return a.accept(new Annotation.Visitor<Word<TSymbol>>() {

						@Override
						public final Word<TSymbol> annotation(
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
		val flatten = new IFlattenTree<ID, TSymbol>(lambdacalc);
		val stltxt = flatten.flatten(anntxt);
		if (stltxt.isLeft()) { return stltxt.getLeft(); }
		val stlhyp = flatten.flatten(annhyp);
		if (stlhyp.isLeft()) { return stlhyp.getLeft(); }
		
		// beta-reduce the expressions.
		val br =
		new Function<Expr<TSymbol>,Expr<TSymbol>>() {
			@Override
			public final Expr<TSymbol> apply(Expr<TSymbol> input) {
				return lambdacalc.betaReduce(input);
			}
		};
		val brstltxt = transform(stltxt.getRight(),br);
		val brstlhyp = transform(stlhyp.getRight(),br);
		
		val nubtxt = ImmutableList.copyOf(ImmutableSet.copyOf(brstltxt));
		val nubhyp = ImmutableList.copyOf(ImmutableSet.copyOf(brstlhyp));
		
		for (val nub: nubtxt) {
			System.err.println("txt:"+nub);
		}
		for (val nub: nubhyp) {
			System.err.println("hyp:"+nub);
		}
		
		// smash to first-order logic.
		val fol = new IPredCalc();
		val stl2fol = new ILambda2Pred<TSymbol>(fol, lambdacalc);
		val smash =
		new Function<Expr<TSymbol>,FOLForm>() {
			@Override
			public final FOLForm apply(Expr<TSymbol> input) {
				val folform = stl2fol.smash(input);
				return folform;
			}
		};
		val foltxt = transform(nubtxt,smash);
		val folhyp = transform(nubhyp,smash);
		
		// iterate and see if any analysis can be proven.
		for (val proptxt : foltxt) {
			for (val prophyp : folhyp) {
				try {
					
					if (fol.prove(proptxt, prophyp, subsumptions)) {
						return new IResult$Proof<ID>();
					}
				} catch (ProverException e) {
					System.err.println(e);
				}
			}
		}
		return new IResult$Unknown<ID>();
	}
}
