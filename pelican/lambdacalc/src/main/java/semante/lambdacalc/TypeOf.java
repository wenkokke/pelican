package semante.lambdacalc;

public interface TypeOf<T extends TSymbol> {
	Type typeOf(Expr<T> expr);
}
