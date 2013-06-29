package predcalc.util;

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
			
			val ee = lcalc.getTypeBuilder().function(Types.E, Types.E); // TODO BUGFIX
			val e_et_et = lcalc.getTypeBuilder().function(Types.E, Types.ET_ET);
			
			if (lcalc.typeOf(fun2).equals(Types.ET_ET) && (lcalc.typeOf(arg2).equals(Types.ET) || lcalc.typeOf(arg2).equals(ee))) {
				// If there's a (et)et constant applied to an et constant, we replace it by a fresh et constant
				if (fun2.accept(isConstant) /*&& arg2.accept(isConstant)*/) {
					return lcalc.getExprBuilder().variable(buildSymbol(app.accept(namer), Types.ET));
				} else {
					throw new HigherOrderError("Non-constants of type (et)et and (et): "+fun2+" "+arg2);
				}
			} else if (lcalc.typeOf(fun2).equals(e_et_et) && lcalc.typeOf(arg2).equals(Types.E)) {
				// If there's a e(et)et constant applied to an e constant, we replace it by a fresh (et)et constant
				if (fun2.accept(isConstant) && arg2.accept(isConstant)) {
					return lcalc.getExprBuilder().variable(buildSymbol(app.accept(namer), Types.ET_ET));
				} else {
					throw new HigherOrderError("Non-constants of type (et)et and (et): "+fun2+" "+arg2);
				}
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
//		System.err.println("rewrote " + lcalc.format(a) + " to " + lcalc.format(o));
		return o;
	}

}