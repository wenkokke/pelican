package lambdacalc;


public final class TypeError extends IllegalArgumentException {
	public TypeError(final String msg) {
		super(msg);
	}
	public TypeError(final Type t1, final Type t2) {
		super(); //TODO write meaningfull error message
	}
	public TypeError(final Symbol s1, final Symbol s2) {
		super(); //TODO write meaningfull error message
	}
	public TypeError(final Expr e1, final Expr e2) {
		super(); //TODO write meaningfull error message
	}
	public TypeError(final DeBruijn e1, final DeBruijn e2) {
		super(); //TODO write meaningfull error message
	}
}
