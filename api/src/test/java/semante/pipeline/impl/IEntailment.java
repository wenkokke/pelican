package semante.pipeline.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Value;
import semante.pipeline.Entailment;
import semante.pipeline.util.Pair;
import semante.pipeline.util.SimpleBinaryTree;

@Value
@RequiredArgsConstructor
public final class IEntailment implements Entailment {

	SimpleBinaryTree<Pair<String,String>> text;
	SimpleBinaryTree<Pair<String,String>> hypothesis;
	String subsumptions;
	
	
	public IEntailment(
		final SimpleBinaryTree<Pair<String,String>> txt,
		final SimpleBinaryTree<Pair<String,String>> hyp) {
		this(txt, hyp, "");
	}
}
