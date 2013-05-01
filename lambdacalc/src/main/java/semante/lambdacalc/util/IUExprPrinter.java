package semante.lambdacalc.util;

import lombok.Delegate;
import semante.lambdacalc.ExprPrinter;
import semante.lambdacalc.USymbol;

public final class IUExprPrinter<U extends USymbol> implements ExprPrinter<U> {
	
	// simply delegates to the generic lambda expression printer.
	@Delegate ExprPrinter<U> exprPrinter = new ISExprPrinter<U>();
	
}
