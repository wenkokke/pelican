package semante.abusechecker;

import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.BinaryTree;

public interface AbuseChecker<ID> {

	public void check(BinaryTree<ID,UnambiguousAnnotation> tree)
			throws IllegalAnnotationException;

}
