package semante.predcalc.impl;

import static lombok.AccessLevel.PRIVATE;

import java.io.FileNotFoundException;

import lombok.Delegate;
import lombok.val;
import lombok.experimental.FieldDefaults;
import semante.predcalc.ExprPrinter;
import semante.predcalc.PredCalc;
import semante.prover.Prover;
import semante.prover.impl.IProver;
import semante.settings.Settings;
import semante.settings.impl.ISettings;

@FieldDefaults(makeFinal=true, level=PRIVATE)
public class IPredCalc implements PredCalc {
	
	@Delegate ExprPrinter    exprPrinter; 
	@Delegate Prover         prover; 
	
	public IPredCalc() throws FileNotFoundException {
		val settings = new ISettings();
		exprPrinter  = new IExprPrinterProver9();
		prover       = new IProver(settings,this);
	}
	
	public IPredCalc(Settings settings) {
		exprPrinter  = new IExprPrinterProver9();
		prover       = new IProver(settings,this);
	}
	
}
