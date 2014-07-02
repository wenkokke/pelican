package semante.pipeline;

import java.util.List;

import lambdacalc.DeBruijn;
import semante.pipeline.impl.ETHType;

import com.google.common.collect.ImmutableMap;

public interface PreparedTree<ID> {

	public ImmutableMap<DeBruijn, List<DeBruijn>> getInterpretations();

	public Result<ID> getResult();
	public ETHType getETHType();
	public boolean isResultSet();
	
}
