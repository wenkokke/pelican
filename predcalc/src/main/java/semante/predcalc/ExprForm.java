package semante.predcalc;

import java.util.List;

/*
 * A pair of semantics and pragmatics
 * 
 */

public interface ExprForm<Kind> {

	public Kind getSemantics();
	public List<Kind> getPragmatics();
}
