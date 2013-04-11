package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.Delegate;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.AlphaConverter;
import semante.lambdacalc.AlphaGenerator;
import semante.lambdacalc.BetaReducer;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Substituter;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.util.ISGetSymbol;

@FieldDefaults(makeFinal=true, level=PRIVATE)
public final class ITBetaReducer<T extends TSymbol> implements BetaReducer<T> {

	@Delegate BetaReducer<T> delegate;
	
	public ITBetaReducer(
		final Equality<T>			eqSymbol,
		final ISGetSymbol.Free<T>	getSymbol,
		final Substituter<T>		subs,
		final AlphaGenerator<T>		alphaGen,
		final AlphaConverter<T>		alphaConv
	) {
		delegate = new ISBetaReducer<T>(eqSymbol,getSymbol,subs,alphaGen,alphaConv);
	}

}
