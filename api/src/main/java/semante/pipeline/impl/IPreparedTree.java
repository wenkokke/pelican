package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lambdacalc.DeBruijn;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.pipeline.PreparedTree;
import semante.pipeline.Result;

import com.google.common.collect.ImmutableMap;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IPreparedTree<ID> implements PreparedTree<ID> {

	ImmutableMap<DeBruijn, List<DeBruijn>> interpretations;
	ETHType thType;
	Result<ID> result;
	
	public IPreparedTree(ImmutableMap<DeBruijn, List<DeBruijn>> immutableMap, ETHType thType) {
		super();
		this.interpretations = immutableMap;
		this.thType = thType;
		this.result = null;
	}
	
	public IPreparedTree(Result<ID> result,ETHType thType) {
		super();
		this.interpretations = null;
		this.thType = thType;
		this.result = result;
	}
	
	public ImmutableMap<DeBruijn, List<DeBruijn>> getInterpretations() {
		return interpretations;
	}

	@Override
	public Result<ID> getResult() {
		return result;
	}

	@Override
	public ETHType getETHType() {
		return thType;
	}

	@Override
	public boolean isResultSet() {
		return result!=null;
	}
	
}

