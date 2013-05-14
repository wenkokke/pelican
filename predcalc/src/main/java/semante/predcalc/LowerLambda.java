package semante.predcalc;

import semante.lambdacalc.Expr;
import semante.lambdacalc.TSymbol;

public interface LowerLambda<T extends TSymbol> {
	ExprForm<Expr<T>> rewrite(Expr<T> a);
}
