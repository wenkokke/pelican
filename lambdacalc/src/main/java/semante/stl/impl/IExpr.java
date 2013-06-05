package semante.stl.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;
import lombok.experimental.Wither;
import semante.stl.Expr;
import semante.stl.Symbol;

@FieldDefaults(makeFinal=true,level=PRIVATE)
public abstract class IExpr implements Expr {

	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IAbstraction extends IExpr {
		Symbol s;
		Expr arg;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.abstraction(s, arg);
		}
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IApplication extends IExpr {
		Expr fun,arg;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.application(fun, arg);
		}
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IVariable extends IExpr {
		Symbol s;

		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.variable(s);
		}
	}
	
	@Value
	public static final class IIdentity implements Identity {

		@Override
		public Expr abstraction(Symbol arg0, Expr arg1) {
			return new IAbstraction(arg0,arg1);
		}

		@Override
		public Expr application(Expr arg0, Expr arg1) {
			return new IApplication(arg0,arg1);
		}

		@Override
		public Expr variable(Symbol s) {
			return new IVariable(s);
		}
	}
}
