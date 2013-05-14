package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import semante.lambdacalc.Expr;
import semante.lambdacalc.ExprPrinter;
import semante.lambdacalc.Symbol;
import semante.lambdacalc.util.ISExprPrinter;

@FieldDefaults(makeFinal=true,level=PRIVATE)
public abstract class IExpr<S extends Symbol> implements Expr<S> {
	
	private final ExprPrinter<S> PRINTER = new ISExprPrinter<S>(); 
	
	@Override
	public final String toString() {
		return PRINTER.format(this);
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class Abstraction<S extends Symbol> extends IExpr<S> {
		S s; Expr<S> arg;
		
		@Override
		public final <X> X accept(Visitor<S,X> v) {
			return v.abstraction(s, arg);
		}
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class Application<S extends Symbol> extends IExpr<S> {
		Expr<S> f; Expr<S> arg;
		
		@Override
		public final <X> X accept(Visitor<S,X> v) {
			return v.application(f, arg);
		}
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class Variable<S extends Symbol> extends IExpr<S> {
		S s;

		@Override
		public final <X> X accept(Visitor<S,X> v) {
			return v.variable(s);
		}
	}
	
	public static class Identity<S extends Symbol> implements semante.lambdacalc.Expr.Identity<S> {
		
		@Override
		public Expr<S> abstraction(S s, Expr<S> arg) {
			return new Abstraction<S>(s,arg);
		}
		
		@Override
		public Expr<S> application(Expr<S> f, Expr<S> arg) {
			return new Application<S>(f,arg);
		}
		
		@Override
		public Expr<S> variable(S s) {
			return new Variable<S>(s);
		}	
	}
}
