package semante.pipeline;

public interface Entailment {
	SimpleBinaryTree<Pair<String,String>> getText();
	SimpleBinaryTree<Pair<String,String>> getHypothesis();
	String getSubsumptions();
}
