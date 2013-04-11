package semante.lambdacalc;


public interface Type {

	<X> X accept(Visitor<X> v);
	
	public interface Visitor<X> {
		
		X typeConstant(String name);
		X typeFunction(Type a, Type b);
		
	}
	
	public interface Identity extends Type.Visitor<Type> {}
	
	Type.Identity ResultType = new Type.Identity() {

		@Override
		public final Type typeFunction(Type a, Type b) {
			return b;
		}

		@Override
		public final Type typeConstant(String name) {
			throw new StlTypeError("Cannot resolve a type constant.");
		}
	};
	
	public interface Test extends Type.Visitor<Boolean> {
		public class True implements Test {
			@Override
			public Boolean typeConstant(String name) {
				return true;
			}
			@Override
			public Boolean typeFunction(Type from, Type to) {
				return true;
			}
		}
		public class False implements Test {
			@Override
			public Boolean typeConstant(String name) {
				return false;
			}
			@Override
			public Boolean typeFunction(Type from, Type to) {
				return false;
			}
		}
	}
	
	public class ErrorVisitor<T> implements Type.Visitor<T> {

		@Override
		public T typeConstant(String name) {
			throw new StlTypeError("illegal type "+name);
		}

		@Override
		public T typeFunction(Type f, Type arg) {
			throw new StlTypeError("illegal types "+f+" and "+arg);
		}
	}
}
