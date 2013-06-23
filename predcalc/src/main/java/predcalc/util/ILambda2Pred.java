package predcalc.util;

import java.util.ArrayList;
import java.util.List;

import lambdacalc.Expr;
import lambdacalc.STL;
import lombok.experimental.FieldDefaults;
import predcalc.ExprForm;
import predcalc.ExtractIotas;
import predcalc.FOLExpr.Formula;
import predcalc.Lambda2Pred;
import predcalc.LowLambda2Pred;
import predcalc.LowerLambda;
import predcalc.PredCalc;

@FieldDefaults(makeFinal=true)
public class ILambda2Pred implements Lambda2Pred {
	
	protected PredCalc pcalc;
	protected STL lcalc;
	private LowerLambda lower;
	private ExtractIotas ext;
	private LowLambda2Pred l2p;
	
	public ILambda2Pred(PredCalc pcalc, STL lcalc) {
		this.pcalc = pcalc;
		this.lcalc = lcalc;
		lower = new ILowerLambda(lcalc);
		ext = new IExtractIotas(lcalc);
		l2p = new ILowLambda2Pred(pcalc, lcalc);
	}
	
	@Override
	public ExprForm<Formula> smash(Expr expr) {
		// Extract the iotas
		ExprForm<Expr> form = ext.extract(expr);
		
		// Convert all expressions in the sentence-form to predicate logic
		List<Formula> prags = new ArrayList<Formula>();
		for (Expr p : form.getPragmatics()) {
			prags.add((Formula) l2p.convert(lower.rewrite(p)));
		}
		Formula sem = (Formula) l2p.convert(lower.rewrite(form.getSemantics()));
		
		return new IExprForm<Formula>(sem, prags);
	}

}
