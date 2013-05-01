package semante.lambdacalc.impl;

import semante.lambdacalc.AlphaConverter2;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TSymbol;

final class ITAlphaConverter2 implements AlphaConverter2<TSymbol> {
	
	@Override
	public final Expr<TSymbol> alphaConvert(final String s1, final String s2, final Expr<TSymbol> subject) {
		return subject.accept(new IExpr.Identity<TSymbol>() {
			@Override
			public Expr<TSymbol> abstraction(TSymbol s, Expr<TSymbol> arg) {
				return super.abstraction(s, arg.accept(this));
			}

			@Override
			public Expr<TSymbol> application(Expr<TSymbol> f, Expr<TSymbol> arg) {
				return super.application(f.accept(this), arg.accept(this));
			}

			@Override
			public final Expr<TSymbol> variable(TSymbol t) {
				return super.variable((s1.equals(t.getName())) ? new ITSymbol(s2, t.getType()) : t);
			}
		});
	}
	
}
