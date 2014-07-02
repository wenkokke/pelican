package semante.pipeline;

import java.util.List;

import semante.pipeline.impl.ETHType;

public interface PreparedRunnableFormulae<ID> {
	
	public List<PreparedRunnableFormula> getFormulae();
	public Result<ID> getResult();
	public ETHType getETHType();
	public boolean isResultSet();

}
