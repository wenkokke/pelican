package semante.checker;

import static lombok.AccessLevel.PRIVATE;
import static semante.checker.util.Direction.Left;
import static semante.pipeline.impl.IMaybe.just;
import static semante.pipeline.impl.IMaybe.nothing;

import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.DeBruijnBuilder;
import lambdacalc.Index;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.Types;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import semante.checker.util.IfAnnotatedWith;
import semante.checker.util.IfAny;
import semante.checker.util.IfConstantWithName;
import semante.checker.util.IfHasId;
import semante.checker.util.IfHoldsForDirectSubtree;
import semante.checker.util.IfHoldsForSubterm;
import semante.disamb.FlattenTree;
import semante.disamb.UnambiguousAnnotation;
import semante.disamb.impl.IUnambiguousAnnotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.BinaryTreeBuilder;
import semante.pipeline.Maybe;
import semante.pipeline.SimpleBinaryTree;

import com.google.common.collect.ImmutableList;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class ICollectivityAndIotaChecker<ID> implements AbuseChecker<ID> {
	
	STL stl;
	FlattenTree<ID> flattener;
	BinaryTreeBuilder<ID,UnambiguousAnnotation> builder;
	SimpleBinaryTree.Visitor<UnambiguousAnnotation, Maybe<UnambiguousAnnotation>> nestedAnd =
		new IfHoldsForDirectSubtree(Left, new IfHoldsForDirectSubtree(Left, new IfAnnotatedWith("AND")));
	
	@Override
	public void check(BinaryTree<ID,UnambiguousAnnotation> tree)
			throws IllegalAnnotationException {
		
		// replace all occurrences of [[AND ...] ...] with [[AND z1] z2]
		val replaceWithConst = new ReplaceNPsInConjunctionByConstant();
		val flattenedTerm    = flattener.flatten(tree.accept(replaceWithConst));
		val usedNames        = replaceWithConst.usedNames();
		
		// check if any of the names occurs under an iota
		//  - traverse the expression until we encounter an application of an iota
		//  - from there, traverse until we encounter one of the names
		val checkUnderIota   = new IfNameOccursUnderIota(usedNames);
		val maybeUnderIota   = flattenedTerm.accept(checkUnderIota);
		if (maybeUnderIota.isJust()) {
			val termUnderIota = maybeUnderIota.fromJust();
			throw new IllegalAnnotationException(null,
				String.format(
					"Illegal use of NP conjunction with definites, generated `%s`",
					stl.format(stl.fromDeBruijn(termUnderIota))));
		}
		
		// check if any of the names occurs equated to an iota
		// TODO:
		//  - traverse the expression until we encounter an application of an equality
		//    (requires: isconstantwithname, holdsforsubterm)
		//  - from there, traverse both subterms until we encounter one of the names
		//    (requires: foranysubterm, isconstantwithname, an 'any' combinator)
	}
	
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	private final class IfNameOccursUnderIota implements DeBruijn.Visitor<Maybe<DeBruijn>> {

		@NonNull
		List<String> usedNames;
		
		DeBruijnBuilder builder =
			stl.getDeBruijnBuilder();
		DeBruijn.Visitor<Maybe<DeBruijn>> isIOTA =
			new IfConstantWithName(stl.getDeBruijnBuilder(), "IOTA");
		DeBruijn.Visitor<Maybe<DeBruijn>> forSubterm =
			new IfHoldsForSubterm(this);
		
		@Getter(lazy = true)
		DeBruijn.Visitor<Maybe<DeBruijn>> anyInList =
			initializeAnyInList();
		
		private final DeBruijn.Visitor<Maybe<DeBruijn>> initializeAnyInList() {
			val init = ImmutableList.<DeBruijn.Visitor<Maybe<DeBruijn>>> builder();
			for (val usedName : usedNames) {
				init.add(new IfConstantWithName(stl.getDeBruijnBuilder(), usedName));
			}
			return new IfAny(stl.getDeBruijnBuilder(), init.build());
		}
		
		@Override
		public final Maybe<DeBruijn> application(DeBruijn arg0, DeBruijn arg1) {
			if (arg0.accept(isIOTA).isJust()) {
				if (arg0.accept(getAnyInList()).isJust()) {
					return just(builder.application(arg0, arg1));
				}
			}
			val rec0 = arg0.accept(this);
			if (rec0.isJust()) return rec0;
			return arg1.accept(this);
		}

		@Override
		public final Maybe<DeBruijn> abstraction(Type arg0, DeBruijn arg1) {
			return arg1.accept(forSubterm);
		}

		@Override
		public final Maybe<DeBruijn> constant(Symbol arg0) {
			return nothing();
		}

		@Override
		public final Maybe<DeBruijn> variable(Index arg0) {
			return nothing();
		}
	}
	
	
	// this helper class is a traversal of an unambiguous annotation tree which replaces
	// all arguments to NP conjunctions with constants, and after application offers a list
	// of all used constant names.
	// this class offers no guarantee that the used names will be unique, and therefore
	// does not avoid name-capturing.
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	private final class ReplaceNPsInConjunctionByConstant implements
		BinaryTree.Visitor<ID, UnambiguousAnnotation, BinaryTree<ID, UnambiguousAnnotation>> {
		
		@NonFinal
		int counter = 0;

		@Override
		public final BinaryTree<ID, UnambiguousAnnotation> leaf(UnambiguousAnnotation x) {
			return builder.leaf(x);
		}

		@Override
		public final BinaryTree<ID, UnambiguousAnnotation> node(ID id1, BinaryTree<ID, UnambiguousAnnotation> l, BinaryTree<ID, UnambiguousAnnotation> r) {
			val node    = builder.node(id1, l, r);
			val query   = node.accept(nestedAnd);
			if (query.isJust()) {
				val z1  = builder.leaf(constant(counter));
				val z2  = builder.leaf(constant(counter + 1));
				val and = builder.leaf(query.fromJust());
				val id2 = l.accept(new IfHasId<ID,UnambiguousAnnotation>()).fromJust();
				return builder.node(id1, builder.node(id2, and, z1), z2);
			}
			else {
				return node;
			}
		}
		
		// create a name for a specific index
		private final String nameFor(int n) {
			return "$NP_" + n + "$";
		}
		
		// recreate all used names from the counter
		public final List<String> usedNames() {
			return usedNamesHelper(counter).build();
		}
		
		private final ImmutableList.Builder<String> usedNamesHelper(int n) {
			if (n <= 0) {
				return ImmutableList.builder();
			}
			else {
				return usedNamesHelper(n - 1).add(nameFor(n));
			}
		}
		
		private final UnambiguousAnnotation constant(int n) {
			val builder  = stl.getDeBruijnBuilder();
			val text     = nameFor(n);
			val category = "NP";
			val meaning  =
				builder.abstraction(Types.ET,
					builder.application(
						builder.variable(0, Types.ET),
						builder.constant(text, Types.E)));
			val type     = Types.ET_T;
			return new IUnambiguousAnnotation(text,category,meaning,type);
		}
	}

}
