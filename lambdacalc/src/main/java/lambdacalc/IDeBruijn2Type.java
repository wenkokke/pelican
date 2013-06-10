package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.DeBruijn.Visitor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IDeBruijn2Type implements Visitor<Type>, DeBruijn2Type {

	TypeBuilder builder;
	
	@Override
	public final Type typeOf(DeBruijn expr) {
		return expr.accept(this);
	}

	@Override
	public final Type abstraction(Type type, DeBruijn body) {
		return builder.function(type,body.accept(this));
	}

	@Override
	public final Type application(DeBruijn fun, DeBruijn arg) {
		return arg.accept(this);
	}

	@Override
	public final Type variable(Index i) {
		return i.getType();
	}

	@Override
	public final Type constant(Symbol s) {
		return s.getType();
	}

}
