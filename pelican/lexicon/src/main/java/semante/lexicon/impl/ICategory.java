package semante.lexicon.impl;

import static com.google.common.collect.Lists.transform;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.Value;
import semante.lambdacalc.AlphaConverter2;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TSymbol;
import semante.lexicon.Category;
import semante.lexicon.Word;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

@Value
@RequiredArgsConstructor
public final class ICategory implements Category<TSymbol> {

	String						name;
	List<Expr<TSymbol>>			expr;
	AlphaConverter2<TSymbol>	alphaConv2;
	
	public final ICategory addExpr(final Expr<TSymbol> newExpr) {
		val builder = ImmutableList.<Expr<TSymbol>>builder();
		builder.addAll(getExpr());
		builder.add(newExpr);
		return withExpr(builder.build());
	}
	
	public final ICategory addAllExpr(final List<Expr<TSymbol>> newExpr) {
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
	public ICategory(final String name, final Expr<TSymbol> expr, final AlphaConverter2<TSymbol> withWord) {
		this(name, (List<Expr<TSymbol>>) ImmutableList.of(expr), withWord);
	}
	
	@Override
	public final Word<TSymbol> apply(final String newName) {
		return new IWord(name,
			transform(expr,
				new Function<Expr<TSymbol>,Expr<TSymbol>>() {
					@Override
					public final Expr<TSymbol> apply(final Expr<TSymbol> input) {
						return alphaConv2.alphaConvert(OldWordName, newName, input);
					}
				}));
	}
}
