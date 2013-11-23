package semante;

import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.impl.IPair;
import semante.pipeline.impl.ISimpleBinaryTree;

public class ATest {

	public ATest() {
		super();
	}

	protected final SimpleBinaryTree<Pair<String,String>> _(SimpleBinaryTree<Pair<String,String>> l, SimpleBinaryTree<Pair<String,String>> r) {
		return ISimpleBinaryTree.node(l, r);
	}

	protected final SimpleBinaryTree<Pair<String,String>> word(String category, String text) {
		return ISimpleBinaryTree.leaf(IPair.pair(text, category));
	}

}