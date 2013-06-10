package semante.lexicon;

import java.util.List;

import lambdacalc.DeBruijn;

public interface Category {

	/**
	 * Represents the variable name used for the word, as seen below.
	 * <blockquote>
	 * 	Category NP \\A:et.A:et WORD:e
	 * </blockquote>
	 * 
	 * Where "WORD" represents the <em>e</em>-type variable that should be inserted.
	 */
	String	WordSymbol	= "WORD";

	/**
	 * @return the category's name.
	 */
	String getName();

	/**
	 * @return a list of all the category's possible representations.
	 */
	List<DeBruijn> getDenotations();

	/**
	 * Applies the Category to a word, substituting the word for all occurances
	 * of {@link OldWordName}.
	 * 
	 * @param word
	 *            the name to use for the word from now on.
	 */
	Word apply(String word);

}