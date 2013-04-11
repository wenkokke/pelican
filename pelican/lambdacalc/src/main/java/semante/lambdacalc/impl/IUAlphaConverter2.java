package semante.lambdacalc.impl;

import semante.lambdacalc.AlphaConverter2;
import semante.lambdacalc.Expr;
import semante.lambdacalc.USymbol;

final class IUAlphaConverter2 implements AlphaConverter2<USymbol> {

	@Override
	public final Expr<USymbol> alphaConvert(final String s1, final String s2, final Expr<USymbol> subject) {
		return subject.accept(new IExpr.Identity<USymbol>() {
			@Override
			public Expr<USymbol> abstraction(USymbol s, Expr<USymbol> arg) {
				return super.abstraction(s, arg.accept(this));
			}

			@Override
			public Expr<USymbol> application(Expr<USymbol> f, Expr<USymbol> arg) {
				return super.application(f.accept(this), arg.accept(this));
			}
			@Override
			public final Expr<USymbol> variable(USymbol s) {
				return super.variable((s1.equals(s.getName())) ? new IUSymbol(s2) : s);
			}
		});
	}
	
}
