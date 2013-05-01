package semante.lambdacalc;

public interface AlphaConverter<S extends Symbol> {
	Expr<S> alphaConvert(S s1, S s2, Expr<S> subject);
}
