package semante.lambdacalc.util;

import lombok.EqualsAndHashCode;
import lombok.experimental.Value;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Expr;
import semante.lambdacalc.Symbol;


@Value
public final class IEqExpr<S extends Symbol> implements Equality<Expr<S>> {
	
	Equality<S> eqSymbol;
	
	@Override
	public boolean apply(Expr<S> a, Expr<S> b) {
		return a.accept(new IEqSExpr(b));
	}
	
	@Value
	@EqualsAndHashCode(callSuper=true)
	final class IEqSExpr extends Expr.Test<S> {

		Expr<S> that;
		
		@Override
		public final Boolean abstraction(final S s1, final Expr<S> e1) {
			return that.accept(new Expr.Test.False<S>() {
				@Override
				public final Boolean abstraction(final S s2, final Expr<S> e2) {
					return eqSymbol.apply(s1, s2) && e1.accept(withThat(e2));
				}
			});
		}

		@Override
		public final Boolean application(final Expr<S> l1, final Expr<S> r1) {
			return that.accept(new Expr.Test.False<S>() {
				@Override
				public final Boolean application(final Expr<S> l2, final Expr<S> r2) {
					return l1.accept(withThat(l2)) && r2.accept(withThat(r2));
				}
			});
		}

		@Override
		public final Boolean variable(final S s1) {
			return that.accept(new Expr.Test.False<S>() {
				@Override
				public final Boolean variable(final S s2) {
					return eqSymbol.apply(s1, s2);
				}
			});
		}
	}
}