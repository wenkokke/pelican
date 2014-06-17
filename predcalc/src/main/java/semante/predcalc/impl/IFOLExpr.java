package semante.predcalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import semante.predcalc.FOLExpr;

/*
 * Predicate = Pred String [Term] -- e*t
 * Quantifier = Quant String Variable Formula -- :(et)t
 * Connective = Conn String [Formula] -- :t*t
 * Variable = Var String -- :e
 * Function = Func String [Var] -- :e*e
 */

@FieldDefaults(makeFinal = true, level = PRIVATE)
abstract class IFOLExpr implements FOLExpr {

	// Term
	@Getter
	@ToString
	@EqualsAndHashCode(callSuper = false)
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal = true, level = PRIVATE)
	static final class Function extends IFOLExpr implements Term {

		String name;
		List<Term> args;

		@Override
		public <X> X accept(Visitor<X> v) {
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

	@Getter
	@ToString
	@EqualsAndHashCode(callSuper = false)
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal = true, level = PRIVATE)
	static final class Variable extends IFOLExpr implements Term {
		
		String name;

		@Override
		public <X> X accept(Visitor<X> v) {
			return v.variable(name);
		}

		@Override
		public FOLExpr add(FOLExpr accept) {
			throw new Error("Cannot add anything to variable: " + accept);
		}
	}

	// Formula
	@Getter
	@ToString
	@EqualsAndHashCode(callSuper = false)
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal = true, level = PRIVATE)
	static class Predicate extends IFOLExpr implements Formula {

		String name;
		List<Term> terms;

		@Override
		public <X> X accept(Visitor<X> v) {
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

	@Getter
	@ToString
	@EqualsAndHashCode(callSuper = false)
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal = true, level = PRIVATE)
	static final class Quantifier extends IFOLExpr implements Formula {

		String name;
		Term var;
		Formula body;

		@Override
		public <X> X accept(Visitor<X> v) {
			return v.quantifier(name, var, body);
		}

		@Override
		public FOLExpr add(FOLExpr accept) {
			throw new Error("Cannot add anything to quantifier: " + accept);
		}
	}

	@Getter
	@ToString
	@EqualsAndHashCode(callSuper = false)
	@RequiredArgsConstructor
	@FieldDefaults(makeFinal = true, level = PRIVATE)
	static final class Connective extends IFOLExpr implements Formula {
		
		String name;
		List<Formula> formulas;

		@Override
		public <X> X accept(Visitor<X> v) {
			return v.connective(name, formulas);
		}

		@Override
		public FOLExpr add(FOLExpr accept) {
			if (accept instanceof Formula) {
				formulas.add((Formula) accept);
				return this;
			} else {
				throw new Error("Cannot add non-formula to connective: "
						+ accept);
			}
		}
	}
}
