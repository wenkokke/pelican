package semante.predcalc.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lambdacalc.Expr;
import lambdacalc.Expr.Visitor;
import lambdacalc.ExprBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.Types;
import lambdacalc.impl.IType.IConstant;
import lambdacalc.impl.IType.IFunction;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.predcalc.Expr2FirstOrderExpr;
import semante.predcalc.HigherOrderError;
import semante.settings.Settings;


@FieldDefaults(makeFinal=true)
public class IExpr2FirstOrderExpr implements Expr2FirstOrderExpr {

	public IExpr2FirstOrderExpr(Settings settings, STL lcalc) {
		this.lcalc = lcalc;
		this.debugMode = Boolean.parseBoolean(settings.get("SemAnTE","Tracer","Smasher"));
	}
	
	protected boolean debugMode;
	protected STL lcalc; 
	
	private ExprBuilder rewriter = new ExprBuilder() {

		final LowerableFunction lowerableFunction = new LowerableFunction(Types.ET);
		
		final Type TypeETT = new IFunction(Types.ET,Types.T);
		final Type TypeTET = new IFunction(Types.T,Types.ET);
		
		@Override
		public Expr application(Expr fun, Expr arg) {

			val fun2 = fun.accept(rewriter);
			val arg2 = arg.accept(rewriter);
			val app2 = lcalc.getExprBuilder().application(fun2, arg2);

			if (lcalc.typeOf(fun2).accept(lowerableFunction) && lcalc.typeOf(arg2).equals(lowerableFunction.getType())) {
				if (debugMode) {
					System.out.println("Lowerable application: the function  ["+ lcalc.format(fun2) + "] is applied to an argument of type " + lcalc.format(lcalc.typeOf(arg2)) + ": [" + lcalc.format(arg2) + "]");
				}
				
				try {
					return fun2.accept(new ExprBuilder() {

							@Override
							public Expr abstraction(Symbol s, Expr body) {
								throw new HigherOrderError("Abstraction of type (et)et: "+lcalc.format(fun2));
							}

							@Override
							public Expr application(Expr fun3, Expr arg3) {
								// in this case the modifier is an application by itself, e.g. "in Brazil" modifies "walks".
								
								Expr e = null;
								if (fun3.accept(isConstant)) {

									// There's a subtle point here - we apply an application ("in Brazil") to a predicate, 
									// (e.g. "walks") and we want to have a new predicate that takes the argument of the 
									// application (i.e. "Brazil" as an argument. So our new predicate will look like 
									// in_walks(Brazil)(John) in the end.
									// To do this, we ignore the argument of the application (i.e. "Brazil"), and smash the
									// application as if it was just "in" applied to "walks". This will generate a new
									// predicate - "in_walks". Since this new predicate needs to be applied to the argument
									// that we omitted - "Brazil", the type of the predicate will be a function from E to
									// the type that the original application (i.e. "in Brazil") returns after applied to 
									// the predicate it modifies.

									
									// here we decide on the type of the new predicate
									Type newType = new IFunction(Types.E,lcalc.typeOf(fun2).accept(getFunctionReturnType));
									
									// lower the modification as if the modifier is a constant (like 'tall') 
									Expr simple = lowerModification(fun3.accept(getConstantName), lcalc.typeOf(fun2), newType , arg2);
									
									// apply the argument (e.g. 'Brazil')
									e = lcalc.getExprBuilder().application(simple,
											lcalc.getExprBuilder().variable(buildSymbol(arg3.accept(getConstantName), Types.E)));
									
									if (debugMode) {
										System.out.println("Lowered expression (modification by application):" + lcalc.format(e));
									}

								} else {
									throw new HigherOrderError("Modifier is not a constant" + lcalc.format(fun3));
								}

								return e;
							}

							@Override
							public Expr variable(Symbol s) {
								// the typical case of lowering, e.g. tall(man), but also of noun compounds - and that's why
								// we decide on the new type dynamically. It's not always 'et', because in the case of noun
								// compounds we may have a noun of type (et)(et)(et)et applied to a noun of type et, so the 
								// return type is (et)(et)et. Note that NP compounds are handled differently, see below.
								Expr e = lowerModification(s.getName(), lcalc.typeOf(fun2), s.getType().accept(getFunctionReturnType), arg2); 
								if (debugMode) {								
									System.out.println("Lowered expression (direct modification):" + lcalc.format(e));
								}
								return e;
							}
							
							private Expr lowerModification(String modifierName, Type modifierType, Type targetType, Expr arg) {
								try {
									List<Predicate> predicates = parsePredicates(arg);
									
									String newName = modifierName + getNewPredicateName(predicates);
									Type newType = getNewPredicateType(targetType,predicates);
									
									Expr newExpr = lcalc.getExprBuilder().variable(buildSymbol(newName, newType));
									for (Predicate p : predicates) {
										for (String argName : p.getArguments()) {
											newExpr = lcalc.getExprBuilder().application(
												newExpr,
												lcalc.getExprBuilder().variable(buildSymbol(argName, Types.E))); 									
										}
									}
									return newExpr;
									
								} catch (HigherOrderError e) {
									Expr newE =  hash(lcalc.getExprBuilder().application(
											lcalc.getExprBuilder().variable(
													buildSymbol(modifierName,modifierType)), arg),targetType);
									if (debugMode) {
										System.out.println("Modification hashed:" + lcalc.format(newE) + "; reason: [" + e.getMessage() + "]");
									}
									return newE;
								}
							}
							
						});
				} catch (HigherOrderError e) {
					Expr expr = hash(app2);
					if (debugMode) {
						System.out.println("Lowered expression (hashed):" + lcalc.format(expr) + "; reason: [" + e.getMessage() + "]");
					}
					return expr;
				}
				
			} else if (lcalc.typeOf(fun2).accept(getFunctionArgType).equals(TypeETT) && lcalc.typeOf(arg2).equals(TypeETT)) {
				// NP compound case - for example: John applied to Smith - John(Smith).
				// The idea is to create a compound John_Smith with the appropriate type.
				// Note that John might be of type ((et)t)e or ((et)t)((et)t)e, etc. if it needs to be combined with
				// more NPs later on in the course of the derivation like in [[John Smith] Elliot].
				if (arg2.accept(isNPConstant)) {
					Expr e = lcalc.getExprBuilder().variable(buildSymbol(
							fun2.accept(getConstantName) + "_" + arg2.accept(getNPConstantName),
							lcalc.typeOf(fun2).accept(getFunctionReturnType)));
					if (debugMode) {
						System.out.println("NP compound: " + lcalc.format(e));
					}
					return e;
				} else {
					throw new HigherOrderError("NP compound with a non-constant argument: " + lcalc.format(app2));
				}
			} else if ( fun2.accept(isConstant) &&
						lcalc.typeOf(fun2).equals(TypeTET) && 
						lcalc.typeOf(arg2).equals(Types.T)) {
				Expr e = lcalc.getExprBuilder().application(
							lcalc.getExprBuilder().variable(buildSymbol(fun2.accept(getConstantName),Types.EET)),
							hash(arg2,Types.E));
				if (debugMode) {				
					System.out.println("Lowering TET to T application: " + lcalc.format(e));
				}
				return e;
			} else if ( fun2.accept(isConstant) &&
					lcalc.typeOf(fun2).equals(Types.ET_E) && 
					lcalc.typeOf(arg2).equals(Types.ET)) {
				Expr e = hash(app2);
				if (debugMode) {				
					System.out.println("Lowering (ET)E to ET application: " + lcalc.format(e));
				}
				return e;
			} else {
				if (lcalc.typeOf(fun2).equals(Types.ET_ET)) {
					System.err.println("Unlowerable (et)et application: " + lcalc.format(fun2) + " to: " + lcalc.format(arg2));
				}
				
				return app2;
			}
		}

		private final Expr hash(final Expr e) {
			return hash(e,lcalc.typeOf(e));
		}
		
		private final Expr hash(final Expr e, final Type hType) {
			val str = lcalc.format(lcalc.toDeBruijn(lcalc.parse(lcalc.format(e))));
			val hCode = str.hashCode();
			val hName = ("h_" + hCode).replaceAll("-", "_");
			val hVar  = lcalc.getExprBuilder().variable(buildSymbol(hName, hType));
			if (debugMode) {
				System.out.println("Hashed: [" + str + "] => " + hName);
			}
			return hVar;
		}
		
		@Override
		public Expr abstraction(Symbol s, Expr body) {
			return lcalc.getExprBuilder().abstraction(s, body.accept(rewriter));
		}

		@Override
		public Expr variable(Symbol s) {
			return lcalc.getExprBuilder().variable(s);
		}
	};
	
	
	private Set<String> getExternalVars(Expr expr) {
		
		final Set<String> definedVars = new HashSet<String>();
		final Set<String> usedVars = new HashSet<String>();
		
		expr.accept(new Visitor<Void>() {

			@Override
			public Void abstraction(Symbol arg0, Expr arg1) {
				definedVars.add(arg0.getName());
				return arg1.accept(this);
			}

			@Override
			public Void application(Expr arg0, Expr arg1) {
				arg0.accept(this);
				arg1.accept(this);
				return null;
			}

			@Override
			public Void variable(Symbol arg0) {
				if (arg0.getType().equals(Types.E)) {
					usedVars.add(arg0.getName());
				}
				return null;
			}
			
		});
				
		usedVars.removeAll(definedVars);
		return usedVars;
		
	}
	
	private List<Predicate> parsePredicates(Expr arg) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (debugMode) {
			System.out.println("parsing predicate: " + lcalc.format(arg));
		}
		parseNextPredicate(arg, predicates,getExternalVars(arg)); 
		return predicates;
	}
	
	private void parseNextPredicate(Expr arg, final List<Predicate> predicates, final Set<String> externalVars) {

		final List<String> arguments = new ArrayList<String>();
		
		arg.accept(new Visitor<String>() {
			// Concatenate all constants of not type e
			@Override public String abstraction(Symbol s, Expr body) { 
				body.accept(this);
				return null;
			}
			@Override public String application(Expr fun, Expr arg) {
				
				// if the argument is an abstraction on type T, process the function only
				if (arg.accept(
						new Visitor<Boolean>() {
							@Override public Boolean abstraction(Symbol s, Expr body) { 
								return body.accept(new Visitor<Boolean>() {
									@Override public Boolean abstraction(Symbol s, Expr body) { return false; }
									@Override public Boolean application(Expr fun, Expr arg) { return false; }
									@Override public Boolean variable(Symbol s) { return s.getType().equals(Types.T); }
								});
							}
							@Override public Boolean application(Expr fun, Expr arg) { return false; }
							@Override public Boolean variable(Symbol s) { return false; }
						})) {
					return fun.accept(this);
				}

				Type argType = lcalc.typeOf(arg);
				boolean argIsTypeE = argType.equals(Types.E);
				boolean argIsTypeT = argType.equals(Types.T);
				
				if (argIsTypeE) {

					// the application can be one of many options. For example, it can be something like "man:et x1:e" or 
					// something like "love:eet x3:e". What's important to check is whether the argument is an external variable 
					// (i.e. a variable introduced by an external abstraction or a constant like John). If this is the case then
					// we collect the argument for the new predicate the we are building.
					String argName = arg.accept(this);
					boolean argIsExternal = externalVars.contains(argName);

					if (argIsExternal) {
						// i.e. the argument is 'John' or 'x4' introduced by an external iota.
						if (debugMode) {							
							System.out.println("Argument [" + argName + "] is external");
						}
						arguments.add(argName);
					}
					
					fun.accept(this);
					
				} else if (argIsTypeT ){
					// the argument is of type t - it's an element in a list of conjuncts, like P2 in: ((AND P1(x0)) P2(x0))
					
					if (fun.accept(isConstant)) {
						// in this case the function is 'AND' or something alike so we have nothing to do with it
					} else {
						// in this case the function is "AND P1(x)" and arg is P2(x), so we investigate the function first 
						fun.accept(this);
					}
					arg.accept(this);
				} else {
					throw new HigherOrderError("Argument type is neither E nor T: " + lcalc.format(arg));
				}
				
				return null;
			}
			
			@Override public String variable(Symbol s) {
				Type type = s.getType();
				if (type.equals(Types.ET) || type.equals(Types.EET) || type.equals(Types.EEET)) {
					// we have here an ET/EET/EEET function - so we define and add a corresponding predicate to the list
					String name = s.getName();

					Expr expr = lcalc.getExprBuilder().variable(buildSymbol(name, type));
					for (String argument : arguments) {
						expr = lcalc.getExprBuilder().application(expr,
								lcalc.getExprBuilder().variable(buildSymbol(argument, Types.E)));
					}
					predicates.add(new Predicate(name,arguments,expr));
					arguments.clear();
				} 
				return s.getName();
			}
		});

		
	}
	
	private String getNewPredicateName(List<Predicate> predicates) {
		StringBuilder newPredName = new StringBuilder();
		for (Predicate p : predicates) {
			newPredName.append('_');
			newPredName.append(p.getName());
		}
		return newPredName.toString();
	}
	
	private Type getNewPredicateType(Type modifierType, List<Predicate> predicates) {
		Type newType = modifierType;
		for (Predicate p : predicates) {
			for (int i=0; i<p.getArguments().size();i++) {
				newType = new IFunction(Types.E,newType);
			}
		}
		return newType;
	}
	
	private Visitor<Boolean> isConstant = new Visitor<Boolean>() {

		@Override public Boolean abstraction(Symbol s, Expr body) 	{ return false; }
		@Override public Boolean application(Expr fun, Expr arg) 	{ return false; }
		@Override public Boolean variable(Symbol s) 				{ return true;}
	};
	
	private Visitor<String> getConstantName = new Visitor<String>() {

		@Override public String abstraction(Symbol s, Expr body) 	{ return body.accept(this); }
		@Override public String application(Expr fun, Expr arg) 	{ return fun.accept(this); }
		@Override public String variable(Symbol s) 					{ return s.getName();}
	};
	
	private Visitor<Boolean> isNPConstant = new Visitor<Boolean>() {

		@Override public Boolean abstraction(Symbol s, Expr body) 	{ return body.accept(this); }
		@Override public Boolean application(Expr fun, Expr arg) 	{ return fun.accept(this); }
		@Override public Boolean variable(Symbol s) 				{ return true;}
	};
	
	private Visitor<String> getNPConstantName = new Visitor<String>() {

		@Override public String abstraction(Symbol s, Expr body) 	{ return body.accept(this); }
		@Override public String application(Expr fun, Expr arg) 	{ return arg.accept(this); }
		@Override public String variable(Symbol s) 					{ return s.getName();}
	};

	
	private Type.Visitor<Type> getFunctionArgType = new Type.Visitor<Type>() {

		@Override public Type constant(String name) { return new IConstant(name); }
		@Override public Type function(Type a, Type b) { return a; }
	};
	
	private Type.Visitor<Type> getFunctionReturnType = new Type.Visitor<Type>() {
		
		@Override public Type constant(String name) { return new IConstant(name); }
		@Override public Type function(Type a, Type b) { return b; }
	};
	
	Symbol buildSymbol(final String name, final Type type) {
		return new Symbol() {
			@Override public String getName() { return name; }
			@Override public Type getType() { return type; }
		};
	}
		
	String name(final Expr exp) {
		return "p"+exp.hashCode();
	}
	
	public Expr rewrite(Expr a) {
		Expr o = a.accept(rewriter);
		return o;
	}
	

	private class LowerableFunction implements Type.Visitor<Boolean> {
	
		Type type;
		
		public LowerableFunction(Type type) {
			this.type = type;
		}
		
		@Override
		public Boolean constant(String name) {
			return false;
		}
		
		public Type getType() {
			return type;
		}

		@Override
		public Boolean function(Type a, Type b) {
			// The function is lowerable if it takes a type X
			// and either return type X or returns a function from type X
			// to something else. For example, tall is of type (et)et, so
			// it's lowerable; and also (et)(et)et (in the case of noun 
			// compounds).
			
			boolean aTypeOK = a.equals(type);
			boolean bTypeOK = b.equals(type) || b.accept(getFunctionArgType).equals(type);
			return aTypeOK && bTypeOK;
		}
	}
	
	class Predicate {
		private String 			name;
		private List<String>	arguments;
		private Expr			expr;
		
		public Predicate(String name, List<String> arguments, Expr expr) {
			super();
			this.name = name;
			this.arguments = new ArrayList<String>(arguments);
			this.expr = expr;
		}

		public String getName() {
			return name;
		}
		
		public List<String> getArguments() {
			return arguments;
		}
		
		public Expr getExpr() {
			return expr;
		}
		
		
		public String toString() {
			return "Predicate: name=" + name + "; arguments=" + arguments.toString() + "; expr=" + lcalc.format(expr);
		}
	}

}