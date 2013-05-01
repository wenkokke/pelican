package semante.pipeline.impl;

import static com.google.common.collect.Lists.transform;
import static java.lang.String.format;

import java.util.List;

import lombok.val;
import lombok.experimental.Value;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;
import semante.lexicon.Word;
import semante.pipeline.BinaryTree;
import semante.util.Pair;
import semante.util.impl.IPair;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

@Value
public final class ITreePrinter<ID, T extends TSymbol>
	implements BinaryTree.Visitor<ID, Word<T>, Pair<String,List<Type>>> {

	TLambdaCalc<T> lambdacalc;
	
	@Override
	public final Pair<String,List<Type>> node(
			final ID id,
			final BinaryTree<ID, Word<T>> lTree,
			final BinaryTree<ID, Word<T>> rTree) {
		val lPair = lTree.accept(this);
		val rPair = rTree.accept(this);
		val types = ImmutableList.<Type> builder();
		for (val lType: lPair.getSecond()) {
			for (val rType: rPair.getSecond()) {
				if (lambdacalc.isCompatible(lType, rType)) {
					types.add(lType.accept(Type.ResultType));
				}
				else
				if (lambdacalc.isCompatible(rType, lType)) {
					types.add(rType.accept(Type.ResultType));
				}
			}
		}
		val string = '('+lPair.getFirst()+' '+rPair.getFirst()+')';
		return IPair.<String,List<Type>> pair(string,types.build());
	}

	@Override
	public final Pair<String,List<Type>> leaf(final Word<T> value) {
		return IPair.<String,List<Type>> pair(
			format("%s|%s", value.getText().toLowerCase(), value.getName()),
			transform(value.getExpr(),
				new Function<Expr<T>,Type>() {
					@Override
					public final Type apply(Expr<T> input) {
						return lambdacalc.typeOf(input);
					}
				}));
	}
}