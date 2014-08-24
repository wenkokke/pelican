package semante.predcalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.Set;

import lambdacalc.Expr;
import lambdacalc.STL;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.predcalc.Expr2FirstOrderExpr;
import semante.predcalc.ExprForm;
import semante.predcalc.FOLExpr;
import semante.predcalc.FOLExpr.Formula;
import semante.predcalc.FirstOrderExpr2Pred;
import semante.predcalc.PredCalc;
import semante.predcalc.Smasher;
import semante.settings.Settings;

import com.google.common.collect.ImmutableSet;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public final class ISmasher implements Smasher {
	
	STL lcalc;
	Expr2FirstOrderExpr expr2foe;
	FirstOrderExpr2Pred foe2pred;
	
	boolean debugMode;
	
	public ISmasher(Settings settings, PredCalc pcalc, STL lcalc) {
		this.lcalc = lcalc;
		expr2foe   = new IExpr2FirstOrderExpr(settings, lcalc);
		foe2pred   = new IFirstOrderExpr2Pred(pcalc, lcalc);
		this.debugMode = Boolean.parseBoolean(settings.get("SemAnTE","Tracer","Smasher"));
	}
	
	@Override
	public ExprForm<Formula> smash(Expr expr, Set<Expr> uniquenessConditions,Set<Expr> implications) {
		
		// Convert all expressions in the sentence-form to predicate logic
		
		// expressions about uniqueness
		val uniquenessB = ImmutableSet.<Formula>builder();
		if (uniquenessConditions!=null) {
			for (Expr p : uniquenessConditions) {
				if (debugMode) {
					System.err.println("Smashing uniqueness: ["+ lcalc.format(p) + "]");
				}
				uniquenessB.add((Formula) foe2pred.convert(expr2foe.rewrite(p)));
			}
		}
		val smashedUniqueness = uniquenessB.build();

		// expressions about implications
		val implicationsB = ImmutableSet.<Formula>builder();
		if (implications!=null) {
			for (Expr p : implications) {
				if (debugMode) {
					System.err.println("Smashing implication: ["+ lcalc.format(p) + "]");
				}
				implicationsB.add((Formula) foe2pred.convert(expr2foe.rewrite(p)));
			}
		}
		val smashedImplications = implicationsB.build();
		
		// apparently, this is not safe:
		// ClassCastException: IFOLExpr$Variable cannot be case to FOLExpr$Formula
		Expr rewritten = expr2foe.rewrite(expr);
		if (debugMode) {		
			System.err.println("Lowered Form: [" + lcalc.format(rewritten) + "]");
		}
		FOLExpr converted = foe2pred.convert(rewritten);
		Formula smashedSemantics = (Formula) converted;
		
		return new IExprForm<Formula>(
				smashedSemantics, 
				smashedUniqueness,
				smashedImplications);
	}

}
