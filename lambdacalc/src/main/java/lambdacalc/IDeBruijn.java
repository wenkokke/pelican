package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;

@FieldDefaults(makeFinal=true,level=PRIVATE)
public abstract class IDeBruijn implements DeBruijn {

	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IAbstraction extends IDeBruijn {
		Type type; DeBruijn body;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.abstraction(type, body);
		}
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IApplication extends IDeBruijn {
		DeBruijn fun,arg;
		
		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.application(fun, arg);
		}
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IVariable extends IDeBruijn {
		Index i;

		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.variable(i);
		}
	}
	
	@Getter @Wither
	@RequiredArgsConstructor
	@EqualsAndHashCode(callSuper=false)
	@FieldDefaults(makeFinal=true,level=PRIVATE)
	public static final class IConstant extends IDeBruijn {
		Symbol s;

		@Override
		public final <X> X accept(Visitor<X> v) {
			return v.constant(s);
		}
	}
}
