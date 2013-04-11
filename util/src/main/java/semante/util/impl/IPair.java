package semante.util.impl;

import lombok.experimental.Value;
import semante.util.Pair;

@Value
public final class IPair<F, S> implements Pair<F, S> {
	
	public static final <F,S> Pair<F,S> pair(F fst, S snd) {
		return new IPair<F,S>(fst, snd);
	}
	
	F first;
    S second;
}