package predcalc.impl;

import lambdacalc.Expr;
import lambdacalc.Expr.Visitor;
import lambdacalc.ExprBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.Types;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import predcalc.HigherOrderError;
import predcalc.LowerLambda;


@RequiredArgsConstructor
@FieldDefaults(makeFinal=true)
public class ILowerLambda implements LowerLambda {

	protected STL lcalc;
	
	private ExprBuilder rewriter = new ExprBuilder() {

		@Override
		public Expr application(Expr fun, Expr arg) {
			val fun2 = fun.accept(rewriter);
			val arg2 = arg.accept(rewriter);
			val app = lcalc.getExprBuilder().application(fun2, arg2);
						
			if (lcalc.typeOf(fun2).equals(Types.ET_ET) && lcalc.typeOf(arg2).equals(Types.ET)) {
				return fun2.accept(new ExprBuilder() {

						@Override
						public Expr abstraction(Symbol s, Expr body) {
							throw new HigherOrderError("Abstraction of type (et)et: "+lcalc.format(fun2));
						}

						@Override
						public Expr application(Expr fun3, Expr arg3) {
							// If there's a e(et)et constant applied to an e constant, we make a fresh eet constant
							return lcalc.getExprBuilder().application(
								   lcalc.getExprBuilder().variable(buildSymbol(app.accept(namer), Types.EET)),
								   lcalc.getExprBuilder().variable(buildSymbol(arg3.accept(namer), Types.E))
								);
						}

						@Override
						public Expr variable(Symbol s) {
							// If there's a (et)et constant applied to an et constant, we replace it by a fresh et constant
							return lcalc.getExprBuilder().variable(buildSymbol(app.accept(namer), Types.ET));
						}
					});
			} else {
				return app;
			}
		}
		
		@Override
		public Expr abstraction(Symbol s, Expr body) {
			return lcalc.getExprBuilder().abstraction(s, body.accept(rewriter));
		}

		@Override
		public Expr variable(Symbol s) {
			return lcalc.getExprBuilder().variable(s);
		}
	};
	
	private Visitor<Boolean> isConstant = new Visitor<Boolean>() {

		@Override
		public Boolean abstraction(Symbol s, Expr body) {
			return false;
		}

		@Override
		public Boolean application(Expr fun, Expr arg) {
			return false;
		}

		@Override
		public Boolean variable(Symbol s) {
			return true;
		}
	};
	
	Symbol buildSymbol(final String name, final Type type) {
		return new Symbol() {
			@Override public String getName() { return name; }
			@Override public Type getType() { return type; }
		};
	}
	
	private Visitor<String> namer = new Visitor<String>() {
		// Concatenate all constants of not type e
		@Override public String abstraction(Symbol s, Expr body) { return body.accept(namer); }
		@Override public String application(Expr fun, Expr arg) {
			if (arg.accept(new Visitor<Boolean>() {
				@Override public Boolean abstraction(Symbol s, Expr body) { 
					return body.accept(new Visitor<Boolean>() {
						@Override public Boolean abstraction(Symbol s, Expr body) { return false; }
						@Override public Boolean application(Expr fun, Expr arg) { return false; }
						@Override public Boolean variable(Symbol s) { return s.getType().equals(Types.T); }
						});
				}
				@Override public Boolean application(Expr fun, Expr arg) { return false; }
				@Override public Boolean variable(Symbol s) { return false; }
				})) {
				return fun.accept(namer);
			}
			return fun.accept(namer)+((arg.accept(isConstant) && lcalc.typeOf(arg).equals(Types.E))? "" : "_"+arg.accept(namer));
		}
		@Override public String variable(Symbol s) { return s.getName(); }
	};
	
	String name(final Expr exp) {
		return "p"+exp.hashCode();
	}
	
	public Expr rewrite(Expr a) {
		Expr o = a.accept(rewriter);
		return o;
	}

}