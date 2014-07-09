package semante.pipeline;

import java.util.Set;

import lambdacalc.Type;

import semante.pipeline.impl.ETHType;

public interface PreparedTypeSet<ID> {

	public Set<Type> getTypeSet();
	
	public Result<ID> getResult();
	public ETHType getETHType();
	public boolean isResultSet();

}
