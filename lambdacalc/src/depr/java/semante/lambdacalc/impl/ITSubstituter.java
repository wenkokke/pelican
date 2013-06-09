package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Substituter;
import semante.lambdacalc.TSymbol;

/**
 * Delegates substitution to an instance of ISSubstituter.
 * 
 * @author Pepijn Kokke
 * @param <T>
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class ITSubstituter<T extends TSymbol> implements Substituter<T> {
	
	// simply delegates to a generic substituter implementation
	@Delegate Substituter<T> delegate;
	
	public ITSubstituter(final Equality<T> eqSymbol) {
		delegate = new ISSubstituter<T>(eqSymbol);
	}
}