package semante.lambdacalc.impl;

import semante.lambdacalc.ULambdaCalc;
import semante.lambdacalc.USymbol;

public final class IU {
	
	public static final ULambdaCalc<USymbol> LambdaCalc() {
		return new IULambdaCalc();
	}
	
	private IU() {}
}