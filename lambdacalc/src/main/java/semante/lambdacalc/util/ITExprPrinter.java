package semante.lambdacalc.util;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Expr;
import semante.lambdacalc.Expr.Test;
import semante.lambdacalc.Expr.Visitor;
import semante.lambdacalc.ExprPrinter;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.TypePrinter;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class ITExprPrinter<T extends TSymbol> implements ExprPrinter<T>, Visitor<T,String> {
	
	TypePrinter printer;
	
	@Override
	public final String format(Expr<T> expr) {
		return expr.accept(this);
	}
	
	@Override
	public final String abstraction(T t, Expr<T> expr) {
		return "\\"+pSymbol(t)+"."+pBetween(expr);
	}
	
	@Override
	public final String application(Expr<T> a2b, Expr<T> a) {
		return pBetween(a2b)+" "+pBetween(a);
	}
	
	@Override
	public final String variable(T t) {
		return pSymbol(t);
	}
	
	// print a symbol as "x_e" or "A_et".
	private final String pSymbol(T t) {
		return t.getName()+":"+printer.format(t.getType());
	}
	
	// print an expression between parentheses.
	private final String pBetween(Expr<T> expr) {
		return (expr.accept(Test.<T>IsVariable())) ? format(expr) : "(" + format(expr) + ")";
	}
}
