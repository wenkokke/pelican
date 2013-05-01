package semante.pipeline.impl;

import static java.lang.String.format;

import java.util.List;

import lombok.val;
import lombok.experimental.Value;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;
import semante.lexicon.Word;
import semante.pipeline.BinaryTree;
import semante.pipeline.FlattenTree;
import semante.pipeline.Result;
import semante.pipeline.util.Either;
import semante.pipeline.util.impl.IEither;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

@Value
public final class IFlattenTree<ID,T extends TSymbol> implements FlattenTree<ID,T> {

	TLambdaCalc<T> lambdacalc;
	
	@Override
	public final Either<Result<ID>,List<Expr<T>>> flatten(final BinaryTree<ID, Word<T>> tree) {
		return tree.accept(
			new BinaryTree.Visitor<ID, Word<T>, Either<Result<ID>,List<Expr<T>>>>() {

			@Override
			public final Either<Result<ID>,List<Expr<T>>> node(
					final ID id,
					final BinaryTree<ID, Word<T>> treeL,
					final BinaryTree<ID, Word<T>> treeR) {
				val builder = ImmutableList.<Expr<T>> builder();
				val eitherL = treeL.accept(this);
				val eitherR = treeR.accept(this);
				
				// check any errors.
				if (eitherL.isLeft()) { return eitherL; }
				if (eitherR.isLeft()) { return eitherR; }
				
				// check all combinations, maintain if *any* is well-typed.
				boolean any = false;
				
				for (val elemL: eitherL.getRight()) {
					for (val elemR: eitherR.getRight()) {
						
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
					val printer = new ITreePrinter<ID,T>(lambdacalc);
					val errorL = treeL.accept(printer);
					val typesL = ImmutableSet.copyOf(errorL.getSecond());
					val errorR = treeR.accept(printer);
					val typesR = ImmutableSet.copyOf(errorR.getSecond());
					return IEither.left((Result<ID>)
						new IResult$ErrorMsg<ID>(id,
							format("cannot combine %s and %s of types %s and %s",
								errorL.getFirst(),errorR.getFirst(),typesL,typesR)));
				}
				
				// else return the well-typed expr.
				return IEither.right((List<Expr<T>>) builder.build());
			}

			@Override
			public final Either<Result<ID>, List<Expr<T>>> leaf(Word<T> word) {
				return IEither.right(word.getExpr());
			}
		});
	}
}
