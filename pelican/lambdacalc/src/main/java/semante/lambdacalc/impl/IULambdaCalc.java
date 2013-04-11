package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.Delegate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.AlphaConverter;
import semante.lambdacalc.AlphaConverter2;
import semante.lambdacalc.AlphaGenerator;
import semante.lambdacalc.BetaReducer;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Expr;
import semante.lambdacalc.ExprParser;
import semante.lambdacalc.ExprPrinter;
import semante.lambdacalc.Substituter;
import semante.lambdacalc.ULambdaCalc;
import semante.lambdacalc.USymbol;
import semante.lambdacalc.util.IEqExpr;
import semante.lambdacalc.util.IEqUSymbol;
import semante.lambdacalc.util.ISGetSymbol;
import semante.lambdacalc.util.IUExprPrinter;

@Accessors(fluent=true)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
final class IULambdaCalc implements ULambdaCalc<USymbol> {

	// dependencies
	@Getter
	Equality<USymbol>			eqSymbol
		= new IEqUSymbol<USymbol>();
	@Getter
	Equality<Expr<USymbol>>		eqExpr
		= new IEqExpr<USymbol>(eqSymbol);
	@Getter
	Expr.Identity<USymbol> 		exprBuilder
		= new IExpr.Identity<USymbol>();
	ISGetSymbol.Free<USymbol>	freeSymbol
		= new ISGetSymbol.Free<USymbol>(eqSymbol);
	Substituter<USymbol>		subs
		= new IUSubstituter<USymbol>(eqSymbol);
	AlphaGenerator<USymbol>		alphaGen
		= new IUAlphaGenerator();

	// composition
	@Delegate
	ExprParser<USymbol>			exprParser
		= new IUExprParser();
	@Delegate
	ExprPrinter<USymbol>		exprPrinter
		= new IUExprPrinter<USymbol>();
	@Delegate
	AlphaConverter<USymbol>		alphaConv
		= new IUAlphaConverter<USymbol>(subs);
	@Delegate
	AlphaConverter2<USymbol>	alphaConv2
		= new IUAlphaConverter2();
	@Delegate
	BetaReducer<USymbol>		betaReduce
		= new IUBetaReducer<USymbol>(eqSymbol, freeSymbol, subs, alphaGen, alphaConv
	);
	
	// manual delegates
	@Override
	public final String toString(Expr<USymbol> expr) {
		return exprPrinter.format(expr);
	}
}
