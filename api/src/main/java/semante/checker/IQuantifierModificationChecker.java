package semante.checker;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IMaybe.just;
import static semante.pipeline.impl.IMaybe.nothing;
import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lambdacalc.Types;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.checker.util.IfAnnotatedWith;
import semante.checker.util.IfHoldsForSubtree;
import semante.disamb.FlattenTree;
import semante.disamb.UnambiguousAnnotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.Maybe;
import semante.pipeline.SimpleBinaryTree;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IQuantifierModificationChecker<ID> implements AbuseChecker<ID> {
	
	STL stl;
	FlattenTree<ID> flattener;
	
	IfAnnotatedWith<ID> isNP   = new IfAnnotatedWith<ID>("NP");
	IfAnnotatedWith<ID> isNP_D = new IfAnnotatedWith<ID>("NP_D");
	IfAnnotatedWith<ID> isModR = new IfAnnotatedWith<ID>("MOD_R");
	IfAnnotatedWith<ID> isModI = new IfAnnotatedWith<ID>("MOD_I");
	IfAnnotatedWith<ID> isMR   = new IfAnnotatedWith<ID>("MR");
	IfAnnotatedWith<ID> isMI   = new IfAnnotatedWith<ID>("MI");
	IfHoldsForSubtree<ID,DeBruijn> check = new IfHoldsForSubtree<ID,DeBruijn>(new IfModifiesComplexNP());  
	
	@Override
	public void check(BinaryTree<ID, UnambiguousAnnotation<ID>> tree) throws IllegalAnnotationException {
		val maybeModifiedComplexNP = tree.accept(check);
		 if (maybeModifiedComplexNP.isJust()) {
			val modifiedComplexNP = maybeModifiedComplexNP.fromJust();
			throw new IllegalAnnotationException(null,
				String.format(
					"Illegal modification of complex NP, `%s`",
					stl.format(stl.fromDeBruijn(modifiedComplexNP))));
		 }
	}
	
	private final boolean isModifier(SimpleBinaryTree<UnambiguousAnnotation<ID>> tree) {
		return tree.accept(isModR).isJust()
			|| tree.accept(isModI).isJust()
			|| tree.accept(isMR).isJust()
			|| tree.accept(isMI).isJust();
	}
	
	private final boolean isNP(SimpleBinaryTree<UnambiguousAnnotation<ID>> tree) {
		return tree.accept(isNP).isJust()
			|| tree.accept(isNP_D).isJust();
	}
	
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	private final class IfModifiesComplexNP implements BinaryTree.Visitor<ID,UnambiguousAnnotation<ID>, Maybe<DeBruijn>> {
		
		@Override
		public Maybe<DeBruijn> leaf(UnambiguousAnnotation<ID> arg0) {
			return nothing();
		}

		@Override
		public Maybe<DeBruijn> node(
				ID id,
				BinaryTree<ID,UnambiguousAnnotation<ID>> l,
				BinaryTree<ID,UnambiguousAnnotation<ID>> r) {
			
			// check if `l` is a modifier:
			if (isModifier(l))
			{
				
				// check if `r` is a simple NP:
				if (isNP(r)) {
					return nothing();
				}
				
				// check if `r` is a complex NP:
				val expR = flattener.flatten(r);
				val typ1 = stl.format(stl.typeOf(expR));
				val typ2 = stl.format(Types.ET_T);
				if (typ1.equals(typ2))
				{
					return just(expR);
				}
			}
			
			return nothing();
		}

	}

}
