package lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.Type;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

public abstract class IType implements Type {
	
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IConstant extends IType {
		
		String	name;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.constant(name);
		}
	}
	
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IFunction extends IType {
		
		Type	fun;
		Type	arg;

		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.function(fun, arg);
		}
	}
}
