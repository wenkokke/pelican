
package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import org.codehaus.jparsec.Parser;

@Accessors(fluent = true)
@FieldDefaults(makeFinal=true,level=PRIVATE)
abstract class AParser<T> {
	
	abstract protected Parser<T> parser();
	abstract protected Parser<Void> ignored();
	abstract protected Parser<?> tokenizer();

	@Getter(lazy=true,value=PROTECTED)
	TypeParser<T> build = buildParser();
	
	private final Parser<T> buildParser() {
		return parser().from(tokenizer(),ignored());
	}
	
}
