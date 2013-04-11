package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Expr;
import semante.lambdacalc.Substituter;
import semante.lambdacalc.Symbol;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class ISSubstituter<S extends Symbol> implements Substituter<S> {

	Equality<S> equals;
	
	@Override
	public final Expr<S> substitute(S s1, Expr<S> e2, Expr<S> subject) {
		return subject.accept(new SubstitutionVisitor(s1,e2));
	}
	
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true, level=PRIVATE)
	private final class SubstitutionVisitor extends IExpr.Identity<S> {
		S s1;
		Expr<S> e2;
		
		@Override
		public final Expr<S> variable(S s2) {
			if (isMatch(s2)) {
				return e2; // return replacement exp
			}
			else {
				return super.variable(s2); // return identity
			}
		}

		@Override
		public final Expr<S> abstraction(S s2, Expr<S> expr) {
			if (isMatch(s2)) {
				// symbol s1 is rebound: stop 
				return super.abstraction(s2, expr);
			}
			else {
				return super.abstraction(s2, expr.accept(this));
			}
		}

		@Override
		public final Expr<S> application(final Expr<S> l, final Expr<S> r) {
			return super.application(l.accept(this), r.accept(this));
		}

		private final boolean isMatch(S s2) {
			return equals.apply(s1, s2);
		}
	}
}
