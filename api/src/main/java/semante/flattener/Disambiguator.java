package semante.flattener;

import java.util.List;

import lambdacalc.DeBruijn;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Either;
import semante.pipeline.Result;
import semante.pipeline.SimpleBinaryTree;

public interface Disambiguator<ID> {

	Either<DisambiguatorException, List<SimpleBinaryTree<UnambiguousAnnotation>>>
		disambiguate(BinaryTree<ID, Annotation> tree);
	
	Either<Result<ID>, List<DeBruijn>>
		disambiguateAndFlatten(BinaryTree<ID, Annotation> tree);

}
