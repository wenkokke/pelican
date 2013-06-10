package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IDeBruijnEtaReducer extends IDeBruijnBuilder implements DeBruijnBuilder, DeBruijnEtaReducer {
	
	@Override
	public final DeBruijn etaReduce(DeBruijn expr) {
		return expr.accept(this);
	}

	@Override
	public final DeBruijn abstraction(final Type type, final DeBruijn body) {
		return body.accept(new IDeBruijnBuilder() {
			@Override
			public final DeBruijn application(final DeBruijn fun, final DeBruijn arg) {
				return arg.accept(new IDeBruijnBuilder() {
					@Override
					public final DeBruijn variable(final Index i) {
						if (i.equals(0)) {
							return etaReduce(fun);
						}
						else {
							return super.abstraction(type, etaReduce(body));
						}
					}
				});
			}
		});
	}

	@Override
	public final DeBruijn application(DeBruijn fun, DeBruijn arg) {
		return super.application(etaReduce(fun), etaReduce(arg));
	}
}
