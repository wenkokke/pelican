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
import semante.lambdacalc.Symbol;
import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;
import semante.lambdacalc.TypeChecker;
import semante.lambdacalc.TypeOf;
import semante.lambdacalc.TypePrinter;
import semante.lambdacalc.util.IEqExpr;
import semante.lambdacalc.util.IEqSymbol;
import semante.lambdacalc.util.IEqTSymbol;
import semante.lambdacalc.util.IEqType;
import semante.lambdacalc.util.ISGetSymbol;
import semante.lambdacalc.util.ITExprPrinter;
import semante.lambdacalc.util.ITTypeChecker;
import semante.lambdacalc.util.ITTypeOf;
import semante.lambdacalc.util.ITypePrinter;

/**
 * Composition of serveral components into a LambdaCalc instance.
 * 
 * @author Pepijn Kokke
 */
@Accessors(fluent=true)
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class ITLambdaCalc implements TLambdaCalc<TSymbol> {

	// dependencies
	@Getter
	Type.Identity 				typeBuilder
		= new IType.Identity();
	@Delegate
	TypePrinter					typePrinter
		= new ITypePrinter();
	@Getter
	Expr.Identity<TSymbol>		exprBuilder
		= new IExpr.Identity<TSymbol>();
	@Getter
	Equality<Type>				eqType
		= new IEqType();
	Equality<Symbol>			eqSymbol2
		= new IEqSymbol<Symbol>();
	@Getter
	Equality<TSymbol>			eqSymbol
		= new IEqTSymbol<TSymbol>(eqType,eqSymbol2);
	@Getter
	Equality<Expr<TSymbol>>		eqExpr
		= new IEqExpr<TSymbol>(eqSymbol);
	
	ISGetSymbol.Free<TSymbol>	iSGetSymbol
		= new ISGetSymbol.Free<TSymbol>(eqSymbol);
	Substituter<TSymbol>		subs
		= new ITSubstituter<TSymbol>(eqSymbol);
	AlphaGenerator<Symbol>		alphaGen0
		= new ISAlphaGenerator();
	AlphaGenerator<TSymbol>		alphaGen
		= new ITAlphaGenerator(alphaGen0);

	// composition
	@Delegate
	TypeOf<TSymbol>				typeOf
		= new ITTypeOf<TSymbol>(typeBuilder, eqType);
	@Delegate
	TypeChecker<TSymbol>		typeChecker
		= new ITTypeChecker<TSymbol>(typeOf,eqType);
	@Delegate
	ExprParser<TSymbol>			exprParser
		= new ITExprParser();
	@Delegate
	ExprPrinter<TSymbol>		exprPrinter
		= new ITExprPrinter<TSymbol>(typePrinter);
	AlphaConverter<TSymbol>		alphaConv0 
		= new ISAlphaConverter<TSymbol>(subs);
	@Delegate
	AlphaConverter<TSymbol>		alphaConv
		= new ITAlphaConverter<TSymbol>(eqType,alphaConv0);
	@Delegate
	AlphaConverter2<TSymbol>	alphaConv2
		= new ITAlphaConverter2();
	@Delegate
	BetaReducer<TSymbol>		betaReduce
		= new ITBetaReducer<TSymbol>(eqSymbol, iSGetSymbol, subs, alphaGen, alphaConv
	);
	
	@Override
	public final String toString(Expr<TSymbol> expr) {
		return exprPrinter.format(expr);
	}
}
