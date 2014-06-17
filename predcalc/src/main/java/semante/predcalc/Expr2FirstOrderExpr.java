package semante.predcalc;

import lambdacalc.Expr;

public interface Expr2FirstOrderExpr {
	Expr rewrite(Expr a);
}
