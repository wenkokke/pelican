package semante.lexicon;

import lambdacalc.STL;

public interface RichLexicon extends Lexicon {

	Word getEntry(String annotation, String text);

	STL getSTL();

}
