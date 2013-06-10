package semante.pipeline;

import java.util.List;

import lambdacalc.Type;
import semante.lexicon.Word;
import semante.pipeline.util.Pair;

public interface TreePrinter<ID> extends BinaryTree.Visitor<ID, Word, Pair<String,List<Type>>> {

}
