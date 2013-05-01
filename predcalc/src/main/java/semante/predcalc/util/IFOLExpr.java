package semante.predcalc.util;

import static lombok.AccessLevel.PUBLIC;

import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

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
		public FOLExpr apply(Stack<FOLExpr> arg) {
			if (arg.empty()) {
				return this;
			} else {
				FOLExpr t = arg.pop();
				if (t instanceof Term) { args.add((Term) t); } // else throw error?
				return this.apply(arg);
			}
		}
		@Override public Function replace(FOLExpr a, FOLExpr b) {
			if (this.equals(a)) {
				return (Function) b;
			} else {
				ListIterator<Term> litr = args.listIterator();
				while(litr.hasNext()) {	litr.set(litr.next().replace(a,b)); }
				return this;
			}
		}
	}
	@Value @EqualsAndHashCode(callSuper = false)
	static final class Variable extends IFOLExpr implements Term {
		String name;
		@Override public <X> X accept(Visitor<X> v) {
			return v.variable(name);
		}
		@Override public Variable replace(FOLExpr a, FOLExpr b) {
			if (this.equals(a)) {
				return (Variable) b;
			} else {
				return this;
			}
		}
	}
	
	// Formula
	@Value @EqualsAndHashCode(callSuper=false)
	static class Predicate extends IFOLExpr implements Formula {
		@Getter String name; @Getter List<Term> terms;
		@Override public <X> X accept(Visitor<X> v) {
			return v.predicate(name, terms);
		}
		@Override public Predicate replace(FOLExpr a, FOLExpr b) {
			if (this.equals(a)) {
				if (null == b) { return null; }
				else { return (Predicate) b; }
			} else {
				ListIterator<Term> litr = terms.listIterator();
				while(litr.hasNext()) {
					Term out = litr.next().replace(a,b);
					if (out == null) { litr.remove(); }
					else { litr.set(out); }
				}
				return this;
			}
		}
		public FOLExpr apply(Stack<FOLExpr> arg) {
			if (arg.empty()) {
				return this;
			} else {
				FOLExpr t = arg.pop();
				if (t instanceof Term) {
					terms.add((Term) t);
					return this.apply(arg);
				} else if (t instanceof Predicate) {
					Predicate tp = (Predicate) t;
					return (new Predicate(name + "_" + tp.getName(), tp.getTerms())).apply(arg);
				} if (t instanceof Connective) {
					Connective tc = (Connective) t;
					for (Formula f : tc.getFormulas()) {
						arg.push(f);
					}
					return (this.apply(arg));
				} else {
					throw new UnsupportedOperationException("Can't apply " + t);
				}
			}
		}
	}
	@Value @EqualsAndHashCode(callSuper=false)
	static final class Quantifier extends IFOLExpr implements Formula {
		@Getter String name; @Getter Term var; @Getter Formula body;
		@Override public <X> X accept(Visitor<X> v) {
			return v.quantifier(name, var, body);
		}
		@Override public Quantifier replace(FOLExpr a, FOLExpr b) {
			body.replace(a, b);
			return this;
		}
	}
	@Value @EqualsAndHashCode(callSuper=false)
	static final class Connective extends IFOLExpr implements Formula {
		@Getter String name; @Getter List<Formula> formulas;
		@Override public <X> X accept(Visitor<X> v) {
			return v.connective(name, formulas);
		}
		@Override public Connective replace(FOLExpr a, FOLExpr b) {
			if (this.equals(a)) {
				return (Connective) b;
			} else {
				ListIterator<Formula> litr = formulas.listIterator();
				while(litr.hasNext()) {	litr.set(litr.next().replace(a,b)); }
				return this;
			}
		}
		public FOLExpr apply(Stack<FOLExpr> arg) {
			if (arg.empty()) {
				return this;
			} else {
				FOLExpr t = arg.pop();
				if (t instanceof Formula) { formulas.add((Formula) t); } // else throw error?
				return this.apply(arg);
			}
		}
	}
}
