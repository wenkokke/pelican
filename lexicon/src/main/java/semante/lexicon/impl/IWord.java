package semante.lexicon.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lambdacalc.DeBruijn;
import lambdacalc.Expr;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import semante.lexicon.Word;

import com.google.common.collect.ImmutableList;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IWord implements Word {
	
	String			name;
	String			text;
	
	@Wither(AccessLevel.PRIVATE)
	List<DeBruijn>	denotations;
	
	public final IWord addDenotation(final DeBruijn denotation) {
		val builder = ImmutableList.<DeBruijn> builder();
		builder.addAll(getDenotations());
		builder.add(denotation);
		return withDenotations(builder.build());
	}
	
	public final IWord addDenotations(final List<DeBruijn> denotations) {
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
	public IWord(final String name, final String text, final DeBruijn expr) {
		this(name, text, (List<DeBruijn>) ImmutableList.of(expr));
	}
	
}
