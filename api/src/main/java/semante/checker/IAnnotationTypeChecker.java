package semante.checker;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lambdacalc.STL;
import lambdacalc.Type;
import lambdacalc.TypeError;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lexicon.RichLexicon;
import semante.pipeline.Annotation;
import semante.pipeline.BinaryTree;

import com.google.common.collect.ImmutableList;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IAnnotationTypeChecker<ID> implements AnnotationTypeChecker<ID> {

	STL stl;
	RichLexicon lexicon;
	
	@Override
	public void checkTypeWithError(BinaryTree<ID, Annotation<ID>> tree)
		throws IllegalAnnotationException {
		tree.accept(new Helper());
	}

	@Override
	public boolean checkType(BinaryTree<ID, Annotation<ID>> tree) {
		try {
			checkTypeWithError(tree);
			return true;
		}
		catch (IllegalAnnotationException e) {
			return false;
		}
	}
	
	private final class Helper implements
			BinaryTree.Visitor<ID, Annotation<ID>, List<Type>> {

		@Override
		public final List<Type> leaf(
				final Annotation<ID> ann) {
			val denotations = lexicon.getEntry(
					ann.getCategory(), ann.getText()).getDenotations();
			val builder = ImmutableList.<Type> builder();
			for (val denotation : denotations) {
				builder.add(stl.typeOf(denotation));
			}
			return builder.build();
		}

		@Override
		public final List<Type> node(
				final ID id,
				final BinaryTree<ID, Annotation<ID>> left,
				final BinaryTree<ID, Annotation<ID>> right) {
			val leftTypes = left.accept(this);
			val rightTypes = right.accept(this);
			val builder = ImmutableList.<Type> builder();
			for (val leftType : leftTypes) {
				for (val rightType : rightTypes) {
					try {
						builder.add(stl.applyType(leftType, rightType));
					}
					catch (TypeError e) {}
					try {
						builder.add(stl.applyType(rightType, leftType));
					}
					catch (TypeError e) {}
				}
			}
			val types = builder.build();
			if (types.isEmpty()) {
				throw new IllegalAnnotationException(id,
					String.format("type error; cannot compose types [%s] and [%s]",
						toString(leftTypes), toString(rightTypes)));
			}
			else {
				return types;
			}
		}

		private String toString(List<Type> types) {
			StringBuilder buffer = new StringBuilder();
			if (types.size()>0) {
				for (val leftType : types) {
					buffer.append(stl.format(leftType));
					buffer.append(", ");
				}
				buffer.delete(buffer.length()-2, buffer.length());
			}
			return buffer.toString();
		}
		
		

	}
}
