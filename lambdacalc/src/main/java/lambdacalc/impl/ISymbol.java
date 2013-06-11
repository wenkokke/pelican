package lambdacalc.impl;

import lambdacalc.Symbol;
import lambdacalc.Type;
import lombok.experimental.Value;

@Value
public final class ISymbol implements Symbol {
	String	name;
	Type	type;
}
