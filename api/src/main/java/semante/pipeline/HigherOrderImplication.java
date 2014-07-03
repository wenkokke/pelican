package semante.pipeline;

import lambdacalc.DeBruijn;

public interface HigherOrderImplication {

	public DeBruijn higherOrderImplication(DeBruijn text, DeBruijn hypo, Iterable<DeBruijn> ctxt) throws HOIException;
	
	@SuppressWarnings("serial")
	public static final class HOIException extends Exception {
		public HOIException(final String msg, final Object... args) {
			super(String.format(msg,args));
		}
	}
	
}
