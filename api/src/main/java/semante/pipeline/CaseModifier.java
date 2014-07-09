package semante.pipeline;

public interface CaseModifier {
	<ID> BinaryTree<ID, Annotation<ID>> apply (BinaryTree<ID, Annotation<ID>> tree);
}
