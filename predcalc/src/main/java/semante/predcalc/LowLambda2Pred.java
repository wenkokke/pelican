package semante.predcalc;

import semante.lambdacalc.Expr;
import semante.lambdacalc.TSymbol;

public interface LowLambda2Pred<T extends TSymbol> {
	public FOLExpr convert(Expr<T> expr);
}
