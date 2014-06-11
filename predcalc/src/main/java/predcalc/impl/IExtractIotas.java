package predcalc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lambdacalc.Expr;
import lambdacalc.Expr.Visitor;
import lambdacalc.ExprBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Types;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import predcalc.ExprForm;
import predcalc.ExtractIotas;

/*
 * Extract the iotas from an expression (deep) into a list of pragmatics
 * 
 * We replace each iota by a fresh constant, which is uniquely described by the iota content in the pragmatics.
 * We assume every expression of type (et)e is a iota.
 * 
 */

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true)
public class IExtractIotas implements ExtractIotas {

	protected STL lcalc;
	private Counter counter = new Counter();
	private List<Expr> pragmatics = new ArrayList<Expr>();
	private Map<String,Expr> usedIotas = new HashMap<String,Expr>();
	
	private Visitor<Boolean> isIOTA = new Visitor<Boolean>() {
		@Override public Boolean abstraction(Symbol s, Expr body) 	{ return false; }
		@Override public Boolean application(Expr fun, Expr arg) 	{ return false; }
		@Override public Boolean variable(Symbol s) 				{ return s.getName().equals("UNIQUE");}
	};

	// use a pragmatics list state, reset every call
	private ExprBuilder extracter = new ExprBuilder() {
		@Override
		public Expr application(final Expr f, final Expr pred) {
			val b = lcalc.getExprBuilder();
			
			if (lcalc.typeOf(f).equals(Types.ET_T) && f.accept(isIOTA)) {
				
				// If we already have a constant for this argument, we use it.
				// Note that we check beta-reduced expressions just because the
				// beta-reducer normalizes the names of the variables.
				val argStr = lcalc.format(lcalc.betaReduce(pred));
				
				if (usedIotas.containsKey(argStr)) {
					
					// return the pre-computed constant:
					return usedIotas.get(argStr);
					
				} else {
					// create the expression that should go to the pragmatics,
					// i.e. for P create: all x (all y (P(x) & P(y) => x = y)).
					val prag =
						b.application(b.variable("FORALL",Types.ET_T),b.abstraction("x",Types.E,
						b.application(b.variable("FORALL",Types.ET_T),b.abstraction("y",Types.E,
							b.application(
								b.variable("IMPLIES", Types.TTT),
								b.application(
									b.variable("AND", Types.TTT),
									b.application(pred, b.variable("x", Types.E)),
									b.application(pred, b.variable("y", Types.E))
								),
								b.application(
									b.variable("EQ", Types.EET),
									b.variable("x", Types.E),
									b.variable("y", Types.E)
								)
							)	
						))));
					System.err.println("MSG: " + lcalc.format(prag));
					pragmatics.add(lcalc.betaReduce(lcalc.betaReduce(prag).accept(extracter)));
					
					// create the expression that should go to the semantics,
					// i.e. for P and top-level expression E create: exists x. P(x) & E(x).
					val c = b.variable("c" + counter.get(), Types.E);
					usedIotas.put(argStr, c);
					return c;
				}
			} else {
				
				// simply continue, as this is not an iota:
				return b.application(
						f.accept(extracter), pred.accept(extracter));

			}
		}
		
		@Override public Expr abstraction(Symbol s, Expr arg) {
			return lcalc.getExprBuilder().abstraction(s, arg.accept(extracter));
		}

		@Override
		public Expr variable(Symbol s) {
			return lcalc.getExprBuilder().variable(s);
		}
	};

	public ExprForm<Expr> extract(Expr a1) {
		usedIotas.clear();
		pragmatics.clear();
		// extract all the iota's:
		val a2 = a1.accept(extracter);
		// TODO wrap existential quantifiers around this here thingie,
		// also the predicate because that has to happen you know.
		return new IExprForm<Expr>(a2, pragmatics);
	}
	
	private class Counter {
		private int c=0;
		private int get() { c++; return c; }
	}

}