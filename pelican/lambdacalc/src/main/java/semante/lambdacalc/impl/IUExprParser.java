package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.functors.Map;

import semante.lambdacalc.USymbol;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class IUExprParser extends AExprParser<USymbol> {
	
	@Override
	protected final Parser<Void> ignored() {
		return IGNORE;
	}
	
	@Override
	protected final Parser<?> tokenizer() {
		val identifier = (Parser<?>) Terminals.Identifier.TOKENIZER;
		return Parsers.or(TERMINALS.tokenizer(), identifier);
	}
	
	// composition
	
	@Override
	protected final Parser<Void> token(String name) {
		return TERMINALS.token(name).skipTimes(1);
	}
	
	@Override
	protected final Parser<Void> lambda() {
		return token("\\");
	}
	
	Terminals		TERMINALS	= Terminals.operators("\\",".","(",")"," ");
	Parser<Void>	IGNORE		= Parsers.<Void> always();
	
	// implementation	
	
	@Override
	protected final Parser<USymbol> symbolParser() {
		return Terminals.Identifier.PARSER.map(
			new Map<String,USymbol>() {
				@Override
				public final USymbol map(String name) {
					return new IUSymbol(name);
				}
			});
	}
}
