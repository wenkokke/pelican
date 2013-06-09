package lambdacalc;

import lombok.experimental.Value;

@Value
public final class ISymbol implements Symbol {
	String		name;
	Type	type;
}
