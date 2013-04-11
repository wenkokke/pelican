package semante.util.binarytree;

import semante.pipeline.BinaryTree;
import semante.pipeline.BinaryTree.Visitor;

public interface Functor<A, B, C, D> extends Visitor<A, B, BinaryTree<C, D>> {
}