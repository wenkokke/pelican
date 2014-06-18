package semante.predcalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import java.util.Map;

import lambdacalc.Expr;
import lambdacalc.ExprBuilder;
import lambdacalc.ExprRichBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Types;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import semante.pipeline.Pair;
import semante.predcalc.ExprForm;
import semante.predcalc.IotaExtractor;
import static semante.pipeline.impl.IPair.*;

import com.google.common.collect.Maps;
import com.google.common.collect.Lists;

/**
 * The IOTA extraction algorithm makes the following changes to a term: at every
 * position where it finds an application of an IOTA to a term P, it generates a
 * fresh constant c and remembers P.
 * 
 * For every found P it then generates the following uniqueness constraint:
 * 
 *     all x (all y (P(x) & P(y) => x = y))
 * 
 * Finally, for every generated c, it wraps the expressions e with the following
 * transformation:
 * 
 *     e => EXISTS c. P(c) /\ e
 *     
 * @author Pepijn Kokke
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level = PRIVATE)
public final class INewIotaExtractor implements IotaExtractor {

	STL stl;
	
	public final ExprForm<Expr> extract(@NonFinal Expr expr) {
		
		System.err.println("IotaExtraction: ["+stl.format(expr)+"]");
		
		// Replace all IOTA applications by fresh constants.
		val bld = stl.getExprBuilder();
		val extr = new ReplaceIotaByFreshConstant();
		expr = expr.accept(extr);
		
		// Wrap existential quantifiers around the generated expression.
		for (val conAndPred : extr.getConstantNamesAndPredicates()) {
			val conName = conAndPred.getFirst();
			val con     = bld.variable(conName, Types.E);
			val pred    = conAndPred.getSecond();
			
			// Update the expression for every unbound constant c in e:
			//   e => P(c) /\ e[...c...]
			expr =
				bld.application(
					bld.variable("AND", Types.TTT),
					bld.application(pred,con),
					expr);
		}
		
		System.err.println("            =>: ["+stl.format(expr)+"]");

		// Return what basically amounts to a pair of the pragmatics section and
		// the newly generated constant.
		return new IExprForm<Expr>(stl.betaReduce(expr), extr.getPragmatics());
	}
	
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true, level = PRIVATE)
	private final class ReplaceIotaByFreshConstant implements ExprBuilder {

		Expr.Visitor<Boolean> isIOTA = new IsIOTA();
		ExprRichBuilder bld = stl.getExprBuilder();
		
		@NonFinal int counter = 0;
		@Getter List<Expr> pragmatics = Lists.newArrayList();
		Map<String, Pair<String,Expr>> constantNamesAndPredicates = Maps.newHashMap();
		
		// obtain the generated contant names and collected predicates.
		public final Iterable<Pair<String,Expr>> getConstantNamesAndPredicates() {
			return this.constantNamesAndPredicates.values();
		}
		
		@Override
		public final Expr application(Expr fun0, Expr arg0) {
			
			// first extract any nested iota's with a recursive call.
			val fun1 = stl.betaReduce(fun0).accept(this);
			val arg1 = stl.betaReduce(arg0).accept(this);
			
			// then see if we are currently dealing with an iota.
			if (stl.typeOf(fun1).equals(Types.ET_E) && fun1.accept(isIOTA)) {

				// We compute the string representation of the term, as equality
				// on the string terms is considered to be more reliable than
				// equality on term.
				// TODO: this should use Object#equals or Object#hashCode.
				val argStr = stl.format(stl.betaReduce(arg1));
					
				// If we already have a constant for this argument, we use it.
				if (constantNamesAndPredicates.containsKey(argStr)) {	
					return bld.variable(constantNamesAndPredicates.get(argStr).getFirst(),Types.E);
				}
				else {
					// Otherwise, we create the uniqueness constraint that
					// should go to the pragmatics,
					// i.e. for P create: all x (all y (P(x) & P(y) => x = y)).
					val uniqueness =
						bld.application(bld.variable("FORALL",Types.ET_T),bld.abstraction("x",Types.E,
						bld.application(bld.variable("FORALL",Types.ET_T),bld.abstraction("y",Types.E,
							bld.application(
								bld.variable("IMPLIES", Types.TTT),
								bld.application(
									bld.variable("AND", Types.TTT),
									bld.application(arg1, bld.variable("x", Types.E)),
									bld.application(arg1, bld.variable("y", Types.E))
								),
								bld.application(
									bld.variable("EQ", Types.EET),
									bld.variable("x", Types.E),
									bld.variable("y", Types.E)
								)
							)	
						))));
					pragmatics.add(stl.betaReduce(uniqueness));

					// In addition, we create a new constant to represent the
					// entity that uniquely satisfies the predicate P.
					val freshConstant = "$" + counter++;
					constantNamesAndPredicates.put(argStr, pair(freshConstant,arg1));
					return bld.variable(freshConstant, Types.E);
				}
			}
			// If we're not dealing with an IOTA, we just recursively apply the
			// extraction.
			else {
				return bld.application(fun1.accept(this), arg1.accept(this));
			}
		}
		
		@Override
		public final Expr abstraction(Symbol arg, Expr body) {
			return bld.abstraction(arg, body.accept(this));
		}

		@Override
		public final Expr variable(Symbol s) {
			return bld.variable(s);
		}
		
	};
	
	/**
	 * Check if an expression is equal to an IOTA.
	 */
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal=true, level = PRIVATE)
	private final class IsIOTA implements Expr.Visitor<Boolean> {

		@Override
		public final Boolean abstraction(Symbol arg0, Expr arg1) {
			return false;
		}
		
		@Override
		public final Boolean application(Expr arg0, Expr arg1) {
			return false;
		}
		
		@Override
		public final Boolean variable(Symbol arg0) {
			return arg0.getName().equals("IOTA");
		}
	};
}