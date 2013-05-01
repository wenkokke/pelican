package semante.pipeline.impl;

import semante.pipeline.Result;
import lombok.experimental.Value;

@Value
public final class IResult$CounterExample<ID> implements Result<ID> {@Override
	
	public final <X> X accept(final Visitor<ID, X> v) {
		return v.counterExample();
	}
	
}
