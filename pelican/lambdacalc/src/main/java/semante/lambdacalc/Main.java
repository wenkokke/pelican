package semante.lambdacalc;

import lombok.val;
import semante.lambdacalc.impl.IT;
import semante.lambdacalc.impl.IU;

public final class Main {
	public static final void main(String[] args) throws Exception {
		
		final ULambdaCalc<USymbol> UTL = IU.LambdaCalc();
		val utl1 = UTL.parse("(\\x.man x) john");
		val utl2 = UTL.betaReduce(utl1);
		System.out.println(UTL.format(utl2));
		
		final TLambdaCalc<TSymbol> STL = IT.LambdaCalc();
		val stl1 = STL.parse("(\\x:e.man:et x:e) john:e");
		val stl2 = STL.betaReduce(stl1);
		System.out.println(STL.format(stl2));
	}
}