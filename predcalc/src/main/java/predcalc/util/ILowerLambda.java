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
			// If there's a (et)et constant applied to an et constant, we replace it
			if (lcalc.typeOf(fun2).equals(Types.ET_ET) && lcalc.typeOf(arg2).equals(Types.ET)) {
				if (fun2.accept(isConstant) && arg2.accept(isConstant)) {
					return lcalc.getExprBuilder().variable(buildSymbol("p"+app.hashCode(), Types.ET));
				} else {
					throw new HigherOrderError("Non-constants of type (et)et and (et): "+fun2+" "+arg2);
				}
			}
			return app;
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
	
	public Expr rewrite(Expr a) {
		return a.accept(rewriter);
	}

}