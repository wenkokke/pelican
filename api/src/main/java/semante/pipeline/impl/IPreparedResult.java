package semante.pipeline.impl;

import semante.pipeline.PreparedResult;
import semante.pipeline.Result;

public class IPreparedResult<ID> implements PreparedResult<ID> {

	private Result<ID> result;
	private ETHType thType;
	
	public IPreparedResult(Result<ID> result, ETHType thType) {
		this.result = result;
		this.thType = thType;
	}
	
	@Override
	public Result<ID> getResult() {
		return result;
	}

	@Override
	public ETHType getETHType() {
		return thType;
	}

	// basically, if the "result" is set, we have an error.
	public boolean isResultSet() {
		return result != null;
	}


}
