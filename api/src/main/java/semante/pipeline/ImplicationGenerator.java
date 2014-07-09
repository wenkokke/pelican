package semante.pipeline;

import java.util.Set;

import com.google.common.collect.ImmutableSet.Builder;

import lambdacalc.DeBruijn;
import lambdacalc.Expr;

public interface ImplicationGenerator {

	Set<Expr> process(Expr source, Expr target, DeBruijn ctxt, Builder<Expr> uniquenessConditionsB) 
			throws UnmatchedTypesException, UnsupportedImplication;
	
	@SuppressWarnings("serial")
	public static class UnmatchedTypesException extends Exception {
		public UnmatchedTypesException(String sourceType, String targetType) {
			super(sourceType + " and " + targetType);
		}
	}	
	@SuppressWarnings("serial")
	public static class UnsupportedImplication extends Exception {
		public UnsupportedImplication(String type) {
			super(type);
		}
	}
	
}
