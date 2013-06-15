package lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class ISymbol implements Symbol {
	
	String	name;
	Type	type;
	
}
