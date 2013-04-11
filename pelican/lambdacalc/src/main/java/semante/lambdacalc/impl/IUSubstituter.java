package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Substituter;
import semante.lambdacalc.USymbol;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
public final class IUSubstituter<U extends USymbol> implements Substituter<U> {
	
	// simply delegates to a generic substituter implementation
	@Delegate Substituter<U> delegate;
	
	public IUSubstituter(final Equality<U> eqSymbol) {
		delegate = new ISSubstituter<U>(eqSymbol);
	}
}