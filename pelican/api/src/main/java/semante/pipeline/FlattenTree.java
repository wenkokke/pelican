package semante.pipeline;

import java.util.List;

import semante.lambdacalc.Expr;
import semante.lambdacalc.TSymbol;
import semante.lexicon.Word;
import semante.util.Either;

public interface FlattenTree<ID, T extends TSymbol> {
	Either<Result<ID>,List<Expr<T>>> flatten(BinaryTree<ID, Word<T>> tree);
}
