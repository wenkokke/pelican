package semante.lambdacalc.impl;

import semante.lambdacalc.Type;
import semante.lambdacalc.TypeParser;

abstract class ATypeParser extends AParser<Type> implements TypeParser {

	@Override
	public final Type parse(final String typeString) throws Exception {
		return build().parse(typeString);
	}
	
}
