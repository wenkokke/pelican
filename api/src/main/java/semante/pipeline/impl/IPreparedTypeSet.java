package semante.pipeline.impl;

import java.util.Set;

import lambdacalc.Type;
import semante.pipeline.PreparedTypeSet;
import semante.pipeline.Result;

public class IPreparedTypeSet<ID> implements PreparedTypeSet<ID> {

	private Set<Type> typeSet;
	private Result<ID> result;
	private ETHType thType;
	
	public IPreparedTypeSet(Set<Type> typeSet) {
		this.typeSet = typeSet;
		this.result = null;
		this.thType = null;
	}
	
	public IPreparedTypeSet(Result<ID> result, ETHType thType) {
		this.typeSet = null;
		this.result = result;
		this.thType = thType;
	}
	

	@Override
	public Set<Type> getTypeSet() {
		return typeSet;
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
