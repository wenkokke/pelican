package semante.disamb.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.DeBruijn;
import lombok.Delegate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.Annotation;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public class IUnambiguousAnnotation implements UnambiguousAnnotation {
	
	@Delegate
	Annotation delegate;
	
	@Getter
	DeBruijn meaning;

	@Override
	public final <X> X accept(Visitor<X> v) {
		return v.annotation(delegate.getText(), delegate.getCategory(), meaning);
	}
	
}
