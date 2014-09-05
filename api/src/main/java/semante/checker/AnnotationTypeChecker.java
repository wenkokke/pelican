package semante.checker;

import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;

public interface AnnotationTypeChecker<ID> {

	void checkTypeWithError(BinaryTree<ID, Annotation<ID>> tree);
	boolean checkType(BinaryTree<ID, Annotation<ID>> tree);
	
}
