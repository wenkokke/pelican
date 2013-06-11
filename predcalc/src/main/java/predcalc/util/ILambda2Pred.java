package predcalc.util;

import java.util.ArrayList;
import java.util.List;

import predcalc.ExprForm;
import predcalc.Lambda2Pred;
import predcalc.LowLambda2Pred;
import predcalc.LowerLambda;
import predcalc.PredCalc;
import predcalc.FOLExpr.Formula;

import lombok.experimental.FieldDefaults;
import lambdacalc.Expr;
import lambdacalc.STL;
import lambdacalc.Symbol;

@FieldDefaults(makeFinal=true)
public class ILambda2Pred implements Lambda2Pred {
	
	protected PredCalc pcalc;
	protected STL lcalc;
	private LowerLambda lower;
	LowLambda2Pred l2p;
	
	public ILambda2Pred(PredCalc pcalc, STL lcalc) {
		this.pcalc = pcalc;
		this.lcalc = lcalc;
		lower = new ILowerLambda(lcalc);
		l2p = new ILowLambda2Pred(pcalc, lcalc);
	}
	
	@Override
	public ExprForm<Formula> smash(Expr expr) {
		ExprForm<Expr> low = lower.rewrite(expr);
		List<Formula> prags = new ArrayList<Formula>();
		for (Expr p : low.getPragmatics()) {
			System.out.println(p);
			prags.add((Formula) l2p.convert(p));
		}
		
		return new IExprForm<Formula>((Formula) l2p.convert(low.getSemantics()), prags);
	}

}
