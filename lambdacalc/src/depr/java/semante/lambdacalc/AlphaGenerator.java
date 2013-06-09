package semante.lambdacalc;

public interface AlphaGenerator<S extends Symbol> {
	S freshName(S name);
}
