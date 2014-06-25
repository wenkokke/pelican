package semante.pipeline;

import java.util.List;

import semante.pipeline.impl.ETHType;

public interface PreparedFormulae<ID> {
	
	public List<PreparedFormula> getFormulae();
	public Result<ID> getResult();
	public ETHType getETHType();
	public boolean isResultSet();

}
