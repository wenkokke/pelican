package semante.predcalc;

import java.util.Set;

import semante.predcalc.FOLExpr.Formula;
import lambdacalc.Expr;

public interface Smasher {
	ExprForm<Formula> smash(Expr expr, Set<Expr> uniquenessConditions, Set<Expr> implications);
}
