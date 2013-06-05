package semante.stl.type;

import semante.stl.Expr$DeBruijn;
import semante.stl.Type;

public interface Parser {
	
	/**
	 * Parses a lambda type.
	 * 
	 * @param exp Lambda expression to parse.
	 * @return {@link Expr$DeBruijn} lambda expression structure.
	 * @throws ParserException When the expression is malformed.
	 */
	Type parse(String type);
}
