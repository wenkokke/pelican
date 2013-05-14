package semante.predcalc.util;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TLambdaCalc;
import semante.lambdacalc.TSymbol;
import semante.predcalc.ExprForm;
import semante.predcalc.FOLExpr.Formula;
import semante.predcalc.Lambda2Pred;
import semante.predcalc.LowLambda2Pred;
import semante.predcalc.LowerLambda;
import semante.predcalc.PredCalc;

@FieldDefaults(makeFinal=true)
public class ILambda2Pred<T extends TSymbol> implements Lambda2Pred<T> {
	
	protected PredCalc pcalc;
	protected TLambdaCalc<T> lcalc;
	private LowerLambda<T> lower;
	LowLambda2Pred<T> l2p;
	
	public ILambda2Pred(PredCalc pcalc, TLambdaCalc<T> lcalc) {
		this.pcalc = pcalc;
		this.lcalc = lcalc;
		lower = new ILowerLambda<T>(lcalc);
		l2p = new ILowLambda2Pred<T>(pcalc, lcalc);
	}
	
	@Override
	public ExprForm<Formula> smash(Expr<T> expr) {
		ExprForm<Expr<T>> low = lower.rewrite(expr);
		List<Formula> prags = new ArrayList<Formula>();
		for (Expr<T> p : low.getPragmatics()) {
			System.out.println(p);
			prags.add((Formula) l2p.convert(p));
		}
		
		return new IExprForm<Formula>((Formula) l2p.convert(low.getSemantics()), prags);
	}

}
