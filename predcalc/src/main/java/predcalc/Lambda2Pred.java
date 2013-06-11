package predcalc;

import predcalc.FOLExpr.Formula;
import lambdacalc.Expr;

public interface Lambda2Pred {
	ExprForm<Formula> smash(Expr expr);
}
