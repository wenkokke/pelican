package semante.flattener.impl;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import java.util.NoSuchElementException;

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.flattener.TreePrinter;
import semante.flattener.FlattenError;
import semante.flattener.FlattenTree;
import semante.lexicon.RichLexicon;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Either;
import semante.pipeline.Pair;
import semante.pipeline.Result;
import semante.pipeline.impl.IEither;
import semante.pipeline.impl.IPair;
import semante.pipeline.impl.IResult$Error;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IFlattenTree<ID> implements
	FlattenTree<ID>,
	BinaryTree.Visitor<ID, Annotation, List<DeBruijn>>,
	Function<DeBruijn,String> {
	
	STL stl;
	RichLexicon lexicon;
	TreePrinter<ID> printer;
	
	@Override
	@SuppressWarnings("unchecked")
	public final Either<Result<ID>, List<DeBruijn>> flatten(BinaryTree<ID, Annotation> tree) {
		try {
			return IEither.right(tree.accept(this));
		}
		catch (FlattenError e) {
			return IEither.left((Result<ID>) new IResult$Error<ID>((ID) e.getId(), e.getMessage()));
		}
	}
	
	public final List<DeBruijn> leaf(Annotation ann) {
		try {
			return lexicon.getEntry(ann.getCategory(), ann.getText()).getDenotations();
		}
		catch (NoSuchElementException e) {
			throw new FlattenError((ID) null, e.getMessage());
		}
	}
	
	public final List<DeBruijn> node(ID id, BinaryTree<ID, Annotation> treeL, BinaryTree<ID, Annotation> treeR) {
		val termsL = treeL.accept(this);
		val termsR = treeR.accept(this);
		
		val exps = ImmutableList.<DeBruijn> builder();
		val errs = ImmutableList.<Pair<DeBruijn, DeBruijn>> builder();
		
		for (val termL : termsL) {
			for (val termR : termsR) {
				val typeL = stl.typeOf(termL);
				val typeR = stl.typeOf(termR);
				if (stl.canApply(typeL,typeR)) {
					exps.add(stl.getDeBruijnBuilder().application(termL,termR));
				}
				else
				if (stl.canApply(typeR,typeL)) {
					exps.add(stl.getDeBruijnBuilder().application(termR,termL));
				}
				else {
					errs.add(IPair.pair(termL,termR));
				}
			}
		}
		
		// build exps and either throw error or return
		val expsBuilt = exps.build(); 
		if (expsBuilt.isEmpty()) {
			throw new FlattenError(id,errorMessage(treeL,termsL,treeR,termsR));
		}
		else {
			return expsBuilt;
		}
	}
	
	private final String errorMessage(BinaryTree<ID,Annotation> treeL, List<DeBruijn> termsL,
	                                  BinaryTree<ID,Annotation> treeR, List<DeBruijn> termsR) {
		return format(
			"cannot construct well-typed lambda expression from %s and %s of types [%s] and [%s], respectively.",
			treeL.accept(printer),
			treeR.accept(printer),
			Joiner.on(", ").join(Lists.transform(termsL,this)),
			Joiner.on(", ").join(Lists.transform(termsR,this)));
	}

	@Override
	public final String apply(DeBruijn input) {
		return stl.format(stl.typeOf(input));
	}
}