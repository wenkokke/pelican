package semante.lambdacalc;

public interface TypeChecker<T extends TSymbol> {
	boolean isCompatible(Type f, Type a);
	boolean isCompatible(Expr<T> f, Expr<T> a);
}
