package semante.pipeline;

import lombok.Getter;

public abstract class Pipeline {
	
	public abstract <ID> Result<ID> prove(
			final BinaryTree<ID, Annotation> text,
			final BinaryTree<ID, Annotation> hypothesis,
			final String subsumptions) throws Exception;
	
	@Getter
	protected static Pipeline instance;
}
