package semante.stl.type;

import semante.stl.Type;


public interface Printer {
	
	/**
	 * Formats a type expression.
	 * 
	 * @param expr the type expression
	 * @return a String representation
	 */
	String format(Type type);
}
