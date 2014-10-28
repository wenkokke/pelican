package semante.checker.util;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IMaybe.nothing;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.Maybe;
import semante.pipeline.SimpleBinaryTree;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IfHoldsForSubtree<ID> implements SimpleBinaryTree.Visitor<UnambiguousAnnotation<ID>, Maybe<SimpleBinaryTree<UnambiguousAnnotation<ID>>>> {
	
	@NonNull
	SimpleBinaryTree.Visitor<UnambiguousAnnotation<ID>, Maybe<SimpleBinaryTree<UnambiguousAnnotation<ID>>>> delegate;

	@Override
	public final Maybe<SimpleBinaryTree<UnambiguousAnnotation<ID>>> leaf(UnambiguousAnnotation<ID> arg0) {
		return nothing();
	}

	@Override
	public final Maybe<SimpleBinaryTree<UnambiguousAnnotation<ID>>> node(
			SimpleBinaryTree<UnambiguousAnnotation<ID>> l, 
			SimpleBinaryTree<UnambiguousAnnotation<ID>> r) {
		
		val checkLeft = l.accept(delegate);
		if (checkLeft.isJust())
		{
			return checkLeft;
		}
		
		val checkRight = r.accept(delegate);
		if (checkRight.isJust())
		{
			
			return checkRight;
		}
		
		return nothing();
	}
}