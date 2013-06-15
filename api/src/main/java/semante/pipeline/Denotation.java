package semante.pipeline;

import lambdacalc.DeBruijn;

public interface Denotation extends Annotation {
	
	public String getText();
	public String getCategory();
	public DeBruijn getDenotation();
	
	public interface Visitor<X> {
		X denotation(String text, String category, DeBruijn denotation);
	}
	
	<X> X accept(Visitor<X> v);
	
}
