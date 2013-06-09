package lambdacalc;

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


/**
 * Implementation of the expression parser {@link TExprParser}. It parses
 * expressions of the form
 * <tt>\P:(et)t.\Q:(et)t.\A:et.AND:ttt (P:(et)t A:et) (Q:(et)t A:et)</tt>.
 * 
 * @author Pepijn Kokke
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IExprParser implements ExprParser {
	
	@Override
	public final Expr parse(final String expr) {
		return parseExpr(expr);
	}

	@Override
	public final Expr parseExpr(String expr) {
		return ExprParser.parse(expr);
	}

	@Override
	public final Symbol parseSymbol(String symbol) {
		return SymbolParser.parse(symbol);
	}

	@Override
	public final Type parseType(String type) {
		return TypeParser.parse(type);
	}
	
	static final Parser<Void>   Ignored       = Parsers.<Void> always();
	
	static final Terminals      ExprOperators = Terminals.operators("e","t","\\",".","(",")"," ",":");
	static final Parser<?>      ExprTokenizer = Parsers.or((Parser<?>) ExprOperators.tokenizer(),(Parser<?>) Terminals.Identifier.TOKENIZER);
	static final ExprBuilder  	ExprBuilder   = new IExprBuilder();
	static final Parser<Expr>   ExprParser    = exprParser().from(ExprTokenizer, Ignored); 
	
	static final TypeIdentity   TypeBuilder   = new ITypeIdentity();
	static final Parser<Type>   TypeParser    = typeParser().from(ExprTokenizer, Ignored);
	
	static final Parser<Symbol> SymbolParser  = symbolParser().from(ExprTokenizer, Ignored);
	
	private static final Parser<Void> getToken(String... names) {
		return ExprOperators.token(names).skipTimes(1);
	}
	
	/**
	 * @return a parser for Type
	 */
	private static final Parser<Type> typeParser() {
		val refType = Parser.<Type> newReference();
		val pEType  = getToken("e").retn((Type) IType.E);
		val pTType  = getToken("t").retn((Type) IType.T);
		val pAtom   = Parsers.or(pEType,pTType,refType.lazy().between(getToken("("), getToken(")"))); 
		val pType   = new OperatorTable<Type>()
		    .infixr(Parsers.always().retn(new Binary<Type>() {
		        @Override
				public final Type map(Type left, Type right) {
				    return TypeBuilder.function(left, right);
				}
			}),10).build(pAtom);
		refType.set(pType);
		return pType;
	}
	
	/**
	 * @return a parser for Symbol
	 */
	protected static final Parser<Symbol> symbolParser() {
		return Parsers.sequence(
			Terminals.Identifier.PARSER, getToken(":"), typeParser(),
				new Map3<String,Void,Type,Symbol>() {
					@Override
					public final Symbol map(String s, Void _, Type t) {
						return new ISymbol(s,t);
					}
				});
	}
	
	/**
	 * @return a parser for Expr
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
			pAtom = Parsers.or(
					pVariable, pAbstraction,
				refExpr.lazy().between(getToken("("), getToken(")")));
		
		final Parser<Expr>
			pExpr = new OperatorTable<Expr>()
			.infixl(getToken(" ").retn(new Binary<Expr>() {
				@Override
				public final Expr map(Expr fun, Expr arg) {
					return ExprBuilder.application(fun,arg);
				}
			}), 10).build(pAtom);
		refExpr.set(pExpr);
		return pExpr;
	}
	
}
