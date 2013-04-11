package semante.lambdacalc.util;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Expr;
import semante.lambdacalc.StlTypeError;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;
import semante.lambdacalc.Type.ErrorVisitor;
import semante.lambdacalc.TypeOf;


@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class ITTypeOf<T extends TSymbol> implements TypeOf<T> {

	Type.Identity builder;
	Equality<Type> eqType;
	
	@Override
	public final Type typeOf(Expr<T> expr) {
		return expr.accept(GetType);
	}
	
	// visitor that reduced expr to its type.
	Expr.Visitor<T, Type> GetType
		= new Expr.Visitor<T, Type>() {
	
			@Override
			public final Type abstraction(final T s, final Expr<T> a) {
				return builder.typeFunction(s.getType(), a.accept(this));
			}
			
			@Override
			public final Type application(final Expr<T> fn, final Expr<T> a1) {
				final Type a1Type = a1.accept(this);
				final Type fnType = fn.accept(this);
				return fnType.accept(new ErrorVisitor<Type>() {
					@Override
					public final Type typeFunction(final Type a2Type, final Type bType) {
						if (eqType.apply(a1Type, a2Type)) {
							return bType;
						}
						else {
							throw new StlTypeError(a1Type,a2Type);
						}
					}
				});
			}
			
			@Override
			public final Type variable(T s) {
				return s.getType();
			}
		};
}
