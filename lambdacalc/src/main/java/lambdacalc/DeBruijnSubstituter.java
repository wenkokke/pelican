package lambdacalc;

public interface DeBruijnSubstituter {

	/**
	 * Substitutes lambda expression bound to the top.
	 * 
	 * <pre>
	 * subst x (0); // (x)
	 * subst x [et](0 1); // [et](0 x) 
	 * </pre>
	 */
	DeBruijn substituteTop(DeBruijn expr, DeBruijn subject);
	
}
