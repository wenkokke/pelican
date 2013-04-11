package semante.lambdacalc;

public interface LambdaCalc<S extends Symbol> extends ExprParser<S>,
		ExprPrinter<S>, AlphaConverter<S>, AlphaConverter2<S>, BetaReducer<S>, ToString<S> {
	
	Equality<S>			eqSymbol();
	Equality<Expr<S>>	eqExpr();
	Expr.Identity<S> 	exprBuilder();
}
