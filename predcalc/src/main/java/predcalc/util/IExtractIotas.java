package predcalc.util;

import java.util.ArrayList;
import java.util.List;

import lambdacalc.Expr;
import lambdacalc.ExprBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.Types;
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
	
	// use a pragmatics list state, reset every call
	private ExprBuilder extracter = new ExprBuilder() {
		@Override
		public Expr application(final Expr f, final Expr arg) {
			if (lcalc.typeOf(f).equals(Types.ET_E)) {
				// iota (return a new constant)
				ExprBuilder b = lcalc.getExprBuilder();
				Expr c = b.variable(buildSymbol("c"+counter.get(), Types.E));
				Expr e =
					b.application(
						b.variable(buildSymbol("EQUIVALENCES", Types.TTT)),
						b.application(
								arg,
								b.variable(buildSymbol("x", Types.E))));
				Expr i =
						b.application(
							b.application(
									b.variable(buildSymbol("EQ", Types.EET)),
									b.variable(buildSymbol("x", Types.E))), 
							c);
				Expr q =
						b.application(
							b.variable(buildSymbol("FORALL", Types.ET_T)),
							b.abstraction(
									buildSymbol("x", Types.E),
									b.application(e, i)));
				pragmatics.add(lcalc.betaReduce(lcalc.betaReduce(q).accept(extracter)));
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
		pragmatics.clear();
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
	}

}