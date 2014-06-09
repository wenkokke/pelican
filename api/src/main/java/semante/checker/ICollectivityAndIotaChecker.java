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
import semante.checker.util.Direction;
import semante.checker.util.IfAnnotatedWith;
import semante.checker.util.IfAny;
import semante.checker.util.IfConstantWithName;
import semante.checker.util.IfHasId;
import semante.checker.util.IfHoldsForDirectSubterm;
import semante.checker.util.IfHoldsForDirectSubtree;
import semante.checker.util.IfHoldsForSubterm;
import semante.disamb.FlattenTree;
import semante.disamb.UnambiguousAnnotation;
import semante.disamb.impl.IUnambiguousAnnotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.BinaryTreeBuilder;
import semante.pipeline.Maybe;
import semante.pipeline.SimpleBinaryTree;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class ICollectivityAndIotaChecker<ID> implements AbuseChecker<ID> {
	
	STL stl;
	FlattenTree<ID> flattener;
	BinaryTreeBuilder<ID,UnambiguousAnnotation> builder;
	
	@Override
	public void check(BinaryTree<ID,UnambiguousAnnotation> tree)
			throws IllegalAnnotationException {
		
		// replace all occurrences of [[AND ...] ...] with [[AND z1] z2]
		val replaceWithConst = new ReplaceNPsInConjunctionByConstant();
		val flattenedTerm    = flattener.flatten(tree.accept(replaceWithConst));
		val reducedTerm      = stl.betaReduce(flattenedTerm);
		val usedNames        = replaceWithConst.usedNames();
		
		// check if any of the names occurs under an iota
		//  - traverse the expression until we encounter an application of an iota
		//  - from there, traverse until we encounter one of the names
		val checkUnderIota = new IfNameOccursUnderIota(usedNames);
		val maybeUnderIota = reducedTerm.accept(checkUnderIota);
		if (maybeUnderIota.isJust()) {
			val termUnderIota = maybeUnderIota.fromJust();
			throw new IllegalAnnotationException(null,
				String.format(
					"Illegal use of NP conjunction with definites, generated `%s`",
					stl.format(stl.fromDeBruijn(termUnderIota))));
		}
		
		// check if any of the names occurs equated to an iota
		//  - traverse the expression until we encounter an application of an equality
		//  - from there, traverse both subterms until we encounter one of the names
		val checkNextToEq = new IfNameOccursEquatedToIota(usedNames);
		val maybeNextToEq = reducedTerm.accept(checkNextToEq);
		if (maybeNextToEq.isJust()) {
			val termNextToEq = maybeNextToEq.fromJust();
			throw new IllegalAnnotationException(null,
					String.format(
						"Illegal use of NP conjunction with definites, generated `%s`",
						stl.format(stl.fromDeBruijn(termNextToEq))));
		}
	}
	
	// this helper class is a traversal of an unambiguous annotation tree which
	// checks if a certain name occurs equated to an iota.
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	private final class IfNameOccursEquatedToIota implements DeBruijn.Visitor<Maybe<DeBruijn>> {
		
		@NonNull
		List<String> usedNames;
		
		DeBruijnBuilder builder =
			stl.getDeBruijnBuilder();
		
		@Getter(lazy = true)
		DeBruijn.Visitor<Maybe<DeBruijn>> anyInList =
			initializeAnyInList();
		
		DeBruijn.Visitor<Maybe<DeBruijn>> leftSubtermIsEQ =
			new IfHoldsForDirectSubterm(Direction.Left,
				new IfConstantWithName(builder, "EQ"));
		DeBruijn.Visitor<Maybe<DeBruijn>> isIOTA =
			new IfHoldsForDirectSubterm(Direction.Left,
				new IfConstantWithName(builder, "IOTA"));
		DeBruijn.Visitor<Maybe<DeBruijn>> rightSubtermIsIOTA =
			new IfHoldsForDirectSubterm(Direction.Right,isIOTA);
		
		@Getter(lazy = true)
		DeBruijn.Visitor<Maybe<DeBruijn>> rightSubtermIsAnyInList =
			initializeRightSubtermIsAnyInList();
		
		private final DeBruijn.Visitor<Maybe<DeBruijn>> initializeAnyInList() {
			val init = ImmutableList.<DeBruijn.Visitor<Maybe<DeBruijn>>> builder();
			for (val usedName : usedNames) {
				init.add(new IfConstantWithName(builder, usedName));
			}
			return new IfAny(builder, init.build());
		}
		
		private final DeBruijn.Visitor<Maybe<DeBruijn>> initializeRightSubtermIsAnyInList() {
			return new IfHoldsForDirectSubterm(Direction.Right, getAnyInList()); 
		}

		@Override
		public final Maybe<DeBruijn> application(DeBruijn arg0, DeBruijn arg1) {
			if (arg0.accept(leftSubtermIsEQ).isJust()) {
				System.err.println("MSG1: " + stl.format(builder.application(arg0, arg1)));
				if (arg1.accept(isIOTA).isJust()) {
					System.err.println("MSG2: " + stl.format(builder.application(arg0, arg1)));
					if (arg0.accept(getRightSubtermIsAnyInList()).isJust()) {
						return just(builder.application(arg0, arg1));
					}
				}
				else
				if (arg0.accept(rightSubtermIsIOTA).isJust()) {
					System.err.println("MSG3: " + stl.format(builder.application(arg0, arg1)));
					if (arg1.accept(getAnyInList()).isJust()) {
						return just(builder.application(arg0, arg1));
					}
				}
			}
			val rec0 = arg0.accept(this);
			if (rec0.isJust()) return rec0;
			return arg1.accept(this);
		}
		
		@Override
		public final Maybe<DeBruijn> abstraction(Type arg0, DeBruijn arg1) {
			return arg1.accept(this);
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
	
	// this helper class is a traversal of an unambiguous annotation tree which
	// checks if a certain name occurs under an iota.
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	private final class IfNameOccursUnderIota implements DeBruijn.Visitor<Maybe<DeBruijn>> {

		@NonNull
		List<String> usedNames;
		
		@Getter(lazy = true)
		DeBruijn.Visitor<Maybe<DeBruijn>> anyInList =
			initializeAnyInList();
		
		DeBruijnBuilder builder =
			stl.getDeBruijnBuilder();
		DeBruijn.Visitor<Maybe<DeBruijn>> isIOTA =
			new IfConstantWithName(builder, "IOTA");
		
		@Getter(lazy = true)
		DeBruijn.Visitor<Maybe<DeBruijn>> anySubtermIsAnyInList =
			initializeAnySubtermIsAnyInList();
		
		private final DeBruijn.Visitor<Maybe<DeBruijn>> initializeAnyInList() {
			val init = ImmutableList.<DeBruijn.Visitor<Maybe<DeBruijn>>> builder();
			for (val usedName : usedNames) {
				init.add(new IfConstantWithName(builder, usedName));
			}
			return new IfAny(builder, init.build());
		}
		
		private final DeBruijn.Visitor<Maybe<DeBruijn>> initializeAnySubtermIsAnyInList() {
			return new IfHoldsForSubterm(getAnyInList()); 
		}
		
		@Override
		public final Maybe<DeBruijn> application(DeBruijn arg0, DeBruijn arg1) {
			if (arg0.accept(isIOTA).isJust()) {
				if (arg1.accept(getAnySubtermIsAnyInList()).isJust()) {
					return just(builder.application(arg0, arg1));
				}
			}
			val rec0 = arg0.accept(this);
			if (rec0.isJust()) return rec0;
			return arg1.accept(this);
		}

		@Override
		public final Maybe<DeBruijn> abstraction(Type arg0, DeBruijn arg1) {
			return arg1.accept(this);
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
		
		SimpleBinaryTree.Visitor<UnambiguousAnnotation, Maybe<UnambiguousAnnotation>> hasNestedAND =
			new IfHoldsForDirectSubtree(Left, new IfAnnotatedWith("AND"));
		

		@Override
		public final BinaryTree<ID, UnambiguousAnnotation> leaf(UnambiguousAnnotation x) {
			return builder.leaf(x);
		}

		@Override
		public final BinaryTree<ID, UnambiguousAnnotation> node(ID id1, BinaryTree<ID, UnambiguousAnnotation> l1, BinaryTree<ID, UnambiguousAnnotation> r1) {
			
			// recursively apply transformation:
			val l2    = l1.accept(this);
			val r2    = r1.accept(this);
			
			// query if required conditions hold:
			val query = l2.accept(hasNestedAND);
			if (query.isJust()) {
				val typeOfR2 = typeOfSubtree(r2); 
				if (typeOfR2.equals(Types.ET_T)) {
					val z1  = builder.leaf(constant(counter += 1));
					val z2  = builder.leaf(constant(counter += 1));
					val and = builder.leaf(query.fromJust());
					val id2 = l1.accept(new IfHasId<ID,UnambiguousAnnotation>()).fromJust();
					return builder.node(id1, builder.node(id2, and, z1), z2);
				}
			}
			return builder.node(id1, l2, r2);
		}
		
		// checks the type of a subtree
		private final Type typeOfSubtree(BinaryTree<ID, UnambiguousAnnotation> t) {
			return stl.typeOf(flattener.flatten(t));
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
