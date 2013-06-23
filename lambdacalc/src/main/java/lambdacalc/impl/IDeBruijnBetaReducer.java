package lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.DeBruijn;
import lambdacalc.DeBruijnBetaReducer;
import lambdacalc.DeBruijnBuilder;
import lambdacalc.DeBruijnMatcher;
import lambdacalc.DeBruijnPredicate;
import lambdacalc.DeBruijnSubstituter;
import lambdacalc.Index;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IDeBruijnBetaReducer implements DeBruijnBuilder, DeBruijnBetaReducer {

	DeBruijnSubstituter	substituter;
	DeBruijnBuilder		builder;
	
	@Override
	public final DeBruijn betaReduce(DeBruijn expr) {
		return expr.accept(this);
	}

	// match e0 with (e1 e2)
	@Override
	public final DeBruijn application(final DeBruijn e1, final DeBruijn e2) {
		return e1.accept(new DeBruijnMatcher<DeBruijn>() {
			
			// match e1 with (\x:t.e)
			@Override
			public final DeBruijn abstraction(final Type _, final DeBruijn e1) {
				return betaReduce(substituter.substituteTop(e2, e1));
			}
			
			// otherwise
			@Override
			protected final DeBruijn otherwise() {
				val e1 = betaReduce(e1);
				if (e1.accept(DeBruijnPredicate.IsAbstraction)) {
					return betaReduce(builder.application(e1,e2));
				}
				else {
					val e2 = betaReduce(e2);
					return builder.application(e1,e2);
				}
			}
		});
	}
	
	// otherwise recurse and reconstruct
	@Override
	public final DeBruijn abstraction(final Type t, final DeBruijn e1) {
		return builder.abstraction(t, betaReduce(e1));
	}
	@Override
	public final DeBruijn variable(final Index i) {
		return builder.variable(i);
	}
	@Override
	public final DeBruijn constant(final Symbol s) {
		return builder.constant(s);
	}
}
