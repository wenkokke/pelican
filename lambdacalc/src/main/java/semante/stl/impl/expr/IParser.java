package semante.stl.impl.expr;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;

import org.codehaus.jparsec.OperatorTable;
import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.functors.Binary;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.functors.Map3;
import org.codehaus.jparsec.functors.Map4;

import semante.stl.Expr;
import semante.stl.Symbol;
import semante.stl.Type;
import semante.stl.impl.IExpr;
import semante.stl.impl.ITSymbol;
import semante.stl.impl.IType;

/**
 * Implementation of the expression parser {@link TExprParser}. It parses
 * expressions of the form
 * <tt>\P:(et)t.\Q:(et)t.\A:et.AND:ttt (P:(et)t A:et) (Q:(et)t A:et)</tt>.
 * 
 * @author Pepijn Kokke
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IParser implements semante.stl.expr.Parser {
	
	@Override
	public final Expr parse(final String input) {
		return ExprParser.parse(input);
	}

	static Type.Identity  TypeBuilder   = new IType.IIdentity();
	static Expr.Identity  ExprBuilder   = new IExpr.IIdentity();
	static Terminals            ExprTerminals = Terminals.operators("e","t","\\",".","(",")"," ",":");
	static Parser<Expr>   ExprParser    = exprParser();
	
	private static final Parser<Void> getToken(String... names) {
		return ExprTerminals.token(names).skipTimes(1);
	}
	
	/**
	 * @return a parser for Type$Simpl
	 */
	private static final Parser<Type> typeParser() {
		val ref    = Parser.<Type> newReference();
		val typeE  = getToken("e").retn((Type) IType.E);
		val typeT  = getToken("t").retn((Type) IType.T);
		val atomic = Parsers.or(typeE,typeT,ref.lazy().between(getToken("("), getToken(")"))); 
		val parser = new OperatorTable<Type>()
				.infixr(Parsers.always().retn(new Binary<Type>() {
					@Override
					public final Type map(Type left, Type right) {
						return TypeBuilder.function(left, right);
					}
				}),10).build(atomic);
		ref.set(parser);
		val ignored = Parsers.<Void> always();
		return parser.from(ExprTerminals.tokenizer(),ignored);
	}
	
	protected static final Parser<Symbol> symbolParser() {
		return Parsers.sequence(
			Terminals.Identifier.PARSER, getToken(":"), typeParser(),
				new Map3<String,Void,Type,Symbol>() {
					@Override
					public final Symbol map(String s, Void _, Type t) {
						return new ITSymbol(s,t);
					}
				});
	}
	
	/**
	 * @return a parser for Expr$Simpl
	 */
	private static final Parser<Expr> exprParser() {
		final Parser.Reference<Expr>
			refExpr = Parser.newReference();
		
		final Parser<Symbol>
			pSymbol = symbolParser();
		
		final Parser<Expr>
			pAbstraction = Parsers.sequence(
			getToken("\\"),pSymbol,getToken("."),refExpr.lazy(),
			new Map4<Void,Symbol,Void,Expr,Expr>() {
				@Override
				public Expr map(Void _1, Symbol sym, Void _2, Expr arg) {
					return ExprBuilder.abstraction(sym,arg);
				}
			});
		
		final Parser<Expr>
			pVariable = pSymbol.map(
			new Map<Symbol,Expr>() {
				@Override
				public final Expr map(Symbol s) {
					return ExprBuilder.variable(s);
				}
			});
		
		final Parser<Expr>
			pAtomic = Parsers.or(
				pAbstraction, pVariable,
				refExpr.lazy().between(getToken("("), getToken(")")));
		
		final Parser<Expr>
			pExpr = new OperatorTable<Expr>()
			.infixl(getToken(" ").retn(new Binary<Expr>() {
				@Override
				public final Expr map(Expr fun, Expr arg) {
					return ExprBuilder.application(fun,arg);
				}
			}), 10).build(pAtomic);
		refExpr.set(pExpr);
		return pExpr;
	}
	
}
