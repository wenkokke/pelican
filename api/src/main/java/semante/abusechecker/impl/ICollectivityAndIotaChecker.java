package semante.abusechecker.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.DeBruijn;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.abusechecker.CollectivityAndIotaChecker;
import semante.abusechecker.IllegalAnnotationException;
import semante.flattener.UnambiguousAnnotation;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.SimpleBinaryTreeBuilder;

import com.google.common.base.Function;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ICollectivityAndIotaChecker implements CollectivityAndIotaChecker {

	Function<DeBruijn, DeBruijn> reducer;
	SimpleBinaryTreeBuilder<UnambiguousAnnotation> builder;
	
	@Override
	public void check(SimpleBinaryTree<UnambiguousAnnotation> tree)
			throws IllegalAnnotationException {

	}
	
}
