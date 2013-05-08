package semante.predcalc;

import semante.lambdacalc.TSymbol;

public interface Lambda2Pred<T extends TSymbol> {
	FOLForm smash(semante.lambdacalc.Expr<T> expr);
}
