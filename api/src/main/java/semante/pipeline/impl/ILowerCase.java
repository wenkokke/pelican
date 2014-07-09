package semante.pipeline.impl;

import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;
import semante.pipeline.BinaryTree.Visitor;
import semante.pipeline.CaseModifier;

public class ILowerCase implements CaseModifier {

	@Override
	public <ID> BinaryTree<ID, Annotation<ID>> apply(
			BinaryTree<ID, Annotation<ID>> tree) {
		return tree.accept(new Visitor<ID, Annotation<ID>, BinaryTree<ID, Annotation<ID>>>() {

			@Override
			public BinaryTree<ID, Annotation<ID>> leaf(Annotation<ID> value) {
				return new IBinaryTreeBuilder<ID, Annotation<ID>>().leaf(
						new IAnnotation<ID>(value.getId(),value.getText().toLowerCase(),value.getCategory()));					
			}

			@Override
			public BinaryTree<ID, Annotation<ID>> node(ID value,
					BinaryTree<ID, Annotation<ID>> l,
					BinaryTree<ID, Annotation<ID>> r) {
				return new IBinaryTreeBuilder<ID, Annotation<ID>>().node(value,l.accept(this),r.accept(this));
			}
			
		});
	}

}
