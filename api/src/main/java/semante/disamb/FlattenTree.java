package semante.disamb;

import java.util.List;

import lambdacalc.DeBruijn;
import semante.pipeline.BinaryTree;

public interface FlattenTree<ID> {

	DeBruijn flatten(BinaryTree<ID,UnambiguousAnnotation<ID>> tree);
	
	List<DeBruijn> flattenAll(List<BinaryTree<ID,UnambiguousAnnotation<ID>>> trees);
	
}
