package semante.lambdacalc.impl;

import lombok.experimental.Value;
import semante.lambdacalc.USymbol;

@Value
final class IUSymbol implements USymbol {
	String name;
}
