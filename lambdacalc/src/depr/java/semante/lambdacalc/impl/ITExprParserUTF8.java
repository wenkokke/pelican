package semante.lambdacalc.impl;

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

import semante.lambdacalc.ExprParser;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;
import semante.lambdacalc.USymbol;
import semante.lambdacalc.impl.IType.Function;

/**
 * Implementation of the expression parser {@link TExprParser}. It parses
 * expressions of the form
 * <tt>λP:(et)t.λQ:(et)t.λA:et.AND:ttt (P:(et)t A:et) (Q:(et)t A:et)</tt>.
 * 
 * @author Pepijn Kokke
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
final class ITExprParserUTF8 extends AExprParser<TSymbol> implements ExprParser<TSymbol> {
	
	// TODO: ITExprParser does not depend on ATypeParser, while it
	// should--however, a naive implementation results in a collision with the
	// "(" and ")" tokens.
	
	@Override
	protected final Parser<Void> ignored() {
		return IGNORE;
	}
	
	@Override
	protected final Parser<?> tokenizer() {
		val tokIdentifier = (Parser<?>) Terminals.Identifier.TOKENIZER;
		return Parsers.or(TERMINALS.tokenizer(), tokIdentifier);
	}
	
	// composition
	
	@Override
	protected final Parser<Void> token(String name) {
		return TERMINALS.token(name).skipTimes(1);
	}
	
	@Override
	protected final Parser<Void> lambda() {
		return token("λ");
	}
	
	Terminals		TERMINALS	= Terminals.operators("e","t","λ",".","(",")"," ",":");
	Parser<Void>	IGNORE		= Parsers.<Void> always();
	
	// implementation

	
	private final Parser<Type> typeParser() {
		val REF = Parser.<Type> newReference();
		val E_TYPE = token("e").retn((Type) IType.E);
		val T_TYPE = token("t").retn((Type) IType.T);
		val ATOMIC = Parsers.or(E_TYPE,T_TYPE,REF.lazy().between(token("("), token(")"))); 
		val PARSER = new OperatorTable<Type>()
				.infixr(Parsers.always().retn(new Binary<Type>() {
					@Override
					public final Type map(Type left, Type right) {
						return new Function(left, right);
					}
				}),10)
				.build(ATOMIC);
		REF.set(PARSER);
		return PARSER;
	}
	
	@Override
	protected final Parser<TSymbol> symbolParser() {
		val pSymbol = Terminals.Identifier.PARSER.map(
			new Map<String,USymbol>() {
				@Override
				public final USymbol map(String name) {
					return new IUSymbol(name);
				}
			});
		return Parsers.sequence(pSymbol, token(":"), typeParser(), new Map3<USymbol,Void,Type,TSymbol>() {
			@Override
			public final TSymbol map(USymbol s, Void _, Type t) {
				return new ITSymbol(s.getName(),t);
			}
		});
	}
}
