package semante.lexicon.impl;

import static com.google.common.collect.Lists.transform;
import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.DeBruijnRenamer;
import lambdacalc.Expr;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import semante.lexicon.Category;
import semante.lexicon.Word;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class ICategory implements Category {

	String			name;
	@Wither(AccessLevel.PRIVATE)
	List<DeBruijn>	denotations;
	DeBruijnRenamer	substituter;
	
	public final ICategory addDenotation(final DeBruijn denotation) {
		val builder = ImmutableList.<DeBruijn> builder();
		builder.addAll(getDenotations());
		builder.add(denotation);
		return withDenotations(builder.build());
	}
	
	public final ICategory addDenotations(final List<DeBruijn> denotations) {
		val builder = ImmutableList.<DeBruijn> builder();
		builder.addAll(getDenotations());
		builder.addAll(denotations);
		return withDenotations(builder.build());
	}
	
	/**
	 * Creates a {@link Word} with an unambiguous meaning.
	 * 
	 * @param name {@link String} representation of the {@link Word}.
	 * @param expr meaning of the {@link Word} as an {@link Expr}.
	 */
	public ICategory(final String name, final DeBruijn expr, final DeBruijnRenamer substituter) {
		this(name, (List<DeBruijn>) ImmutableList.of(expr), substituter);
	}
	
	@Override
	public final Word apply(final String text) {
		return new IWord(name, text, transform(denotations,
				new Function<DeBruijn, DeBruijn>() {
					@Override
					public final DeBruijn apply(final DeBruijn expr) {
						return substituter.rename(WordSymbol, text, expr);
					}
				}));
	}
}
