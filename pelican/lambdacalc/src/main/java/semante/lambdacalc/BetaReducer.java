package semante.lambdacalc;


public interface BetaReducer<S extends Symbol> {
	Expr<S> betaReduce(Expr<S> expr);
}
