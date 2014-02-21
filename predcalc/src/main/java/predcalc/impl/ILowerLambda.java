package predcalc.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lambdacalc.Expr;
import lambdacalc.Expr.Visitor;
import lambdacalc.ExprBuilder;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.Types;
import lambdacalc.impl.IType.IConstant;
import lambdacalc.impl.IType.IFunction;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.FieldDefaults;
import predcalc.HigherOrderError;
import predcalc.LowerLambda;


@RequiredArgsConstructor
@FieldDefaults(makeFinal=true)
public class ILowerLambda implements LowerLambda {

	
	private static final String VAR_PREFIX = "x";	
	private Pattern varPattern = Pattern.compile("^"+VAR_PREFIX+"([0-9]+)$");			

	private static final String S_PREFIX = "S";	
	
	final Type TypeETT = new IFunction(Types.ET,Types.T);
	
	Smasher smasher = new Smasher();
	
	protected STL lcalc;
	
	private ExprBuilder rewriter = new ExprBuilder() {

		
		@Override
		public Expr application(Expr fun, Expr arg) {

			val app = lcalc.betaReduce(lcalc.getExprBuilder().application(fun.accept(rewriter), arg.accept(rewriter)));
			val fun2 = app.accept(getAppFunc);
			val arg2 = app.accept(getAppArg);

			System.out.println("Application [" + lcalc.format(app) + "]");

			if (checkETSmashing(fun2,arg2)) {
				// here go the simple smashing short(man), the complex smashing (in brazil)(man), and Noun compounds
				System.out.println("Lowerable application: ["+ lcalc.format(fun2) + "] to: [" + lcalc.format(arg2) + "]");
				return smasher.smash(fun2,arg2);
			} else if (checkETTSmashing(fun2,arg2)) {
				// NP compound case - for example: John applied to Smith - John(Smith).
				// The idea is to create a compound John_Smith with the appropriate type.
				// Note that John might be of type ((et)t)e or ((et)t)((et)t)e, etc. if it needs to be combined with
				// more NPs later on in the course of the derivation like in [[John Smith] Elliot].
				if (arg2.accept(isNPConstant)) {
					Expr e = lcalc.getExprBuilder().variable(buildSymbol(
							fun2.accept(getConstantName) + "_" + arg2.accept(getNPConstantName),
							lcalc.typeOf(fun2).accept(getFunctionReturnType)));
					System.out.println("NP compound: " + lcalc.format(e));
					return e;
				} else {
					throw new HigherOrderError("NP compound with a non-constant argument: " + lcalc.format(app));
				}
				
			} else {
				return app;
			}
		}
		
		@Override
		public Expr abstraction(Symbol s, Expr body) {
			return lcalc.getExprBuilder().abstraction(s, body.accept(rewriter));
		}

		@Override
		public Expr variable(Symbol s) {
			return lcalc.getExprBuilder().variable(s);
		}
	};
	
	private boolean checkETTSmashing(Expr func, Expr arg) {
		return lcalc.typeOf(func).accept(getFunctionArgType).equals(TypeETT) && 
				lcalc.typeOf(arg).equals(TypeETT);
	}
	
	private boolean checkETSmashing(Expr func, Expr arg) {
		
		return 	lcalc.typeOf(func).accept(new Type.Visitor<Boolean>() {
			@Override
			public Boolean constant(String name) {
				return false;
			}
			
			@Override
			public Boolean function(Type a, Type b) {
				// The function is lowerable if it takes a type X
				// and either return type X or returns a function from type X
				// to something else. For example, tall is of type (et)et, so
				// it's lowerable; and also (et)(et)et (in the case of noun 
				// compounds).
				
				boolean aTypeOK = a.equals(Types.ET);
				boolean bTypeOK = b.equals(Types.ET) || b.accept(getFunctionArgType).equals(Types.ET);
				return aTypeOK && bTypeOK;
			}
		}) && lcalc.typeOf(arg).equals(Types.ET); 
	}
	
	
	private class Smasher implements ExprBuilder {
		
		AlphaConverter alphaConverter;
		VariableRewriter varRewriter;
		ConjunctionCleaner conjCleaner;

		MaxVarIdFinder maxVarIdFinder;
		int nextVarId;
		
		int nextSId;
		
		String mainArgName;
		MainArgChecker mainArgChecker;
		List<Expr> expressions;
		List<Predicate> predicates;
		ExpressionParser expParser;
		
		List<QuantifierRewriting> frontedQuantifiers;
		
		public Smasher() {
			alphaConverter = new AlphaConverter();
			varRewriter = new VariableRewriter();
			frontedQuantifiers = new ArrayList<QuantifierRewriting>();

			mainArgChecker = new MainArgChecker();
			expressions = new ArrayList<Expr>();
			maxVarIdFinder = new MaxVarIdFinder();
			predicates = new ArrayList<Predicate>();
			expParser = new ExpressionParser();
			conjCleaner = new ConjunctionCleaner();
		}
		
		Expr smash(Expr modifierExpr, Expr argExpr) {
			frontedQuantifiers.clear();
			predicates.clear();
			expressions.clear();
			
			nextVarId = Math.max(argExpr.accept(maxVarIdFinder),modifierExpr.accept(maxVarIdFinder)) + 1;
			nextSId = 0;
			
			if (!modifierExpr.accept(isVariableBased)) {
				throw new HigherOrderError("Modifier is not constant based: [" + lcalc.format(modifierExpr) + "]");
			}

			System.out.println("Smashing Modifier: "  + lcalc.format(modifierExpr));
			mainArgChecker.setMainArg(null);
			// we run the visitor in order to remove quantifiers (we re-place them later on)
			// since main arg is null, there is no danger of messing with any argument (e.g. in (from:e(et)(et) x1:e))
			modifierExpr = modifierExpr.accept(this);
			System.out.println("Modifier after removing quantifiers: "  + lcalc.format(modifierExpr));
						
			System.out.println("Smashing Modifiee: "  + lcalc.format(argExpr));
			
			// First thing to do is to add an abstraction if it's missing 			
			Expr ret;
			String mainArgId;
			if (!argExpr.accept(isAbstraction)) {
				if (!argExpr.accept(isVariableBased)) {
					throw new HigherOrderError("Modifiee is neither an abstraction nor a constant: [" + lcalc.format(argExpr) + "]");
				}
				mainArgId = Integer.toString(getNextVarId());
				mainArgName = VAR_PREFIX+mainArgId;
				Symbol newVar = buildSymbol(mainArgName,Types.E);
				argExpr = lcalc.getExprBuilder().abstraction(newVar,lcalc.getExprBuilder().application(argExpr,
						lcalc.getExprBuilder().variable(newVar)));
			} else {
				mainArgName = argExpr.accept(getAbstractionSymbol).getName();
				Matcher matcher = varPattern.matcher(mainArgName);
				if (matcher.find()) {
					mainArgId = matcher.group(1);
				} else {
					throw new HigherOrderError("Failed to recognize main argument in expression: [" + lcalc.format(argExpr) + "]");
				}	
			}
			mainArgChecker.setMainArg(mainArgId);

			// This call will remove all of the quantifiers and collect applications to the main arg
			ret = argExpr.accept(getAbstractionContent).accept(this);
			System.out.println("Smashing after quantifiers and app of main-arg cleaning: "  + lcalc.format(ret));

//			if (expressions.size()==1) {
	//			
		//	}
			
			// now we add the modifier as an expression that needs to be concatenated. Note that it is added
			// at the beginning of the list.
			expressions.add(0,modifierExpr);
			
			// Now we can create the new predicate as a concatenation of those applied to mainArgName and then 
			// add/use it. This is done in several steps.
			
			// step 1 - prepare a predicates list from the expressions that included application to the main arg
			for (Expr e : expressions) {
				if (!expParser.parse(e,mainArgName)) {
					throw new HigherOrderError("Failed to parse predication structure: [" + lcalc.format(e) + "]");
				}
				predicates.add(new Predicate(expParser.getName(),expParser.getParams())); 
			}
			
			// step 2 - decide on the name and type of the concatenated predicate and create it
			Type modifierType = lcalc.typeOf(modifierExpr).accept(getFunctionReturnType);
			
			String newName = getNewPredicateName(predicates);
			List<String> newPredParams = getNewPredicateParams(predicates); 
			Type newType = getNewPredicateType(modifierType,newPredParams);
			Expr newExpr = lcalc.getExprBuilder().variable(buildSymbol(newName, newType));
						
			// iteratively add the parameters of all concatenated predicates
			for (String paramName : newPredParams) {
				newExpr = lcalc.getExprBuilder().application(
					newExpr,
					lcalc.getExprBuilder().variable(buildSymbol(paramName, Types.E))); 									
			}
			// and also add the main argument
			newExpr = lcalc.getExprBuilder().application(
					newExpr,
					lcalc.getExprBuilder().variable(buildSymbol(mainArgName, Types.E))); 									
			
			// add the new predicate expression to the full expression
			ret = 	(ret.accept(is1Constant) || ret.accept(is2Constant)) ? newExpr :
						conjunctNewExprAtEnd(newExpr,ret);

			System.out.println("Smashed predicate added: "  + lcalc.format(ret));
			
			// Now we re-introduce the quantifiers 
			Collections.reverse(frontedQuantifiers); // to preserve the original order of quantifiers
			for (QuantifierRewriting qr : frontedQuantifiers) {
				System.out.println("Re-Inserting new QR: " + qr.getName() + " var id: " + qr.getNewId());
				ret = lcalc.getExprBuilder().application(
						lcalc.getExprBuilder().variable(buildSymbol(qr.getName(), Types.ET_T)),
						lcalc.getExprBuilder().abstraction(buildSymbol(VAR_PREFIX+qr.getNewId(),Types.E),ret));
			}
			System.out.println("Fronted quantifiers re-introduced: "  + lcalc.format(ret));

			// And finally we also re-introduce the abstraction of the main arg
			ret = lcalc.getExprBuilder().abstraction(buildSymbol(mainArgName,Types.E),ret);

			System.out.println("Abstraction re-introduced: "  + lcalc.format(ret));
			
			ret = lcalc.parse(lcalc.format(ret));
				
			System.out.println("Smashing done: "  + lcalc.format(ret));
			
			return ret;
		}
		
		private Expr conjunctNewExprAtEnd(final Expr newExpr, Expr ret) {
			System.out.println("Adding new expression by conjunction");
			return lcalc.getExprBuilder().application(
					lcalc.getExprBuilder().application(
							lcalc.getExprBuilder().variable(buildSymbol("AND", Types.TTT)),ret),
								newExpr);
		}

		private Expr setNewExprUnderScope(final Expr newExpr, Expr ret) {
			System.out.println("Setting new expression under scope sensitive expression");
			return ret.accept(new ExprBuilder() {
				@Override
				public Expr application(Expr f, Expr arg) {
					return lcalc.getExprBuilder().application(f.accept(this), 
								arg.accept(is2Constant) ? newExpr : arg.accept(this));
				}

				@Override 
				public Expr abstraction(Symbol s, Expr arg) {
					return lcalc.getExprBuilder().abstraction(s, arg.accept(this));
				}

				@Override
				public Expr variable(Symbol s) {
					return lcalc.getExprBuilder().variable(s);
				}
			});
		}

		private int getNextVarId() {
			return nextVarId++;
		}
		
		private int getNextSId() {
			return nextSId++;
		}
		
		@Override
		public Expr application(final Expr f, final Expr arg) {
			Expr ret = null;
			
			System.out.println("Checking: app of [" + lcalc.format(f) + "] to ["+ lcalc.format(arg) + "]");
			
			if (f.accept(isQuantifier)) {
				System.out.println("Quantifier spotted!");
				Symbol abstractSymbol = arg.accept(getAbstractionSymbol);
				String qName = f.accept(getConstantName);

				System.out.println("Inserting QR to fronted list: " + qName.toString());
				int oldVariableId = checkVariable(abstractSymbol);
				int newVariableId = getNextVarId();
				alphaConverter.reset(abstractSymbol.getName(), "x"+newVariableId);
				QuantifierRewriting qr = new QuantifierRewriting(qName,oldVariableId,newVariableId);
				frontedQuantifiers.add(qr);
				ret = arg.accept(getAbstractionContent).accept(alphaConverter).accept(this);
/*						
						lcalc.getExprBuilder().application(
						lcalc.getExprBuilder().variable(buildSymbol(qr.getName(), Types.ET_T)),
						lcalc.getExprBuilder().abstraction(buildSymbol(VAR_PREFIX+qr.getNewId(),Types.E),
								arg.accept(getAbstractionContent).accept(alphaConverter).accept(this)));
								*/			
			} else {
				boolean mainArg = arg.accept(mainArgChecker); 
				if (mainArg) {
					System.out.println("Main arg spotted!");
					mainArg = true;
				}
				ret = lcalc.getExprBuilder().application(f.accept(this), arg.accept(this));
				if (mainArg) {
					expressions.add(ret);
				}
			}

			return ret;
		}
		
		@Override 
		public Expr abstraction(Symbol s, Expr arg) {
			return lcalc.getExprBuilder().abstraction(s, arg.accept(this));
		}

		@Override
		public Expr variable(Symbol s) {
			return lcalc.getExprBuilder().variable(s);
		}

		// This visitor tells whether we are dealing with an argument that is the main argument for the smashing. E.g., x2 in:
		// [on:e(et)et x1:e] [\x2:e.(EXISTS:(et)t (\x3:e.((AND:ttt (girl:et x3:e)) ((loves:eet x3:e) x2:e))))]
		private class MainArgChecker implements Expr.Visitor<Boolean> {
			String mainArgRegExp;
			
			public void setMainArg(String mainArgIdRegExp) {
				this.mainArgRegExp = mainArgIdRegExp==null ? null : VAR_PREFIX + mainArgIdRegExp;
			}			
			
			@Override public Boolean application(final Expr f, final Expr arg) 	{ return false; } 
			@Override public Boolean abstraction(Symbol s, Expr arg) 			{ return false; }
			@Override public Boolean variable(Symbol s) { 
				return mainArgRegExp==null ? false : s.getName().matches(mainArgRegExp); 
			}
		};

		
		private class ConjunctionCleaner implements ExprBuilder {

			@Override
			public Expr application(final Expr f, final Expr arg) {

				Expr newF = f.accept(this);
				Expr newArg = arg.accept(this);
				
				Expr ret = null;
				if (newF.accept(isConjApp)) {
					Expr conjArg = newF.accept(getConjArg);
					if (newArg.accept(is1Constant)) {
						ret = conjArg;
					} else if (conjArg.accept(is1Constant)){
						ret = newArg;
					}
				} 
				return ret!=null ? ret : lcalc.getExprBuilder().application(newF,newArg); 
			}
			
			@Override 
			public Expr abstraction(Symbol s, Expr arg) {
				return lcalc.getExprBuilder().abstraction(s, arg.accept(this));
			}

			@Override
			public Expr variable(Symbol s) {
				return lcalc.getExprBuilder().variable(s);
			}

		}

		private class ScopeSensitiveQuantifiersFiller implements ExprBuilder {

			Map<String,QuantifierRewriting> scopeSensitiveQuantifiers;
			
			@Override
			public Expr application(final Expr f, final Expr arg) {
				Expr ret = null;
				Expr newF = f.accept(this);
				Expr newArg = arg.accept(this);
				String fVariableName = newF.accept(getVariableName); 
				if (fVariableName!=null) {
					if (scopeSensitiveQuantifiers.containsKey(fVariableName)) {
						System.out.println("Quantifier place holder spotted!");

						QuantifierRewriting qr = scopeSensitiveQuantifiers.get(fVariableName); 
						
						ret = lcalc.getExprBuilder().application(
								lcalc.getExprBuilder().variable(buildSymbol(qr.getName(), Types.ET_T)),
								lcalc.getExprBuilder().abstraction(buildSymbol(VAR_PREFIX+qr.getNewId(),Types.E),newArg));
						
						System.out.println("Quantifier place holder replaced with " + lcalc.format(ret));
					}
				}
				return ret!=null ? ret : lcalc.getExprBuilder().application(newF,newArg); 
			}
			
			@Override 
			public Expr abstraction(Symbol s, Expr arg) {
				return lcalc.getExprBuilder().abstraction(s, arg.accept(this));
			}

			@Override
			public Expr variable(Symbol s) {
				return lcalc.getExprBuilder().variable(s);
			}

			public void setScopeSensitiveQuantifiers(
					Map<String, QuantifierRewriting> scopeSensitiveQuantifiers) {
				this.scopeSensitiveQuantifiers = scopeSensitiveQuantifiers;
			}
			
		}
		
		private class AlphaConverter implements ExprBuilder {
			String oldSymbolName;
			String newSymbolName;
			
			public void reset(String oldSymbolName, String newSymbolName) {
				this.oldSymbolName = oldSymbolName;
				this.newSymbolName = newSymbolName;
			}

			@Override
			public Expr application(final Expr f, final Expr arg) {
				return lcalc.getExprBuilder().application(f.accept(this), arg.accept(this));
			}
			
			@Override 
			public Expr abstraction(Symbol s, Expr arg) {
				return lcalc.getExprBuilder().abstraction(checkSymbol(s), arg.accept(this));
			}

			@Override
			public Expr variable(Symbol s) {
				return lcalc.getExprBuilder().variable(checkSymbol(s));
			}
			
			private Symbol checkSymbol(Symbol s) {
				return s.getName().equals(oldSymbolName) ? buildSymbol(newSymbolName, s.getType()) : s;
			}
		}
		
		private class MaxVarIdFinder implements Expr.Visitor<Integer> {

			@Override
			public Integer application(final Expr f, final Expr arg) {
				return Math.max(f.accept(this), arg.accept(this));
			}
			
			@Override 
			public Integer abstraction(Symbol s, Expr arg) {
				return Math.max(checkVariable(s),arg.accept(this));
			}

			@Override
			public Integer variable(Symbol s) {
				return checkVariable(s);
			}
			
		}	
		
		private int checkVariable(Symbol s) {
			Matcher matcher = varPattern.matcher(s.getName());
			return matcher.find() ? Integer.parseInt(matcher.group(1)) : -1;
		}
		
		class ExpressionParser implements Expr.Visitor<Boolean> {
			List<String> params;
			String name;
			
			String mainArgName;
			
			public ExpressionParser() {
				params = new ArrayList<String>();
			}
			
			public boolean parse(Expr e, final String mainArgName) {
				params.clear();
				name = null;
				this.mainArgName = mainArgName;
				return e.accept(this);
			}

			String getName() {
				return name;
			}
			
			List<String> getParams() {
				return params;
			}
			
			@Override public Boolean abstraction(Symbol arg0, Expr arg1) 	{ return false; }
			@Override public Boolean variable(Symbol arg0) { 
				name = arg0.getName();
				return true; 
			}
			@Override public Boolean application(Expr func, Expr arg) {
				boolean ret = false;
				if (arg.accept(isVariable)) {
					String argName = arg.accept(getConstantName);
					if (!argName.equals(mainArgName)) {
						params.add(argName);
					}
					if (func.accept(isVariable)) {
						name = func.accept(getConstantName);
						ret = true;
					} else {
						ret = func.accept(this);
					}
				} 
				return ret;
			}
		}
		
		private String getNewPredicateName(List<Predicate> predicates) {
			StringBuilder newPredName = new StringBuilder("_");
			for (Predicate p : predicates) {
				newPredName.append(p.getName());
				newPredName.append('_');
			}

			String[] predicatesNames = newPredName.toString().split("_");		
			int firstOccurrence;
			for (int i = 0 ; i<predicatesNames.length ; i++) {
				while (((firstOccurrence = newPredName.indexOf("_"+predicatesNames[i]+"_"))<
					newPredName.lastIndexOf("_"+predicatesNames[i]+"_")) && (firstOccurrence>0)) {
					newPredName.delete(firstOccurrence, firstOccurrence+1+predicatesNames[i].length());
				}
			}
			while (newPredName.charAt(0)=='_') {
				newPredName.deleteCharAt(0);
			}
			while (newPredName.charAt(newPredName.length()-1)=='_') {
				newPredName.deleteCharAt(newPredName.length()-1);
			}
			return newPredName.toString().replaceAll("[_]{2,}", "_");
		}
		
		private List<String> getNewPredicateParams(List<Predicate> predicates) {
			List<String> usedArgs = new ArrayList<String>();
			for (Predicate p : predicates) {
				List<String> args = p.getArguments(); 
				for (int i=0; i<args.size();i++) {
					if (!usedArgs.contains(args.get(i))) {
						usedArgs.add(args.get(i));
					}
				}
			}
			return usedArgs;
		}
		
		private Type getNewPredicateType(Type modifierType, List<String> newPredParams) {
			Type newType = modifierType;
			for (int i = 0 ; i<newPredParams.size() ; i++) {
				newType = new IFunction(Types.E,newType);
			}
			return newType;
		}

		private Visitor<Boolean> isQuantifier = new Visitor<Boolean>() {
			List<String> quantifiersNames = Arrays.asList("EXISTS","FORALL");

			@Override public Boolean abstraction(Symbol s, Expr body) 	{ return false; }
			@Override public Boolean application(Expr fun, Expr arg) 	{ return false; }
			@Override public Boolean variable(Symbol s) 				{ return quantifiersNames.contains(s.getName());}
		};
		
		private Visitor<String> getScopeSensitiveFunc = new Visitor<String>() {
			List<String> scopesSensitiveFuncNames = Arrays.asList("IMPLIES");
			boolean appIncluded;
			
			@Override public String abstraction(Symbol s, Expr body) 	{ return null; }
			@Override public String application(Expr fun, Expr arg) { 
				appIncluded = true;
				return fun.accept(this);
			}
			@Override public String variable(Symbol s) { 
				return appIncluded && scopesSensitiveFuncNames.contains(s.getName()) ? 
						s.getName() : null;
			}
		};
		
		private class VariableRewriter implements ExprBuilder{
			String elementOldName;
			String elementNewName;
			
			public Expr rewrite(Expr e, String elementOldName, String elementNewName) {
				this.elementOldName = elementOldName;
				this.elementNewName = elementNewName;
				return e.accept(this);
			}
			
			@Override
			public Expr abstraction(Symbol s, Expr body) {
				return lcalc.getExprBuilder().abstraction(s, body.accept(rewriter));
			}

			@Override
			public Expr variable(Symbol s) {
				return lcalc.getExprBuilder().variable(
					buildSymbol(s.getName().equals(elementOldName) ? elementNewName : s.getName(),s.getType()));
			}
			
			@Override public Expr application(Expr fun, Expr arg) { 
				return lcalc.getExprBuilder().application(fun.accept(this),arg.accept(this));
			}
			
		};

		abstract class ExprRewriting {
			String exprName;
			
			public ExprRewriting(String exprName) {
				this.exprName = exprName;
			}
			
			String getName() {
				return exprName;
			}
			
			public String toString() {
				return getName();
			}
			
		}
		
		class ElementRewriting extends ExprRewriting {

			public ElementRewriting(String exprName) {
				super(exprName);
			}
		}
		
		class QuantifierRewriting extends ExprRewriting{
			int oldId;
			int newId;
			
			public QuantifierRewriting(String quantifierName, int oldId, int newId) {
				super(quantifierName);
				this.oldId = oldId;
				this.newId = newId;
			}

			public int getOldId() {
				return oldId;
			}

			public int getNewId() {
				return newId;
			}
			
			public String toString() {
				return getName() + " oldVarId=" + getOldId() + ", newVarId=" + getNewId();
			}
		}
		
		class Predicate {
			private String 			name;
			private List<String>	arguments;
			
			public Predicate(String name, List<String> arguments) {
				super();
				this.name = name;
				this.arguments = new ArrayList<String>(arguments);
			}

			public String getName() {
				return name;
			}
			
			public List<String> getArguments() {
				return arguments;
			}
			
		}
		
	}

	
	
	
	private Visitor<Boolean> isVariable = new Visitor<Boolean>() {

		@Override public Boolean abstraction(Symbol s, Expr body) 	{ return false; }
		@Override public Boolean application(Expr fun, Expr arg) 	{ return false; }
		@Override public Boolean variable(Symbol s) 				{ return true;}
	};
	
	private Visitor<String> getVariableName = new Visitor<String>() {

		@Override public String abstraction(Symbol s, Expr body) 	{ return null; }
		@Override public String application(Expr fun, Expr arg) 	{ return null; }
		@Override public String variable(Symbol s) 					{ return s.getName();}
	};
	

	private Visitor<Boolean> is1Constant = new Visitor<Boolean>() {

		@Override public Boolean abstraction(Symbol s, Expr body) 	{ return false; }
		@Override public Boolean application(Expr fun, Expr arg) 	{ return false; }
		@Override public Boolean variable(Symbol s) 				{ return s.getName().equals("1") && s.getType().equals(Types.T);}
	};

	private Visitor<Boolean> is2Constant = new Visitor<Boolean>() {

		@Override public Boolean abstraction(Symbol s, Expr body) 	{ return false; }
		@Override public Boolean application(Expr fun, Expr arg) 	{ return false; }
		@Override public Boolean variable(Symbol s) 				{ return s.getName().equals("2") && s.getType().equals(Types.T);}
	};

	private Visitor<Boolean> isAbstraction = new Visitor<Boolean>() {

		@Override public Boolean abstraction(Symbol s, Expr body) 	{ return true; }
		@Override public Boolean application(Expr fun, Expr arg) 	{ return false; }
		@Override public Boolean variable(Symbol s) 				{ return false;}
	};

	private Visitor<Symbol> getAbstractionSymbol = new Visitor<Symbol>() {

		@Override public Symbol abstraction(Symbol s, Expr body) 	{ return s; }
		@Override public Symbol application(Expr fun, Expr arg) 	{ return null; }
		@Override public Symbol variable(Symbol s) 					{ return null; }
	};

	
	private Visitor<Expr> getAbstractionContent = new Visitor<Expr>() {

		@Override public Expr abstraction(Symbol s, Expr body) 		{ return body; }
		@Override public Expr application(Expr fun, Expr arg) 		{ return null; }
		@Override public Expr variable(Symbol s) 					{ return null; }
	};

	private Visitor<Boolean> isVariableBased = new Visitor<Boolean>() {

		@Override public Boolean abstraction(Symbol s, Expr body) 	{ return false; }
		@Override public Boolean application(Expr fun, Expr arg) 	{ return fun.accept(this); }
		@Override public Boolean variable(Symbol s) 				{ return true;}
	};

	
	private Visitor<String> getConstantName = new Visitor<String>() {

		@Override public String abstraction(Symbol s, Expr body) 	{ return body.accept(this); }
		@Override public String application(Expr fun, Expr arg) 	{ return fun.accept(this); }
		@Override public String variable(Symbol s) 					{ return s.getName();}
	};
	
	private Visitor<Boolean> isNPConstant = new Visitor<Boolean>() {

		@Override public Boolean abstraction(Symbol s, Expr body) 	{ return body.accept(this); }
		@Override public Boolean application(Expr fun, Expr arg) 	{ return fun.accept(this); }
		@Override public Boolean variable(Symbol s) 				{ return true;}
	};
	
	private Visitor<String> getNPConstantName = new Visitor<String>() {

		@Override public String abstraction(Symbol s, Expr body) 	{ return body.accept(this); }
		@Override public String application(Expr fun, Expr arg) 	{ return arg.accept(this); }
		@Override public String variable(Symbol s) 					{ return s.getName();}
	};
	
	private Visitor<Boolean> isConjApp = new Visitor<Boolean>() {

		@Override public Boolean abstraction(Symbol s, Expr body) 	{ return false; }
		@Override public Boolean application(Expr fun, Expr arg) 	{ return fun.accept(isVariable) && fun.accept(getConstantName).equals("AND") ? true : false; }
		@Override public Boolean variable(Symbol s) 				{ return false; }
	};
	
	private Visitor<Expr> getConjArg = new Visitor<Expr>() {

		@Override public Expr abstraction(Symbol s, Expr body) 	{ return null; }
		@Override public Expr application(Expr fun, Expr arg) 	{ return fun.accept(isVariable) && fun.accept(getConstantName).equals("AND") ? arg : null; }
		@Override public Expr variable(Symbol s) 				{ return null; }
	};


	private Visitor<Expr> getAppFunc = new Visitor<Expr>() {

		@Override public Expr abstraction(Symbol s, Expr body) 	{ return null; }
		@Override public Expr application(Expr fun, Expr arg) 	{ return fun; }
		@Override public Expr variable(Symbol s) 				{ return null; }
	};
	
	private Visitor<Expr> getAppArg = new Visitor<Expr>() {

		@Override public Expr abstraction(Symbol s, Expr body) 	{ return null; }
		@Override public Expr application(Expr fun, Expr arg) 	{ return arg; }
		@Override public Expr variable(Symbol s) 				{ return null; }
	};
	
	
	private Type.Visitor<Type> getFunctionArgType = new Type.Visitor<Type>() {

		@Override public Type constant(String name) { return new IConstant(name); }
		@Override public Type function(Type a, Type b) { return a; }
	};
	
	private Type.Visitor<Type> getFunctionReturnType = new Type.Visitor<Type>() {
		
		@Override public Type constant(String name) { return new IConstant(name); }
		@Override public Type function(Type a, Type b) { return b; }
	};
	
	Symbol buildSymbol(final String name, final Type type) {
		return new Symbol() {
			@Override public String getName() { return name; }
			@Override public Type getType() { return type; }
		};
	}
		
	String name(final Expr exp) {
		return "p"+exp.hashCode();
	}
	
	public Expr rewrite(Expr a) {
		return a.accept(rewriter);
	}
	
}
