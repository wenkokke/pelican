package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.pipeline.PreparedRunnableFormula;
import semante.pipeline.PreparedRunnableFormulae;
import semante.pipeline.Result;

import com.google.common.collect.ImmutableList;


@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IPreparedRunnableFormulae<ID> implements PreparedRunnableFormulae<ID> {

	List<PreparedRunnableFormula> formulae;
	Result<ID> result;
	ETHType ETHType;

	public IPreparedRunnableFormulae(List<PreparedRunnableFormula> formulae) {
		this(ImmutableList.copyOf(formulae), null, null);
	}

	public IPreparedRunnableFormulae(Result<ID> result, ETHType thType) {
		this(null, result, thType);
	}

	// basically, if the "result" is set, we have an error.
	public boolean isResultSet() {
		return result != null;
	}
}
