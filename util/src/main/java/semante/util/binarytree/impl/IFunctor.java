package semante.util.binarytree.impl;

import lombok.experimental.Value;
import semante.pipeline.BinaryTree;
import semante.util.binarytree.Functor;
import semante.util.impl.IBinaryTree;

import com.google.common.base.Function;

@Value
public final class IFunctor<A,B,C,D> implements Functor<A,B,C,D> {
	
	Function<A,C> a2C;
	Function<B,D> b2D;
			
	@Override
	public final BinaryTree<C,D> node(
			final A a,
			final BinaryTree<A, B> l,
			final BinaryTree<A, B> r) {
		return IBinaryTree.node(a2C.apply(a),l.accept(this),r.accept(this));
	}
	@Override
	public final BinaryTree<C,D> leaf(
			final B b) {
		return IBinaryTree.leaf(b2D.apply(b));
	}
}
