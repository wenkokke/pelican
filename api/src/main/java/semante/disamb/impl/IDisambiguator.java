package semante.disamb.impl;

import static com.google.common.collect.Lists.transform;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IPair.pair;
import static semante.pipeline.impl.IPair.second;

import java.util.List;
import java.util.NoSuchElementException;

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lambdacalc.Type;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.disamb.AnnotationTreePrinter;
import semante.disamb.Disambiguator;
import semante.disamb.DisambiguatorException;
import semante.disamb.FlattenTree;
import semante.disamb.UnambiguousAnnotation;
import semante.lexicon.RichLexicon;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.BinaryTreeBuilder;
import semante.pipeline.Either;
import semante.pipeline.Pair;
import semante.pipeline.Result;
import semante.pipeline.impl.IEither;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IDisambiguator<ID> implements Disambiguator<ID> {
	
	STL stl;
	RichLexicon lexicon;
	FlattenTree<ID> flattener;
	AnnotationTreePrinter<ID> printer;
	BinaryTreeBuilder<ID,UnambiguousAnnotation> builder;
	Function<Pair<Type,BinaryTree<ID,UnambiguousAnnotation>>,
	                   BinaryTree<ID,UnambiguousAnnotation>> snd = second();

	@Override
	public Either<DisambiguatorException, List<BinaryTree<ID,UnambiguousAnnotation>>> disambiguate(
			BinaryTree<ID, Annotation> tree) {
		try {
			return IEither.right(transform(tree.accept(new Helper()),snd));
		}
		catch (DisambiguatorException e) {
			return IEither.left(e);
		}
	}
	
	@Override
	public Either<Result<ID>, List<DeBruijn>> disambiguateAndFlatten(
			BinaryTree<ID, Annotation> tree) {
		return disambiguate(tree)
				.accept(new Either.Visitor<DisambiguatorException, List<BinaryTree<ID,UnambiguousAnnotation>>, Either<Result<ID>, List<DeBruijn>>>() {

					@Override
					public final Either<Result<ID>, List<DeBruijn>> left(DisambiguatorException except) {
						return IEither.left(except.<ID> toResult());
					}

					@Override
					public final Either<Result<ID>, List<DeBruijn>> right(List<BinaryTree<ID,UnambiguousAnnotation>> list) {
						return IEither.right(flattener.flattenAll(list));
					}

				});
	}
	
	private final class Helper implements BinaryTree.Visitor<ID, Annotation, List<Pair<Type,BinaryTree<ID,UnambiguousAnnotation>>>> {

		@Override
		public final List<Pair<Type,BinaryTree<ID,UnambiguousAnnotation>>>
			leaf(Annotation x) {
			try {
				
				// lookup denotations in lexicon (may throw NoSuchElementException)
				val denotations
					= lexicon.getEntry(x.getCategory(), x.getText()).getDenotations();
				
				// transform denotations to unambiguous trees, or in pseudo code:
				// map (leaf . flip IUnambiguousAnnotation x) denotations
				val trees
					= ImmutableList.<Pair<Type,BinaryTree<ID,UnambiguousAnnotation>>> builder();
				for (val d : denotations) {
					trees.add(pair(stl.typeOf(d),builder.leaf(new IUnambiguousAnnotation(x,d))));
				}
				return trees.build();
			}
			catch (NoSuchElementException e) {
				throw new DisambiguatorException((ID) null, e.getMessage());
			}
		}

		@Override
		public final List<Pair<Type,BinaryTree<ID,UnambiguousAnnotation>>>
			node(ID id, BinaryTree<ID, Annotation> l, BinaryTree<ID, Annotation> r) {
			
			// recursively apply the transformation
			val ls = l.accept(this);
			val rs = r.accept(this);
			
			// set up variables for the results (new trees and errors)
			val exps = ImmutableList.<Pair<Type,BinaryTree<ID,UnambiguousAnnotation>>> builder(); 
			val errs = ImmutableList.<Pair<Pair<Type,BinaryTree<ID,UnambiguousAnnotation>>
			                              ,Pair<Type,BinaryTree<ID,UnambiguousAnnotation>>>> builder(); 
			
			// try all combinations of the left and right subtrees
			for (val tul : ls) {
				for (val tur : rs) {
					
					// types of left and right trees
					val tyl = tul.getFirst();
					val tyr = tur.getFirst();
					
					// subtrees of left and right trees
					val subl = tul.getSecond();
					val subr = tur.getSecond();
					
					// check both ways of function application
					if (stl.canApply(tyl, tyr)) {
						val ty = stl.applyType(tyl, tyr);
						val sub = builder.node(id, subl, subr);
						exps.add(pair(ty,sub));
					}
					else
					if (stl.canApply(tyr, tyl)) {
						val ty = stl.applyType(tyr, tyl);
						val sub = builder.node(id, subr, subl);
						exps.add(pair(ty,sub));
					}
					else {
						errs.add(pair(tul,tur));
					}
				}
			}
			
			// build exps and either throw error or return
			val expsBuilt = exps.build(); 
			if (expsBuilt.isEmpty()) {
				throw new DisambiguatorException(id,
					errorMessage(l,flattener.flattenAll(transform(ls,snd))
							    ,r,flattener.flattenAll(transform(rs,snd))));
			}
			else {
				return expsBuilt;
			}
		}
		
	}
	
	// joiner for use in the error message generation below
	Joiner commaJoiner = Joiner.on(", ").skipNulls();
	
	// show function for De Bruijn terms' types
	Function<DeBruijn,String> showDeBruijnType =
		new Function<DeBruijn,String>() {
			@Override
			public final String apply(DeBruijn input) {
				return stl.format(stl.typeOf(input));
			}
		};
	
	private final String errorMessage(BinaryTree<ID,Annotation> treeL, List<DeBruijn> termsL,
            BinaryTree<ID,Annotation> treeR, List<DeBruijn> termsR) {
		return format(
				"cannot construct well-typed lambda expression from %s and %s of types [%s] and [%s], respectively.",
				treeL.accept(printer),
				treeR.accept(printer),
				commaJoiner.join((Iterable<?>) transform(termsL,showDeBruijnType)),
				commaJoiner.join((Iterable<?>) transform(termsR,showDeBruijnType)));
	}


}
