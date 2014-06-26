package semante.pipeline.impl;

import java.util.ArrayList;
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
import lombok.val;

public class IIotaCollector {

	public static Expr collect(final STL stl, Expr exp) {

		System.err.println("Collecting IOTAs from: [" + stl.format(exp) + "]");
		
		
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
					bld.variable("FIOTA", Types.ET_E),
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
										bld.application(bld.variable("FEQ", Types.EET),newVar),
										iotaExpr)),
							res)));
			
			varId++;
		}
		
		// just before returning, we have to restructure everything 
		// (otherwise variables mess up later)
		res = stl.parse(stl.format(res));
		
		System.err.println("                   =>: [" + stl.format(res) + "]");

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
	
	public static Expr alphaConvert(final String oldSymbolName, final String newSymbolName, Expr e, final STL stl) {
		
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
	
	static Symbol buildSymbol(final String name, final Type type) {
		return new Symbol() {
			@Override public String getName() { return name; }
			@Override public Type getType() { return type; }
		};
	}
	
}
