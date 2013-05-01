package semante.lambdacalc.util;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Symbol;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
public final class IEqTSymbol<T extends TSymbol> implements Equality<T> {

	Equality<Type> eqType;
	Equality<Symbol> eqSymbol;
	
	@Override
	public final boolean apply(final T s1, final T s2) {
		return eqSymbol.apply(s1, s2) && eqType.apply(s1.getType(), s2.getType());
	}
}
