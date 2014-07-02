package semante.checker.util;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IMaybe.nothing;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.Maybe;
import semante.pipeline.SimpleBinaryTree;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IfHoldsForDirectSubtree<ID> implements SimpleBinaryTree.Visitor<UnambiguousAnnotation<ID>, Maybe<UnambiguousAnnotation<ID>>> {
	
	@NonNull
	Direction direction;
	
	@NonNull
	SimpleBinaryTree.Visitor<UnambiguousAnnotation<ID>, Maybe<UnambiguousAnnotation<ID>>> delegate;

	@Override
	public final Maybe<UnambiguousAnnotation<ID>> leaf(UnambiguousAnnotation<ID> arg0) {
		return nothing();
	}

	@Override
	public final Maybe<UnambiguousAnnotation<ID>> node(SimpleBinaryTree<UnambiguousAnnotation<ID>> l, SimpleBinaryTree<UnambiguousAnnotation<ID>> r) {
		switch (direction) {
		case Left:
			return l.accept(delegate);
		case Right:
			return r.accept(delegate);
		default:
			throw new RuntimeException("Panic! Null is not a valid direction!");
		}
	}
}