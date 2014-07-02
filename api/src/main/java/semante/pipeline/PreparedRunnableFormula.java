package semante.pipeline;

import semante.predcalc.ExprForm;
import semante.predcalc.FOLExpr.Formula;

public interface PreparedRunnableFormula {

	public String getTText();
	public String getHText();
	public ExprForm<Formula> getTFormula();
	public ExprForm<Formula> getHFormula();
	
}
