package semante.predcalc;

import lambdacalc.Expr;

public interface FirstOrderExpr2Pred {
	public FOLExpr convert(Expr expr);
}
