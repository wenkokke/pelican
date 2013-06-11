package semante.pipeline.impl;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lexicon.Word;
import semante.pipeline.BinaryTree;
import semante.pipeline.Either;
import semante.pipeline.FlattenTree;
import semante.pipeline.Result;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IFlattenTree<ID> implements FlattenTree<ID> {

	STL stl;
	
	@Override
	public final Either<Result<ID>,List<DeBruijn>> flatten(final BinaryTree<ID, Word> tree) {
		return tree.accept(
			new BinaryTree.Visitor<ID, Word, Either<Result<ID>,List<DeBruijn>>>() {

			@Override
			public final Either<Result<ID>,List<DeBruijn>> node(
					final ID id,
					final BinaryTree<ID, Word> treeL,
					final BinaryTree<ID, Word> treeR) {
				val builder = ImmutableList.<DeBruijn> builder();
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
						val lType = stl.typeOf(elemL);
						val rType = stl.typeOf(elemR);
						
						// check if (L R) is well-typed.
						if (stl.canApply(lType, rType)) {
							builder.add(stl.getDeBruijnBuilder().application(elemL, elemR));
							any |= true;
						}
						else
						
						// check if (R L) is well-typed.
						if (stl.canApply(rType, lType)) {
							builder.add(stl.getDeBruijnBuilder().application(elemR, elemL));
							any |= true;
						}
					}
				}
				
				// if no expr is well-typed, throw a typeerror.
				if (!any) {
					val printer = new ITreePrinter<ID>(stl);
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
				return IEither.right((List<DeBruijn>) builder.build());
			}

			@Override
			public final Either<Result<ID>, List<DeBruijn>> leaf(Word word) {
				return IEither.right(word.getDenotations());
			}
		});
	}
}
