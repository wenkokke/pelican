package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.Delegate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Type;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
public enum ITypes implements Type {
	
	E(IType.E),									// entity
	T(IType.T),									// truth-value
	ET(new IType.Function(IType.E,IType.T)),	// predicate
	ET_T(new IType.Function(ET,IType.T)),		// quantifier
	ET_E(new IType.Function(ET,IType.E)), 		// iota
	ET_ET(new IType.Function(ET,ET)),			// modifier
	ET_ET_T(new IType.Function(ET,ET_T));		// gq
	
	@Delegate
	Type type;
	
}
