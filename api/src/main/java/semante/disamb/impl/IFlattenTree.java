package semante.disamb.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.DeBruijnBuilder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.disamb.FlattenTree;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.BinaryTree;

import com.google.common.collect.ImmutableList;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IFlattenTree<ID> implements FlattenTree<ID> {

	DeBruijnBuilder builder;
	
	@Override
	public final DeBruijn flatten(BinaryTree<ID,UnambiguousAnnotation> tree) {
		return tree.accept(new Helper());
	}

	@Override
	public List<DeBruijn> flattenAll(
			List<BinaryTree<ID,UnambiguousAnnotation>> trees) {
		val result = ImmutableList.<DeBruijn> builder();
		for (val tree : trees) {
			result.add(flatten(tree));
		}
		return result.build();
	}
	
	private final class Helper implements BinaryTree.Visitor<ID, UnambiguousAnnotation, DeBruijn> {

		@Override
		public final DeBruijn leaf(UnambiguousAnnotation ua) {
			return ua.getMeaning();
		}

		@Override
		public final DeBruijn node(ID _, BinaryTree<ID,UnambiguousAnnotation> l, BinaryTree<ID,UnambiguousAnnotation> r) {
			return builder.application(l.accept(this), r.accept(this));
		}
		
	}

}
