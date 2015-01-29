package semante.checker.util;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IMaybe.nothing;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Maybe;
import semante.pipeline.SimpleBinaryTree;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IfHoldsForSubtree<ID,X> implements BinaryTree.Visitor<ID,UnambiguousAnnotation<ID>, Maybe<X>> {
	
	@NonNull
	BinaryTree.Visitor<ID,UnambiguousAnnotation<ID>, Maybe<X>> delegate;

	@Override
	public final Maybe<X> leaf(UnambiguousAnnotation<ID> arg0) {
		return nothing();
	}

	@Override
	public final Maybe<X> node(
			ID id,
			BinaryTree<ID,UnambiguousAnnotation<ID>> l, 
			BinaryTree<ID,UnambiguousAnnotation<ID>> r) {
		
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
		
		val recurseLeft = l.accept(this);
		if (recurseLeft.isJust())
		{
			return recurseLeft;
		}
		
		val recurseRight = r.accept(this);
		if (recurseRight.isJust())
		{
			return recurseRight;
		}
		
		return nothing();
	}
}