package semante.stl.impl;

import semante.stl.Symbol;
import semante.stl.Type;
import lombok.experimental.Value;

@Value
public final class ITSymbol implements Symbol {
	String		name;
	Type	type;
}
