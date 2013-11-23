package semante;

import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Value;

import com.google.common.base.Joiner;

@Value
@RequiredArgsConstructor
public final class IEntailment implements Entailment {

	SimpleBinaryTree<Pair<String,String>> text;
	SimpleBinaryTree<Pair<String,String>> hypothesis;
	String subsumptions;
	
	public IEntailment(
			final SimpleBinaryTree<Pair<String,String>> txt,
			final SimpleBinaryTree<Pair<String,String>> hyp,
			final String[] subs) {
			this(txt, hyp, Joiner.on('\n').join(subs));
		}
	
	public IEntailment(
		final SimpleBinaryTree<Pair<String,String>> txt,
		final SimpleBinaryTree<Pair<String,String>> hyp) {
		this(txt, hyp, "");
	}
}
