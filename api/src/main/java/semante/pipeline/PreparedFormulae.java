package semante.pipeline;

import java.util.List;
import java.util.Map;

import lambdacalc.DeBruijn;

import semante.pipeline.impl.ETHType;

public interface PreparedFormulae<ID> {
	public Map<DeBruijn,List<DeBruijn>> getTextDesc();
	public Map<DeBruijn,List<DeBruijn>> getHypoDesc();
	
	public Result<ID> getResult();
	public ETHType getETHType();
	public boolean isResultSet();

}
