package lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.DeBruijn;
import lambdacalc.DeBruijnBetaReducer;
import lambdacalc.DeBruijnBuilder;
import lambdacalc.DeBruijnSubstituter;
import lambdacalc.Index;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IDeBruijnBetaReducer extends IDeBruijnBuilder implements DeBruijnBuilder, DeBruijnBetaReducer {

	DeBruijnSubstituter substituter;
	
	@Override
	public final DeBruijn betaReduce(DeBruijn expr) {
		return expr.accept(this);
	}

	@Override
	public final DeBruijn application(final DeBruijn fun0, final DeBruijn arg0) {
		
		// betaReduce the components
		val fun1 = betaReduce(fun0);
		val arg1 = betaReduce(arg0);
		
		// attempt to betaReduce the current application
		return fun1.accept(new IDeBruijnBuilder() {
			@Override
			public final DeBruijn abstraction(final Type type, final DeBruijn body) {
				return substituter.substituteTop(arg1, body);
			}

			@Override
			public final DeBruijn application(DeBruijn _1, DeBruijn _2) {
				return super.application(fun1, arg1); // reconstruct application
			}
			@Override
			public final DeBruijn variable(Index _) {
				return super.application(fun1, arg1); // reconstruct application
			}
			@Override
			public final DeBruijn constant(Symbol _) {
				return super.application(fun1, arg1); // reconstruct application
			}
		});
	}
	@Override
	public final DeBruijn abstraction(final Type type, final DeBruijn body) {
		return super.abstraction(type, betaReduce(body));
	}
}
