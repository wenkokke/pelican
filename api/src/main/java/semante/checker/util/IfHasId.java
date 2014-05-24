package semante.checker.util;

import semante.pipeline.BinaryTree;
import semante.pipeline.Maybe;
import static semante.pipeline.impl.IMaybe.just;
import static semante.pipeline.impl.IMaybe.nothing;

public class IfHasId<ID,A> implements BinaryTree.Visitor<ID,A,Maybe<ID>> {

	@Override
	public final Maybe<ID> leaf(A _) {
		return nothing();
	}

	@Override
	public final Maybe<ID> node(ID id, BinaryTree<ID, A> l, BinaryTree<ID, A> r) {
		return just(id);
	}

}
