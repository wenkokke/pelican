package semante.disamb;

import lambdacalc.DeBruijn;
import lambdacalc.Type;
import semante.pipeline.Annotation;

public interface UnambiguousAnnotation extends Annotation {

	public Type getType();
	public DeBruijn getMeaning();

	public interface Visitor<X> {
		X annotation(String text, String category, DeBruijn meaning);
	}

	<X> X accept(UnambiguousAnnotation.Visitor<X> v);
}