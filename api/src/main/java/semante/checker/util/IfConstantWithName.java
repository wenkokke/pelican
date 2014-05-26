package semante.checker.util;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IMaybe.just;
import static semante.pipeline.impl.IMaybe.nothing;
import lambdacalc.DeBruijn;
import lambdacalc.DeBruijnBuilder;
import lambdacalc.Index;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.pipeline.Maybe;

import com.google.common.base.Predicate;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IfConstantWithName implements Predicate<DeBruijn>, DeBruijn.Visitor<Maybe<DeBruijn>> {

	DeBruijnBuilder builder;
	String name;

	@Override
	public final boolean apply(DeBruijn arg0) {
		return arg0.accept(this).isJust();
	}

	@Override
	public final Maybe<DeBruijn> constant(Symbol arg0) {
		if (arg0.getName().equals(name)) {
			return just(builder.constant(arg0));
		}
		else {
			return nothing();
		}
	}

	@Override
	public final Maybe<DeBruijn> abstraction(Type arg0, DeBruijn arg1) {
		return nothing();
	}

	@Override
	public final Maybe<DeBruijn> application(DeBruijn arg0, DeBruijn arg1) {
		return nothing();
	}

	@Override
	public final Maybe<DeBruijn> variable(Index arg0) {
		return nothing();
	}
}
