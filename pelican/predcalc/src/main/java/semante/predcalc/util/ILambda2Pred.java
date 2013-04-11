package semante.predcalc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
import semante.predcalc.Lambda2Pred;
import semante.predcalc.PredCalc;
import semante.predcalc.util.IFOLExpr.Connective;

/*
 * smash []      a::e           = Var a
 *       ss      a::e*t         = Pred a(ss)
 *       ss      a::e*e         = Form a(ss)
 *       ss      a::t*t         = Conn a(ss)
 *       ss      a::(et)*et     = (Pred a()).compound(ss)
 *        
 *       [] (a::(et)t b::et)    = Quant (new v) (smash [v] b)
 *       [] (iota::(et)e b::et) = new z { prag &= (all x ((smash [x] b)->x=z) & (smash [z] b)) }
 *       ss (a::(et)et b::et)   = smash [smash ss b] a
 *       ss (a b)               = smash [(smash [] b):ss] a
 *       
 *       (s:ss) (\x a)          = (smash ss a)[(smash [] x) := s]
 *       
 * a.compound (p:ss) = (Pred a_p(p.terms)).compound(ss)
 *            (c:ss) = a.compound(c.formulas+ss)
 *               
 * a[x := y] = if (a=x) then y else a { all (b : a.args) { b[x := y] } }
 * 
 */

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true)
public class ILambda2Pred<T extends TSymbol> implements Lambda2Pred<T> {

	protected PredCalc pcalc;
	protected TLambdaCalc<T> lcalc;
	
	private List<IFOLExpr.Formula> pragmatics = new ArrayList<IFOLExpr.Formula>();

	@Override
	public final Formula smash(final Expr<T> expr) {
		pragmatics.clear();
		AnonymousVar.reset();

		// Smash the expression
		Formula out = (Formula) expr.accept(smash(new Stack<FOLExpr>()));
		
		// If we ran into iotas, add their uniqueness to the beginning
		if (!pragmatics.isEmpty()) {
			Formula top = pragmatics.remove(0);
			for (IFOLExpr.Formula p : pragmatics) {
				top = new IFOLExpr.Connective("&", Arrays.asList(new Formula[] { top, p}));
			}
			return new IFOLExpr.Connective("&", Arrays.asList(new Formula[] { top, out }));
		} else {
			return out;
		}
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

	Visitor<T,FOLExpr> smash(final Stack<FOLExpr> args) {
		return new Visitor<T, FOLExpr>() {
			@Override public FOLExpr variable(T s) {
				if (typeIs(s.getType(), ITypes.E)) {
					// Variable
					if (!args.empty()) { System.err.println("Something was applied to a variable"); }
					return new IFOLExpr.Variable(s.getName());
				} else if (typeAll(s.getType(), ITypes.T)) {
					// Connective
					return (new IFOLExpr.Connective(lookup(s.getName()), new ArrayList<Formula>())).apply(args);
				} else if (typeIs(s.getType(), ITypes.ET_ET)) {
					// Modifier
					return (new IFOLExpr.Predicate(lookup(s.getName()), new ArrayList<Term>())).apply(args);
				} else if (!typeIs(s.getType(), ITypes.ET_T) && typeEndsWith(s.getType(), ITypes.T)) { // Not a quantifier
					// Predicate
					return (new IFOLExpr.Predicate(lookup(s.getName()), new ArrayList<Term>())).apply(args);
				} else if (!typeIs(s.getType(), ITypes.ET_E) && typeEndsWith(s.getType(), ITypes.E)) { // Not a iota!
					// Function
					return (new IFOLExpr.Function(lookup(s.getName()), new ArrayList<Term>())).apply(args);
				} else {
					throw new UnsupportedOperationException("Unknown type: "+ s.getType());
				}
			}
			
			@Override public FOLExpr application(Expr<T> f, Expr<T> b) {
				if (typeIs(lcalc.typeOf(f), ITypes.ET_T)) {
					// Quantifier
					Term v = AnonymousVar.give();
					args.push(v);
					Formula body = (Formula) b.accept(smash(args));
					
					return new IFOLExpr.Quantifier(lookup(f.accept(getName)), v, body);
				} if (typeIs(lcalc.typeOf(f), ITypes.ET_E)) {
					// Iota
					Term v = AnonymousVar.give();
					// I don't understand why I can't just clone a stack
					@SuppressWarnings("unchecked")
					Stack<FOLExpr> args2 = (Stack<FOLExpr>) args.clone();
					args.push(v);
					Formula body = (Formula) b.accept(smash(args));
					
					// all x (p(x)->x=z) & p(z)
					Term x = AnonymousVar.give();
					args2.push(x);
					Formula unique =  new IFOLExpr.Quantifier("all", x,
							new IFOLExpr.Connective("->", Arrays.asList(new Formula[] {
									(Formula) b.accept(smash(args2)),
									new IFOLExpr.Predicate("=", Arrays.asList(new Term[] {x, v}))})));
					pragmatics.add(new Connective("&", Arrays.asList(new Formula[] { unique, body })));
					
					return v;
				} else {
					// Any application
					args.push(b.accept(smash(new Stack<FOLExpr>())));
					return f.accept(smash(args));
				}
			}
			
			@Override public FOLExpr abstraction(T s, Expr<T> arg) {
				FOLExpr theold = new IFOLExpr.Variable(s.getName());
				FOLExpr thenew = args.empty()? null : args.pop();
				return arg.accept(smash(args)).replace(theold, thenew);
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
	Boolean typeEndsWith(final Type t, final Type end) {
		if (lcalc.eqType().apply(t, end)) {
			return true;
		} else {
			return t.accept(new Type.Visitor<Boolean>() {
				@Override public Boolean typeConstant(String name) {
					return false;
				}
				@Override public Boolean typeFunction(Type a, Type b) {
					return typeEndsWith(b, end);
				}
			});
		}		
	};

	Boolean typeAll(final Type t, final Type all) {
		return lcalc.eqType().apply(t, all) || t.accept(new Type.Visitor<Boolean>() {
			@Override public Boolean typeConstant(String name) { return false; }
			@Override public Boolean typeFunction(Type a, Type b) {
				return lcalc.eqType().apply(a, all) && typeAll(b, all);
			}
		});
	};
	
}

class AnonymousVar {
	private static AnonymousVar master;
	int varcount = 0;
	public static Term give() {
		if (null == master) { master = new AnonymousVar(); }
		return master.make();
	}
	public static void reset() {
		if (null == master) { master = new AnonymousVar(); }
		master.o();
	}
	Term make() {
		varcount++;
		return new IFOLExpr.Variable("x"+varcount);
	}
	void o() { varcount = 0; }
}