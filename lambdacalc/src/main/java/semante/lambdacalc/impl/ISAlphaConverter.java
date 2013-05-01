package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.AlphaConverter;
import semante.lambdacalc.Expr;
import semante.lambdacalc.Substituter;
import semante.lambdacalc.Symbol;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class ISAlphaConverter<S extends Symbol> implements AlphaConverter<S> {
	
	// depends on an implementation of substituter
	Substituter<S> substituter;
	
	/**
	 * Note: AlphaConverter makes the assumption that all occurances of s1
	 * are bound in the subject expression.
	 */
	@Override
	public final Expr<S> alphaConvert(final S s1, final S s2, final Expr<S> subject) {
		return substituter.substitute(s1, new IExpr.Variable<S>(s2), subject);
	}
}
