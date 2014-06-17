package semante.predcalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import semante.predcalc.ExprPrinter;
import semante.predcalc.FOLExpr.Formula;
import semante.predcalc.FOLExpr.Term;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal=true, level=PRIVATE)
final class IExprPrinterProver9 implements ExprPrinter, Formula.Visitor<String>, Term.Visitor<String> {
	
	@Override
	public String format(Formula f) {
		return f.accept(this);
	}
	private String format(Term t) {
		return t.accept(this);
	}
	
	@Override
	public String quantifier(String name, Term x, Formula p) {
		return name + " " + format(x) + " " + format(p);
	}

	@Override
	public String function(String name, List<Term> args) {
		//TODO: reverse args
		return name + "(" + makeList(", ", args) + ")";
	}
	@Override
	public String predicate(String name, List<Term> terms) {
		// TODO: reverse terms
		if (name.equals("=")) {
			return makeList("=", terms);
		} else {
			return name + "(" + makeList(", ", terms) + ")";
		}
	}
	@Override
	public String connective(String name, List<Formula> formulas) {
		if (formulas.isEmpty()) {
			return name;
		} else if (name.equals("-")) {
			return "-(" + makeList(" | ", formulas) + ")";
		} else if (name.equals(".")) {
			return makeList(".\n", formulas);
		} else {
			return "(" + makeList(" "+name+" ", formulas) + ")";
		}
	}

	@Override
	public String variable(String name) {
		return name;
	}
	
	
	// Two or more, use a for
	private <X> String makeList(String delimiter, List<X> args) {
		String out = "";
		boolean first = true;
		for (X arg : args) {
			if (!first) out += delimiter;
			// casting shmasting
			if (arg instanceof Formula) {
				out += format((Formula) arg);
			} else if (arg instanceof Term) {
				out += format((Term) arg);
			}
			first = false;
		}
		return out;
	}
	

}
