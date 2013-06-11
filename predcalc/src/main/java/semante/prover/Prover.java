package semante.prover;

import predcalc.ExprForm;
import predcalc.FOLExpr.Formula;

public interface Prover {
	boolean prove(ExprForm<Formula> text, ExprForm<Formula> hypothesis) throws ProverException;
	boolean prove(ExprForm<Formula> text, ExprForm<Formula> hypothesis, String subsumptionRules) throws ProverException;
}
