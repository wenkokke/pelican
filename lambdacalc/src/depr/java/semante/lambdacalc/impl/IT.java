package semante.lambdacalc.impl;

import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;

public final class IT {
	
	public static final TLambdaCalc<TSymbol> LambdaCalc() {
		return new ITLambdaCalc();
	}
	
	private IT() {}
}