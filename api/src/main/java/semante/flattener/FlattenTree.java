package semante.flattener;

import java.util.List;

import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Either;
import semante.pipeline.Result;
import lambdacalc.DeBruijn;

public interface FlattenTree<ID> {
	
	Either<Result<ID>, List<DeBruijn>> flatten(BinaryTree<ID, Annotation> tree);
	
}
