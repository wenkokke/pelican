package semante.lexicon.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import java.io.InputStream;

import lambdacalc.STL;
import lombok.Delegate;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lexicon.Lexicon;
import semante.lexicon.RichLexicon;
import semante.lexicon.Word;
import semante.settings.Settings;
import semante.settings.SettingsException;

@FieldDefaults(makeFinal=true, level=PRIVATE)
public class IRichLexicon implements RichLexicon {
	
	@Delegate
	Lexicon	lex;

	/**
	 * Constructor that uses a settings object to find the lexicon file.
	 */
	public IRichLexicon(final Settings settings, final STL stl) throws FileNotFoundException, SettingsException, IOException {
		this.lex = new ILexicon(settings.get("SemAnTE","Lexicon","Default"),stl);
	}

	/**
	 * Constructor that uses an opened stream to the lexicon file.
	 */
	public IRichLexicon(final InputStream is, final STL stl) throws IOException {
		this.lex = new ILexicon(is,stl);
	}

	/**
	 * Constructor that uses an string containing the lexicon file.
	 */
	public IRichLexicon(final String fn, final STL stl) throws IOException {
		this.lex = new ILexicon(fn,stl);
	}

	/**
	 * Constructor that uses an string containing the lexicon file.
	 */
	public IRichLexicon(final String[] lines, final STL stl) throws IOException {
		this.lex = new ILexicon(lines,stl);
	}
	
	@Override
	public Word getEntry(String key, String text) {
		
		// try words then categories.
		val cat1 = lex.getWord(key);
		if (cat1 != null) {
			return cat1;
		}
		else {
			val cat2 = lex.getCategory(key, text);
			if (cat2 != null) {
				return cat2;
			}
			else {
				throw new NoSuchElementException(key);
			}
		}
	}
}
