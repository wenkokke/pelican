package semante.stl.expr.debruijn;

import semante.stl.Expr$DeBruijn;
import semante.stl.Symbol;
import semante.stl.Expr$DeBruijn.Visitor;

public abstract class Predicate implements Visitor<Boolean> {
		
		public static class True extends Predicate {
			@Override public Boolean abstraction(Expr$DeBruijn body)          { return true; }
			@Override public Boolean application(Expr$DeBruijn fun, Expr$DeBruijn arg) { return true; }
			@Override public Boolean variable(Integer i)             { return true; }
			@Override public Boolean constant(Symbol s)              { return true; }
		}
		
		public static class False extends Predicate {
			@Override public Boolean abstraction(Expr$DeBruijn body)          { return false; }
			@Override public Boolean application(Expr$DeBruijn fun, Expr$DeBruijn arg) { return false; }
			@Override public Boolean variable(Integer i)             { return false; }
			@Override public Boolean constant(Symbol s)              { return false; }
		}

		
		public static final Predicate IsAbstraction() {
			return new False() {
				@Override
				public final Boolean abstraction(final Expr$DeBruijn expr$DeBruijn) {
					return true;
				}
			};
		}
		
		public static final Predicate IsApplication() {
			return new False() {
				@Override
				public final Boolean application(final Expr$DeBruijn l, final Expr$DeBruijn r) {
					return true;
				}
			};
		}
		
		public static final Predicate IsVariable() {
			return new False() {
				@Override
				public final Boolean variable(Integer i) {
					return true;
				}
			};
		}
		
		public static final Predicate IsConstant() {
			return new False() {
				@Override
				public final Boolean constant(Symbol s) {
					return true;
				}
			};
		}
	}