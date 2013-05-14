package semante.predcalc.util;

import semante.lambdacalc.Expr;
import semante.lambdacalc.TSymbol;
import semante.predcalc.FOLExpr;

public interface LowLambda2Pred<T extends TSymbol> {
	public FOLExpr convert(Expr<T> expr);
}
