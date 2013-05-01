package semante.lexicon;

import java.util.List;

import semante.lambdacalc.Expr;
import semante.lambdacalc.TSymbol;

public interface Word<T extends TSymbol> {

	String 			getName();
	String          getText();
	List<Expr<T>> 	getExpr();
	Word<T>			addExpr(Expr<T> expr);

}
