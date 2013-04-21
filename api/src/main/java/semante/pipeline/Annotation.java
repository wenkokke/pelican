package semante.pipeline;

public interface Annotation {

	public interface Visitor<X> {
		X annotation(String text, String category);
	}
	
	<X> X accept(Visitor<X> v);
	
}
