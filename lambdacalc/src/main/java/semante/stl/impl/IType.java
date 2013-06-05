package semante.stl.impl;

import static lombok.AccessLevel.PUBLIC;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;
import semante.stl.Type;

@FieldDefaults(makeFinal=true,level=PUBLIC)
public abstract class IType implements Type {
	
	static Type E = new IConstant("e");
	static Type T = new IConstant("t");
	
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
	
	@Value
	public static class IIdentity implements Identity {
		
		@Override
		public Type constant(String name) {
			switch (name) {
			case "e" : return E;
			case "t" : return T;
			default  : return new IConstant(name); 
			}
		}
		
		@Override
		public Type function(Type arg0, Type arg1) {
			return new IFunction(arg0,arg1);
		}
	}
}
