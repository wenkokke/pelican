package predcalc;

import lambdacalc.Expr;

public interface LowerLambda {
	Expr rewrite(Expr a);
}
