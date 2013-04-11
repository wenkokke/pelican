package semante.lambdacalc.util;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Type;
import semante.lambdacalc.TypePrinter;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class ITypePrinter implements TypePrinter {
	
	@Override
	public final String format(Type type) {
		return type.accept(TypePrinterVisitor);
	}
	
	// visitor to print types:
	static Type.Visitor<String> TypePrinterVisitor = new Type.Visitor<String>() {

		@Override
		public final String typeConstant(String name) {
			return name;
		}

		@Override
		public String typeFunction(Type a, Type b) {
			final StringBuilder builder = new StringBuilder();
			if (a.accept(IsFunction)) {
				builder.append('(').append(a.accept(this)).append(')');
			} else {
				builder.append(a.accept(this));
			}
			return builder.append(b.accept(this)).toString();
		}
	};
	
	// visitor to determine complexity of type:
	static Type.Test IsFunction = new Type.Test.False() {
		@Override
		public final Boolean typeFunction(final Type _1, final Type _2) {
			return true;
		}
	};

}
