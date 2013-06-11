package predcalc;

import java.util.List;

/*
 * Formula = Predicate | Quantifier | Connective
 * Term = Variable | Function
 */

public interface FOLExpr {
	
	public interface Term extends FOLExpr {
		<X> X accept(Visitor<X> v);
		
		public interface Visitor<X> {
			X function	(String name, List<Term> args);
			X variable	(String name);
		}
		
		public interface Identity extends Visitor<Term> {}
	}

	public interface Formula extends FOLExpr {
		<X> X accept(Visitor<X> v);

		public interface Visitor<X> {
			X predicate	(String name, List<Term> terms);	// includes 'is'
			X quantifier(String name, Term x, Formula p);
			X connective(String name, List<Formula> formulas); // includes 'not'
		}
		
		public interface Identity extends Visitor<Formula> {}
	}

	FOLExpr add(FOLExpr accept);
}
