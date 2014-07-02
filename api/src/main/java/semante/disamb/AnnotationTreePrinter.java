package semante.disamb;

import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;

public interface AnnotationTreePrinter<ID,A extends Annotation<ID>> extends BinaryTree.Visitor<ID, A, String> {

}
