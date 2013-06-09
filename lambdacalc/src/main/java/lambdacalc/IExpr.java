package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;

@FieldDefaults(makeFinal=true,level=PRIVATE)
public abstract class IExpr implements Expr {

	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IAbstraction extends IExpr {
		Symbol symbol;
		Expr arg;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.abstraction(symbol, arg);
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
		Symbol symbol;

		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.variable(symbol);
		}
	}
}
