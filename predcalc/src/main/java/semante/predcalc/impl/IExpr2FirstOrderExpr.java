package semante.predcalc.impl;

import java.util.ArrayList;
import java.util.List;

import lambdacalc.Expr;
import lambdacalc.Expr.Visitor;
import lambdacalc.ExprBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.Types;
import lambdacalc.impl.IType.IConstant;
import lambdacalc.impl.IType.IFunction;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.predcalc.Expr2FirstOrderExpr;
import semante.predcalc.HigherOrderError;


@RequiredArgsConstructor
@FieldDefaults(makeFinal=true)
public class IExpr2FirstOrderExpr implements Expr2FirstOrderExpr {

	protected STL lcalc; 
	
	public List<String> getComments() {
		return new ArrayList<String>();
	}
	
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
				System.out.println("Lowerable application: the function  ["+ lcalc.format(fun2) + "] is applied to an argument of type " + lcalc.format(lcalc.typeOf(arg2)) + ": [" + lcalc.format(arg2) + "]");
				
				try {
					return fun2.accept(new ExprBuilder() {

							@Override
							public Expr abstraction(Symbol s, Expr body) {
								throw new HigherOrderError("Abstraction of type (et)et: "+lcalc.format(fun2));
							}

							@Override
							public Expr application(Expr fun3, Expr arg3) {
								// in this case the modifier is an application by itself, e.g. "in Brasil" modifies "walks".
								Expr e = null;
								if (fun3.accept(isConstant)) {

									// There's a subtle point here - we apply an application ("in Brasil") to a predicate, 
									// (e.g. "walks") and we want to have a new predicate that takes the argument of the 
									// application (i.e. "Brasil" as an argument. So our new predicate will look like 
									// in_walks(Brasil)(John) in the end.
									// To do this, we ignore the argument of the application (i.e. "Brasil"), and smash the
									// application as if it was just "in" applied to "walks". This will generate a new
									// predicate - "in_walks". Since this new predicate needs to be applied to the argument
									// that we ommitted - "brasil", the type of the predicate will be a function from E to
									// the type that the original application (i.e. "in Brasil") returns after applied to 
									// the predicate it modifies.
								
									// here we decide on the type of the new predicate
									Type newType = new IFunction(Types.E,lcalc.typeOf(fun2).accept(getFunctionReturnType));
									
									// lower the modification as if the modifier is a constant (like 'tall') 
									Expr simple = lowerModification(fun3.accept(getConstantName), lcalc.typeOf(fun2), newType , arg2);
									
									// apply the argument (e.g. 'Brasil')
									e = lcalc.getExprBuilder().application(simple,
											lcalc.getExprBuilder().variable(buildSymbol(arg3.accept(getConstantName), Types.E)));
									
									System.out.println("Lowered expression (modification by application):" + lcalc.format(e));
								
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
								System.out.println("Lowered expression (direct modification):" + lcalc.format(e));
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
									return hash(lcalc.getExprBuilder().application(
											lcalc.getExprBuilder().variable(
													buildSymbol(modifierName,modifierType)), arg));
								}
							}
							
						});
				} catch (HigherOrderError e) {
					return hash(app2);
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
					System.out.println("NP compound: " + lcalc.format(e));
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
				System.out.println("Lowering TET to T application: " + lcalc.format(e));
				return e;
			} else if ( fun2.accept(isConstant) &&
					lcalc.typeOf(fun2).equals(Types.ET_E) && 
					lcalc.typeOf(arg2).equals(Types.ET)) {
				Expr e = hash(app2);
				System.out.println("Lowering (ET)E to ET application: " + lcalc.format(e));
				return e;
			} else {
				return app2;
			}
		}

		private final Expr hash(final Expr e) {
			return hash(e,lcalc.typeOf(e));
		}
		
		private final Expr hash(final Expr e, final Type hType) {
			val hCode = lcalc.format(lcalc.toDeBruijn(e)).hashCode();
			val hName = ("h_" + hCode).replaceAll("-", "_");
			val hVar  = lcalc.getExprBuilder().variable(buildSymbol(hName, hType));
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
	
	private List<Predicate> parsePredicates(Expr arg) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		parseNextPredicate(arg, predicates); 
		return predicates;
	}
	
	private void parseNextPredicate(Expr arg, final List<Predicate> predicates) {

		final List<String> arguments = new ArrayList<String>();
		
		arg.accept(new Visitor<String>() {
			// Concatenate all constants of not type e
			@Override public String abstraction(Symbol s, Expr body) { 
				body.accept(this);
				return null;
			}
			@Override public String application(Expr fun, Expr arg) {
				if (arg.accept(new Visitor<Boolean>() {
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
					if (fun.accept(isConstant)) {
						// the application can be one of many options. For example, it can be something like "man:et x1:e" or 
						// something like "man:et c1:e" or something like "loves:eet c2:e". What's important to check is whether
						// the argument is a variable (e.g. x0) or not. If it is, then it's due to some abstraction and we ignore
						// it. In that case we investigate only the function. 
						// if, however, the argument is not a variable, it means that it is either a fixed constant like John or
						// a constant created by an iota, like c1. In these cases we create a complex predicate - a predicate with
						// an argument.

						String argName = arg.accept(this);
						boolean argIsVariable = argIsTypeE && argName.matches("x[0-9]+");

						if (!argIsVariable) {
							// i.e. the argument is 'John' or 'c1'
							arguments.add(argName);
						}
						fun.accept(this);
						
					} else {
						// the function is not constant, so it's an application by itself - something like: ((Loves c1) x1) 
						fun.accept(this);
					}
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
					throw new HigherOrderError("Argument type is neither E nor T" + lcalc.format(arg));
				}
				
				return null;

				// in this case have an argument like x0 - an argument this is a result some abstraction; we ignore it and investigate the function
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
	}

}