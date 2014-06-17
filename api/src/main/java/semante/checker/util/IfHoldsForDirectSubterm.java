package semante.checker.util;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IMaybe.nothing;
import lambdacalc.DeBruijn;
import lambdacalc.Index;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.checker.util.Direction;
import semante.pipeline.Maybe;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IfHoldsForDirectSubterm implements DeBruijn.Visitor<Maybe<DeBruijn>> {
		
	@NonNull
	Direction direction;
	
	@NonNull
	DeBruijn.Visitor<Maybe<DeBruijn>> delegate;
	
	@Override
	public final Maybe<DeBruijn> abstraction(Type arg0, DeBruijn arg1) {
		return arg1.accept(delegate);
	}

	@Override
	public final Maybe<DeBruijn> application(DeBruijn arg0, DeBruijn arg1) {
		switch (direction) {
		case Left:
			return arg0.accept(delegate);
		case Right:
			return arg1.accept(delegate);
		default:
			throw new RuntimeException("Panic! Null is not a valid direction!");
		}
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
