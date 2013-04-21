package semante.lexicon;

import java.util.List;

import semante.lambdacalc.TSymbol;

public interface Lexicon<T extends TSymbol> {
	
	List<String> getEntries();
	
	Word<T> getWord(String name);
	Word<T> getCategory(String name, String wordName);
	
	List<Word<TSymbol>> getWords();
	List<Category<TSymbol>> getCategories();
}
