package semante.pipeline;

import semante.util.Pair;
import semante.util.SimpleBinaryTree;

public interface Entailment {
	SimpleBinaryTree<Pair<String,String>> getText();
	SimpleBinaryTree<Pair<String,String>> getHypothesis();
	String getSubsumptions();
}
