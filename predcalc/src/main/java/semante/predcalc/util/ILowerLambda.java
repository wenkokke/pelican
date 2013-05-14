package semante.predcalc.util;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Expr;
import semante.lambdacalc.Expr.Identity;
import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;
import semante.lambdacalc.impl.IExpr;
import semante.lambdacalc.impl.ITypes;
import semante.predcalc.ExprForm;
import semante.predcalc.LowerLambda;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true)
public class ILowerLambda<T extends TSymbol> implements LowerLambda<T> {

	protected TLambdaCalc<T> lcalc;
	private Counter counter = new Counter();
	private List<Expr<T>> pragmatics = new ArrayList<Expr<T>>();
	
	// use a pragmatics list state, reset every call
	private IExpr.Identity<T> rewrite = new IExpr.Identity<T>() {
		@Override
		public Expr<T> application(final Expr<T> f, final Expr<T> arg) {
		
			if (lcalc.eqType().apply(lcalc.typeOf(arg), ITypes.E) || lcalc.eqType().apply(lcalc.typeOf(arg), ITypes.T)) {
				// Recursive for e and t types
				return lcalc.exprBuilder().application(f.accept(rewrite), arg.accept(rewrite));
			} else if (ETStr(lcalc.typeOf(arg)).length() > 0) {
				// The right is <e>t: it's not first order, but we can rewrite it
				Expr<T> left = f.accept(new IExpr.Identity<T>(){
					@Override public Expr<T> variable(T s1) {
						// f is a quantifier or iota
						if (lcalc.eqType().apply(s1.getType(), ITypes.ET_T)) {
							// quantifier (leave it in place)
							return lcalc.exprBuilder().application(f, arg.accept(new IExpr.Identity<T>(){
								@Override public Expr<T> abstraction(final T s2, final Expr<T> body2) {
									// .. if the right is an abstraction, return a new abstraction
									return lcalc.exprBuilder().abstraction(s2, body2.accept(rewrite));
								}
								@Override public Expr<T> application(Expr<T> f2, Expr<T> arg2)
									{ throw new Error("Non-abstraction in quantifier: " + f2 + " " + arg2); }
								@Override public Expr<T> variable(T s2)
									{ throw new Error("Non-abstraction in quantifier: " + s2); }
							}));
						} else if (lcalc.eqType().apply(s1.getType(), ITypes.ET_E)) {
							// iota (return a new constant)
							Identity<T> b = lcalc.exprBuilder();
							Expr<T> c = b.variable(buildSymbol("c"+counter.get(), ITypes.E));
							Expr<T> e =
								b.application(
									b.variable(buildSymbol("EQUIVALENCES", ITypes.TTT)),
									b.application(
											arg,
											b.variable(buildSymbol("x", ITypes.E))));
							Expr<T> i =
									b.application(
										b.application(
												b.variable(buildSymbol("EQ", lcalc.typeBuilder().typeFunction(ITypes.E, ITypes.ET))),
												b.variable(buildSymbol("x", ITypes.E))), 
										c);
							Expr<T> q =
									b.application(
										b.variable(buildSymbol("FORALL", ITypes.ET_T)),
										b.abstraction(
												buildSymbol("x", ITypes.E),
												b.application(e, i)));
							pragmatics.add(lcalc.betaReduce(lcalc.betaReduce(q).accept(rewrite)));
							return c;
						} else {
							return null;
						}						
					}
					@Override public Expr<T> abstraction(final T s1, final Expr<T> body1)	{ return null; }
					@Override public Expr<T> application(Expr<T> f1, Expr<T> arg1)			{ return null; }
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
		
		@Override public Expr<T> abstraction(T s, Expr<T> arg) {
			return lcalc.exprBuilder().abstraction(s, arg.accept(rewrite));
		}
		
		// +
		private Expr<T> compound(final Expr<T> a, final Expr<T> b) {
			return b.accept(new IExpr.Identity<T>(){
				@Override public Expr<T> variable(final T sb) {
					if (typeAll(sb.getType(), ITypes.E) || typeAll(sb.getType(), ITypes.T)) {
						// a + b:<e> = b   ,   a + b:<t> = b
						return lcalc.exprBuilder().variable(sb);
					} else {
						return a.accept(new IExpr.Identity<T>(){
							@Override public Expr<T> variable(final T sa) {
								// a : <e>_g (<e>_a t) x + b : <e>_b t = ab : <e>_g+(b-a) x
								Type type = sa.getType().accept(walka(sb.getType()));
								if (null != type) {
									return lcalc.exprBuilder().variable(buildSymbol(sa.getName() + "_" + sb.getName(), type));
								} else {
									throw new Error("Unrewritable higher order: ("+a+" "+b+")");
								}
							}
							@Override public Expr<T> abstraction(final T sa, final Expr<T> bodya) {
								// \x.a + b = \x.(a+b)
								return lcalc.exprBuilder().abstraction(sa, compound(bodya, b));
							}
							@Override public Expr<T> application(Expr<T> fa, Expr<T> arga) {
								// (f g) + b = (f+b g)
								return lcalc.exprBuilder().application(compound(fa, b), arga);
							}
						});
					}
					
				}
				@Override public Expr<T> abstraction(final T sb, final Expr<T> bodyb) {
					return a.accept(new IExpr.Identity<T>(){
						@Override public Expr<T> variable(final T sa) {
							// a + \x.b = \x.(a+b)
							return lcalc.exprBuilder().abstraction(sb, compound(a, bodyb));
						}
						@Override public Expr<T> abstraction(final T sa, final Expr<T> bodya) {
							// a + \x.b = \x.(a+b)
							return lcalc.exprBuilder().abstraction(sb, compound(a, bodyb));
						}
						@Override public Expr<T> application(Expr<T> fa, Expr<T> arga) {
							// (f g) + \x.b = ((\x.f+b) g)
							return lcalc.exprBuilder().application(lcalc.exprBuilder().abstraction(sb, compound(fa, bodyb)), arga);
						}
					});
				}
				@Override public Expr<T> application(Expr<T> fb, Expr<T> argb) {
					// a + (f g) = (a+f a+g)
					return lcalc.exprBuilder().application(compound(a, fb), compound(a, argb));
				}
			});
		}
		
		@SuppressWarnings("unchecked")
		T buildSymbol(final String name, final Type type) {
			return (T) new TSymbol() {
				@Override public String getName() { return name; }
				@Override public Type getType() { return type; }
			};
		}
		
		// Is this type just applications of "all" ?
		Boolean typeAll(final Type t, final Type all) {
			return lcalc.eqType().apply(t, all) || t.accept(new Type.Visitor<Boolean>() {
				@Override public Boolean typeConstant(String name) { return false; }
				@Override public Boolean typeFunction(Type a, Type b) {
					return lcalc.eqType().apply(a, all) && typeAll(b, all);
				}
			});
		};
		
		// all the e's in a eeeeeeet type
		String ETStr(final Type t) {
			return lcalc.eqType().apply(t, ITypes.T)? "" : t.accept(new Type.Visitor<String>() {
				@Override public String typeConstant(String name)
					{ return null; }
				@Override public String typeFunction(Type a, Type b) {
					if (lcalc.eqType().apply(a, ITypes.E)) {
						String etstr = ETStr(b);
						return (null == etstr)? null : "e" + etstr;
					} else {
						return null;
					}
				}
			});
		};
		
		// Make a string that corresponds to <e>_g <e>_b-a x
		// where b is the number of e's from b
		// and g is the number of e's from a, and a is the number of e's from the (et)
		// (It's complicated)
		private Type.Visitor<Type> walka(final Type b) {
			final semante.lambdacalc.Type.Identity tb = lcalc.typeBuilder();
			return new Type.Visitor<Type>(){
				@Override public Type typeConstant(String name) { return null; }
				@Override public Type typeFunction(Type l, final Type r) {
					if (lcalc.eqType().apply(l, ITypes.E)) {
						Type wb = r.accept(walka(b));
						return (null == wb)? null : tb.typeFunction(ITypes.E, wb);									
					} else {
						return r.accept(new Type.Visitor<Type>() {
							@Override public Type typeConstant(String name) { return r; }
							@Override public Type typeFunction(Type a, Type x) {
								return b.accept(rmb(a, x));
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
							if (lcalc.eqType().apply(l, ITypes.E)) {
								// if we're looking at an e in m
								return a.accept(new Type.Visitor<Type>(){
									@Override public Type typeConstant(String name) {
										// if we're at the end of a, add an e to the front of x
										return tb.typeFunction(ITypes.E, r.accept(rmb(a, x)));
									}
									@Override public Type typeFunction(Type a1, Type a2) {
										// if we're stripping the e's off b (and off a)
										if (lcalc.eqType().apply(a1, ITypes.E)) {
											return r.accept(rmb(a1, x));
										} else {
											throw new Error("a is not an application of an e-type to anything!");
										}
									}
								});									
							} else {
								// if the last of m is T, return x (the rest)
								return x;
							}
						}
					};
				}
			};
		}
		
		
	};
	
	Expr<T> newPredicate(String l, String r) {
		// Uncomment for pulman-style
//		return lcalc.parse("("+l+":eet "+r.toUpperCase()+":e)");
		return lcalc.parse(l+"_"+r+":et");
	}

	public ExprForm<Expr<T>> rewrite(Expr<T> a) {
		pragmatics.clear();
		return new IExprForm<Expr<T>>(lcalc.betaReduce(a.accept(rewrite)), pragmatics);
	}
	
	private class Counter {
		private int c=0;
		private int get() { c++; return c; }
	}

}