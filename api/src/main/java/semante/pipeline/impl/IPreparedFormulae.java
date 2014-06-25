package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.pipeline.PreparedFormula;
import semante.pipeline.PreparedFormulae;
import semante.pipeline.Result;

import com.google.common.collect.ImmutableList;


@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IPreparedFormulae<ID> implements PreparedFormulae<ID> {

	List<PreparedFormula> formulae;
	Result<ID> result;
	ETHType ETHType;

	public IPreparedFormulae(List<PreparedFormula> formulae) {
		this(ImmutableList.copyOf(formulae), null, null);
	}

	public IPreparedFormulae(Result<ID> result, ETHType thType) {
		this(null, result, thType);
	}

	public boolean isResultSet() {
		return result != null;
	}
}
