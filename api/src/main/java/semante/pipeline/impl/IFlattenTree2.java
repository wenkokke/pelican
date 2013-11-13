package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import java.util.NoSuchElementException;

import com.google.common.collect.ImmutableList;

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lexicon.RichLexicon;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Either;
import semante.pipeline.FlattenError;
import semante.pipeline.FlattenError.NoLexicalEntry;
import semante.pipeline.FlattenError.NoPossibleTerm;
import semante.pipeline.FlattenTree;
import semante.pipeline.Pair;
import semante.pipeline.Result;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IFlattenTree2<ID> implements FlattenTree<ID>, BinaryTree.Visitor<ID, Annotation, List<DeBruijn>> {
	
	STL stl;
	RichLexicon lexicon;
	
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
			throw new NoLexicalEntry(e.getMessage());
		}
	}
	
	public final List<DeBruijn> node(ID id, BinaryTree<ID, Annotation> left, BinaryTree<ID, Annotation> right) {
		val termsL = left.accept(this);
		val termsR = right.accept(this);
		
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
			throw new NoPossibleTerm(id, stl, errs.build());
		}
		else {
			return expsBuilt;
		}
	}

}