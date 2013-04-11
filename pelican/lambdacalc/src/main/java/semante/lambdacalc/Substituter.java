package semante.lambdacalc;


public interface Substituter<S extends Symbol> {
	/** Substitute R for S in expr. */
	Expr<S> substitute(S e1, Expr<S> e2, Expr<S> subject);
}
