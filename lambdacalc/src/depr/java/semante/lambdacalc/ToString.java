package semante.lambdacalc;

public interface ToString<S extends Symbol> {
	String toString(Expr<S> expr);
}
