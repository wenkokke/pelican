package semante.util.impl;

import com.google.common.base.Function;

import lombok.EqualsAndHashCode;
import lombok.experimental.Value;
import semante.pipeline.BinaryTree;
import semante.util.binarytree.Functor;
import semante.util.binarytree.impl.IFunctor;

public final class IBinaryTree {
	
	public static final <A,B> BinaryTree<A,B> leaf(
			final B value) {
		return new ILeaf<A,B>(value);
	}

	public static final <A,B> BinaryTree<A,B> node(
			final A value,
			final BinaryTree<A,B> left,
			final BinaryTree<A,B> right) {
		return new INode<A,B>(value, left, right);
	}
	
	public static final <A, B, C, D> Functor<A, B, C, D> functor(
			final Function<A, C> a2C,
			final Function<B, D> b2D) {
		return new IFunctor<A, B, C, D>(a2C, b2D);
	}
	
	@Value
	@EqualsAndHashCode(callSuper = false)
	private static final class ILeaf<A, B> implements BinaryTree<A, B> {

		B value;

		@Override
		public final <X> X accept(final Visitor<A, B, X> v) {
			return v.leaf(value);
		}
	}

	@Value
	@EqualsAndHashCode(callSuper = false)
	private static final class INode<A,B> implements BinaryTree<A,B> {
		
		A value;
		BinaryTree<A,B> left;
		BinaryTree<A,B> right;

		@Override
		public <X> X accept(final BinaryTree.Visitor<A,B,X> v) {
			return v.node(value, left, right);
		}
	}
	
	private IBinaryTree() {}
}
