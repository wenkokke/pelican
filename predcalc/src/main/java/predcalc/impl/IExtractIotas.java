package predcalc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lambdacalc.Expr;
import lambdacalc.ExprBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.Types;
import lambdacalc.Expr.Visitor;
import lombok.RequiredArgsConstructor;
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
		@Override public Boolean variable(Symbol s) 				{ return s.getName().equals("IOTA");}
	};

	// use a pragmatics list state, reset every call
	private ExprBuilder extracter = new ExprBuilder() {
		@Override
		public Expr application(final Expr f, final Expr arg) {
			if (lcalc.typeOf(f).equals(Types.ET_E) && f.accept(isIOTA)) {
				// iota (return a new constant)

				ExprBuilder b = lcalc.getExprBuilder();
				Expr c;
				
				// if we already have a constant for this argument, we use it
				// note that we check beta-reduced expressions just because the beta-reducer normalizes
				// the names of the variables.
				String argStr = lcalc.format(lcalc.betaReduce(arg)); 
				if (usedIotas.containsKey(argStr)) {
					c = usedIotas.get(argStr);
				} else {
					Symbol s = buildSymbol("c"+counter.get(), Types.E);
					c = b.variable(s);
					usedIotas.put(argStr, c);

					Symbol x = buildSymbol("x", Types.E);
					Expr e =
						b.application(
							b.variable(buildSymbol("EQUIVALENCES", Types.TTT)),
							b.application(
									arg,
									b.variable(x)));
					Expr i =
							b.application(
								b.application(
										b.variable(buildSymbol("EQ", Types.EET)),
										b.variable(x)), 
								c);
					Expr q =
							b.application(
								b.variable(buildSymbol("FORALL", Types.ET_T)),
								b.abstraction(
										x,
										b.application(e, i)));
					
					pragmatics.add(lcalc.betaReduce(lcalc.betaReduce(q).accept(extracter)));
				}
				return c;
			} else {
				return lcalc.getExprBuilder().application(f.accept(extracter), arg.accept(extracter));
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

	public ExprForm<Expr> extract(Expr a) {
		usedIotas.clear();
		pragmatics.clear();
		counter.reset();
		return new IExprForm<Expr>(a.accept(extracter), pragmatics);
	}
	
	Symbol buildSymbol(final String name, final Type type) {
		return new Symbol() {
			@Override public String getName() { return name; }
			@Override public Type getType() { return type; }
		};
	}
	
	private class Counter {
		private int c=0;
		private int get() { c++; return c; }
		private void reset() {c=0; };
	}

}