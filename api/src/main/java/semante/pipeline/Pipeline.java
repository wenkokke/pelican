package semante.pipeline;

import java.util.List;

public interface Pipeline {
	
	<ID> Result<ID> prove(
			final BinaryTree<ID, Annotation> text,
			final BinaryTree<ID, Annotation> hypothesis,
			final String subsumptions) throws Exception;
	
	List<Category> getCategories();
  
}
