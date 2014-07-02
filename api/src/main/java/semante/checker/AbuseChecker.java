package semante.checker;

import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.BinaryTree;

public interface AbuseChecker<ID> {

	public void check(BinaryTree<ID,UnambiguousAnnotation<ID>> tree)
			throws IllegalAnnotationException;

}
