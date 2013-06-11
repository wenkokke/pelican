package predcalc.util;

import java.util.ArrayList;

import predcalc.FOLExpr;
import predcalc.LowLambda2Pred;
import predcalc.PredCalc;
import predcalc.FOLExpr.Formula;
import predcalc.FOLExpr.Term;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lambdacalc.Expr;
import lambdacalc.Expr.Visitor;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.IType;

/*
 * Convert a first-order lambda expression to predicate logic
 * types: <e>t, , <e>, (et)t
 */

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true)
public class ILowLambda2Pred implements LowLambda2Pred {

	protected PredCalc pcalc;
	protected STL lcalc;
	
	private Visitor<FOLExpr> smash = new Visitor<FOLExpr>() {
		@Override public FOLExpr variable(Symbol s) {
			if (typeIs(s.getType(), IType.E)) {
				// Variable
				return new IFOLExpr.Variable(s.getName());
			} else if (typeVector(s.getType(), IType.T,  IType.T)) {
				// Connective
				return new IFOLExpr.Connective(lookup(s.getName()), new ArrayList<Formula>());
			} else if (typeVector(s.getType(), IType.E, IType.T)) {
				// Predicate
				return new IFOLExpr.Predicate(lookup(s.getName()), new ArrayList<Term>());
			} else if (typeVector(s.getType(), IType.E, IType.E)) {
				// Function
				return new IFOLExpr.Function(lookup(s.getName()), new ArrayList<Term>());
			} else {
				throw new UnsupportedOperationException("Unknown type: "+ s.getType());
			}
		}
		
		@Override public FOLExpr application(final Expr f, final Expr b) {
			// Look for a quantifier
			return b.accept(new Visitor<FOLExpr>(){
				// Abstraction -> quantifier
				@Override public FOLExpr abstraction(final Symbol s2, final Expr body2) {
					// .. if the right is an abstraction, return a new abstraction
					if (typeIs(lcalc.typeOf(f), IType.ET_T)) {
						Term v = new IFOLExpr.Variable(s2.getName());
						return new IFOLExpr.Quantifier(lookup(f.accept(getName)), v, (Formula) convert(body2));
					} else {
						{ throw new Error("Abstractions are higher order: " + s2 + " " + body2); }
					}
				}
				// Normal Application
				@Override public FOLExpr application(Expr f2, Expr arg2)
					{ return f.accept(smash).add(b.accept(smash)); }
				@Override public FOLExpr variable(Symbol s2)
					{ return f.accept(smash).add(b.accept(smash)); }
			});
		}
		
		@Override public FOLExpr abstraction(Symbol s, Expr arg) {
			throw new Error("Abstractions are higher order: " + s + " " + arg);
		}

		Visitor<String> getName = new Visitor<String>() {
			@Override public String abstraction(Symbol s, Expr arg) { return null; }
			@Override public String application(Expr f, Expr arg) { return null; }
			@Override public String variable(Symbol s) { return s.getName(); }
		};
		
	};
	
	@Override
	public final FOLExpr convert(final Expr expr) {
		return expr.accept(smash);
	}

	private static String lookup(String in) {
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
			return "<->";
		} else if (in.equals("EQ")) {
			return "=";
		} else if (in.equals("NOT")) {
			return "-";
		} else if (in.equals("T")) {
			return "$T";
		} else if (in.equals("F")) {
			return "$F";
		} else {
			return in.toLowerCase().replaceAll(" ", "_");
		}
	}
	
	private Boolean typeIs(Type s, Type t) {
		return s.equals(t);
	}
	
	private Boolean typeVector(final Type t, final Type vector, final Type end) {
		if (t.equals(end)) {
			return true;
		} else {
			return t.accept(new Type.Visitor<Boolean>() {
				@Override public Boolean constant(String name) {
					return false;
				}
				@Override public Boolean function(Type a, Type b) {
					return a.equals(vector) && typeVector(b, vector, end);
				}
			});
		}		
	};
	
}