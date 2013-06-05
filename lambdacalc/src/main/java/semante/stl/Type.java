package semante.stl;



public interface Type {

	<X> X accept(Visitor<X> v);
	
	public interface Visitor<X> {
		X constant(String name);
		X function(Type a, Type b);
	}

	public interface Identity extends Visitor<Type> {	
	}
}
