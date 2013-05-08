package semante.prover;

import semante.predcalc.FOLForm;

public interface Prover {
	boolean prove(FOLForm text, FOLForm hypothesis) throws ProverException;
	boolean prove(FOLForm text, FOLForm hypothesis, String subsumptionRules) throws ProverException;
}
