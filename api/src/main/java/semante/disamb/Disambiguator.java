package semante.disamb;

import java.util.List;

import lambdacalc.DeBruijn;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Either;
import semante.pipeline.Result;

public interface Disambiguator<ID> {

	Either<DisambiguatorException, List<BinaryTree<ID,UnambiguousAnnotation<ID>>>>
		disambiguate(BinaryTree<ID, Annotation<ID>> tree);
	
	Either<Result<ID>, List<DeBruijn>>
		disambiguateAndFlatten(BinaryTree<ID, Annotation<ID>> tree);

}
