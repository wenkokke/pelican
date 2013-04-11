package semante.util.binarytree.impl;

import lombok.experimental.Value;
import semante.pipeline.BinaryTree;
import semante.util.Pair;
import semante.util.SimpleBinaryTree;
import semante.util.binarytree.Labeller;
import semante.util.impl.IBinaryTree;
import static semante.util.impl.IPair.*;

@Value
public final class ILabeller implements Labeller {

	public static final Labeller labeller() {
		return new ILabeller();
	}
	
	@Override
	public final <B> BinaryTree<Integer, B> label(final SimpleBinaryTree<B> tree) {
		return labelFrom(0,tree).getSecond();
	}
	
	private final <B> Pair<Integer,BinaryTree<Integer, B>> labelFrom(
			final Integer n,
			final SimpleBinaryTree<B> tree
		) {
		return tree.accept(
			new SimpleBinaryTree.Visitor<B,Pair<Integer,BinaryTree<Integer,B>>>() {

				@Override
				public final Pair<Integer, BinaryTree<Integer, B>> node(
					   final SimpleBinaryTree<B> l,
					   final SimpleBinaryTree<B> r
					) {
					
					final
					Pair<Integer,BinaryTree<Integer,B>>
						l1 = labelFrom(n + 1,l);
					final
					Pair<Integer,BinaryTree<Integer,B>>
						r1 = labelFrom(l1.getFirst(),r);
					
					// return a pair of the final label and ...
					return pair(r1.getFirst(),
						// ... the binary-tree-node of the l1 and r1 labeled with n 
						IBinaryTree.<Integer,B> node(n,l1.getSecond(),r1.getSecond()));
				}

				@Override
				public final Pair<Integer, BinaryTree<Integer,B>> leaf(final B value) {
					return pair(n, IBinaryTree.<Integer,B> leaf(value));
				}
			
			}
		);
	}
	
}
