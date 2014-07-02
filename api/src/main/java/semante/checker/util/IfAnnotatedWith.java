package semante.checker.util;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IMaybe.just;
import static semante.pipeline.impl.IMaybe.nothing;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.Maybe;
import semante.pipeline.SimpleBinaryTree;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IfAnnotatedWith<ID> implements SimpleBinaryTree.Visitor<UnambiguousAnnotation<ID>, Maybe<UnambiguousAnnotation<ID>>> {
	
	String category;

	@Override
	public final Maybe<UnambiguousAnnotation<ID>> leaf(UnambiguousAnnotation<ID> arg0) {
		if (arg0.getCategory().equals(category))
			return just(arg0);
		else
			return nothing();
	}

	@Override
	public final Maybe<UnambiguousAnnotation<ID>> node(SimpleBinaryTree<UnambiguousAnnotation<ID>> arg1, SimpleBinaryTree<UnambiguousAnnotation<ID>> arg2) {
		return nothing();
	}
	
}