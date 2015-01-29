package semante.checker.util;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IMaybe.nothing;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.BinaryTreeBuilder;
import semante.pipeline.Maybe;


@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IfAnyForTree<ID> implements BinaryTree.Visitor<ID,UnambiguousAnnotation<ID>, Maybe<BinaryTree<ID,UnambiguousAnnotation<ID>>>> {
	
	@NonNull
	BinaryTreeBuilder<ID,UnambiguousAnnotation<ID>> builder;
	
	@NonNull
	List<BinaryTree.Visitor<ID,UnambiguousAnnotation<ID>, Maybe<BinaryTree<ID,UnambiguousAnnotation<ID>>>>> delegates;
	
	public final Maybe<BinaryTree<ID,UnambiguousAnnotation<ID>>> tryAll(BinaryTree<ID,UnambiguousAnnotation<ID>> tree) {
		for (val delegate : delegates) {
			val here = tree.accept(delegate);
			if (here.isJust()) return here;
		}
		return nothing();
	}
	
	@Override
	public Maybe<BinaryTree<ID,UnambiguousAnnotation<ID>>> leaf(UnambiguousAnnotation<ID> arg0) {
		return tryAll(builder.leaf(arg0));
	}

	@Override
	public Maybe<BinaryTree<ID,UnambiguousAnnotation<ID>>> node(
			ID id,
			BinaryTree<ID,UnambiguousAnnotation<ID>> arg0,
			BinaryTree<ID,UnambiguousAnnotation<ID>> arg1) {
		return tryAll(builder.node(id, arg0, arg1));
	}

}
