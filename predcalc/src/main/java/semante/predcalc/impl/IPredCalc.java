package semante.predcalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;

import lombok.Delegate;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.lambdacalc.Equality;
import semante.lambdacalc.Type;
import semante.lambdacalc.util.IEqType;
import semante.predcalc.ExprPrinter;
import semante.predcalc.PredCalc;
import semante.prover.Prover;
import semante.prover.impl.IProver;
import semante.settings.impl.ISettings;

@FieldDefaults(makeFinal=true, level=PRIVATE)
public class IPredCalc implements PredCalc {
	
	@Delegate ExprPrinter    exprPrinter; 
	@Delegate Prover         prover; 
	@Delegate Equality<Type> eqType; 
	
	public IPredCalc() throws FileNotFoundException {
		val settings = new ISettings();
		exprPrinter  = new IExprPrinter();
		prover       = new IProver(settings,this);
		eqType       = new IEqType();
	}
}
