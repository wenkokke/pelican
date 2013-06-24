package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lexicon.RichLexicon;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Denotation;
import semante.pipeline.Either;
import semante.pipeline.FlattenTree;
import semante.pipeline.Pair;
import semante.pipeline.Result;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IFlattenTree<ID> implements FlattenTree<ID> {

	STL										stl;
	RichLexicon								lexicon;

	@Override
	public final Either<Result<ID>, List<DeBruijn>> flatten(final BinaryTree<ID, Annotation> tree) {
		val builder = ImmutableList.<Either<Result<ID>,DeBruijn>> builder();
		for (val expr: join1(lookup(tree))) {
			builder.add(compose(expr));
		}
		val results = join2(builder.build());
		if (results.isLeft()) {
			
			// TODO this unchecked cast is kind of ugly
			val errors = ImmutableList.<Pair<ID,String>> builder();
			for (val result: results.getLeft()) {
				val error = (IResult$Error<ID>) result;
				errors.add(IPair.pair(error.getId(), error.getMsg()));
			}
			return IEither.left((Result<ID>) new IResult$Errors<ID>(errors.build()));
			
		}
		else {
			return IEither.right(results.getRight());
		}
	}
	

	
	// performs a lookup into the given lexicon;
	Function<Annotation, List<Denotation>>	lookup
		= new Function<Annotation, List<Denotation>>() {
			@Override
			public final List<Denotation> apply(final Annotation input) {
				return input.accept(new Annotation.Visitor<List<Denotation>>() {
					@Override
					public final List<Denotation> annotation(final String text, String category) {
						final Builder<Denotation> builder = ImmutableList.<Denotation> builder();
						for (val denotation: lexicon.getEntry(category, text).getDenotations()) {
							builder.add(new IDenotation(text, category, denotation));
						}
						return builder.build();
					}
				});
			}
		};
	
	// runs lookup over all the leafs in the tree;
	private final BinaryTree<ID,List<Denotation>> lookup(final BinaryTree<ID,Annotation> tree) {
		return tree.accept(IBinaryTree.functor(Functions.<ID> identity(), lookup));
	}
	
	// joins an ambiguous binarytree into multiple unambiguous binarytrees; 
	private final List<BinaryTree<ID,Denotation>> join1(final BinaryTree<ID,List<Denotation>> tree) {
		return tree.accept(new BinaryTree.Visitor<ID,List<Denotation>,List<BinaryTree<ID,Denotation>>>() {

			@Override
			public final List<BinaryTree<ID, Denotation>> leaf(List<Denotation> denotations) {
				final Builder<BinaryTree<ID,Denotation>> 
				builder = ImmutableList.<BinaryTree<ID, Denotation>> builder();
				
				for (val denotation: denotations) {
					builder.add(IBinaryTree.<ID,Denotation> leaf(denotation));
				}
				return builder.build();
			}

			@Override
			public final List<BinaryTree<ID, Denotation>> node(final ID id,
					final BinaryTree<ID, List<Denotation>> lefts,
					final BinaryTree<ID, List<Denotation>> rights) {
				final Builder<BinaryTree<ID,Denotation>> 
				builder = ImmutableList.<BinaryTree<ID, Denotation>> builder();
				
				for (val left: lefts.accept(this)) {
					for (val right: rights.accept(this)) {
						builder.add(IBinaryTree.<ID,Denotation> node(id,left,right));
					}
				}
				return builder.build();
			}
		});
	}
	
	private final Either<Result<ID>,DeBruijn> compose(final BinaryTree<ID,Denotation> tree) {
		return tree.accept(new BinaryTree.Visitor<ID,Denotation,Either<Result<ID>,Pair<String,DeBruijn>>>() {

			@Override
			public final Either<Result<ID>, Pair<String,DeBruijn>> leaf(final Denotation denotation) {
				return
					IEither.right(
						IPair.pair(
							denotation.getText(),
							denotation.getDenotation()
						)
					);
			}

			@Override
			public Either<Result<ID>, Pair<String,DeBruijn>> node(final ID id,
				final BinaryTree<ID, Denotation> left,
				final BinaryTree<ID, Denotation> right) {
				
				// recursively apply the algorithm;
				val eitherL = left.accept(this);
				val eitherR = right.accept(this);
				
				// forward errors without any composition;
				if (eitherL.isLeft()) return eitherL;
				if (eitherR.isLeft()) return eitherR;
				
				// attempt to compose the two expressions;
				val pairL = eitherL.getRight();
				val pairR = eitherR.getRight();
				val textL = pairL.getFirst();
				val textR = pairR.getFirst();
				val exprL = pairL.getSecond();
				val exprR = pairR.getSecond();
				val typeL = stl.typeOf(exprL);
				val typeR = stl.typeOf(exprR);
				
				// apply as [left right];
				if (stl.canApply(typeL, typeR)) {
					val text = String.format("[%s %s]", textL, textR);
					val expr = stl.getDeBruijnBuilder().application(exprL, exprR);
					return IEither.right(IPair.pair(text, expr));
				}
				else
				
				// apply as [right left];
				if (stl.canApply(typeR, typeL)) {
					val text = String.format("[%s %s]", textR, textL);
					val expr = stl.getDeBruijnBuilder().application(exprR, exprL);
					return IEither.right(IPair.pair(text, expr));
				}
				else
				
				// return a type error;
				return IEither.<Result<ID>,Pair<String,DeBruijn>>
					left(new IResult$Error<ID>(id,String.format(
						"cannot apply '%s' of type '%s' to '%s' of type '%s'",
						stl.format(stl.fromDeBruijn(exprL)),
						stl.format(typeL),
						stl.format(stl.fromDeBruijn(exprR)),
						stl.format(typeR)
					)));
			}
		}).accept(new Either.Visitor<Result<ID>,Pair<String,DeBruijn>,Either<Result<ID>,DeBruijn>>() {
			@Override
			public final Either<Result<ID>, DeBruijn> left(final Result<ID> result) {
				return IEither.left(result);
			}

			@Override
			public final Either<Result<ID>, DeBruijn> right(final Pair<String, DeBruijn> pair) {
				return IEither.right(pair.getSecond());
			}
		});
	}
	
	private final Either<List<Result<ID>>,List<DeBruijn>> join2(final List<Either<Result<ID>,DeBruijn>> list) {
		final Builder<Result<ID>>
		lefts = ImmutableList.<Result<ID>> builder();
		
		final Builder<DeBruijn> 
		rights = ImmutableList.<DeBruijn> builder();
		
		for (val item: list) {
			if (item.isLeft()) {
				lefts.add(item.getLeft());
			}
			else {
				rights.add(item.getRight());
			}
		}
		
		val result = rights.build();
		if (result.isEmpty()) {
			return IEither.<List<Result<ID>>,List<DeBruijn>> left(lefts.build());
		}
		else {
			return IEither.<List<Result<ID>>,List<DeBruijn>> right(result);
		}
	}
}
