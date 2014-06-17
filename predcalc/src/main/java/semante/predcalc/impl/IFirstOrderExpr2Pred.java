package semante.predcalc.impl;

import java.util.ArrayList;

import lambdacalc.Expr;
import lambdacalc.Expr.Visitor;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.Types;
import lombok.RequiredArgsConstructor;
import semante.predcalc.FOLExpr;
import semante.predcalc.HigherOrderError;
import semante.predcalc.LowLambda2Pred;
import semante.predcalc.PredCalc;
import semante.predcalc.FOLExpr.Formula;
import semante.predcalc.FOLExpr.Term;

/*
 * Convert a first-order lambda expression to predicate logic
 * types: <e>t, , <e>, (et)t
 */

@RequiredArgsConstructor
public class IFirstOrderExpr2Pred implements LowLambda2Pred {

	protected PredCalc pcalc;
	protected STL lcalc;
	private Expr nowsmashing;

	public IFirstOrderExpr2Pred(PredCalc pcalc, STL lcalc) {
		this.pcalc     = pcalc;
		this.lcalc     = lcalc;
	};

	private Visitor<FOLExpr> smash = new Visitor<FOLExpr>() {
		@Override public FOLExpr variable(Symbol s) {
			if (typeIs(s.getType(), Types.E)) {
				// Variable
				return new IFOLExpr.Variable(s.getName());
			} else if (typeVector(s.getType(), Types.T,  Types.T)) {
				// Connective
				return new IFOLExpr.Connective(lookup(s.getName()), new ArrayList<Formula>());
			} else if (typeVector(s.getType(), Types.E, Types.T)) {
				// Predicate
				return new IFOLExpr.Predicate(lookup(s.getName()), new ArrayList<Term>());
			} else if (typeVector(s.getType(), Types.E, Types.E)) {
				// Function
				return new IFOLExpr.Function(lookup(s.getName()), new ArrayList<Term>());	
			} else {
				// TODO
				// 	Do we really need a case here for (non-)factives?
				// 	We would like to compile `say:tet c:t z:e` to `say(c,z)`
				throw new UnsupportedOperationException("Unknown type: "+ lcalc.format(s.getType()));
			}
		}  

		@Override public FOLExpr application(final Expr f, final Expr b) {
			// Look for a quantifier
			return b.accept(new Visitor<FOLExpr>(){
				// Abstraction -> quantifier
				@Override public FOLExpr abstraction(final Symbol s2, final Expr body2) {
					// .. if the right is an abstraction, return a new abstraction
					if (typeIs(lcalc.typeOf(f), Types.ET_T)) {
						Term v = new IFOLExpr.Variable(s2.getName());
						return new IFOLExpr.Quantifier(lookup(f.accept(getName)), v, (Formula) convert(body2));
					} else {
						throw new HigherOrderError("Abstractions are higher order: " + 
								lcalc.format(s2) + " " + lcalc.format(body2) + "\n in " + lcalc.format(nowsmashing));
					}
				}
				// Normal Application
				@Override public FOLExpr application(Expr f2, Expr arg2) {
					return f.accept(smash).add(b.accept(smash));
				}
				@Override public FOLExpr variable(Symbol s2) {
					return f.accept(smash).add(b.accept(smash));
				}
			});
		}

		@Override public FOLExpr abstraction(Symbol s, Expr arg) {
			throw new HigherOrderError("Abstractions are higher order: " + 
					lcalc.format(s) + " " + lcalc.format(arg) + "\n in " + lcalc.format(nowsmashing));
		}

		Visitor<String> getName = new Visitor<String>() {
			@Override public String abstraction(Symbol s, Expr arg) { return null; }
			@Override public String application(Expr f, Expr arg) { return null; }
			@Override public String variable(Symbol s) { return s.getName(); }
		};

	};

	@Override
	public final FOLExpr convert(final Expr expr) {
		nowsmashing = expr;
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
		} else if (in.equals("EQUIV")) {
			return "<->";
		} else if (in.equals("EQ")) {
			return "=";
		} else if (in.equals("NOT")) {
			return "-";
		} else if (in.equals("T") || in.equals("TRUE")) {
			return "$T";
		} else if (in.equals("F") || in.equals("FALSE")) {
			return "$F";
		} else {
			return in.toLowerCase().replaceAll(" ", "_");
		}
	}

	private Boolean typeIs(Type s, Type t) {
		return s.equals(t);
	}

	// TODO please add comment describing the function
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
	}
}