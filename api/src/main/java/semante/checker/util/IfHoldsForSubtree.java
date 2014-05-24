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
public final class IfHoldsForSubtree implements SimpleBinaryTree.Visitor<UnambiguousAnnotation, Maybe<UnambiguousAnnotation>> {
	
	@NonNull
	Direction direction;
	
	@NonNull
	SimpleBinaryTree.Visitor<UnambiguousAnnotation, Maybe<UnambiguousAnnotation>> delegate;

	@Override
	public final Maybe<UnambiguousAnnotation> leaf(UnambiguousAnnotation arg0) {
		return nothing();
	}

	@Override
	public final Maybe<UnambiguousAnnotation> node(SimpleBinaryTree<UnambiguousAnnotation> l, SimpleBinaryTree<UnambiguousAnnotation> r) {
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