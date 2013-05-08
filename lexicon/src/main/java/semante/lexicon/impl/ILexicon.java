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

	ExprParser<TSymbol>			pExpr;
	AlphaConverter2<TSymbol>	alphaConv2;
	Map<String, IWord>			wMap;
	Map<String, ICategory>		cMap;

	@Override
	public final List<Word<TSymbol>> getWords() {
		return ImmutableList.<Word<TSymbol>> copyOf(wMap.values());
	}

	@Override
	public final List<Category<TSymbol>> getCategories() {
		return ImmutableList.<Category<TSymbol>> copyOf(cMap.values());
	}

	@Override
	public final Word<TSymbol> getWord(String name) {
		return wMap.get(name);
	}

	@Override
	public final Word<TSymbol> getCategory(String name, String wordName) {
		val cat = cMap.get(name);
		if (cat != null) {
			return cat.apply(wordName);
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
			val tag  = tokens[TAG];
			val term = on(' ').join(copyOfRange(tokens, TERM, tokens.length));
			val expr = pExpr.parse(term);
			if (isCategory(line)) {

				// check if category exists.
				if (cMap.containsKey(tag)) {
					cMap.put(tag, cMap.get(tag).addExpr(expr));
				}
				else {
					cMap.put(tag, new ICategory(tag,expr,alphaConv2));
				}
			}
			else {

				// check if word exists.
				if (wMap.containsKey(tag)) {
					wMap.put(tag, wMap.get(tag).addExpr(expr));
				}
				else {
					wMap.put(tag, new IWord(tag,tag,expr));
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
	public ILexicon(final ExprParser<TSymbol> pExpr, final AlphaConverter2<TSymbol> alphaConv2) throws Exception {
		this(Thread.currentThread().getContextClassLoader().getResourceAsStream("default.lex"), pExpr, alphaConv2);
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
	public ILexicon(final InputStream is, final ExprParser<TSymbol> pExpr, final AlphaConverter2<TSymbol> alphaConv2) throws IOException {

		// assign the parsers.
		this.pExpr 		= pExpr;
		this.alphaConv2 = alphaConv2;
		this.wMap  		= Maps.newHashMap();
		this.cMap  		= Maps.newHashMap();

		// open the lexion file.
		@Cleanup
		val rd = new BufferedReader(
					new InputStreamReader(is, "UTF-8"));

		// process the lexicon file.
		String line;
		while ((line = rd.readLine()) != null)
			parse(line);
	}

	@Override
	public final List<String> getEntries() {
		val builder = ImmutableList.<String> builder();
		builder.addAll(wMap.keySet());
		builder.addAll(cMap.keySet());
		return builder.build();
	}
}
