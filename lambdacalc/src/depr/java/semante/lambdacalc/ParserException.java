package semante.lambdacalc;

import org.codehaus.jparsec.error.ParseErrorDetails;

import lombok.EqualsAndHashCode;
import lombok.experimental.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ParserException extends RuntimeException {
	
	ParseErrorDetails errorDetails;

}
