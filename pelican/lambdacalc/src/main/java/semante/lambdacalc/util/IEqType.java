package semante.lambdacalc.util;

import lombok.experimental.Value;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Type;

public final class IEqType implements Equality<Type> {

	@Override
	public final boolean apply(Type t1, Type t2) {
		return t1.accept(new ITypeEqualityVisitor(t2));
	}
	
	@Value
	private final class ITypeEqualityVisitor implements Type.Test {
		
		Type that;
		
		@Override
		public final Boolean typeConstant(final String n1) {
			return that.accept(new Type.Test.False() {
				@Override
				public final Boolean typeConstant(final String n2) {
					return n1.equals(n2);
				}
			});
		}

		@Override
		public final Boolean typeFunction(final Type l1, final Type r1) {
			return that.accept(new Type.Test.False() {
				@Override
				public final Boolean typeFunction(final Type l2, final Type r2) {
					return l1.accept(withThat(l2)) && r1.accept(withThat(r2));
				}
			});
		}
	}

}
