package lambdacalc;

import static lombok.AccessLevel.PUBLIC;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;

@FieldDefaults(makeFinal=true,level=PUBLIC)
public abstract class IType implements Type {
	
	static Type	E				= new IConstant("e");
	static Type	T				= new IConstant("t");
	static Type	ET				= new IFunction(E, T);
	static Type	EET				= new IFunction(E, ET);
	static Type	EEET			= new IFunction(E, EET);
	static Type	ET_T			= new IFunction(ET, T);
	static Type	ET_ET			= new IFunction(ET, ET);
	static Type	ET_ET__ET_ET	= new IFunction(ET_ET, ET_ET);
	
	@Value
	@EqualsAndHashCode(callSuper = false)
	public static final class IConstant extends IType {
		
		String name;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.constant(name);
		}
	}
	
	@Value
	@EqualsAndHashCode(callSuper = false)
	public static final class IFunction extends IType {
		
		Type fun, arg;

		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.function(fun, arg);
		}
	}
}
