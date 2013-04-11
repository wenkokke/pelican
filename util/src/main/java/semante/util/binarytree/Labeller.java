package semante.util.binarytree;

import java.io.Serializable;

import semante.pipeline.BinaryTree;
import semante.util.SimpleBinaryTree;

public interface Labeller extends Serializable {
	
	<B> BinaryTree<Integer,B> label(final SimpleBinaryTree<B> tree);
	
}
