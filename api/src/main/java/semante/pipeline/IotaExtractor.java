package semante.pipeline;

import java.util.Set;

import lambdacalc.Expr;
import lambdacalc.STL;

public interface IotaExtractor {
	
	public interface IotaExtractorResult  {
		Expr getAssertion();
		Set<Expr> getUniquenessConditions();
	};
	
	IotaExtractorResult extract(final Expr expr, final STL stl);
}
