package semante.stl;


public interface Expr$DeBruijn {
	<X> X accept(Visitor<X> v);
	
	public interface Visitor<X> {
		X abstraction	(Expr$DeBruijn body);			// expression body
		X application	(Expr$DeBruijn fun, Expr$DeBruijn arg);	// function and arguments
		X variable		(Integer s);			// a debruijn index 
		X constant      (Symbol s);				// a typed symbol
	}
	
	public interface Identity extends Visitor<Expr$DeBruijn> {
	}
}
