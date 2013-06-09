package lambdacalc;

import lambdacalc.ExprBuilder;
import lambdacalc.IExpr.IAbstraction;
import lambdacalc.IExpr.IApplication;
import lambdacalc.IExpr.IVariable;
import lombok.experimental.Value;

@Value
public class IExprBuilder implements ExprBuilder {

	@Override
	public Expr abstraction(Symbol arg0, Expr arg1) {
		return new IAbstraction(arg0,arg1);
	}

	@Override
	public Expr application(Expr arg0, Expr arg1) {
		return new IApplication(arg0,arg1);
	}

	@Override
	public Expr variable(Symbol s) {
		return new IVariable(s);
	}
}