package semante.lexicon.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;
import java.io.IOException;

import lombok.Delegate;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;
import semante.lexicon.Lexicon;
import semante.lexicon.RichLexicon;
import semante.lexicon.Word;
import semante.settings.Settings;
import semante.settings.SettingsException;

@FieldDefaults(makeFinal=true, level=PRIVATE)
public class IRichLexicon implements RichLexicon {
	
	@Delegate
	Lexicon<TSymbol>		lex;
	TLambdaCalc<TSymbol>	stl;

	public IRichLexicon(final Settings settings, final TLambdaCalc<TSymbol> stl) throws FileNotFoundException, SettingsException, IOException {
		this.stl = stl;
		this.lex = new ILexicon(settings.get("SemAnTE","Lexicon","Default"),stl,stl);
	}

	@Override
	public Word<TSymbol> getEntry(String key, String text) {
		
		// try words then categories.
		val word = lex.getWord(key);
		if (word != null) {
			return word;
		}
		else {
			return lex.getCategory(key, text);
		}
	}

	@Override
	public TLambdaCalc<TSymbol> getSTL() {
		return this.stl;
	}


}
