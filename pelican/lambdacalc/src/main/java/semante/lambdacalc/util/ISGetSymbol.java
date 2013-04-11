package semante.lambdacalc.util;

import static com.google.common.collect.Iterables.filter;
import static lombok.AccessLevel.PRIVATE;

import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Expr;
import semante.lambdacalc.Symbol;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;

public abstract class ISGetSymbol<S extends Symbol> implements Expr.Visitor<S,Set<S>> {
	
	public static final class All<S extends Symbol> extends ISGetSymbol<S> {
		/**
		 * When encountering a lambda abstraction, return the vars found in its sub
		 * expression.
		 */
		@Override
		public final Set<S> abstraction(final S s, final Expr<S> expr) {
			return expr.accept(this);
		}	
	}
	
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true, level=PRIVATE)
	public static final class Free<S extends Symbol> extends ISGetSymbol<S> {
		Equality<S> eqSymbol;
		
		/**
		 * When encountering a lambda abstraction, filter the name that was locally
		 * bound from the free vars in the sub expression.
		 */
		@Override
		public final Set<S> abstraction(final S s1, final Expr<S> expr) {
			return ImmutableSet.copyOf(
				filter(expr.accept(this),
					new Predicate<S>() {
						@Override
						public final boolean apply(final S s2) {
							return eqSymbol.apply(s1, s2);
						}
				}));
		}	
	}
	
	
	/**
	 * When encountering a variable, return its name.
	 */
	@Override
	public final Set<S> variable(final S s) {
		return ImmutableSet.of(s);
	}

	/**
	 * When encountering an application, merge the free vars coming from the two
	 * sub expressions.
	 */
	@Override
	public final Set<S> application(final Expr<S> l, final Expr<S> r) {
		val builder = ImmutableSet.<S> builder();
		builder.addAll(l.accept(this));
		builder.addAll(r.accept(this));
		return builder.build();
	}
}
