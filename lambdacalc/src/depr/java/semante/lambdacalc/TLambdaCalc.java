package semante.lambdacalc;

public interface TLambdaCalc<T extends TSymbol> extends LambdaCalc<T>, TypeOf<T>, TypeChecker<T>, TypePrinter {
	
	Equality<Type>	eqType();
	Type.Identity 	typeBuilder();
	
}
