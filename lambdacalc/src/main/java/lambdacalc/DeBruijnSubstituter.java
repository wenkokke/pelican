package lambdacalc;

public interface DeBruijnSubstituter {

	/**
	 * Substitutes variable bound to the top.
	 * 
	 * <pre>
	 * subst x (0); 		// (x)
	 * subst x [et](0 1); 	// [et](0 x) 
	 * </pre>
	 */
	DeBruijn substituteTop(DeBruijn expr, DeBruijn subject);
	
	/**
	 * Substitutes a free term in a lambda expression.
	 * 
	 * <pre>
	 * subst x "y" (y); 		// (x)
	 * subst x "y" [et](0 1); 	// [et](0 1) 
	 * </pre>
	 */
	DeBruijn substituteFree(DeBruijn expr, String name, DeBruijn subject);

	/**
	 * Renames a free term in a lambda expression.
	 * 
	 * <pre>
	 * subst "y" "x" (y); // (x) 
	 * </pre>
	 */
	DeBruijn renameFree(String from, String to, DeBruijn subject);
	
}
