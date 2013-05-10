package semante.lexicon.impl;

import static com.google.common.base.Joiner.on;
import static java.util.Arrays.copyOfRange;
import static lombok.AccessLevel.PRIVATE;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.AlphaConverter2;
import semante.lambdacalc.ExprParser;
import semante.lambdacalc.TSymbol;
import semante.lexicon.Category;
import semante.lexicon.Lexicon;
import semante.lexicon.Word;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class ILexicon implements Lexicon<TSymbol> {

	ExprParser<TSymbol>			parser;
	AlphaConverter2<TSymbol>	alphaconv;
	Map<String, IWord>			words;
	Map<String, ICategory>		categories;

	@Override
	public final List<Word<TSymbol>> getWords() {
		return ImmutableList.<Word<TSymbol>> copyOf(words.values());
	}

	@Override
	public final List<Category<TSymbol>> getCategories() {
		return ImmutableList.<Category<TSymbol>> copyOf(categories.values());
	}

	@Override
	public final Word<TSymbol> getWord(String name) {
		return words.get(name);
	}

	@Override
	public final Word<TSymbol> getCategory(String name, String wordName) {
		val category = categories.get(name);
		if (category != null) {
			return category.apply(wordName.replace(' ','_'));
		}
		else {
			return null;
		}
	}
	
	private static final int TAG  = 1;
	private static final int TERM = 2; 
	
	private final boolean isCategory(final String line) {
		return line.contains("WORD:");
	}

	private final void parse(final String line) {
		if (isEntry(line)) {
			// try to parse the line.
			val tokens = line.split("\\s+");
			val tag    = tokens[TAG];
			val term   = on(' ').join(copyOfRange(tokens, TERM, tokens.length));
			val expr   = parser.parse(term);
			
			if (isCategory(line)) {

				// check if category exists.
				if (categories.containsKey(tag)) {
					categories.put(tag, categories.get(tag).addExpr(expr));
				}
				else {
					categories.put(tag, new ICategory(tag,expr,alphaconv));
				}
			}
			else {

				// check if word exists.
				if (words.containsKey(tag)) {
					words.put(tag, words.get(tag).addExpr(expr));
				}
				else {
					words.put(tag, new IWord(tag,tag,expr));
				}
			}
		}
	}

	private final boolean isEntry(final String line) {
		return line.startsWith("> ");
	}

	/**
	 * Constructor that uses the lexicon file contained within the JAR.
	 */
	public ILexicon(final ExprParser<TSymbol> parser, final AlphaConverter2<TSymbol> alphaConv2) throws Exception {
		this(Thread.currentThread().getContextClassLoader().getResourceAsStream("default.lex"), parser, alphaConv2);
	}


	/**
	 * Constructor that takes a path to the lexicon file.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public ILexicon(final String fn, final ExprParser<TSymbol> pExpr, final AlphaConverter2<TSymbol> alphaConv2) throws FileNotFoundException, IOException {
		this(new FileInputStream(fn), pExpr, alphaConv2);
	}

	/**
	 * Most general constructor accepting an InputStream.
	 * @throws IOException 
	 */
	@SneakyThrows(UnsupportedEncodingException.class)
	public ILexicon(final InputStream is, final ExprParser<TSymbol> parser, final AlphaConverter2<TSymbol> alphaconv) throws IOException {

		// assign the parsers.
		this.parser     = parser;
		this.alphaconv  = alphaconv;
		this.words      = Maps.newHashMap();
		this.categories = Maps.newHashMap();

		// open the lexion file.
		@Cleanup
		val rd =
			new BufferedReader(
				new InputStreamReader(is, "UTF-8"));

		// process the lexicon file.
		String line;
		int linenumber = 0;
		while ((line = rd.readLine()) != null) {
			try {
				parse(line);
			}
			catch (Exception e) {
				
			}
			linenumber += 1;
		}
	}

	@Override
	public final List<String> getEntries() {
		val builder = ImmutableList.<String> builder();
		builder.addAll(words.keySet());
		builder.addAll(categories.keySet());
		return builder.build();
	}
}
