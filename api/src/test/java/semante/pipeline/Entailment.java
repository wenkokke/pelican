package semante.pipeline;

import semante.pipeline.util.Pair;
import semante.pipeline.util.SimpleBinaryTree;

public interface Entailment {
	SimpleBinaryTree<Pair<String,String>> getText();
	SimpleBinaryTree<Pair<String,String>> getHypothesis();
	String getSubsumptions();
}
