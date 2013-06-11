package semante.lexicon;


public interface RichLexicon extends Lexicon {

	Word getEntry(String annotation, String text);

}
