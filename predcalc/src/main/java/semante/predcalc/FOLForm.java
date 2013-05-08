package semante.predcalc;

import java.util.List;

import semante.predcalc.FOLExpr.Formula;

/*
 * A pair of semantics and pragmatics
 * 
 */

public interface FOLForm {

	public Formula getSemantics();
	public List<Formula> getPragmatics();
}
