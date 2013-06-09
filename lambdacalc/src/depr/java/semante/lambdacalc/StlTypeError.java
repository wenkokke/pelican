package semante.lambdacalc;

public class StlTypeError extends IllegalArgumentException {
	public StlTypeError(final String msg) {
		super(msg);
	}
	public StlTypeError(final Type t1, final Type t2) {
		super(); //TODO write meaningfull error message
	}
	public <T extends TSymbol> StlTypeError(final T t1, final T t2) {
		super(); //TODO write meaningfull error message
	}
	public <T extends TSymbol> StlTypeError(final Expr<T> e1, final Expr<T> e2) {
		super(); //TODO write meaningfull error message
	}
}
