package semante.predcalc.util;

import static lombok.AccessLevel.PUBLIC;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Value;
import semante.predcalc.FOLExpr;

/*
 * Predicate = Pred String [Term] -- e*t
 * Quantifier = Quant String Variable Formula -- :(et)t
 * Connective = Conn String [Formula] -- :t*t
 * Variable = Var String -- :e
 * Function = Func String [Var] -- :e*e
 */

@FieldDefaults(makeFinal=true, level=PUBLIC)
abstract class IFOLExpr implements FOLExpr {

	// Term
	@Value @EqualsAndHashCode(callSuper = false)
	static final class Function extends IFOLExpr implements Term {
		String name; List<Term> args;
		@Override public <X> X accept(Visitor<X> v) {
			return v.function(name, args);
		}
		@Override
		public FOLExpr add(FOLExpr accept) {
			if (accept instanceof Term) {
				args.add((Term) accept);
				return this;
			} else {
				throw new Error("Cannot add non-term to function: " + accept);
			}
		}
	}
	@Value @EqualsAndHashCode(callSuper = false)
	static final class Variable extends IFOLExpr implements Term {
		String name;
		@Override public <X> X accept(Visitor<X> v) {
			return v.variable(name);
		}
		@Override
		public FOLExpr add(FOLExpr accept) {
			throw new Error("Cannot add anything to variable: " + accept);
		}
	}
	
	// Formula
	@Value @EqualsAndHashCode(callSuper=false)
	static class Predicate extends IFOLExpr implements Formula {
		@Getter String name; @Getter List<Term> terms;
		@Override public <X> X accept(Visitor<X> v) {
			return v.predicate(name, terms);
		}
		@Override
		public FOLExpr add(FOLExpr accept) {
			if (accept instanceof Term) {
				terms.add((Term) accept);
				return this;
			} else {
				throw new Error("Cannot add non-term to predicate: " + accept);
			}
		}
	}
	@Value @EqualsAndHashCode(callSuper=false)
	static final class Quantifier extends IFOLExpr implements Formula {
		@Getter String name; @Getter Term var; @Getter Formula body;
		@Override public <X> X accept(Visitor<X> v) {
			return v.quantifier(name, var, body);
		}
		@Override
		public FOLExpr add(FOLExpr accept) {
			throw new Error("Cannot add anything to quantifier: " + accept);
		}
	}
	@Value @EqualsAndHashCode(callSuper=false)
	static final class Connective extends IFOLExpr implements Formula {
		@Getter String name; @Getter List<Formula> formulas;
		@Override public <X> X accept(Visitor<X> v) {
			return v.connective(name, formulas);
		}
		@Override
		public FOLExpr add(FOLExpr accept) {
			if (accept instanceof Formula) {
				formulas.add((Formula) accept);
				return this;
			} else {
				throw new Error("Cannot add non-formula to connective: " + accept);
			}
		}
	}
}
