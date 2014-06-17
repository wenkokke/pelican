package semante.prover;

import semante.predcalc.ExprForm;
import semante.predcalc.FOLExpr.Formula;

public interface Prover {
	ProverResult prove(ExprForm<Formula> text, ExprForm<Formula> hypothesis);
	ProverResult prove(ExprForm<Formula> text, ExprForm<Formula> hypothesis, String subsumptionRules);
	
	String toProverInput(ExprForm<Formula> txt, ExprForm<Formula> hyp, String subs);
}
