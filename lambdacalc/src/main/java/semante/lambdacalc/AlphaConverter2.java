package semante.lambdacalc;

public interface AlphaConverter2<S extends Symbol> {
	Expr<S> alphaConvert(String s1, String s2, Expr<S> subject);
}
