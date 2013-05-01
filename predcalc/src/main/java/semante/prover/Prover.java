package semante.prover;

import semante.predcalc.FOLExpr.Formula;

public interface Prover {
	boolean prove(Formula text, Formula hypothesis) throws ProverException;
	boolean prove(Formula text, Formula hypothesis, String subsumptionRules) throws ProverException;
}
