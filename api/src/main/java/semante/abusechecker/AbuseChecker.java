package semante.abusechecker;

import semante.flattener.UnambiguousAnnotation;
import semante.pipeline.SimpleBinaryTree;

public interface AbuseChecker {

	public void check(SimpleBinaryTree<UnambiguousAnnotation> tree)
			throws IllegalAnnotationException;

}
