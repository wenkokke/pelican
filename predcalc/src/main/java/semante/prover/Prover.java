package semante.prover;

import semante.predcalc.ExprForm;
import semante.predcalc.FOLExpr.Formula;

public interface Prover {
	boolean prove(ExprForm<Formula> text, ExprForm<Formula> hypothesis) throws ProverException;
	boolean prove(ExprForm<Formula> text, ExprForm<Formula> hypothesis, String subsumptionRules) throws ProverException;
}
