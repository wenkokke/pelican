package lambdacalc.impl;

import lambdacalc.Index;
import lambdacalc.Type;
import lombok.experimental.Value;

@Value
public final class IIndex implements Index {
	Integer	index;
	Type	type;
}
