package semante.stl.expr;

import semante.stl.Expr;
import semante.stl.Symbol;
import semante.stl.Expr.Visitor;

public abstract class Predicate implements Visitor<Boolean> {
		
		public static class True extends Predicate {
			@Override public Boolean abstraction(Symbol sym, Expr arg)     { return true; }
			@Override public Boolean application(Expr fun, Expr arg) { return true; }
			@Override public Boolean variable(Symbol s)                          { return true; }
		}
		
		public static class False extends Predicate {
			@Override public Boolean abstraction(Symbol sym, Expr arg)     { return false; }
			@Override public Boolean application(Expr fun, Expr arg) { return false; }
			@Override public Boolean variable(Symbol s)                          { return false; }
		}

		
		public static final Predicate IsAbstraction() {
			return new False() {
				@Override
				public final Boolean abstraction(final Symbol sym, final Expr arg) {
					return true;
				}
			};
		}
		
		public static final Predicate IsApplication() {
			return new False() {
				@Override
				public final Boolean application(final Expr fun, final Expr arg) {
					return true;
				}
			};
		}
		
		public static final Predicate IsVariable() {
			return new False() {
				@Override
				public final Boolean variable(Symbol s) {
					return true;
				}
			};
		}
	}