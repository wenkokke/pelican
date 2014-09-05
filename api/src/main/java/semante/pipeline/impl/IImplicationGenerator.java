package semante.pipeline.impl;

import java.util.Set;

import lambdacalc.DeBruijn;
import lambdacalc.Expr;
import lambdacalc.STL;
import lambdacalc.Symbol;
import lambdacalc.Type;
import lambdacalc.Types;
import lambdacalc.impl.IType.IFunction;
import lombok.val;
import semante.pipeline.ImplicationGenerator;
import semante.pipeline.IotaExtractor;
import semante.pipeline.IotaExtractor.IotaExtractorResult;
import semante.settings.Settings;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

public class IImplicationGenerator implements ImplicationGenerator {

	private STL stl;
	private IotaExtractor iotaExtractor;
	private boolean debugMode;
	
	public IImplicationGenerator(Settings settings, STL stl, IotaExtractor iotaExtractor) {
		this.debugMode = Boolean.parseBoolean(settings.get("SemAnTE","Tracer","Pipeline"));
		this.stl = stl;
		this.iotaExtractor = iotaExtractor;
	}

	private Expr extractIotas(Expr e, Builder<Expr> uniquenessConditionsB) {
		IotaExtractorResult result = iotaExtractor.extract(e,stl);
		uniquenessConditionsB.addAll(result.getUniquenessConditions());
		return result.getAssertion();
	}
	
	
	private static final Type TypeET_T_ET = new IFunction(Types.ET_T,Types.ET);

	/*
	private static final Visitor<Boolean> isConstant = new Visitor<Boolean>() {
		@Override public Boolean abstraction(Type arg0, DeBruijn arg1) {return false;}
		@Override public Boolean application(DeBruijn arg0, DeBruijn arg1) { return false; }
		@Override public Boolean constant(Symbol arg0) {return true; }
		@Override public Boolean variable(Index arg0) {return false; }
	};*/
	
	@Override
	public Set<Expr> process(Expr sourceExp, Expr targetExp, final DeBruijn context, final Builder<Expr> uniquenessConditionsB) throws UnmatchedTypesException, UnsupportedImplication {
		
		val sourceType = stl.typeOf(sourceExp);
		val targetType = stl.typeOf(targetExp);
		
		if (debugMode) {
			System.err.println("Defining implication between: ["+stl.format(sourceExp)+"] and ["+stl.format(targetExp)+"] of types: [" + stl.format(sourceType) + "] and [" + stl.format(targetType) +"].");
		}
		
		if (!sourceType.equals(targetType)) {
			throw new UnmatchedTypesException(stl.format(sourceType),stl.format(targetType)); 
		}

		val implicationsB = ImmutableSet.<Expr>builder();

		val bld = stl.getExprBuilder();

		if (sourceType.equals(Types.ET)) {
			// this is the simplest case of et subsumption. We simply define a 
			// universal quantification on the argument of the two et predicates
			// and assert that the truthfulness of the first entails the second.
			Symbol newVar = buildSymbol("z0", Types.E);
			implicationsB.add( 
				bld.application(
					bld.variable("FORALL", Types.ET_T),
					bld.abstraction(newVar,
						bld.application(
							bld.application(bld.variable("IMPLIES",Types.TTT),
								extractIotas(bld.application(sourceExp,bld.variable(newVar)),uniquenessConditionsB)),
								extractIotas(bld.application(targetExp,bld.variable(newVar)),uniquenessConditionsB)))));
		} else if (sourceType.equals(Types.EET)) {
			// this is aso a relatively simple case of subsumption - concerning relational
			// nouns. We simply define a universal quantification on the two argument of the
			//  eet predicates and assert that the truthfulness of the first entails the second.
			
			// two symbols for the universal quantification
			val newVarSymbol1 = buildSymbol("z0", Types.E);
			val newVarSymbol2 = buildSymbol("z1", Types.E);
			
			implicationsB.add( 
				bld.application(
					bld.variable("FORALL", Types.ET_T),
					bld.abstraction(newVarSymbol1,
						bld.application(	
							bld.variable("FORALL", Types.ET_T),
							bld.abstraction(newVarSymbol2,						
								bld.application(
									bld.application(bld.variable("IMPLIES",Types.TTT),
										extractIotas(bld.application(bld.application(sourceExp,bld.variable(newVarSymbol1)),bld.variable(newVarSymbol2)),uniquenessConditionsB)),
										extractIotas(bld.application(bld.application(targetExp,bld.variable(newVarSymbol1)),bld.variable(newVarSymbol2)),uniquenessConditionsB)))))));

		} else if (sourceType.equals(TypeET_T_ET)) {
			// we assume that ((et)t)et is a V_2 case. So the argument (which
			// has the type (et)t) is the internal argument of the verb in 
			// a lifted form, and the returned value is a function of type et.
			// Fr example: John kissed Mary.
			// Mary and John are of type (et)t, kissed is of type ((et)t)et.
			// So 'love' takes 'Mary' as an argument, and returns a function of type
			// et that John takes as an argument.
			// Say that we have a subsumption between 'kissed' and 'touched', we 
			// will generate a formula that ends up as:
			// for all x (for all y (kissed(x,y) -> touched(x,y)))
			
			// Note that since we are dealing with lifted entities, it is a bit 
			// more work to saturate the arguments in order to reach the above
			// logical formula.
			// For example, in the case of "John pushed X" --> "John moved X".
			// 'pushed' is described as: 
			// \P0:(et)t.(\x0:e.(P0:(et)t (\x1:e.((pushed:eet x1:e) x0:e))))
			
			// two symbols for the universal quantification
			val newVarSymbol1 = buildSymbol("z0", Types.E);
			val newVarSymbol2 = buildSymbol("z1", Types.E);
			
			// defining an (et)t predicate to saturate the first argument.
			// this results in a lifted entity
			val newPredSymbol = buildSymbol("A", Types.ET);
			val liftedEntity = bld.abstraction(newPredSymbol,
				bld.application(bld.variable(newPredSymbol),bld.variable(newVarSymbol1)));
			
			implicationsB.add( 
				bld.application(
					bld.variable("FORALL", Types.ET_T),
					bld.abstraction(newVarSymbol1,
						bld.application(	
							bld.variable("FORALL", Types.ET_T),
							bld.abstraction(newVarSymbol2,						
								bld.application(
									bld.application(bld.variable("IMPLIES",Types.TTT),
										extractIotas(bld.application(
											bld.application(sourceExp,liftedEntity),
											bld.variable(newVarSymbol2)),uniquenessConditionsB)),
										extractIotas(bld.application(
											bld.application(targetExp,liftedEntity),
											bld.variable(newVarSymbol2)),uniquenessConditionsB)))))));

		} else if (sourceType.equals(Types.ET_T)){
			// We assume that this is a subsumption between two lifted entities.
			// For example: \A0:et.(A0:et Virginia_Wolf:e) and \A0:et.(A0:et Wolf:e)
			// We create an equation between the two entities.
		
			// defining an (et)t predicate to saturate the first argument.
			// this results in a lifted entity
			
			Symbol newMainVar = buildSymbol("z0", Types.E);
			Symbol newArgVar = buildSymbol("z1", Types.E);
			
			val eqNewVar = bld.abstraction(newArgVar, 
								bld.application(	
									bld.application(
										bld.variable("EQ", Types.EET),
										bld.variable(newMainVar)),
									bld.variable(newArgVar)));
									
			implicationsB.add( 
				bld.application(
					bld.variable("EXISTS", Types.ET_T),
					bld.abstraction(newMainVar,
						bld.application(
							bld.application(bld.variable("AND",Types.TTT),
								extractIotas(bld.application(sourceExp,eqNewVar),uniquenessConditionsB)),
								extractIotas(bld.application(targetExp,eqNewVar),uniquenessConditionsB)))));
			
		} else if (sourceType.equals(Types.ET_ET)){
			// We assume that this is a subsumption between two restrictive modifiers.
			// For example: 'last' and 'former'.
			// We loop over all predicates of type et and add corresponding 
			// implications.
			val domain = ImmutableSet.copyOf(stl.domainOf(Types.ET, context));
			for (val pred : domain) {
				//if (pred.accept(isConstant)) {
				//	System.err.println("constant: " + stl.format(pred));
					
					val predExp = stl.fromDeBruijn(pred);
					val newVarSymbol1 = buildSymbol("z0", Types.E);
					
					val newImp = 
							bld.application(
								bld.variable("FORALL", Types.ET_T),
								bld.abstraction(newVarSymbol1,						
									bld.application(
										bld.application(bld.variable("IMPLIES",Types.TTT),
											extractIotas(bld.application(
												bld.application(sourceExp,predExp),
												bld.variable(newVarSymbol1)),uniquenessConditionsB)),
											extractIotas(bld.application(
												bld.application(targetExp,predExp),
												bld.variable(newVarSymbol1)),uniquenessConditionsB))));
					
					implicationsB.add(newImp);
			//	} else {
			//		System.err.println("ignoring: " + stl.format(pred));
			//	}
			}
		} else {
			throw new UnsupportedImplication(stl.format(sourceType));
		}
	
		val implications = implicationsB.build();
		
		if (implications.size()>0) {
			for (val implication : implications) {
				if (debugMode) {
					System.err.println("Generated implication: " + stl.format(implication));
				}
			}
		}
		
		return implications;
	}

	static Symbol buildSymbol(final String name, final Type type) {
		return new Symbol() {
			@Override public String getName() { return name; }
			@Override public Type getType() { return type; }
		};
	}
	
}
