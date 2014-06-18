package semante.predcalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;

import lambdacalc.Expr;
import lambdacalc.STL;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import semante.predcalc.Expr2FirstOrderExpr;
import semante.predcalc.ExprForm;
import semante.predcalc.FOLExpr;
import semante.predcalc.IotaExtractor;
import semante.predcalc.LowLambda2Pred;
import semante.predcalc.PredCalc;
import semante.predcalc.Smasher;
import semante.predcalc.FOLExpr.Formula;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class ISmasher implements Smasher {
	
	protected PredCalc pcalc;
	protected STL lcalc;
	private Expr2FirstOrderExpr lower;
	private IotaExtractor ext;
	private LowLambda2Pred l2p;
	
	public ISmasher(PredCalc pcalc, STL lcalc) {
		this.pcalc = pcalc;
		this.lcalc = lcalc;
		lower = new IExpr2FirstOrderExpr(lcalc);
		ext = new IOldIotaExtractor(lcalc);
		//ext = new INewIotaExtractor(lcalc);
		l2p = new IFirstOrderExpr2Pred(pcalc, lcalc);
	}
	
	@Override
	public ExprForm<Formula> smash(Expr expr) {
		// Extract the iotas
		ExprForm<Expr> form = ext.extract(expr);
		
		System.err.println("IotaExtraction: " + lcalc.format(form.getSemantics()));
		
		// Convert all expressions in the sentence-form to predicate logic
		List<Formula> prags = new ArrayList<Formula>();
		for (Expr p : form.getPragmatics()) {
			prags.add((Formula) l2p.convert(lower.rewrite(p)));
		}
		// apparently, this is not safe:
		// ClassCastException: IFOLExpr$Variable cannot be case to FOLExpr$Formula
		Expr rewritten = lower.rewrite(form.getSemantics());
		
		System.out.println("Rewritten: [" + lcalc.format(rewritten) + "]");
		
		FOLExpr converted = l2p.convert(rewritten);
		
		System.out.println(converted.getClass().getSimpleName());
		
		
		
		Formula sem = (Formula) converted;
		return new IExprForm<Formula>(sem, prags);
	}

}
