package semante.pipeline.impl;

import lombok.experimental.Value;
import semante.pipeline.Result;

@Value
public final class IResult$Error<ID> implements Result<ID> {

	ID id;
	String msg;
	
	@Override
	public final <X> X accept(final Visitor<ID, X> v) {
		return v.exception(id,msg);
	}

}
