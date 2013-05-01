package semante.lexicon;

import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;

public interface RichLexicon extends Lexicon<TSymbol> {

	Word<TSymbol> getEntry(String annotation, String text);
	TLambdaCalc<TSymbol> getSTL();
	
}
