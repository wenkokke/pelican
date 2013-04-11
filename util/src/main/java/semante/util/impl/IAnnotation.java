package semante.util.impl;

import semante.pipeline.Annotation;
import lombok.experimental.Value;

@Value
public final class IAnnotation implements Annotation {
	
	String text;
	String annotation;
	
	@Override
	public final <X> X accept(Visitor<X> v) {
		return v.annotation(text, annotation);
	}
}
