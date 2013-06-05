package semante.stl.expr;

import semante.stl.Expr$DeBruijn;
import semante.stl.Symbol;
import semante.stl.Expr$DeBruijn.Visitor;

import com.google.common.base.Optional;

public class None<X> implements Visitor<Optional<X>> {
	@Override public Optional<X> abstraction(Expr$DeBruijn body)          { return Optional.absent(); }
	@Override public Optional<X> application(Expr$DeBruijn fun, Expr$DeBruijn arg) { return Optional.absent(); }
	@Override public Optional<X> variable(Integer s)             { return Optional.absent(); }
	@Override public Optional<X> constant(Symbol s)              { return Optional.absent(); }	
}