package semante.pipeline;

public interface BinaryTree<A,B> {
	
	public interface Visitor<A,B,X> {
		X node(A a, BinaryTree<A,B> left, BinaryTree<A,B> right);
		X leaf(B b);
	}
	
	<X> X accept(Visitor<A,B,X> v);
}
