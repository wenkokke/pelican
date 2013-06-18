package lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.Index;
import lambdacalc.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;

@Getter
@Wither
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IIndex implements Index {
	
	Integer	index;
	Type	type;
	
}
