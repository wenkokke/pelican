package semante.lambdacalc.impl;

import static lombok.AccessLevel.PUBLIC;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;
import semante.lambdacalc.Type;
import semante.lambdacalc.TypePrinter;
import semante.lambdacalc.util.ITypePrinter;

@FieldDefaults(makeFinal=true,level=PUBLIC)
abstract class IType implements Type {
	
	static Constant E = new Constant("e");
	static Constant T = new Constant("t");
	
	@Value
	@EqualsAndHashCode(callSuper = false)
	static final class Constant extends IType {
		String name;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.typeConstant(name);
		}
		@Override
		public final String toString() {
			return PRINTER.format(this);
		}
	}
	
	@Value
	@EqualsAndHashCode(callSuper = false)
	static final class Function extends IType {
		Type f, arg;

		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.typeFunction(f, arg);
		}
		@Override
		public final String toString() {
			return PRINTER.format(this);
		}
	}
	
	@Value
	public static final class Identity implements Type.Identity {

		@Override
		public Type typeConstant(String name) {
			if (name.equals("e")) {
				return E;
			} else if (name.equals("t")) {
				return T;
			} else {
				return new Constant(name);
			}
		}

		@Override
		public Type typeFunction(Type from, Type to) {
			return new Function(from,to);
		}
	}
	
	private static TypePrinter PRINTER = new ITypePrinter();
}
