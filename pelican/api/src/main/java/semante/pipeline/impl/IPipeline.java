package semante.pipeline.impl;

import static com.google.common.collect.Lists.transform;
import static semante.util.impl.IBinaryTree.functor;

import java.io.FileNotFoundException;

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
import semante.pipeline.Pipeline;
import semante.pipeline.Result;
import semante.predcalc.FOLExpr.Formula;
import semante.predcalc.impl.IPredCalc;
import semante.predcalc.util.ILambda2Pred;
import semante.prover.ProverException;
import semante.settings.Settings;

import com.google.common.base.Function;
import com.google.common.base.Functions;

@Value
@EqualsAndHashCode(callSuper = false)
public final class IPipeline extends Pipeline {
	
	Settings				settings;
	TLambdaCalc<TSymbol>	lambdacalc;
	RichLexicon				lexicon;
	
	@Override
	public <ID> Result<ID> prove(
		final BinaryTree<ID, Annotation> txt,
		final BinaryTree<ID, Annotation> hyp,
		final String subsumptions) throws FileNotFoundException {
		
		// lookup annotations in the lexicon.
		val lookup = functor(
			Functions.<ID>identity(), 
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
		if (stltxt.isLeft()) {
			val printer = new ASTPrinter<ID, TSymbol>(lambdacalc);
			return new IResult$ErrorMsg<ID>(stltxt.getLeft(),
					anntxt.accept(printer).getFirst());
		}
		val stlhyp = flatten.flatten(annhyp);
		if (stlhyp.isLeft()) {
			val printer = new ASTPrinter<ID, TSymbol>(lambdacalc);
			return new IResult$ErrorMsg<ID>(stlhyp.getLeft(),
					annhyp.accept(printer).getFirst());
		}
		
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
		
		// smash to first-order logic.
		val fol = new IPredCalc();
		val stl2fol = new ILambda2Pred<TSymbol>(fol, lambdacalc);
		val smash =
		new Function<Expr<TSymbol>,Formula>() {
			@Override
			public final Formula apply(Expr<TSymbol> input) {
				return stl2fol.smash(input);
			}
		};
		val foltxt = transform(brstltxt,smash);
		val folhyp = transform(brstlhyp,smash);
		
		// iterate and see if any analysis can be proven.
		for (val proptxt : foltxt) {
			for (val prophyp : folhyp) {
				try {
					if (fol.prove(proptxt, prophyp, subsumptions)) {
						return new IResult$Proof<ID>();
					}
				} catch (ProverException e) {
					e.printStackTrace();
				}
			}
		}
		return new IResult$CounterExample<ID>();
	}
}
