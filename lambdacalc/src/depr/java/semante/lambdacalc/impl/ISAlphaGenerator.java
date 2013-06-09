package semante.lambdacalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.Map;

import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.AlphaGenerator;
import semante.lambdacalc.Symbol;

import com.google.common.collect.Maps;

/**
 * Note: Assumes no variable names are suffixed with indices, doesn't actually
 * guarantee freshness if some variables <em>are</em> indexed.
 * 
 * @author Pepijn Kokke
 */
@FieldDefaults(makeFinal=true, level=PRIVATE)
final class ISAlphaGenerator implements AlphaGenerator<Symbol> {

	Map<String,Integer> ix = Maps.newHashMap();

	@Override
	public final Symbol freshName(Symbol s) {
		// TODO remove tailing numbers
		val name = s.getName();
		final Integer i;
		if (! ix.containsKey(s)) {
			i=1;
		}
		else {
			i=ix.get(name)+1;
		}
		ix.put(name, i);
		return new IUSymbol(name+i);
	}
}
