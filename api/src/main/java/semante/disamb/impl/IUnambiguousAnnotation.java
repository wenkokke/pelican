package semante.disamb.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.DeBruijn;
import lambdacalc.Type;
import lombok.Delegate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.Annotation;
import semante.pipeline.impl.IAnnotation;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public class IUnambiguousAnnotation implements UnambiguousAnnotation {
	
	@Delegate
	Annotation delegate;
	
	@Getter
	DeBruijn meaning;
	
	@Getter
	Type type;

	@Override
	public final <X> X accept(Visitor<X> v) {
		return v.annotation(delegate.getText(), delegate.getCategory(), meaning);
	}
	
	public IUnambiguousAnnotation(String text, String category, DeBruijn meaning, Type type) {
		this(new IAnnotation(text, category), meaning, type);
	}
	
}
