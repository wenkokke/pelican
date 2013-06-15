package semante.pipeline;

import java.util.List;

import lambdacalc.DeBruijn;

public interface FlattenTree<ID> {
	
	Either<Result<ID>, List<DeBruijn>> flatten(BinaryTree<ID, Annotation> tree);
	
}
