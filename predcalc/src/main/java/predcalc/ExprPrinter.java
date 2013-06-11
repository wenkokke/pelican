package predcalc;

import predcalc.FOLExpr.Formula;


public interface ExprPrinter {

	/**
	 * Formats a Predicate expression.
	 * 
	 * @param term the lambda expression
	 * @return a String representation
	 */
	String format(Formula term);
}
