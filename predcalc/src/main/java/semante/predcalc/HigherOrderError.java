package semante.predcalc;

@SuppressWarnings("serial")
public final class HigherOrderError extends IllegalArgumentException {
	public HigherOrderError(final String msg, final Object... args) {
		super(String.format(msg,args));
	}
}
