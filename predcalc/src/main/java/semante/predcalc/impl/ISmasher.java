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
import semante.predcalc.FirstOrderExpr2Pred;
import semante.predcalc.PredCalc;
import semante.predcalc.Smasher;
import semante.predcalc.FOLExpr.Formula;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class ISmasher implements Smasher {
	
	STL lcalc;
	IotaExtractor extractor;
	Expr2FirstOrderExpr expr2foe;
	FirstOrderExpr2Pred foe2pred;
	
	public ISmasher(PredCalc pcalc, STL lcalc) {
		this.lcalc = lcalc;
		extractor  = new IOldIotaExtractor(lcalc);
		expr2foe   = new IExpr2FirstOrderExpr(lcalc);
		foe2pred   = new IFirstOrderExpr2Pred(pcalc, lcalc);
	}
	
	@Override
	public ExprForm<Formula> smash(Expr expr) {
		// Extract the iotas
		ExprForm<Expr> form = extractor.extract(expr);
		
		System.err.println("IotaExtraction: " + lcalc.format(form.getSemantics()));
		
		// Convert all expressions in the sentence-form to predicate logic
		List<Formula> prags = new ArrayList<Formula>();
		for (Expr p : form.getPragmatics()) {
			prags.add((Formula) foe2pred.convert(expr2foe.rewrite(p)));
		}
		// apparently, this is not safe:
		// ClassCastException: IFOLExpr$Variable cannot be case to FOLExpr$Formula
		Expr rewritten = expr2foe.rewrite(form.getSemantics());
		
		System.out.println("Rewritten: [" + lcalc.format(rewritten) + "]");
		
		FOLExpr converted = foe2pred.convert(rewritten);
		
		System.out.println(converted.getClass().getSimpleName());
		
		
		
		Formula sem = (Formula) converted;
		return new IExprForm<Formula>(sem, prags);
	}

}
