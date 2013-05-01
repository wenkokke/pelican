package semante.predcalc;

import semante.lambdacalc.TSymbol;
import semante.predcalc.FOLExpr.Formula;

public interface Lambda2Pred<T extends TSymbol> {
	Formula smash(semante.lambdacalc.Expr<T> expr);
}
