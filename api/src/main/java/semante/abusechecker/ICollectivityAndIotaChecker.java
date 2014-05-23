package semante.abusechecker;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.DeBruijn;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.BinaryTreeBuilder;

import com.google.common.base.Function;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ICollectivityAndIotaChecker<ID> implements AbuseChecker<ID> {

	Function<DeBruijn, DeBruijn> reducer;
	BinaryTreeBuilder<ID,UnambiguousAnnotation> builder;
	
	@Override
	public void check(BinaryTree<ID,UnambiguousAnnotation> tree)
			throws IllegalAnnotationException {

	}
	
}
