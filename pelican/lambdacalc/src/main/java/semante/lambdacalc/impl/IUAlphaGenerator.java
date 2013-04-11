package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.AlphaGenerator;
import semante.lambdacalc.Symbol;
import semante.lambdacalc.USymbol;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class IUAlphaGenerator implements AlphaGenerator<USymbol> {

	// simply delegates to the isalphagen implementation (with "unsafe" cast)
	AlphaGenerator<Symbol> alphaGen = new ISAlphaGenerator(); 
	
	@Override
	public final USymbol freshName(USymbol name) {
		return (USymbol) alphaGen.freshName(name);
	}
	
}
