package semante.checker.util;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static semante.pipeline.impl.IMaybe.nothing;
import lambdacalc.DeBruijn;
import lambdacalc.DeBruijnBuilder;
import lambdacalc.Index;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.pipeline.Maybe;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IfAny implements DeBruijn.Visitor<Maybe<DeBruijn>> {

	@NonNull
	DeBruijnBuilder builder;
	
	@NonNull
	List<DeBruijn.Visitor<Maybe<DeBruijn>>> delegates;
	
	public final Maybe<DeBruijn> tryAll(DeBruijn term) {
		for (val delegate : delegates) {
			val here = term.accept(delegate);
			if (here.isJust()) return here;
		}
		return nothing();
	}

	@Override
	public final Maybe<DeBruijn> abstraction(Type arg0, DeBruijn arg1) {
		return tryAll(builder.abstraction(arg0, arg1));
	}

	@Override
	public final Maybe<DeBruijn> application(DeBruijn arg0, DeBruijn arg1) {
		return tryAll(builder.application(arg0, arg1));
	}

	@Override
	public final Maybe<DeBruijn> constant(Symbol arg0) {
		return tryAll(builder.constant(arg0));
	}

	@Override
	public final Maybe<DeBruijn> variable(Index arg0) {
		return tryAll(builder.variable(arg0));
	}	
}
