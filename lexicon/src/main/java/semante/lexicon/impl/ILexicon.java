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

import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lexicon.Category;
import semante.lexicon.Lexicon;
import semante.lexicon.Word;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

@FieldDefaults(makeFinal=true,level=PRIVATE)
public final class ILexicon implements Lexicon {

	STL						stl;
	Map<String, IWord>		words;
	Map<String, ICategory>	categories;

	@Override
	public final List<Word> getWords() {
		return ImmutableList.<Word> copyOf(words.values());
	}

	@Override
	public final List<Category> getCategories() {
		return ImmutableList.<Category> copyOf(categories.values());
	}

	@Override
	public final Word getWord(String name) {
		return words.get(name);
	}

	@Override
	public final Word getCategory(String name, String wordName) {
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
	
	private static final int ALIAS_REFERENCE 	= 1; 
	private static final int ALIAS_NAME  		= 2;
	
	private final boolean isCategory(final String line) {
		return line.contains("WORD:");
	}

	private final void parse(final String line) {
		if (isEntry(line)) {
			// try to parse the line.
			val tokens   = line.split("\\s+");
			val tag      = tokens[TAG];
			val raw      = on(' ').join(copyOfRange(tokens, TERM, tokens.length));
			val expr     = stl.parse(raw);
			val deBruijn = stl.toDeBruijn(expr);
			stl.checkType(deBruijn);
			
			if (isCategory(line)) {

				// check if category exists.
				if (categories.containsKey(tag)) {
					categories.put(tag, categories.get(tag).addDenotation(deBruijn));
				}
				else {
					categories.put(tag, new ICategory(tag,deBruijn,stl));
				}
			}
			else {

				// check if word exists.
				if (words.containsKey(tag)) {
					words.put(tag, words.get(tag).addDenotation(deBruijn));
				}
				else {
					words.put(tag, new IWord(tag,tag,deBruijn));
				}
			}
		} else if (isAlias(line)) {
			val tokens    = line.split("\\s+");
			val alias     = tokens[ALIAS_NAME];
			val reference = tokens[ALIAS_REFERENCE];
			
			// check if category exists.
			if (categories.containsKey(reference)) {
				for (DeBruijn deBruijn : categories.get(reference).getDenotations()) {
					if (categories.containsKey(alias)) {
						categories.put(alias, categories.get(alias).addDenotation(deBruijn));
					} else {
						categories.put(alias, new ICategory(alias,deBruijn,stl));
					}
				}
			} else if (words.containsKey(reference)) {
				for (DeBruijn deBruijn : words.get(reference).getDenotations()) {
					if (words.containsKey(alias)) {
						words.put(alias, words.get(alias).addDenotation(deBruijn));
					} else {
						words.put(alias, new IWord(alias,alias,deBruijn));
					}
				}

			} 
		}
	}

	private final boolean isAlias(final String line) {
		return line.startsWith("@ ");
	}
	
	private final boolean isEntry(final String line) {
		return line.startsWith("> ");
	}

	/**
	 * Constructor that uses the lexicon file contained within the JAR.
	 */
	public ILexicon(final STL stl) throws Exception {
		this(Thread.currentThread().getContextClassLoader().getResourceAsStream("default.lex"), stl);
	}


	/**
	 * Constructor that takes a path to the lexicon file.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public ILexicon(final String fn, final STL stl) throws FileNotFoundException, IOException {
		this(new FileInputStream(fn), stl);
	}

	/**
	 * Most general constructor accepting an InputStream.
	 * @throws IOException 
	 */
	@SneakyThrows(UnsupportedEncodingException.class)
	public ILexicon(final InputStream is, final STL stl) throws IOException {

		// assign the parsers.
		this.stl        = stl;
		this.words      = Maps.newHashMap();
		this.categories = Maps.newHashMap();

		// open the lexion file.
		@Cleanup
		val rd =
			new BufferedReader(
				new InputStreamReader(is, "UTF-8"));

		// process the lexicon file.
		String line;
		while ((line = rd.readLine()) != null) {
			try {
				parse(line);
			}
			catch (Exception e) {
				
			}
		}
	}
	
	/**
	 * Most general constructor accepting a list of lines.
	 * @throws IOException 
	 */
	public ILexicon(String[] lines, STL stl) {

		// assign the parsers.
		this.stl        = stl;
		this.words      = Maps.newHashMap();
		this.categories = Maps.newHashMap();
		
		for (final String line : lines) {
			try {
				parse(line);
			}
			catch (Exception e) {
			}
		}
	}

	@Override
	public final List<String> getEntries() {
		val builder = ImmutableList.<String> builder();
		builder.addAll(words.keySet());
		builder.addAll(categories.keySet());
		return builder.build();
	}
	
	@Override
	public final String toString() {
		val acc = new StringBuilder();
		acc.append("Lexicon:\n");
		for (val word : words.values()) {
			acc.append(" - ");
			acc.append(word.getName());
			acc.append("[");
			acc.append(word.getDenotations().size());
			acc.append("]\n");
		}
		for (val category : categories.values()) {
			acc.append(" - ");
			acc.append(category.getName());
			acc.append("[");
			acc.append(category.getDenotations().size());
			acc.append("]\n");
		}
		return acc.toString();
	}
}
