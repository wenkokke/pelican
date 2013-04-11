package semante.lambdacalc.impl;

import lombok.experimental.Value;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;

@Value
final class ITSymbol implements TSymbol {
	String name; Type type;
}
