package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.AlphaConverter;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Expr;
import semante.lambdacalc.StlTypeError;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;

/**
 * Delegates alpha-conversion to an instance of ISAlphaConvert. 
 * 
 * @author Pepijn
 * @param <T>
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class ITAlphaConverter<T extends TSymbol> implements AlphaConverter<T> {

	Equality<Type> eqType;
	AlphaConverter<T> delegate;
	
	

	/**
	 * Enforces type-checking before delegating to a generic alphaconverter.
	 */
	@Override
	public final Expr<T> alphaConvert(final T s1, final T s2, final Expr<T> subject) {
		if (! eqType.apply(s1.getType(), s2.getType())) {
			throw new StlTypeError(s1, s2);
		}
		else {
			return delegate.alphaConvert(s1, s2, subject);
		}
	}
}