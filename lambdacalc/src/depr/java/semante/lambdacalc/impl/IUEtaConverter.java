package semante.lambdacalc.impl;

//import static lombok.AccessLevel.PRIVATE;
//import lombok.RequiredArgsConstructor;
//import lombok.val;
//import lombok.experimental.FieldDefaults;
//import semante.lambdacalc.EtaConverter;
//import semante.lambdacalc.Expr;
//import semante.lambdacalc.Expr.Null;
//import semante.lambdacalc.Expr.Test;
//import semante.lambdacalc.Expr.Test.False;
//import semante.lambdacalc.Symbol;
//
//final class ISEtaConverter<S extends Symbol> extends IExpr.Identity<S> implements EtaConverter<S> {
//
//	@Override
//	public Expr<S> etaConvert(Expr<S> expr) {
//		return expr.accept(this);
//	}
//
//	@Override
//	public final Expr<S> abstraction(final S name, Expr<S> expr) {
//		// eta-reduce the sub expression.
//		val expR = etaConvert(expr);
//		
//		// try to reduce this abstraction.
//		val tryReduce = expR.accept(new TryConvertWith(name));
//		if (tryReduce != null) {
//			// return function out of \x.(f x)
//			return tryReduce;
//		}
//		else {
//			// return identity (Abs(name,expR))
//			return super.abstraction(name, expR);
//		}
//	}
//
//	@Override
//	public final Expr<S> application(final Expr<S> l1, final Expr<S> r1) {
//		val lR = etaConvert(l1);
//		val rR = etaConvert(r1);
//		return super.application(lR,rR);
//	}
//	
//	/**
//	 * If eta-conversion is possible it returns the extracted function.
//	 * Otherwise it returns null.
//	 */
//	@FieldDefaults(makeFinal=true, level=PRIVATE)
//	private final class TryConvertWith extends Expr.Null<S,Expr<S>> {
//		Test<S> isVarWithName;
//		Test<S> nameIsBoundTL;
//		
//		public TryConvertWith(String name) {
//			isVarWithName = new IsVarWith(name);
//			nameIsBoundTL = new IsBoundTL(name);
//		}
//		
//		@Override
//		public final Expr application(Expr fun, Expr arg) {
//			if (arg.accept(isVarWithName)) {
//				if (! fun.accept(nameIsBoundTL)) {
//					return fun;
//				}
//			}
//			return null;
//		}
//	}
//	
//	/**
//	 * Test: is Var with a given name.
//	 */
//	@RequiredArgsConstructor
//	@FieldDefaults(makeFinal=true, level=PRIVATE)
//	private final class IsVarWith extends False {
//		String name;
//		
//		@Override
//		public final Boolean variable(String name) {
//			return name.equals(this.name);
//		}
//	}
//}
