package semante.lambdacalc;


public interface EtaConverter<S extends Symbol> {
	Expr<S> etaConvert(Expr<S> expr);
}
