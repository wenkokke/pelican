package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import java.util.Map;

import lambdacalc.DeBruijn;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.pipeline.PreparedFormulae;
import semante.pipeline.Result;

import com.google.common.collect.ImmutableMap;


@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IPreparedFormulae<ID> implements PreparedFormulae<ID> {

	private Map<DeBruijn, List<DeBruijn>> textDesc;
	private Map<DeBruijn, List<DeBruijn>> hypoDesc;
	
	Result<ID> result;
	ETHType ETHType;

	public IPreparedFormulae(Map<DeBruijn, List<DeBruijn>> textDesc, Map<DeBruijn, List<DeBruijn>> hypoDesc) {
		this(ImmutableMap.copyOf(textDesc), ImmutableMap.copyOf(hypoDesc), null, null);
	}

	public IPreparedFormulae(Result<ID> result, ETHType thType) {
		this(null, null, result, thType);
	}

	// basically, if the "result" is set, we have an error.
	public boolean isResultSet() {
		return result != null;
	}

	@Override
	public Map<DeBruijn, List<DeBruijn>> getTextDesc() {
		return textDesc;
	}

	@Override
	public Map<DeBruijn, List<DeBruijn>> getHypoDesc() {
		return hypoDesc;
	}
}
