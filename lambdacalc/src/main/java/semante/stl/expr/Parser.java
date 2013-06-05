package semante.stl.expr;

import semante.stl.Expr;

public interface Parser {
	
	/**
	 * Parses a lambda expression.
	 * 
	 * @param exp Lambda expression to parse.
	 * @return {@link Expr$DeBruijn} lambda expression structure.
	 * @throws ParserException When the expression is malformed.
	 */
	Expr parse(String exp);
}
