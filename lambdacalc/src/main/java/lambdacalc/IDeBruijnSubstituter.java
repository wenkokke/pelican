package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IDeBruijnSubstituter implements DeBruijnSubstituter {

	DeBruijnBuilder builder;
	
	@Override
	public final DeBruijn substituteTop(DeBruijn expr, DeBruijn subject) {
		return subject.accept(new ISubstituteTop(expr,0));
	}

	@Override
	public final DeBruijn substituteFree(DeBruijn expr, String name, DeBruijn subject) {
		return subject.accept(new ISubstituteFree(expr,name));
	}
	
	@Override
	public final DeBruijn renameFree(String from, String to, DeBruijn subject) {
		return subject.accept(new IRenameFree(from,to));
	}
	
	@Value
	private final class IRenameFree implements DeBruijnBuilder {
		
		String from,to;
		
		@Override
		public final DeBruijn abstraction(Type type, DeBruijn body) {
			return builder.abstraction(type, body.accept(this));
		}

		@Override
		public final DeBruijn application(DeBruijn fun, DeBruijn arg) {
			return builder.application(fun.accept(this), arg.accept(this));
		}

		@Override
		public final DeBruijn variable(Index i) {
			return builder.variable(i);
		}

		@Override
		public final DeBruijn constant(Symbol s) {
			if (s.getName().equals(from)) {
				return builder.constant(new ISymbol(to,s.getType()));
			}
			else {
				return builder.constant(s);
			}
		}
		
	}
	
	@Value
	private final class ISubstituteFree implements DeBruijnBuilder {
		
		DeBruijn expr; String name;
		
		@Override
		public final DeBruijn abstraction(Type type, DeBruijn body) {
			return builder.abstraction(type, body.accept(this));
		}

		@Override
		public final DeBruijn application(DeBruijn fun, DeBruijn arg) {
			return builder.application(fun.accept(this), arg.accept(this));
		}

		@Override
		public final DeBruijn variable(Index i) {
			return builder.variable(i);
		}

		@Override
		public final DeBruijn constant(Symbol s) {
			if (s.getName().equals(name)) {
				return expr;
			}
			else {
				return builder.constant(s);
			}
		}
		
	}
	
	@Value
	private final class ISubstituteTop implements DeBruijnBuilder {

		DeBruijn expr; Integer n;
		
		@Override
		public final DeBruijn abstraction(Type type, DeBruijn body) {
			return builder.abstraction(type, body.accept(withN(n + 1)));
		}
		@Override
		public final DeBruijn application(DeBruijn fun, DeBruijn arg) {
			return builder.application(fun.accept(this), arg.accept(this));
		}
		@Override
		public final DeBruijn variable(Index index) {
			if (index.getIndex().equals(n)) {
				return expr;
			}
			else {
				return builder.variable(index);
			}
		}
		@Override
		public final DeBruijn constant(Symbol symbol) {
			return builder.constant(symbol);
		}	
	}
}
