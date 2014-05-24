package semante.checker;

import static lombok.AccessLevel.PRIVATE;
import static semante.checker.util.Direction.Left;

import java.util.List;

import lambdacalc.STL;
import lambdacalc.Types;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import semante.checker.util.IfAnnotatedWith;
import semante.checker.util.IfHasId;
import semante.checker.util.IfHoldsForSubtree;
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
		new IfHoldsForSubtree(Left, new IfHoldsForSubtree(Left, new IfAnnotatedWith("AND")));
	
	@Override
	public void check(BinaryTree<ID,UnambiguousAnnotation> tree)
			throws IllegalAnnotationException {
		
		// replace all occurrences of [[AND ...] ...] with [[AND z1] z2]
		val helper = new Helper();
		val expr   = flattener.flatten(tree.accept(helper));
		val names  = helper.usedNames(); 
		
		// check if any of the names occurs under an iota
		// TODO: 
		//  - traverse the expression until we encounter an application of an iota
		//    (requires: isconstantwithname, holdsforsubterm)
		//  - from there, traverse until we encounter one of the names
		//    (requires: foranysubterm, isconstantwithname, an 'any' combinator)
		
		// check if any of the names occurs equated to an iota
		// TODO:
		//  - traverse the expression until we encounter an application of an equality
		//    (requires: isconstantwithname, holdsforsubterm)
		//  - from there, traverse both subterms until we encounter one of the names
		//    (requires: foranysubterm, isconstantwithname, an 'any' combinator)

	}
	
	private final class Helper implements
		BinaryTree.Visitor<ID, UnambiguousAnnotation, BinaryTree<ID, UnambiguousAnnotation>> {
	
		private static final String REPLACEMENT = "$NP";
		
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
		
		// recreate all used names from the counter
		public final List<String> usedNames() {
			return usedNamesHelper(counter).build();
		}
		
		private final ImmutableList.Builder<String> usedNamesHelper(int n) {
			if (n <= 0) {
				return ImmutableList.builder();
			}
			else {
				return usedNamesHelper(n - 1).add(REPLACEMENT + n);
			}
		}
		
		private final UnambiguousAnnotation constant(int n) {
			val builder  = stl.getDeBruijnBuilder();
			val text     = REPLACEMENT + n;
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
