package semante.checker.util;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IMaybe.nothing;
import lambdacalc.DeBruijn;
import lambdacalc.Index;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.pipeline.Maybe;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IfHoldsForSubterm implements DeBruijn.Visitor<Maybe<DeBruijn>> {
		
	@NonNull
	DeBruijn.Visitor<Maybe<DeBruijn>> delegate;
	
	@Override
	public final Maybe<DeBruijn> abstraction(Type arg0, DeBruijn arg1) {
		val now1 = arg1.accept(delegate);
		if (now1.isJust()) return now1;
		return arg1.accept(this);
	}

	@Override
	public final Maybe<DeBruijn> application(DeBruijn arg0, DeBruijn arg1) {
		val now0 = arg0.accept(delegate);
		if (now0.isJust()) return now0;
		val now1 = arg1.accept(delegate);
		if (now1.isJust()) return now1;
		val rec0 = arg0.accept(this);
		if (rec0.isJust()) return rec0;
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
