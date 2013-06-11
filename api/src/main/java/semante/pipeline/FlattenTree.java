package semante.pipeline;

import java.util.List;

import lambdacalc.DeBruijn;
import semante.lexicon.Word;

public interface FlattenTree<ID> {
	Either<Result<ID>, List<DeBruijn>> flatten(BinaryTree<ID, Word> tree);
}
