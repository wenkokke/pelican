package lambdacalc;

import static lombok.AccessLevel.PUBLIC;
import lambdacalc.impl.IType.IConstant;
import lambdacalc.impl.IType.IFunction;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal=true,level=PUBLIC)
public final class Types {
	
	static Type	E				= new IConstant("e");
	static Type	T				= new IConstant("t");
	static Type	ET				= new IFunction(E, T);
	static Type	EET				= new IFunction(E, ET);
	static Type	EEET			= new IFunction(E, EET);
	static Type	ET_T			= new IFunction(ET, T);
	static Type	ET_ET			= new IFunction(ET, ET);
	static Type	ET_ET__ET_ET	= new IFunction(ET_ET, ET_ET);
	
}
