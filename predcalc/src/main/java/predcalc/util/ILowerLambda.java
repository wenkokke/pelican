package predcalc.util;

import java.util.ArrayList;
import java.util.List;

import predcalc.ExprForm;
import predcalc.LowerLambda;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lambdacalc.Expr;
import lambdacalc.ExprBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.IExpr;
import lambdacalc.IType;
import lambdacalc.IType.IFunction;


@RequiredArgsConstructor
@FieldDefaults(makeFinal=true)
public class ILowerLambda implements LowerLambda {

	protected STL lcalc;
	private Counter counter = new Counter();
	private List<Expr> pragmatics = new ArrayList<Expr>();
	
	// use a pragmatics list state, reset every call
	private ExprBuilder rewrite = new ExprBuilder() {
		@Override
		public Expr application(final Expr f, final Expr arg) {
			if (lcalc.typeOf(arg).equals(IType.E) || lcalc.typeOf(arg).equals(IType.T)) {
				// Recursive for e and t types
				return lcalc.getExprBuilder().application(f.accept(rewrite), arg.accept(rewrite));
			} else if (ETStr(lcalc.typeOf(arg)).length() > 0) {
				// The right is <e>t: it's not first order, but we can rewrite it
				Expr left = f.accept(new ExprBuilder(){
					@Override public Expr variable(Symbol s1) {
						// f is a quantifier or iota
						if (s1.getType().equals(IType.ET_T)) {
							// quantifier (leave it in place)
							return lcalc.getExprBuilder().application(f, arg.accept(new ExprBuilder(){
								@Override public Expr abstraction(final Symbol s2, final Expr body2) {
									// .. if the right is an abstraction, return a new abstraction
									return lcalc.getExprBuilder().abstraction(s2, body2.accept(rewrite));
								}
								@Override public Expr application(Expr f2, Expr arg2)
									{ throw new Error("Non-abstraction in quantifier: " + f2 + " " + arg2); }
								@Override public Expr variable(Symbol s2)
									{ throw new Error("Non-abstraction in quantifier: " + s2); }
							}));
						} else if (s1.getType().equals(new IFunction(IType.ET, IType.E))) {
							// iota (return a new constant)
							ExprBuilder b = lcalc.getExprBuilder();
							Expr c = b.variable(buildSymbol("c"+counter.get(), IType.E));
							Expr e =
								b.application(
									b.variable(buildSymbol("EQUIVALENCES".equals(IType.TTT)),
									b.application(
											arg,
											b.variable(buildSymbol("x", IType.E)))));
							Expr i =
									b.application(
										b.application(
												b.variable(buildSymbol("EQ", lcalc.typeBuilder().typeFunction(IType.E.equals(IType.ET))),
												b.variable(buildSymbol("x", IType.E)))), 
										c);
							Expr q =
									b.application(
										b.variable(buildSymbol("FORALL".equals(IType.ET_T)),
										b.abstraction(
												buildSymbol("x", IType.E),
												b.application(e, i))));
							pragmatics.add(lcalc.betaReduce(lcalc.betaReduce(q).accept(rewrite)));
							return c;
						} else {
							return null;
						}						
					}
					@Override public Expr abstraction(final Symbol s1, final Expr body1)	{ return null; }
					@Override public Expr application(Expr f1, Expr arg1)			{ return null; }
				});
				if (null != left) {
					return left;
				} else {
					return compound(f.accept(rewrite), arg.accept(rewrite));
				}
			} else {
				// normal application
				return compound(f.accept(rewrite), arg.accept(rewrite));
			}
			
		}
		
		@Override public Expr abstraction(Symbol s, Expr arg) {
			return lcalc.getExprBuilder().abstraction(s, arg.accept(rewrite));
		}
		
		// +
		private Expr compound(final Expr a, final Expr b) {
			return b.accept(new ExprBuilder(){
				@Override public Expr variable(final Symbol sb) {
					if (typeAll(sb.getType().equals(IType.E) || typeAll(sb.getType(), IType.T))) {
						// a + b:<e> = b   ,   a + b: = b
						return lcalc.getExprBuilder().variable(sb);
					} else {
						return a.accept(new ExprBuilder(){
							@Override public Expr variable(final Symbol sa) {
								// a : <e>_g (<e>_a t) x + b : <e>_b t = ab : <e>_g+(b-a) x
								Type type = sa.getType().accept(walka(sb.getType()));
								if (null != type) {
									return lcalc.getExprBuilder().variable(buildSymbol(sa.getName() + "_" + sb.getName(), type));
								} else {
									throw new Error("Unrewritable higher order: ("+a+" "+b+")");
								}
							}
							@Override public Expr abstraction(final Symbol sa, final Expr bodya) {
								// \x.a + b = \x.(a+b)
								return lcalc.getExprBuilder().abstraction(sa, compound(bodya, b));
							}
							@Override public Expr application(Expr fa, Expr arga) {
								// (f g) + b = (f+b g)
								return lcalc.getExprBuilder().application(compound(fa, b), arga);
							}
							@Override
							public Expr abstraction(Symbol s, Expr body) {
								// TODO Auto-generated method stub
								return null;
							}
							@Override
							public Expr variable(Symbol s) {
								// TODO Auto-generated method stub
								return null;
							}
						});
					}
					
				}
				@Override public Expr abstraction(final Symbol sb, final Expr bodyb) {
					return a.accept(new ExprBuilder(){
						@Override public Expr variable(final Symbol sa) {
							// a + \x.b = \x.(a+b)
							return lcalc.getExprBuilder().abstraction(sb, compound(a, bodyb));
						}
						@Override public Expr abstraction(final Symbol sa, final Expr bodya) {
							// a + \x.b = \x.(a+b)
							return lcalc.getExprBuilder().abstraction(sb, compound(a, bodyb));
						}
						@Override public Expr application(Expr fa, Expr arga) {
							// (f g) + \x.b = ((\x.f+b) g)
							return lcalc.getExprBuilder().application(lcalc.getExprBuilder().abstraction(sb, compound(fa, bodyb)), arga);
						}
						@Override
						public Expr abstraction(Symbol s, Expr body) {
							// TODO Auto-generated method stub
							return null;
						}
						@Override
						public Expr variable(Symbol s) {
							// TODO Auto-generated method stub
							return null;
						}
					});
				}
				@Override public Expr application(Expr fb, Expr argb) {
					// a + (f g) = (a+f a+g)
					return lcalc.getExprBuilder().application(compound(a, fb), compound(a, argb));
				}
			});
		}
		
		@SuppressWarnings("unchecked")
		Symbol buildSymbol(final String name, final Type type) {
			return new Symbol() {
				@Override public String getName() { return name; }
				@Override public Type getType() { return type; }
			};
		}
		
		// Is this type just applications of "all" ?
		Boolean typeAll(final Type t, final Type all) {
			return t.equals(all) || t.accept(new Type.Visitor<Boolean>() {
				@Override public Boolean typeConstant(String name) { return false; }
				@Override public Boolean typeFunction(Type a, Type b) {
					return a.equals(all) && typeAll(b, all);
				}
				@Override
				public Boolean constant(String name) {
					// TODO Auto-generated method stub
					return null;
				}
				@Override
				public Boolean function(Type a, Type b) {
					// TODO Auto-generated method stub
					return null;
				}
			});
		};
		
		// all the e's in a eeeeeeet type
		String ETStr(final Type t) {
			return t.equals(IType.T)? "" : t.accept(new Type.Visitor<String>() {
				@Override public String typeConstant(String name)
					{ return null; }
				@Override public String typeFunction(Type a, Type b) {
					if (a.equals(IType.E)) {
						String etstr = ETStr(b);
						return (null == etstr)? null : "e" + etstr;
					} else {
						return null;
					}
				}
				@Override
				public String constant(String name) {
					// TODO Auto-generated method stub
					return null;
				}
				@Override
				public String function(Type a, Type b) {
					// TODO Auto-generated method stub
					return null;
				}
			});
		};
		
		// Make a string that corresponds to <e>_g <e>_b-a x
		// where b is the number of e's from b
		// and g is the number of e's from a, and a is the number of e's from the (et)
		// (It's complicated)
		private Type.Visitor<Type> walka(final Type b) {
			final lambdacalc.Type.Identity tb = lcalc.typeBuilder();
			return new Type.Visitor<Type>(){
				@Override public Type typeConstant(String name) { return null; }
				@Override public Type typeFunction(Type l, final Type r) {
					if (l.equals(IType.E)) {
						Type wb = r.accept(walka(b));
						return (null == wb)? null : tb.typeFunction(IType.E, wb);									
					} else {
						return r.accept(new Type.Visitor<Type>() {
							@Override public Type typeConstant(String name) { return r; }
							@Override public Type typeFunction(Type a, Type x) {
								return b.accept(rmb(a, x));
							}
							@Override
							public Type constant(String name) {
								// TODO Auto-generated method stub
								return null;
							}
							@Override
							public Type function(Type a, Type b) {
								// TODO Auto-generated method stub
								return null;
							}
						});
					}
				}
				
				// remove a from b, return x in the place of t from b
				// perfectly clear astoundingly legible
				private Type.Visitor<Type> rmb(final Type a, final Type x) {
					return new Type.Visitor<Type>(){
						@Override public Type typeConstant(String name) { return x; }
						@Override public Type typeFunction(Type l, final Type r) {
							if (l.equals(IType.E)) {
								// if we're looking at an e in m
								return a.accept(new Type.Visitor<Type>(){
									@Override public Type typeConstant(String name) {
										// if we're at the end of a, add an e to the front of x
										return tb.typeFunction(IType.E, r.accept(rmb(a, x)));
									}
									@Override public Type typeFunction(Type a1, Type a2) {
										// if we're stripping the e's off b (and off a)
										if (a1.equals(IType.E)) {
											return r.accept(rmb(a1, x));
										} else {
											throw new Error("a is not an application of an e-type to anything!");
										}
									}
									@Override
									public Type constant(String name) {
										// TODO Auto-generated method stub
										return null;
									}
									@Override
									public Type function(Type a, Type b) {
										// TODO Auto-generated method stub
										return null;
									}
								});									
							} else {
								// if the last of m is T, return x (the rest)
								return x;
							}
						}
						@Override
						public Type constant(String name) {
							// TODO Auto-generated method stub
							return null;
						}
						@Override
						public Type function(Type a, Type b) {
							// TODO Auto-generated method stub
							return null;
						}
					};
				}
				@Override
				public Type constant(String name) {
					// TODO Auto-generated method stub
					return null;
				}
				@Override
				public Type function(Type a, Type b) {
					// TODO Auto-generated method stub
					return null;
				}
			};
		}

		@Override
		public Expr abstraction(Symbol s, Expr body) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Expr variable(Symbol s) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	};
	
	Expr newPredicate(String l, String r) {
		// Uncomment for pulman-style
//		return lcalc.parse("("+l+":eet "+r.toUpperCase()+":e)");
		return lcalc.parse(l+"_"+r+":et");
	}

	public ExprForm<Expr> rewrite(Expr a) {
		pragmatics.clear();
		return new IExprForm<Expr>(lcalc.betaReduce(a.accept(rewrite)), pragmatics);
	}
	
	private class Counter {
		private int c=0;
		private int get() { c++; return c; }
	}

}