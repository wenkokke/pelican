package predcalc;

import lambdacalc.Expr;

public interface LowerLambda {
	ExprForm<Expr> rewrite(Expr a);
}
