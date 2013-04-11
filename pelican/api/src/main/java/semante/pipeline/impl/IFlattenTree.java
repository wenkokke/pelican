package semante.pipeline.impl;

import java.util.List;

import lombok.val;
import lombok.experimental.Value;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;
import semante.lexicon.Word;
import semante.pipeline.BinaryTree;
import semante.pipeline.FlattenTree;
import semante.util.Either;
import semante.util.impl.IEither;

import com.google.common.collect.ImmutableList;

@Value
public final class IFlattenTree<ID,T extends TSymbol> implements FlattenTree<ID,T> {

	TLambdaCalc<T> lambdacalc;
	
	@Override
	public final Either<ID,List<Expr<T>>> flatten(final BinaryTree<ID, Word<T>> tree) {
		return tree.accept(new BinaryTree.Visitor<ID, Word<T>, Either<ID,List<Expr<T>>>>() {

			@Override
			public final Either<ID,List<Expr<T>>> node(
					final ID id,
					final BinaryTree<ID, Word<T>> treeL,
					final BinaryTree<ID, Word<T>> treeR) {
				val builder = ImmutableList.<Expr<T>> builder();
				val listL = treeL.accept(this);
				val listR = treeR.accept(this);
				
				// check any errors.
				if (listL.isLeft()) { return listL; }
				if (listR.isLeft()) { return listR; }
				
				// check all combinations, maintain if *any* is well-typed.
				boolean any = false;
				
				for (val elemL: listL.getRight()) {
					for (val elemR: listR.getRight()) {
						
						// get types of L and R.
						val lType = lambdacalc.typeOf(elemL);
						val rType = lambdacalc.typeOf(elemR);
						
						// check if (L R) is well-typed.
						if (lambdacalc.isCompatible(lType, rType)) {
							builder.add(lambdacalc.exprBuilder().application(elemL, elemR));
							any |= true;
						}
						else
						
						// check if (R L) is well-typed.
						if (lambdacalc.isCompatible(rType, lType)) {
							builder.add(lambdacalc.exprBuilder().application(elemR, elemL));
							any |= true;
						}
					}
				}
				
				// if no expr is well-typed, throw a typeerror.
				if (!any) {
					return IEither.left(id);
				}
				
				// else return the well-typed expr.
				return IEither.<ID, List<Expr<T>>> right(builder.build());
			}

			@Override
			public final Either<ID, List<Expr<T>>> leaf(Word<T> word) {
				return IEither.right(word.getExpr());
			}
		});
	}
}
