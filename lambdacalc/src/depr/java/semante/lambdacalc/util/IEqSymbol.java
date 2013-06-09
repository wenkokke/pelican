package semante.lambdacalc.util;

import semante.lambdacalc.Equality;
import semante.lambdacalc.Symbol;

public final class IEqSymbol<S extends Symbol> implements Equality<S> {

	@Override
	public final boolean apply(final S a, final S b) {
		return a.getName().equals(b.getName());
	}

}
