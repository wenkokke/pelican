package semante.predcalc.util;

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Expr;
import semante.lambdacalc.Expr.Visitor;
import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;
import semante.lambdacalc.impl.ITypes;
import semante.predcalc.FOLExpr;
import semante.predcalc.FOLExpr.Formula;
import semante.predcalc.FOLExpr.Term;
import semante.predcalc.PredCalc;

/*
 * Convert a first-order lambda expression to predicate logic
 * types: <e>t, <t>, <e>, (et)t
 */

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true)
public class ILowLambda2Pred<T extends TSymbol> implements LowLambda2Pred<T> {

	protected PredCalc pcalc;
	protected TLambdaCalc<T> lcalc;
	
	@Override
	public final FOLExpr convert(final Expr<T> expr) {
		return null;
	}

	static String lookup(String in) {
		if (in.equals("EXISTS")) {
			return "exists";
		} else if (in.equals("FORALL")) {
			return "all";
		} else if (in.equals("AND")) {
			return "&";
		} else if (in.equals("OR")) {
			return "|";
		} else if (in.equals("IMPLIES")) {
			return "->";
		} else if (in.equals("EQUIVALENCES")) {
			return "->";
		} else if (in.equals("EQ")) {
			return "=";
		} else if (in.equals("NOT")) {
			return "-";
		} else if (in.equals("T")) {
			return "$T";
		} else if (in.equals("F")) {
			return "$F";
		} else {
			return in.replaceAll(" ", "_");
		}
	}

	Visitor<T,FOLExpr> smash() {
		return new Visitor<T, FOLExpr>() {
			@Override public FOLExpr variable(T s) {
				if (typeIs(s.getType(), ITypes.E)) {
					// Variable
					return new IFOLExpr.Variable(s.getName());
				} else if (typeVector(s.getType(), ITypes.T, ITypes.T)) {
					// Connective
					return new IFOLExpr.Connective(lookup(s.getName()), new ArrayList<Formula>());
				} else if (typeVector(s.getType(), ITypes.E, ITypes.T)) {
					// Predicate
					return new IFOLExpr.Predicate(lookup(s.getName()), new ArrayList<Term>());
				} else if (typeVector(s.getType(), ITypes.E, ITypes.E)) {
					// Function
					return new IFOLExpr.Function(lookup(s.getName()), new ArrayList<Term>());
				} else {
					throw new UnsupportedOperationException("Unknown type: "+ s.getType());
				}
			}
			
			@Override public FOLExpr application(final Expr<T> f, Expr<T> b) {
				if (typeIs(lcalc.typeOf(f), ITypes.ET_T)) {
					// Quantifier
					return b.accept(new Visitor<T,FOLExpr>(){
						@Override public FOLExpr abstraction(final T s2, final Expr<T> body2) {
							// .. if the right is an abstraction, return a new abstraction
							Term v = new IFOLExpr.Variable(s2.getName());
							return new IFOLExpr.Quantifier(f.accept(getName), v, (Formula) convert(body2));
						}
						@Override public FOLExpr application(Expr<T> f2, Expr<T> arg2)
							{ throw new Error("Non-abstraction in quantifier: " + f2 + " " + arg2); }
						@Override public FOLExpr variable(T s2)
							{ throw new Error("Non-abstraction in quantifier: " + s2); }
					});
				} else {
					// Any application
					return f.accept(smash()).add(b.accept(smash()));
				}
			}
			
			@Override public FOLExpr abstraction(T s, Expr<T> arg) {
				throw new Error("Abstractions are higher order: " + s + " " + arg);
			}

			Visitor<T,String> getName = new Visitor<T,String>() {
				@Override public String abstraction(T s, Expr<T> arg) { return null; }
				@Override public String application(Expr<T> f, Expr<T> arg) { return null; }
				@Override public String variable(T s) { return s.getName(); }
			};
			
		};
	}
	
	Boolean typeIs(Type s, Type t) {
		return lcalc.eqType().apply(s, t);
	}
	
	Boolean typeVector(final Type t, final Type vector, final Type end) {
		if (lcalc.eqType().apply(t, end)) {
			return true;
		} else {
			return t.accept(new Type.Visitor<Boolean>() {
				@Override public Boolean typeConstant(String name) {
					return false;
				}
				@Override public Boolean typeFunction(Type a, Type b) {
					return lcalc.eqType().apply(a, vector) && typeVector(t, b, end);
				}
			});
		}		
	};
	
}