package semante.predcalc.util;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;
import semante.lambdacalc.impl.IExpr;
import semante.lambdacalc.impl.ITypes;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true)
public class ILowerLambda<T extends TSymbol> {

	protected TLambdaCalc<T> lcalc;
	
	private List<Expr<T>> pragmatics = new ArrayList<Expr<T>>();
	
	// use a pragmatics list state, reset every call
	private IExpr.Identity<T> rewrite = new IExpr.Identity<T>() {

		@Override
		public Expr<T> application(final Expr<T> f, final Expr<T> arg) {
		
			if (lcalc.eqType().apply(lcalc.typeOf(arg), ITypes.E) || lcalc.eqType().apply(lcalc.typeOf(arg), ITypes.T)) {
				// Recursive for e and t types
				return new IExpr.Application<T>(f.accept(rewrite), arg.accept(rewrite));
			} else if (ETStr(lcalc.typeOf(arg)).length() > 0) {
				// The right is <e>t: it's not first order, but we can rewrite it
				Expr<T> left = f.accept(new IExpr.Identity<T>(){
					@Override public Expr<T> variable(T s1) {
						// f is a quantifier or iota
						if (lcalc.eqType().apply(s1.getType(), ITypes.ET_T)) {
							// quantifier (leave it in place)
							return new IExpr.Application<T>(f, arg.accept(new IExpr.Identity<T>(){
								@Override public Expr<T> abstraction(final T s2, final Expr<T> body2) {
									// .. if the right is an abstraction, return a new abstraction
									return new IExpr.Abstraction<T>(s2, body2.accept(rewrite));
								}
								@Override public Expr<T> application(Expr<T> f2, Expr<T> arg2)
									{ throw new Error("Non-abstraction in quantifier: " + f2 + " " + arg2); }
								@Override public Expr<T> variable(T s2)
									{ throw new Error("Non-abstraction in quantifier: " + s2); }
							}));
						} else if (lcalc.eqType().apply(s1.getType(), ITypes.ET_E)) {
							// iota (return a new constant)
							String c = "c"+pragmatics.size();
							val s = "FORALL:(et)t (\\x:e.((EQUIVALENCES:ttt (("+lcalc.format(arg)+") x:e)) ((IS:eet x:e) "+c+":e)))";
							pragmatics.add(lcalc.betaReduce(lcalc.parse(s)).accept(rewrite));
							return lcalc.parse(c+":e");
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
			return new IExpr.Abstraction<T>(s, arg.accept(rewrite));
		}
		
		// +
		private Expr<T> compound(final Expr<T> a, final Expr<T> b) {
			return b.accept(new IExpr.Identity<T>(){
				@Override public Expr<T> variable(final T sb) {
					if (typeAll(sb.getType(), ITypes.E) || typeAll(sb.getType(), ITypes.T)) {
						// a + b:<e> = b   ,   a + b:<t> = b
						return new IExpr.Variable<T>(sb);
					} else {
						return a.accept(new IExpr.Identity<T>(){
							@Override public Expr<T> variable(final T sa) {
								// a : <e>_g (<e>_a t) x + b : <e>_b t = ab : <e>_g+(b-a) x
								String type = sa.getType().accept(walka(ETStr(sb.getType())));
								if (null != type) {
									return lcalc.parse(sa.getName() + "_" + sb.getName() + ":" + type);
								} else {
									throw new Error("Unrewritable higher order: ("+a+" "+b+")");
								}
							}
							@Override public Expr<T> abstraction(final T sa, final Expr<T> bodya) {
								// \x.a + b = \x.(a+b)
								return new IExpr.Abstraction<T>(sa, compound(bodya, b));
							}
							@Override public Expr<T> application(Expr<T> fa, Expr<T> arga) {
								// (f g) + b = (f+b g)
								return new IExpr.Application<T>(compound(fa, b), arga);
							}
						});
					}
					
				}
				@Override public Expr<T> abstraction(final T sb, final Expr<T> bodyb) {
					return a.accept(new IExpr.Identity<T>(){
						@Override public Expr<T> variable(final T sa) {
							// a + \x.b = \x.(a+b)
							return new IExpr.Abstraction<T>(sb, compound(a, bodyb));
						}
						@Override public Expr<T> abstraction(final T sa, final Expr<T> bodya) {
							// a + \x.b = \x.(a+b)
							return new IExpr.Abstraction<T>(sb, compound(a, bodyb));
						}
						@Override public Expr<T> application(Expr<T> fa, Expr<T> arga) {
							// (f g) + \x.b = ((\x.f+b) g)
							return new IExpr.Application<T>(new IExpr.Abstraction<T>(sb, compound(fa, bodyb)), arga);
						}
					});
				}
				@Override public Expr<T> application(Expr<T> fb, Expr<T> argb) {
					// a + (f g) = (a+f a+g)
					return new IExpr.Application<T>(compound(a, fb), compound(a, argb));
				}
			});
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
		private Type.Visitor<String> walka(final String beta) {
			return new Type.Visitor<String>(){
				@Override public String typeConstant(String name)
					{ return null; }
				@Override public String typeFunction(Type a, Type b) {
					if (lcalc.eqType().apply(a, ITypes.E)) {
						String wb = b.accept(walka(beta));
						return (null == wb)? null : "e" + wb;											
					} else {
						String etstr = ETStr(a);
						return (null == etstr || null == beta)? null : beta.substring(etstr.length()) + b;
					}
				}
			};
		}
	};
	
	Expr<T> newPredicate(String l, String r) {
		// Uncomment for pulman-style
//		return lcalc.parse("("+l+":eet "+r.toUpperCase()+":e)");
		return lcalc.parse(l+"_"+r+":et");
	}

	public SemPrag<T> rewrite(Expr<T> a) {
		return new SemPrag<T>(lcalc.betaReduce(a.accept(rewrite)), pragmatics);
	}
	

	@Value
	public static class SemPrag<T extends TSymbol> {
		Expr<T> semantics;
		List<Expr<T>> pragmatics;	
	}

}