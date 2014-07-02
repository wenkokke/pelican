package semante.disamb.impl;

import semante.disamb.AnnotationTreePrinter;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import static java.lang.String.format; 

public class IAnnotationTreePrinter<ID,A extends Annotation<ID>> implements AnnotationTreePrinter<ID,A> {

	@Override
	public String leaf(A ann) {
		return format("%s|%s", ann.getText(), ann.getCategory());
	}

	@Override
	public String node(ID _, BinaryTree<ID, A> treeL, BinaryTree<ID, A> treeR) {
		return format("[%s %s]", treeL.accept(this), treeR.accept(this));
	}

}
