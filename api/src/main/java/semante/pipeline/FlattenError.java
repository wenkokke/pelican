package semante.pipeline;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;

public abstract class FlattenError extends IllegalArgumentException {
	
	public abstract Object getId(); 
	
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class NoLexicalEntry extends FlattenError {
		
		String category;
		
		@Override
		public final Object getId() {
			return -1;
		}
		
		@Override
		public final String getMessage() {
			return String.format("no lexical entry found for annotation '%s'", category);
		}
		
	}
	
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class NoPossibleTerm extends FlattenError {

		@Getter
		Object id;
		STL stl;
		List<Pair<DeBruijn,DeBruijn>> pairs;

		@Override
		public final String getMessage() {
			val builder = new StringBuilder();
			builder.append("cannot construct application from terms ");
			for (val pair : pairs) {
				builder.append("(");
				val l = pair.getFirst();
				builder.append(stl.format(l));
				builder.append(" of type ");
				builder.append(stl.format(stl.typeOf(l)));
				builder.append(" and ");
				val r = pair.getSecond();
				builder.append(stl.format(r));
				builder.append(" of type ");
				builder.append(stl.format(stl.typeOf(r)));
				builder.append("), ");
			}
			builder.append("aborting flattening.");
			return builder.toString();
		}
	}
	
}
