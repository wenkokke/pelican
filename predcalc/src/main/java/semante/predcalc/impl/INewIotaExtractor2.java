package semante.predcalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import lambdacalc.Expr;
import lambdacalc.ExprBuilder;
import lambdacalc.ExprRichBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Types;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import semante.predcalc.ExprForm;
import semante.predcalc.IotaExtractor;

import com.google.common.base.Function;
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
public final class INewIotaExtractor2 implements IotaExtractor {

	STL stl;
	
	public final ExprForm<Expr> extract(@NonFinal Expr expr) {

		final Map<String,Expr> 	prevIotas = new HashMap<String,Expr>();
		final Deque<String> 	orderedIotaVars = new ArrayDeque<String>();
		
		final ExprRichBuilder bld = stl.getExprBuilder();

		final List<Expr> pragmatics = Lists.newArrayList();

		System.err.println("IotaExtraction: ["+stl.format(expr)+"]");
		
		Expr newExpr = expr.accept(new ExprBuilder() {
			
			@Override
			public final Expr application(Expr fun0, Expr arg0) {
			
				fun0 = fun0.accept(this);
				arg0 = arg0.accept(this);
				
				Symbol var1 = fun0.accept(getFEQAppVar);
				boolean iota = arg0.accept(isFIOTAApp);

				//System.out.println("App: " + stl.format(fun0) + ", " + stl.format(arg0) + ";    var1=null:" + (var1==null ? "null" : "ok") + ", iota:" + (iota ? "true" : "false"));
				
				if (var1!=null && iota) {
					Expr iotaArg = arg0.accept(getFIOTArg);
					Expr iotaArgWithDeps = addDependentIotas(var1.getName(), iotaArg);
					addUniquenessCondition(iotaArgWithDeps);
					Expr app = bld.application(iotaArg, bld.variable(var1)); // we will beta-reduce later; 
					return app;
				} else {
					return bld.application(fun0, arg0);
				}
			}
			
			private void addUniquenessCondition(Expr predicate) {
				// We create the uniqueness constraint that
				// should go to the pragmatics,
				// i.e. for P create: all x (all y (P(x) & P(y) => x = y)).
				val uniqueness =
					bld.application(bld.variable("FORALL",Types.ET_T),bld.abstraction("x",Types.E,
					bld.application(bld.variable("FORALL",Types.ET_T),bld.abstraction("y",Types.E,
						bld.application(
							bld.variable("IMPLIES", Types.TTT),
							bld.application(
								bld.variable("AND", Types.TTT),
								bld.application(predicate, bld.variable("x", Types.E)),
								bld.application(predicate, bld.variable("y", Types.E))
							),
							bld.application(
								bld.variable("EQ", Types.EET),
								bld.variable("x", Types.E),
								bld.variable("y", Types.E)
							)
						)	
					))));
				
				pragmatics.add(stl.betaReduce(uniqueness));
			}

			private Expr addDependentIotas(String varName, Expr exp) {
				
				// collect the dependencies that exp has
				final Set<String> iotaDeps = new TreeSet<String>();
				exp.accept(new Expr.Visitor<Void>() {

					@Override
					public Void abstraction(Symbol arg0, Expr arg1) {
						arg1.accept(this);
						return null;
					}

					@Override
					public Void application(Expr arg0, Expr arg1) {
						arg0.accept(this);
						arg1.accept(this);
						return null;
					}

					@Override
					public Void variable(Symbol arg0) {
						if (prevIotas.containsKey(arg0.getName())) {
							iotaDeps.add(arg0.getName());
						}
						return null;
					}
				});
				
				// \x6:e.((AND:ttt ((EQ:eet x6:e) (Jessica:((et)t)e (\A0:et.(A0:et Litman:e))))) (EXISTS:(et)t (\x7:e.((AND:ttt ((AND:ttt ((law:(et)et professor:et) x7:e)) (((at:e(et)et x0:e) (\x8:e.((law:(et)et professor:et) x8:e))) x7:e))) ((EQ:eet x6:e) x7:e)))))
				// student(x)
				
				if (iotaDeps.size()>0) {
					if (exp.accept(isAbs)) {
						
						// separate the abstraction var fron the content
						Symbol absVar = exp.accept(getAbsVar);
						Expr absContent = exp.accept(getAbsContent);

						// add the required existentials about dependent iotas to the content part  
						for (String iotaVar : orderedIotaVars) {
							if (iotaDeps.contains(iotaVar)) {
								absContent = bld.application(
										bld.variable("EXISTS", Types.ET_T),
										bld.abstraction(iotaVar, Types.E,
											bld.application(
												bld.application(	
													bld.variable("AND",Types.TTT),
													bld.application(
															prevIotas.get(iotaVar), bld.variable(iotaVar,Types.E))),
												absContent)));
							}
						}
						
						// reintroduce the abstraction variable at the beginning
						exp = bld.abstraction(absVar,absContent);
					} else {
						// Unexpected case - an expression with dependent iotas that is not an abstraction.
						// IOTAs without abstraction are usually simple nouns , e.g. IOTA:(et)e student:et
						// but here we also have dependencies. it can be because it is a relational noun
						// like: IOTA:(et)e (mother:eet xo:e) - so the expression depends on x0.
					}
				}
				
				orderedIotaVars.push(varName);
				prevIotas.put(varName, exp);
				
				return exp;
			}

			@Override
			public Expr abstraction(Symbol arg, Expr body) {
				return bld.abstraction(arg, body.accept(this));
			}

			@Override
			public Expr variable(Symbol s) {
				return bld.variable(s);
			}
					
		});
		System.err.println("            =>: ["+stl.format(newExpr)+"]");
		
		return new IExprForm<Expr>(stl.betaReduce(newExpr), Lists.transform(pragmatics, getReducer(stl)));
	}
	
	
	// REDUCER: simple function wrapper around the STL beta reducer
	static Function<Expr,Expr> getReducer(final STL stl) {
		return new Function<Expr,Expr>() {
			@Override
			public final Expr apply(final Expr expr) {
				return stl.betaReduce(expr);
			}
		};
	}
	
	
	/**
	 * Check if an expression is equal to an FEQ.
	 */
	private final static Expr.Visitor<Boolean> isFEQ = new Expr.Visitor<Boolean>() {
		@Override public final Boolean abstraction(Symbol arg0, Expr arg1) {return false;}
		@Override public final Boolean application(Expr arg0, Expr arg1) {return false;}
		@Override public final Boolean variable(Symbol arg0) {
			return arg0.getName().equals("FEQ") && arg0.getType().equals(Types.EET);
		}
	};

	/**
	 * Check if an expression is equal to a variable (e.g. x0, A1, y2...).
	 */
	private final static Expr.Visitor<Symbol> getVar = new Expr.Visitor<Symbol>() {
		@Override public final Symbol abstraction(Symbol arg0, Expr arg1) {return null;}
		@Override public final Symbol application(Expr arg0, Expr arg1) {return null;}
		@Override public final Symbol variable(Symbol arg0) {
			return arg0.getName().matches("^[a-zA-Z]\\d+$") && arg0.getType().equals(Types.E)
					? arg0 : null;
		}
	};

	/**
	 * Returns the variable that FEQ is applied to, e.g. 'y6' in (FEQ:eet y6:e)
	 */
	private final static Expr.Visitor<Symbol> getFEQAppVar = new Expr.Visitor<Symbol>() {
		@Override public final Symbol abstraction(Symbol arg0, Expr arg1) {return null;}
		@Override public final Symbol application(Expr arg0, Expr arg1) {
			return arg0.accept(isFEQ) ? arg1.accept(getVar) : null;
		}
		@Override public final Symbol variable(Symbol arg0) {return null;}
	};

	/**
	 * Check if an expression is equal to an FIOTA.
	 */
	private final static Expr.Visitor<Boolean> isFIOTA = new Expr.Visitor<Boolean>() {
		@Override public final Boolean abstraction(Symbol arg0, Expr arg1) {return false;}
		@Override public final Boolean application(Expr arg0, Expr arg1) {return false;}
		@Override public final Boolean variable(Symbol arg0) {
			return arg0.getName().equals("FIOTA") && arg0.getType().equals(Types.ET_E);
		}
	};

	/**
	 * Returns the variable introduced in an abstraction 
	 * e.g. 'x4' in the case of \x4. f(x4):t
	 */
	private final static Expr.Visitor<Symbol> getAbsVar = new Expr.Visitor<Symbol>() {
		@Override public final Symbol abstraction(Symbol arg0, Expr arg1) {return arg0;}
		@Override public final Symbol application(Expr arg0, Expr arg1) {return null;}
		@Override public final Symbol variable(Symbol arg0) { return null;}
	};

	/**
	 * Returns the content of an abstraction 
	 * e.g. 'f(x4)' in the case of \x4. f(x4):t
	 */
	private final static Expr.Visitor<Expr> getAbsContent = new Expr.Visitor<Expr>() {
		@Override public final Expr abstraction(Symbol arg0, Expr arg1) {return arg1;}
		@Override public final Expr application(Expr arg0, Expr arg1) {return null;}
		@Override public final Expr variable(Symbol arg0) { return null;}
	};

	/**
	 * Returns the variable introduce by an abstraction that is applied to an FIOTA
	 * e.g. 'x7' in (FIOTA:(et)e (\x7:e. f(x7):t))
	 */
	private final static Expr.Visitor<Boolean> isFIOTAApp = new Expr.Visitor<Boolean>() {
		@Override public final Boolean abstraction(Symbol arg0, Expr arg1) {return false;}
		@Override public final Boolean application(Expr arg0, Expr arg1) {
			return arg0.accept(isFIOTA);
		}
		@Override public final Boolean variable(Symbol arg0) {return false;}
	};

	/**
	 * Returns whether the expression is an abstraction 
	 */
	private final static Expr.Visitor<Boolean> isAbs = new Expr.Visitor<Boolean>() {
		@Override public final Boolean abstraction(Symbol arg0, Expr arg1) {return true;}
		@Override public final Boolean application(Expr arg0, Expr arg1) {return false;}
		@Override public final Boolean variable(Symbol arg0) {return false;}
	};

	/**
	 * Returns the variable introduce by an abstraction that is applied to an FIOTA
	 * e.g. 'x7' in (FIOTA:(et)e (\x7:e. f(x7):t))
	 */
	private final static Expr.Visitor<Expr> getFIOTArg = new Expr.Visitor<Expr>() {
		@Override public final Expr abstraction(Symbol arg0, Expr arg1) {return null;}
		@Override public final Expr application(Expr arg0, Expr arg1) {
			return arg0.accept(isFIOTA) ? arg1 : null;
		}
		@Override public final Expr variable(Symbol arg0) {return null;}
	};
	
}