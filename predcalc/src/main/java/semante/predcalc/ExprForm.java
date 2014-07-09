package semante.predcalc;

import java.util.List;
import java.util.Set;

/*
 * A pair of semantics and pragmatics
 * 
 */

public interface ExprForm<Kind> {

	public Kind getSemantics();
	
	// Pragmatics
	public Set<Kind> getUniquenessConditions();
	public Set<Kind> getImplications();
	
}
