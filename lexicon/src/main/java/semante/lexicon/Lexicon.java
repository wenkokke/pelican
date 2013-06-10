package semante.lexicon;

import java.util.List;

public interface Lexicon {
	
	List<String> getEntries();
	
	Word getWord(String name);
	Word getCategory(String name, String wordName);
	
	List<Word> getWords();
	List<Category> getCategories();
}
