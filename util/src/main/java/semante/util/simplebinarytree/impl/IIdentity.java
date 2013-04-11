package semante.util.simplebinarytree.impl;

import semante.util.SimpleBinaryTree;
import semante.util.impl.ISimpleBinaryTree;
import semante.util.simplebinarytree.Identity;

public class IIdentity<A> implements Identity<A> {
	
	@Override
	public SimpleBinaryTree<A> node(
			final SimpleBinaryTree<A> l,
			final SimpleBinaryTree<A> r) {
		return ISimpleBinaryTree.node(l,r);
	}
	
	@Override
	public SimpleBinaryTree<A> leaf(A a) {
		return ISimpleBinaryTree.leaf(a);
	}
}

