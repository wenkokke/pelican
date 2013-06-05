package semante.stl.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;
import lombok.experimental.Wither;
import semante.stl.Expr$DeBruijn;
import semante.stl.Symbol;

@FieldDefaults(makeFinal=true,level=PRIVATE)
public abstract class IExpr$DeBruijn implements Expr$DeBruijn {

	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IAbstraction extends IExpr$DeBruijn {
		Expr$DeBruijn body;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.abstraction(body);
		}
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IApplication extends IExpr$DeBruijn {
		Expr$DeBruijn fun,arg;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.application(fun, arg);
		}
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IVariable extends IExpr$DeBruijn {
		Integer i;

		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.variable(i);
		}
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IConstant extends IExpr$DeBruijn {
		Symbol s;

		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.constant(s);
		}
	}
	
	@Value
	public static final class IIdentity implements Identity {
		
		@Override
		public Expr$DeBruijn abstraction(Expr$DeBruijn arg0) {
			return new IAbstraction(arg0);
		}

		@Override
		public Expr$DeBruijn application(Expr$DeBruijn arg0, Expr$DeBruijn arg1) {
			return new IApplication(arg0,arg1);
		}

		@Override
		public Expr$DeBruijn variable(Integer i) {
			return new IVariable(i);
		}

		@Override
		public Expr$DeBruijn constant(Symbol s) {
			return new IConstant(s);
		}

	}
}
