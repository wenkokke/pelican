package lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lambdacalc.DeBruijn;
import lambdacalc.DeBruijn2Type;
import lambdacalc.DeBruijnBetaReducer;
import lambdacalc.DeBruijnBuilder;
import lambdacalc.DeBruijnMatcher;
import lambdacalc.Index;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IDeBruijnBetaReducer implements DeBruijnBuilder, DeBruijnBetaReducer {

	STL					stl;
	DeBruijnBuilder		builder;
	DeBruijn2Type		typer;
	
	@Override
	public final DeBruijn betaReduce(DeBruijn exp) {
		val red = exp.accept(this);
		if (red.equals(exp)) {
			return red;
		}
		else {
			return betaReduce(red);
		}
	}

	// match e0 with (e1 e2)
	@Override
	public final DeBruijn application(final DeBruijn exp1, final DeBruijn exp2) {
		return exp1.accept(new DeBruijnMatcher<DeBruijn>() {
			
			// match e1 with (\x:t.e1)
			@Override
			public final DeBruijn abstraction(final Type lambdaType, final DeBruijn exp1) {
				checkIfTypesAreCompatible(exp1,lambdaType,exp2);
				val sub1 = exp1.accept(new ISubsituter(exp2,0));
				return sub1; 
			}
			
			// otherwise
			@Override
			protected final DeBruijn otherwise() {
				val red1 = exp1.accept(IDeBruijnBetaReducer.this);
				if (!red1.equals(exp1)) {
					return builder.application(red1, exp2);
				}
				else {
					val red2 = exp2.accept(IDeBruijnBetaReducer.this);
					return builder.application(red1, red2);
				}
			}
		});
	}
	
	// otherwise recurse and reconstruct
	@Override
	public final DeBruijn abstraction(final Type t, final DeBruijn e1) {
		return builder.abstraction(t, e1.accept(IDeBruijnBetaReducer.this));
	}
	@Override
	public final DeBruijn variable(final Index i) {
		return builder.variable(i);
	}
	@Override
	public final DeBruijn constant(final Symbol s) {
		return builder.constant(s);
	}
	
	@Value
	private final class ISubsituter implements DeBruijnBuilder {

		DeBruijn	expr;
		Integer		depth;
		
		@Override
		public final DeBruijn abstraction(final Type type, final DeBruijn e1) {
			return builder.abstraction(type, e1.accept(withDepth(depth + 1)));
		}
		@Override
		public final DeBruijn application(final DeBruijn e1, final DeBruijn e2) {
			return builder.application(e1.accept(this), e2.accept(this));
		}
		@Override
		public final DeBruijn variable(final Index i) {
			if (i.getIndex().compareTo(depth) > 0) {
				// decrement the index to reflect the removal of a lambda;
				return builder.variable(i.withIndex(i.getIndex() - 1));
			}
			else
			if (i.getIndex().equals(depth)) {
				return expr.accept(new IAdjuster(depth,0));
			}
			else {
				// return the index unchanged;
				return builder.variable(i);
			}
		}
		@Override
		public final DeBruijn constant(final Symbol s) {
			return builder.constant(s);
		}
	}
	
	@Value
	private final class IAdjuster implements DeBruijnBuilder {
		
		Integer adjustment;
		Integer depth;

		@Override
		public final DeBruijn abstraction(final Type type, final DeBruijn e1) {
			return builder.abstraction(type, e1.accept(withDepth(depth + 1)));
		}
		@Override
		public final DeBruijn application(final DeBruijn e1, final DeBruijn e2) {
			return builder.application(e1.accept(this), e2.accept(this));
		}
		@Override
		public final DeBruijn variable(final Index i) {
			if (i.getIndex() > depth) {
				return builder.variable(i.withIndex(i.getIndex() + adjustment));
			}
			else {
				return builder.variable(i);
			}
			
		}
		@Override
		public final DeBruijn constant(final Symbol s) {
			return builder.constant(s);
		}
	}
	
	// warn: in case of unsafe type reduction
	private final void checkIfTypesAreCompatible(final DeBruijn lambdaBody, final Type lambdaType, final DeBruijn expr) {
		val exprType = typer.typeOf(expr);
		if (!lambdaType.equals(exprType)) {
			log.warning(String.format(
				"unsafe reduction for %s with term \"%s\" of type %s in \"%s\"\n",
				stl.format(lambdaType), stl.format(expr), stl.format(exprType), stl.format(lambdaBody)));
		}
	}
}
