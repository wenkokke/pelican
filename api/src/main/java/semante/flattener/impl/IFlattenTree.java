package semante.flattener.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.DeBruijnBuilder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.flattener.FlattenTree;
import semante.flattener.UnambiguousAnnotation;
import semante.pipeline.SimpleBinaryTree;

import com.google.common.collect.ImmutableList;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IFlattenTree implements FlattenTree {

	DeBruijnBuilder builder;
	
	@Override
	public final DeBruijn flatten(SimpleBinaryTree<UnambiguousAnnotation> tree) {
		return tree.accept(new Helper());
	}

	@Override
	public List<DeBruijn> flattenAll(
			List<SimpleBinaryTree<UnambiguousAnnotation>> trees) {
		val result = ImmutableList.<DeBruijn> builder();
		for (val tree : trees) {
			result.add(flatten(tree));
		}
		return result.build();
	}
	
	private final class Helper implements SimpleBinaryTree.Visitor<UnambiguousAnnotation, DeBruijn> {

		@Override
		public final DeBruijn leaf(UnambiguousAnnotation ua) {
			return ua.getMeaning();
		}

		@Override
		public final DeBruijn node(SimpleBinaryTree<UnambiguousAnnotation> l, SimpleBinaryTree<UnambiguousAnnotation> r) {
			return builder.application(l.accept(this), r.accept(this));
		}
		
	}

}
