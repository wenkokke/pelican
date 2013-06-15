package semante.pipeline.impl;

import lambdacalc.DeBruijn;
import lombok.experimental.Value;
import semante.pipeline.Annotation;
import semante.pipeline.Denotation;

@Value
public final class IDenotation implements Denotation {
	
	String		text, category;
	DeBruijn	denotation;

	@Override
	public final <X> X accept(final Annotation.Visitor<X> v) {
		return v.annotation(text, category);
	}

	@Override
	public final <X> X accept(final Denotation.Visitor<X> v) {
		return v.denotation(text, category, denotation);
	}

}
