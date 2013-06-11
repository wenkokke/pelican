package lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.Type;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public abstract class IType implements Type {
	
	@Value
	@EqualsAndHashCode(callSuper=false)
	public static final class IConstant extends IType {
		
		String name;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.constant(name);
		}
	}
	
	@Value
	@EqualsAndHashCode(callSuper=false)
	public static final class IFunction extends IType {
		
		Type fun, arg;

		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.function(fun, arg);
		}
	}
}
