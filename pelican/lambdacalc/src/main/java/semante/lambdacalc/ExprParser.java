package semante.lambdacalc;

import org.codehaus.jparsec.error.ParserException;

public interface ExprParser<S extends Symbol> {
	
	/**
	 * Parses a lambda expression.
	 * 
	 * @param exp Lambda expression to parse.
	 * @return {@link Expr} lambda expression structure.
	 * @throws ParserException When the expression is malformed.
	 */
	Expr<S> parse(String exp);
}
