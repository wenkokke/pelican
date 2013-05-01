package semante.lambdacalc.util;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.Type;
import semante.lambdacalc.TypeChecker;
import semante.lambdacalc.TypeOf;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class ITTypeChecker<T extends TSymbol> implements TypeChecker<T> {
	
	TypeOf<T>		typeOf;
	Equality<Type>	eqType;
	
	@Override
	public final boolean isCompatible(final Type fn, final Type a1) {
		return fn.accept(new Type.Test.False() {
			@Override
			public final Boolean typeFunction(final Type a2, final Type _) {
				return eqType.apply(a1, a2);
			}
		});
	}

	@Override
	public final boolean isCompatible(Expr<T> f, Expr<T> a) {
		return isCompatible(typeOf.typeOf(f),typeOf.typeOf(a));
	}

}
