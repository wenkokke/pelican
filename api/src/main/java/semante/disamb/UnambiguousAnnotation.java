package semante.disamb;

import lambdacalc.DeBruijn;
import lambdacalc.Type;
import semante.pipeline.Annotation;

public interface UnambiguousAnnotation<ID> extends Annotation<ID> {

	public Type getType();
	public DeBruijn getMeaning();

	public interface Visitor<X,ID> {
		X annotation(ID id, String text, String category, DeBruijn meaning);
	}

	<X> X accept(UnambiguousAnnotation.Visitor<X,ID> v);
}