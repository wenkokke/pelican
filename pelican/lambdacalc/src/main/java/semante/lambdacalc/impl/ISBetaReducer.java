package semante.lambdacalc.impl;

import static com.google.common.collect.Iterables.any;
import static lombok.AccessLevel.PRIVATE;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.experimental.Value;
import semante.lambdacalc.AlphaConverter;
import semante.lambdacalc.AlphaGenerator;
import semante.lambdacalc.BetaReducer;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Expr;
import semante.lambdacalc.Substituter;
import semante.lambdacalc.Symbol;
import semante.lambdacalc.util.ISGetSymbol;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class ISBetaReducer<S extends Symbol> extends IExpr.Identity<S> implements BetaReducer<S> {

	// depends on an implementation of substituter, alpha converter and generator
	Equality<S>			eqSymbol;
	ISGetSymbol.Free<S>	iSGetSymbol;
	Substituter<S>		substituter;
	AlphaGenerator<S>	alphaGen;
	AlphaConverter<S>	alphaConv;
	
	
	@Override
	public final Expr<S> betaReduce(Expr<S> e1) {
		return e1.accept(new BetaReductionVisitor(ImmutableSet.<S> of()));
	}
	
	@Value
	@EqualsAndHashCode(callSuper=false)
	final class BetaReductionVisitor extends IExpr.Identity<S> {
		
		Set<S> bound;
		
		@Override
		public final Expr<S> abstraction(final S s, @NonFinal Expr<S> expr) {
			
			// add the current $name to the bound set
			val builder = ImmutableSet.<S> builder();
			builder.add(s);
			builder.addAll(bound);
			
			// warn: rebinding of $expr!
			// recursively beta reduce the sub expression
			expr = expr.accept(withBound(builder.build()));
			
			// reconstruct the lambda term
			return super.abstraction(s, expr);
		}

		@Override
		public final Expr<S> application(final Expr<S> l1, final Expr<S> r1) {
			
			// recursively beta-reduce l1 and r1.
			val lR = l1.accept(BetaReductionVisitor.this);
			val rR = r1.accept(BetaReductionVisitor.this);
			
			// attempt to beta-reduce the expression
			return lR.accept(new IExpr.Identity<S>() {
				@Override
				public final Expr<S> variable(final S s1) {
					return super.application(lR, rR);
				}
				@Override
				public final Expr<S> application(final Expr<S> l2, final Expr<S> r2) {
					return super.application(lR, rR);
				}
				@Override
				public final Expr<S> abstraction(@NonFinal S s1, @NonFinal Expr<S> subject) {
					
					// beta-reduce the current expression.
					for (val free: rR.accept(iSGetSymbol)) {
						/*
						 * if the "free" variable is bound here, we have to perform
						 * alpha convertion over name before we can attempt beta
						 * reduction.
						 */
						if (eqSymbol.apply(free, s1)) {
							
							// warn: rebinding of $s1!
							s1 = alphaGen.freshName(s1);
							
							// warn: rebinding of $subject!
							subject = alphaConv.alphaConvert(free, s1, subject);
						}
						/*
						 * if the "free" variable is bound above, it is actually
						 * bound both in the function and in the argument. therefore
						 * we do not have to (and cannot) perform alpha conversion.
						 */
						if (! any(bound,
								new Predicate<S>() {
									@Override
									public final boolean apply(final S s) {
										return eqSymbol.apply(free,s);
									}
								}
							)) {
							
							// bind a new name for alpha conversion
							val s2 = alphaGen.freshName(free);
							
							// warn: rebinding of $subject!
							subject = alphaConv.alphaConvert(free, s2, subject);
						}
					}
					
					// substitute rR for s1 in subject
					subject = substituter.substitute(s1, rR, subject);
					
					// recursively beta-reduce the result.
					return subject.accept(BetaReductionVisitor.this);
				}
			});
		}
	}
}
