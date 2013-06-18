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
import lombok.experimental.Value;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IDeBruijnBetaReducer implements DeBruijnBuilder, DeBruijnBetaReducer {

	DeBruijnSubstituter	substituter;
	DeBruijnBuilder		builder;
	
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
				
				// apply beta-reduction to the body
				val exp0 = substituter.substituteTop(arg1, body);
				// decrement all indices by one to reflect that removed lambda
				val exp1 = exp0.accept(new IDecrementer(0));
						
				return betaReduce(exp1);
			}
			
			// recurse, and reconstruct expression
			
			@Override
			public final DeBruijn application(final DeBruijn _1, final DeBruijn _2) {
				return builder.application(fun1, arg1);
			}
			@Override
			public final DeBruijn variable(final Index _) {
				return builder.application(fun1, arg1);
			}
			@Override
			public final DeBruijn constant(final Symbol _) {
				return builder.application(fun1, arg1);
			}
		});
	}
	
	// recurse, and reconstruct expression
	
	@Override
	public final DeBruijn abstraction(final Type type, final DeBruijn body) {
		return builder.abstraction(type, betaReduce(body));
	}
	@Override
	public final DeBruijn variable(final Index i) {
		return builder.variable(i);
	}
	@Override
	public final DeBruijn constant(final Symbol s) {
		return builder.constant(s);
	}
	
	// visitor that decrements all indices by one if they bind higher that a
	// certain index (usually 0);
	@Value
	private final class IDecrementer implements DeBruijnBuilder {

		Integer depth;
		
		// decrement variable indexes;
		
		@Override
		public final DeBruijn variable(final Index i) {
			if (i.getIndex() > depth) {
				return builder.variable(i.withIndex(i.getIndex() - 1));
			}
			else {
				return builder.variable(i);
			}
		}
		
		// increase the depth of the context;
		
		@Override
		public final DeBruijn abstraction(final Type type, final DeBruijn body) {
			return builder.abstraction(type, body.accept(withDepth(depth + 1)));
		}
		
		// recurse, and reconstruct expression;
		
		@Override
		public final DeBruijn application(final DeBruijn fun, final DeBruijn arg) {
			return builder.application(fun.accept(this), arg.accept(this));
		}
		@Override
		public final DeBruijn constant(Symbol s) {
			return builder.constant(s);
		}
	}
}
