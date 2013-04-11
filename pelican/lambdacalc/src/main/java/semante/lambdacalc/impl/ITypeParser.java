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

import semante.lambdacalc.Type;
import semante.lambdacalc.impl.IType.Function;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
public final class ITypeParser extends ATypeParser {
	
	@Override
	protected final Parser<Type> parser() {
		return PARSER;
	}
	
	@Override
	protected final Parser<Void> ignored() {
		return IGNORE;
	}
	
	@Override
	protected final Parser<?> tokenizer() {
		return TERMINALS.tokenizer();
	}
	
	// implementation:

	static Terminals TERMINALS = Terminals.operators("e", "t", "(", ")");
	static Parser<Void> IGNORE = Parsers.<Void> always();
	static Parser<Type> PARSER = getParser();
	
	private static final Parser<Void> getToken(String... names) {
		return TERMINALS.token(names).skipTimes(1);
	}
	
	private static final Parser<Type> getParser() {
		val REF = Parser.<Type> newReference();
		val E_TYPE = getToken("e").retn((Type) IType.E);
		val T_TYPE = getToken("t").retn((Type) IType.T);
		val ATOMIC = Parsers.or(E_TYPE,T_TYPE,REF.lazy().between(getToken("("), getToken(")"))); 
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
}
