package semante.pipeline.impl;

import java.util.List;

import lombok.val;
import lombok.experimental.Value;
import semante.pipeline.Pair;
import semante.pipeline.Result;

@Value
public final class IResult$Errors<ID> implements Result<ID> {

	List<Pair<ID,String>> errors;
	
	@Override
	public final <X> X accept(final Visitor<ID, X> v) {
		X result = null;
		for (val error: errors) {
			result = v.exception(error.getFirst(),error.getSecond());
		}
		return result;
	}

}
