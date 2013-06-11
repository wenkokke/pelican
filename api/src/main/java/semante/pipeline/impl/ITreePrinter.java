package semante.pipeline.impl;

import static com.google.common.collect.Lists.transform;
import static java.lang.String.format;

import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lambdacalc.Type;
import lombok.val;
import lombok.experimental.Value;
import semante.lexicon.Word;
import semante.pipeline.BinaryTree;
import semante.pipeline.Pair;
import semante.pipeline.TreePrinter;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

@Value
public final class ITreePrinter<ID> implements TreePrinter<ID> {

	STL stl;
	
	@Override
	public final Pair<String,List<Type>> node(
			final ID id,
			final BinaryTree<ID, Word> lTree,
			final BinaryTree<ID, Word> rTree) {
		val lPair = lTree.accept(this);
		val rPair = rTree.accept(this);
		val types = ImmutableList.<Type> builder();
		for (val lType: lPair.getSecond()) {
			for (val rType: rPair.getSecond()) {
				if (stl.canApply(lType, rType)) {
					types.add(stl.applyType(lType, rType));
				}
				else
				if (stl.canApply(rType, lType)) {
					types.add(stl.applyType(lType, rType));
				}
			}
		}
		val string = '('+lPair.getFirst()+' '+rPair.getFirst()+')';
		return IPair.<String,List<Type>> pair(string,types.build());
	}

	@Override
	public final Pair<String,List<Type>> leaf(final Word value) {
		return IPair.<String,List<Type>> pair(
			format("%s|%s", value.getText().toLowerCase(), value.getName()),
			transform(value.getDenotations(),
				new Function<DeBruijn,Type>() {
					@Override
					public final Type apply(DeBruijn input) {
						return stl.typeOf(input);
					}
				}));
	}
}