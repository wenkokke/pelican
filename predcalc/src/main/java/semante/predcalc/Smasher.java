package semante.predcalc;

import semante.predcalc.FOLExpr.Formula;
import lambdacalc.Expr;

public interface Smasher {
	ExprForm<Formula> smash(Expr expr);
}
