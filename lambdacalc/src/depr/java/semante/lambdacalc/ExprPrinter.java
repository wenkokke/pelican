package semante.lambdacalc;

public interface ExprPrinter<S extends Symbol> {

	/**
	 * Formats a lambda expression.
	 * 
	 * @param expr the lambda expression
	 * @return a String representation
	 */
	String format(Expr<S> expr);
}
