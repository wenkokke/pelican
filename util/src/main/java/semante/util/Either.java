package semante.util;

public interface Either<A,B> {
	
	public interface Visitor<A,B,X> {
		X left(A a);
		X right(B b);
	}
	
	<X> X accept(Visitor<A,B,X> v);
	
	boolean isLeft();
	boolean isRight();
	A getLeft();
	B getRight();
}
