package lambdacalc;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class IExprBetaReducer implements ExprBetaReducer {

	Expr2DeBruijn		expr2DeBruijn;
	DeBruijnBetaReducer	deBruijnBetaReducer;
	DeBruijn2Expr		deBruijn2Expr;
	
	
	@Override
	public final Expr betaReduce(final Expr expr) {
		return deBruijn2Expr.fromDeBruijn(
			deBruijnBetaReducer.betaReduce(
				expr2DeBruijn.toDeBruijn(expr)));
	}

}
