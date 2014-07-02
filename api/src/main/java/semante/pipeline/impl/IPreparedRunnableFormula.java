package semante.pipeline.impl;

import static lombok.AccessLevel.PRIVATE;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.pipeline.PreparedRunnableFormula;
import semante.predcalc.ExprForm;
import semante.predcalc.FOLExpr.Formula;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class IPreparedRunnableFormula implements PreparedRunnableFormula {
	
	String tText, hText;
	ExprForm<Formula> tFormula, hFormula;

	@Override
	public String getTText() {
		return tText;
	}
	
	@Override
	public String getHText() {
		return hText;
	}
	
	@Override
	public ExprForm<Formula> getTFormula() {
		return tFormula;
	}
	
	@Override
	public ExprForm<Formula> getHFormula() {
		return hFormula;
	}	
}

