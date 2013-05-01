package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.AlphaGenerator;
import semante.lambdacalc.Symbol;
import semante.lambdacalc.TSymbol;

/**
 * Delegates alpha generation to an instance of ISAlphaGenerator. 
 * 
 * @author Pepijn Kokke
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class ITAlphaGenerator implements AlphaGenerator<TSymbol> {

	AlphaGenerator<Symbol> alphaGen;
	
	@Override
	public final TSymbol freshName(TSymbol t) {
		return new ITSymbol(alphaGen.freshName(t).getName(),t.getType());
	}
}