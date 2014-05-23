package semante.flattener;

import lambdacalc.DeBruijn;
import semante.pipeline.Annotation;

public interface UnambiguousAnnotation extends Annotation {

	public DeBruijn getMeaning();

	public interface Visitor<X> {
		X annotation(String text, String category, DeBruijn meaning);
	}

	<X> X accept(UnambiguousAnnotation.Visitor<X> v);
}