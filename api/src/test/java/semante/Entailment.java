package semante;

import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;


public interface Entailment {
	SimpleBinaryTree<Pair<String,String>> getText();
	SimpleBinaryTree<Pair<String,String>> getHypothesis();
	String getSubsumptions();
}
