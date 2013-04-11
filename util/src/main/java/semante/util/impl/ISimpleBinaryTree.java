package semante.util.impl;

import lombok.experimental.Value;
import semante.util.SimpleBinaryTree;

public final class ISimpleBinaryTree {

	public static final <A> SimpleBinaryTree<A> leaf(final A a) {
		return new ILeaf<A>(a);
	}

	public static final <A> SimpleBinaryTree<A> node(
			final SimpleBinaryTree<A> left,
			final SimpleBinaryTree<A> right) {
		return new INode<A>(left, right);
	}

	@Value
	private static final class ILeaf<A> implements SimpleBinaryTree<A> {
		A value;
	
		@Override
		public final <X> X accept(final Visitor<A, X> v) {
			return v.leaf(value);
		} 
	}
	
	@Value
	private static final class INode<A> implements SimpleBinaryTree<A> {
		
		SimpleBinaryTree<A> left;
		SimpleBinaryTree<A> right;

		@Override
		public final <X> X accept(final SimpleBinaryTree.Visitor<A, X> v) {
			return v.node(left, right);
		} 
	}
	
	private ISimpleBinaryTree() {}
}
