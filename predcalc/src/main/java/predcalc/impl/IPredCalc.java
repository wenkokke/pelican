package predcalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;

import predcalc.ExprPrinter;
import predcalc.PredCalc;

import lombok.Delegate;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.prover.Prover;
import semante.prover.impl.IProver;
import semante.settings.impl.ISettings;

@FieldDefaults(makeFinal=true, level=PRIVATE)
public class IPredCalc implements PredCalc {
	
	@Delegate ExprPrinter    exprPrinter; 
	@Delegate Prover         prover; 
	
	public IPredCalc() throws FileNotFoundException {
		val settings = new ISettings();
		exprPrinter  = new IExprPrinter();
		prover       = new IProver(settings,this);
	}
}
