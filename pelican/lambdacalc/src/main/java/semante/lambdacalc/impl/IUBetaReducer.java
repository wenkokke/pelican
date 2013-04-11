package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.AlphaConverter;
import semante.lambdacalc.AlphaGenerator;
import semante.lambdacalc.BetaReducer;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Substituter;
import semante.lambdacalc.USymbol;
import semante.lambdacalc.util.ISGetSymbol;

// delegating beta-reduction to IS.BetaReducer
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class IUBetaReducer<U extends USymbol> implements BetaReducer<U> {

	@Delegate
	BetaReducer<U> delegate;
	
	public IUBetaReducer(final Equality<U> eqSymbol,
			final ISGetSymbol.Free<U> getSymbol, final Substituter<U> subs,
			final AlphaGenerator<U> alphaGen, final AlphaConverter<U> alphaConv) {
		delegate = new ISBetaReducer<U>(eqSymbol,getSymbol,subs,alphaGen,alphaConv);
	}
}
