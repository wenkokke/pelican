package semante.lexicon;

import java.util.List;
import java.util.Set;

public interface Lexicon {
	
	List<String> getEntries();
	
	Word getWord(String name);
	Word getCategory(String name, String wordName);
	
	List<Word> getWords();
	List<Category> getCategories();
	
	Set<String> getCompoundable();
}
