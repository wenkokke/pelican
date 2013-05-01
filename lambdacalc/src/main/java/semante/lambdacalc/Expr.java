package semante.lambdacalc;

import com.google.common.base.Optional;

public interface Expr<S extends Symbol> {
	<X> X accept(Visitor<S,X> v);
	
	public interface Visitor<S extends Symbol, X> {
		X abstraction	(S s, Expr<S> arg);
		X application	(Expr<S> f, Expr<S> arg);
		X variable		(S s);
	}
	
	public interface Identity<S extends Symbol> extends Visitor<S,Expr<S>> {}
	
	public class None<S extends Symbol,X> implements Visitor<S,Optional<X>> {
		@Override public Optional<X> abstraction(S s, Expr<S> arg) { return Optional.absent(); }
		@Override public Optional<X> application(Expr<S> f, Expr<S> arg) { return Optional.absent(); }
		@Override public Optional<X> variable(S s) { return Optional.absent(); }
	}
	
	public abstract class Test<S extends Symbol> implements Visitor<S,Boolean> {
		
		public static class True<S extends Symbol> extends Test<S> {
			@Override public Boolean abstraction(S s, Expr<S> arg) { return true; }
			@Override public Boolean application(Expr<S> f, Expr<S> arg) { return true; }
			@Override public Boolean variable(S s) { return true; }
		}
		
		public static class False<S extends Symbol> extends Test<S> {
			@Override public Boolean abstraction(S s, Expr<S> arg) { return false; }
			@Override public Boolean application(Expr<S> f, Expr<S> arg) { return false; }
			@Override public Boolean variable(S s) { return false; }
		}
		
		public static <S extends Symbol> Test<S> IsVariable() {
			return new False<S>() {
				@Override
				public final Boolean variable(S s) {
					return true;
				}
			};
		}
		
		public static <S extends Symbol> Test<S> IsApplication() {
			return new False<S>() {
				@Override
				public final Boolean application(final Expr<S> l, final Expr<S> r) {
					return true;
				}
			};
		}
		
		public static <S extends Symbol> Test<S> IsAbstraction() {
			return new False<S>() {
				@Override
				public final Boolean abstraction(final S s, final Expr<S> expr) {
					return true;
				}
			};
		}
	}

}
