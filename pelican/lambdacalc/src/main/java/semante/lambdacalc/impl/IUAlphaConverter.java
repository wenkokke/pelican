package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.AlphaConverter;
import semante.lambdacalc.Substituter;
import semante.lambdacalc.USymbol;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class IUAlphaConverter<U extends USymbol> implements AlphaConverter<U> {

	// simply delegates to a generic implementation of alphaconverter
	@Delegate AlphaConverter<U> delegate;
	
	public IUAlphaConverter(final Substituter<U> substituter) {
		delegate = new ISAlphaConverter<U>(substituter);
	}
}
