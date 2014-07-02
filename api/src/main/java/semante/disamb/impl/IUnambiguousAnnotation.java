package semante.disamb.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.DeBruijn;
import lambdacalc.Type;
import lombok.Delegate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.Annotation;
import semante.pipeline.impl.IAnnotation;

@ToString
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public class IUnambiguousAnnotation<ID> implements UnambiguousAnnotation<ID> {
	
	@Delegate
	Annotation<ID> delegate;
	
	@Getter
	DeBruijn meaning;
	
	@Getter
	Type type;

	@Override
	public final <X> X accept(Visitor<X,ID> v) {
		return v.annotation(delegate.getId(),delegate.getText(), delegate.getCategory(), meaning);
	}
	
	public IUnambiguousAnnotation(ID id, String text, String category, DeBruijn meaning, Type type) {
		this(new IAnnotation<ID>(id, text, category), meaning, type);
	}
	
}
