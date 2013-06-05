package semante.stl.expr;

import static lombok.AccessLevel.PRIVATE;

import java.util.LinkedList;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;

import com.google.common.collect.Lists;

import semante.stl.Expr;
import semante.stl.Expr$DeBruijn;
import semante.stl.Expr.Visitor;
import semante.stl.Symbol;

@RequiredArgsConstructor
@FieldDefaults(makeFinal=true,level=PRIVATE)
public class ToDeBruijn implements Visitor<Expr$DeBruijn> {
	
	Expr$DeBruijn.Identity	builder;
	LinkedList<Symbol>		env	= Lists.newLinkedList();

	@Override
	public final Expr$DeBruijn abstraction(Symbol sym, Expr arg) {
		env.addFirst(sym);
		val arg$db = arg.accept(this);
		env.removeFirst();
		return builder.abstraction(arg$db);
	}

	@Override
	public final Expr$DeBruijn application(Expr fun, Expr arg) {
		val fun$db = fun.accept(this);
		val arg$db = arg.accept(this);
		return builder.application(fun$db, arg$db);
	}

	@Override
	public final Expr$DeBruijn variable(Symbol sym) {
		if (env.contains(sym)) {
			return builder.variable(env.indexOf(sym));
		}
		else {
			return builder.constant(sym);
		}
	}

}
