package semante.pipeline.impl;

import lombok.experimental.Value;
import semante.pipeline.Result;

@Value
public final class IResult$Proof<ID> implements Result<ID> {
	
	@Override
	public final <X> X accept(final Visitor<ID, X> v) {
		return v.proof();
	}
}
