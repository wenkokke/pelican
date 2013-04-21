package semante.lambdacalc.util;

import semante.lambdacalc.Expr;
import semante.lambdacalc.Expr.Test;
import semante.lambdacalc.Expr.Visitor;
import semante.lambdacalc.ExprPrinter;
import semante.lambdacalc.Symbol;

public final class ISExprPrinter<S extends Symbol> implements ExprPrinter<S>, Visitor<S,String> {
	
	@Override
	public final String format(Expr<S> expr) {
		return expr.accept(this);
	}
	@Override
	public String abstraction(S s, Expr<S> expr) {
		return "\\"+s.getName()+"."+withParens(expr);
	}
	@Override
	public String application(Expr<S> f, Expr<S> arg) {
		return withParens(f)+" "+withParens(arg);
	}
	@Override
	public final String variable(S s) {
		return s.getName();
	}
	private final String withParens(Expr<S> expr) {
		return (expr.accept(Test.<S>IsVariable())) ? format(expr) : "(" + format(expr) + ")";
	}
}
