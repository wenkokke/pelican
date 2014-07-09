package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import lambdacalc.Expr;
import lambdacalc.Expr.Visitor;
import lambdacalc.ExprBuilder;
import lambdacalc.ExprRichBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.Types;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.pipeline.IotaExtractor;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

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
public final class IIotaExtractor implements IotaExtractor {
	
	private static final String FRONTED_IOTA_NAME 	= "FIOTA"; 
	private static final String FRONTED_EQ_NAME 	= "FEQ"; 

	@Override
	public IotaExtractorResult extract(final Expr expr, final STL stl) {

		final Map<String,Expr> 	prevIotas = new HashMap<String,Expr>();
		final Deque<String> 	orderedIotaVars = new ArrayDeque<String>();
		
		final ExprRichBuilder bld = stl.getExprBuilder();

		final Builder<Expr> uniquenessConditionsB = ImmutableSet.<Expr>builder();

		System.err.println("Extracting IOTAs from =>: ["+stl.format(expr)+"]");
		
		// COLLECT and FRONT IOTAs as existentials
		final Expr frontedExpr = front(stl, expr);

		System.err.println("(After fronting)        : ["+stl.format(expr)+"]");
		
		// Extract the uniqueness condition of IOTAs
		final Expr newExpr = frontedExpr.accept(new ExprBuilder() {
			
			@Override
			public final Expr application(Expr fun0, Expr arg0) {
			
				fun0 = fun0.accept(this);
				arg0 = arg0.accept(this);
				
				Symbol var1 = fun0.accept(getFEQAppVar);
				boolean iota = arg0.accept(isFIOTAApp);

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
				
				uniquenessConditionsB.add(stl.betaReduce(uniqueness));
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
		
		val newExprReduced = stl.betaReduce(newExpr);
		
		System.err.println("(Final output)          : ["+stl.format(newExprReduced)+"]");
	
		return new IotaExtractorResult() {

			@Override
			public Expr getAssertion() {
				return newExprReduced;
			}

			@Override
			public Set<Expr> getUniquenessConditions() {
				return uniquenessConditionsB.build();
			}
			
		};
	}
	
	private static Expr front(final STL stl, Expr exp) {

		final ExprRichBuilder bld = stl.getExprBuilder();	
		
		// the first step is to identify all IOTA instances and to replace them with constants of
		// type 'e'. We reuse the same constant for repeated IOTAs.
		// In the end of this step, we have the expressions and constants in the map: 
		// iotasExpToName organized by expression -> constant, and in the map:
		// iotasNameToExp organized by constant -> expression

		final Map<String,String> iotasExpToName = new TreeMap<String,String>();
		final Map<String,String> iotasNameToExp = new TreeMap<String,String>();

		
		// beta reduce at the beginning, to avoid unclear issues.
		exp = stl.betaReduce(exp);
		
		Expr res = exp.accept(new ExprBuilder() {

			int counter = 0;
			
			@Override
			public Expr application(Expr fun0, Expr arg0) {
				
				// first extract any nested iota's with a recursive call.
				val fun1 = fun0.accept(this);
				val arg1 = arg0.accept(this);

				Expr ret = null;
				
				// then see if we are currently dealing with an iota.
				if (stl.typeOf(fun1).equals(Types.ET_E) && fun1.accept(IsIOTA)) {
					
					// we make sure that the iota doesn't refer to external variables
					if (isSelfCotnained(arg1)) {
						
						// to be able to identify repeated IOTAs we normalize the variables it has
						String iotaExprStr = stl.format(stl.betaReduce(arg1));

						// either invent a new constant or use an existing one
						String newIOTA;
						if (!iotasExpToName.containsKey(iotaExprStr)) {
							newIOTA = "IOTA" + (++counter);
							iotasExpToName.put(iotaExprStr, newIOTA);
							iotasNameToExp.put(newIOTA, iotaExprStr);
						} else {
							newIOTA = iotasExpToName.get(iotaExprStr);
						}
						ret = bld.variable(newIOTA, Types.E);
					} else {
						System.err.println("*** IOTA expression refers to external variables: [" + stl.format(arg1) + "] ***");
					}
					
				} 
				
				if (ret==null) {
					ret = bld.application(fun1,arg1); 
				}
				
				return ret;
			}
		
			@Override
			public final Expr abstraction(Symbol arg, Expr body) {
				return bld.abstraction(arg, body.accept(this));
			}
		
			@Override
			public final Expr variable(Symbol s) {
				return bld.variable(s);
			}
			
			private boolean isSelfCotnained(Expr e) {
				
				boolean ret2 = e.accept(new Visitor<Boolean>() {

					List<String> vars = new ArrayList<String>();
					
					@Override
					public Boolean abstraction(Symbol arg0, Expr arg1) {
						vars.add(arg0.getName());
						return arg1.accept(this);
					}

					@Override
					public Boolean application(Expr arg0, Expr arg1) {
						return arg0.accept(this) && arg1.accept(this);
					}

					@Override
					public Boolean variable(Symbol arg0) {
						boolean ret = true;
						if (arg0.getName().matches("^x\\d$")) {
							ret = vars.contains(arg0.getName());
						} 
						return ret;
					}
					
				});
				
				return ret2;
				
			}
			
		});

		// now we look at the names of the constants that we have in order to sort them.
		// we sort the names such that given two constants o1 and o2 from the list, 
		// if o1's expression includes o2 (i.e. refers to it) than o1 would be BEFORE o2.
		// later we reverse this order - when we reintroduce the iotas. 
		final List<String> iotasNames = order(iotasNameToExp);
		
		// Now we start reintroducing the IOTAs. We go over the sorted list of constant names,
		// and we create a structure like: Exists (\y0. (AND ((FEQ y0) FIOTA...) remaining-exp))
		// notice that FEQ and FIOTA are labels like that intentionally, distinguish this 
		// structure from other structures that contain EQ or IOTA (there shouldn't be any other
		// that include IOTA - because we take care of ALL of them here, but just for safety).
		// Also notice that given the ordering of the constants, it turns out that those constants
		// whose expression does not depend on others will be introduced at the end, and therefore
		// they will appear at the beginning of the new expression.
		
		int varId = 0;
		String varBaseName = "y";
		
		for (String iotaLabel : iotasNames) {
			String newVarName = varBaseName + varId;
			Expr newVar = bld.variable(newVarName, Types.E);
			Expr iotaExpr = bld.application(
					bld.variable(FRONTED_IOTA_NAME, Types.ET_E),
					stl.parse(iotasNameToExp.get(iotaLabel)));
					
			// alpha conversion - since 'res' uses the constant name, we weed to update it. 
			res = alphaConvert(iotaLabel,newVarName,res,stl);

			
			
			// create the following structure:
			// Exists (\y. (AND ((EQ y) IOTA...) expr-res))
			res = bld.application(
					bld.variable("EXISTS", Types.ET_T),
					bld.abstraction(newVarName, Types.E,
						bld.application(
							bld.application(
								bld.variable("AND", Types.TTT),
									bld.application(
										bld.application(bld.variable(FRONTED_EQ_NAME, Types.EET),newVar),
										iotaExpr)),
							res)));
			
			varId++;
		}
		
		// just before returning, we have to restructure everything 
		// (otherwise variables mess up later)
		res = stl.parse(stl.format(res));
		
		return res; 
	}	
	
	private static List<String> order(Map<String,String> items) {
		
		/*
		 * Algorithm:
		 * Assuming that we have IOTA1, IOTA2, IOTA3,... IOTA7, with constraints
		 * IOTA2 includes IOTA1
		 * IOTA6 includes IOTA2,3,4,5
		 * 
		 * Step A
		 * We begin by listing all the items in the 'freeItems' set. We will 
		 * remove those that are not free later on.
		 * 
		 * Step B
		 * Now we start looping over all of them and check each one against all 
		 * the other. When we find that one IOTA includes another, for example, 
		 * IOTA2 includes IOTA1, we do the following:
		 * 1. Put IOTA2 in the map 'itemDependents' pointing to IOTA1  
		 * 2. Put IOTA1 in the map 'dependentItems' pointing to IOTA2
		 * 3. Remove IOTA1 from 'freeItems'
		 * 
		 * Step C
		 * Next we add empty sets in dependentItems for those that are in 
		 * our 'freeIItems' list. It indicates that they do not depent on any 
		 * other item.
		 * 
		 * After going over all combinations, we will have:
		 * freeItems: 
		 * 		IOTA6, IOTA7
		 * itemDependents:
		 * 		IOTA2 -> {IOTA1}
		 * 		IOTA6 -> {IOTA2, IOTA3, IOTA4, IOTA5}
		 * dependentItems:
		 *  	IOTA1 -> {IOTA2}
		 *  	IOTA2 -> {IOTA6}
		 *  	IOTA3 -> {IOTA6}
		 *  	IOTA4 -> {IOTA6}
		 *  	IOTA5 -> {IOTA6}
		 *  	IOTA6 -> {}
		 *  	IOTA7 -> {}
		 *  
		 *  Step D
		 *  We now start to loop over the freeItems and do the following:
		 *  1. Take out an item, e.g. IOTA6, and add it to the ordered list.
		 *  2. Remove this item from all lists in dependentItems. So in our case:
		 *  dependentItems:
		 *  	IOTA1 -> {IOTA2}
		 *  	IOTA2 -> {}
		 *  	IOTA3 -> {}
		 *  	IOTA4 -> {}
		 *  	IOTA5 -> {}
		 *  3. If this item has dependencies - i.e. its itemDependents is not 
		 *  empty. we go over the items in that list and check each one if it is 
		 *  dependent on any item. In our case, we go over {IOTA2, IOTA3, IOTA4, 
		 *  IOTA5} and we will see that none of these depends on any item. So all
		 *  of them are added to 'freeItems'.
		 *  4. Return to step 1 and restart the loop over the freeItems. 
		 *  
		 */
		
		Map<String,Set<String>> itemDependents = new HashMap<String, Set<String>>();
		Map<String,Set<String>> dependentItems = new HashMap<String, Set<String>>();

		// Step A
		Set<String> freeItems = new TreeSet<String>(items.keySet());
		
		// Step B
		Iterator<Map.Entry<String,String>> it = items.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> currentItem = it.next();
			itemDependents.put(currentItem.getKey(), new TreeSet<String>());			
			for (String s : items.keySet()) {
				if (currentItem.getValue().contains(s)) {
					itemDependents.get(currentItem.getKey()).add(s);
					freeItems.remove(s);
					
					if (!dependentItems.containsKey(s)) {
						dependentItems.put(s, new TreeSet<String>());
					}
					dependentItems.get(s).add(currentItem.getKey());
				}
			}
		}
		
		// Step C
		for (String s : freeItems) {
			dependentItems.put(s, new TreeSet<String>());
		}
		
		// Step D
		Set<String> newFreeItems = new TreeSet<String>();
		List<String> orderedList = new ArrayList<String>();
		while (orderedList.size()<items.size()) {
			for (String item : freeItems) {
				
				// Step 1
				orderedList.add(item);
				
				// Step 2
				for (Set<String> set : dependentItems.values()) {
					set.remove(item);
				}
				
				// Step 3
				for (String dependent : itemDependents.get(item)) {
					if (dependentItems.get(dependent).isEmpty()) {
						newFreeItems.add(dependent);
					}
				}
				itemDependents.get(item).clear();
			}
			
			freeItems.removeAll(orderedList);
			freeItems.addAll(newFreeItems);
			newFreeItems.clear();
			
		}
		
		return orderedList;
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

	
	// performs alpha-conversion for the given symbol in the given expression.  
	private static Expr alphaConvert(final String oldSymbolName, final String newSymbolName, Expr e, final STL stl) {
		
		return e.accept(new ExprBuilder() {
			
			@Override
			public Expr application(final Expr f, final Expr arg) {
				return stl.getExprBuilder().application(f.accept(this), arg.accept(this));
			}
			
			@Override 
			public Expr abstraction(Symbol s, Expr arg) {
				return stl.getExprBuilder().abstraction(checkSymbol(s), arg.accept(this));
			}

			@Override
			public Expr variable(Symbol s) {
				return stl.getExprBuilder().variable(checkSymbol(s));
			}
			
			private Symbol checkSymbol(Symbol s) {
				return s.getName().equals(oldSymbolName) ? buildSymbol(newSymbolName, s.getType()) : s;
			}
		});
	}

	// utility function for creating symbol objects
	private static Symbol buildSymbol(final String name, final Type type) {
		return new Symbol() {
			@Override public String getName() { return name; }
			@Override public Type getType() { return type; }
		};
	}	
	
	/**
	 * Check if an expression is equal to an IOTA.
	 */
	static Expr.Visitor<Boolean> IsIOTA = new  Expr.Visitor<Boolean>() {
		@Override public final Boolean abstraction(Symbol arg0, Expr arg1) {return false;}
		@Override public final Boolean application(Expr arg0, Expr arg1) {return false; }
		@Override public final Boolean variable(Symbol arg0) {
			return arg0.getName().equals("IOTA") && arg0.getType().equals(Types.ET_E);
		}
	};	
	/**
	 * Check if an expression is equal to an FEQ.
	 */
	private final static Expr.Visitor<Boolean> isFEQ = new Expr.Visitor<Boolean>() {
		@Override public final Boolean abstraction(Symbol arg0, Expr arg1) {return false;}
		@Override public final Boolean application(Expr arg0, Expr arg1) {return false;}
		@Override public final Boolean variable(Symbol arg0) {
			return arg0.getName().equals(FRONTED_EQ_NAME) && arg0.getType().equals(Types.EET);
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
			return arg0.getName().equals(FRONTED_IOTA_NAME) && arg0.getType().equals(Types.ET_E);
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