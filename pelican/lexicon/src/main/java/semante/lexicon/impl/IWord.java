package semante.lexicon.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.Value;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TSymbol;
import semante.lexicon.Word;

import com.google.common.collect.ImmutableList;

@Value
@RequiredArgsConstructor
public final class IWord implements Word<TSymbol> {
	
	String				name;
	List<Expr<TSymbol>>	expr;
	
	public final IWord addExpr(final Expr<TSymbol> newExpr) {
		val builder = ImmutableList.<Expr<TSymbol>>builder();
		builder.addAll(getExpr());
		builder.add(newExpr);
		return withExpr(builder.build());
	}
	
	public final IWord addAllExpr(final List<Expr<TSymbol>> newExpr) {
		val builder = ImmutableList.<Expr<TSymbol>>builder();
		builder.addAll(getExpr());
		builder.addAll(newExpr);
		return withExpr(builder.build());
	}
	
	/**
	 * Creates a {@link Word} with an unambiguous meaning.
	 * 
	 * @param name {@link String} representation of the {@link Word}.
	 * @param expr meaning of the {@link Word} as an {@link Expr}.
	 */
	public IWord(final String name, final Expr<TSymbol> expr) {
		this(name, (List<Expr<TSymbol>>) ImmutableList.of(expr));
	}
	
}
