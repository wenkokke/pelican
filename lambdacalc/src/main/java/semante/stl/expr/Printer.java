package semante.stl.expr;

import semante.stl.Expr;

interface Printer {

	/**
	 * Formats a lambda expression.
	 * 
	 * @param expr the lambda expression
	 * @return a String representation
	 */
	String format(Expr expr);
}
