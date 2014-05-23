package semante.flattener;

import java.util.List;

import lambdacalc.DeBruijn;
import semante.pipeline.SimpleBinaryTree;

public interface FlattenTree {

	DeBruijn flatten(SimpleBinaryTree<UnambiguousAnnotation> tree);
	
	List<DeBruijn> flattenAll(List<SimpleBinaryTree<UnambiguousAnnotation>> trees);
	
}
