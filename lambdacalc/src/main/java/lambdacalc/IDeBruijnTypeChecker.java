package lambdacalc;

import static lombok.AccessLevel.PRIVATE;

import java.util.LinkedList;

import com.google.common.collect.Lists;

import lambdacalc.DeBruijn.Visitor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IDeBruijnTypeChecker implements DeBruijnTypeChecker {

	TypeBuilder builder;
	
	@Override
	public final Type checkType(final DeBruijn expr) throws TypeError {
		return expr.accept(new ITypeChecker());
	}
	
	@Override
	public final boolean canApply(final Type funType, final Type argType) {
		try {
			applyType(funType,argType);
			return true;
		}
		catch (TypeError e) {
			return false;
		}
	}
	
	@Override
	public final Type applyType(final Type funType, final Type argType) throws TypeError {
		return funType.accept(new TypeBuilder() {
			@Override
			public final Type constant(String _) {
				throw new TypeError(funType,argType);
			}
			@Override
			public final Type function(Type a, Type b) {
				if (argType.equals(a)) {
					return b;
				}
				else {
					throw new TypeError(a,argType);
				}
			}
		});
	}
	
	@Value
	private final class ITypeChecker implements Visitor<Type> {
		
		LinkedList<Type> env = Lists.newLinkedList();

		@Override
		public final Type abstraction(final Type argType, final DeBruijn body) {
			env.addFirst(argType);
			val bodyType = body.accept(this);
			env.removeFirst();
			return builder.function(argType, bodyType);
		}

		@Override
		public final Type application(final DeBruijn fun, final DeBruijn arg) {
			val argType = arg.accept(this);
			val funType = fun.accept(this);
			return funType.accept(new TypeBuilder() {
				@Override
				public final Type constant(String _) {
					throw new TypeError(funType,argType);
				}
				@Override
				public final Type function(Type a, Type b) {
					if (argType.equals(a)) {
						return b;
					}
					else {
						throw new TypeError(a,argType);
					}
				}
			});
		}

		@Override
		public final Type variable(final Index i) {
			return i.getType();
		}

		@Override
		public final Type constant(final Symbol s) {
			return s.getType();
		}

	}

}
