package semante.lambdacalc.impl;

import org.codehaus.jparsec.OperatorTable;
import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.functors.Binary;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.functors.Map4;

import semante.lambdacalc.Expr;
import semante.lambdacalc.ExprParser;
import semante.lambdacalc.Symbol;
import semante.lambdacalc.impl.IExpr.Abstraction;
import semante.lambdacalc.impl.IExpr.Application;
import semante.lambdacalc.impl.IExpr.Variable;

abstract class AExprParser<S extends Symbol> extends AParser<Expr<S>> implements ExprParser<S> {

	@Override
	public final Expr<S> parse(final String exprString) {
		return build().parse(exprString);
	}
	
	// implementation
	
	abstract protected Parser<Void> token(String name);
	abstract protected Parser<Void> lambda();
	abstract protected Parser<S> symbolParser();
	
	@Override
	protected final Parser<Expr<S>> parser() {
		final Parser.Reference<Expr<S>> refExpr
			= Parser.newReference();
		final Parser<S> pSymbol
			= symbolParser();
		final Parser<Expr<S>> pAbstraction
			= Parsers.sequence(lambda(),pSymbol,token("."),refExpr.lazy(),
				new Map4<Void,S,Void,Expr<S>,Expr<S>>() {
					@Override
					public final Expr<S> map(Void _1, S s, Void _2, Expr<S> arg) {
						return new Abstraction<S>(s,arg);
					}
				});
		final Parser<Expr<S>> pVariable
			= pSymbol.map(
				new Map<S,Expr<S>>() {
					@Override
					public final Expr<S> map(S s) {
						return new Variable<S>(s);
					}
				});
		final Parser<Expr<S>> pAtom
			= Parsers.or(pAbstraction, pVariable, refExpr.lazy().between(token("("), token(")")));
		final Parser<Expr<S>> pExpr
			= new OperatorTable<Expr<S>>()
				.infixl(token(" ").retn(new Binary<Expr<S>>() {
					@Override
					public final Expr<S> map(Expr<S> f, Expr<S> arg) {
						return new Application<S>(f,arg);
					}
				}), 10)
				.build(pAtom);
		refExpr.set(pExpr);
		return pExpr;
	}
	
}
