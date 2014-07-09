package semante.pipeline;

import semante.pipeline.impl.ETHType;

public interface PreparedResult<ID> {

	public Result<ID> getResult();
	public ETHType getETHType();
	public boolean isResultSet();
	
}
