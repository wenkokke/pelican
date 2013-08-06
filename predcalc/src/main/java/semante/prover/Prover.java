package semante.prover;

import predcalc.ExprForm;
import predcalc.FOLExpr.Formula;

public interface Prover {
	ProverResult prove(ExprForm<Formula> text, ExprForm<Formula> hypothesis);
	ProverResult prove(ExprForm<Formula> text, ExprForm<Formula> hypothesis, String subsumptionRules);
}
