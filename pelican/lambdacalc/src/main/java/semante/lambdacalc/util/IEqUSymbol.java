package semante.lambdacalc.util;

import lombok.Delegate;
import semante.lambdacalc.Equality;
import semante.lambdacalc.USymbol;

public final class IEqUSymbol<U extends USymbol> implements Equality<U> {
	
	// simply delegates to the generic symbol equality.
	@Delegate Equality<U> eqSymbol = new IEqSymbol<U>();
	
}
