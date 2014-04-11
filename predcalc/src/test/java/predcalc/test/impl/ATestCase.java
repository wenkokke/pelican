package predcalc.test.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import lambdacalc.STL;

import org.junit.Before;

import predcalc.Lambda2Pred;
import predcalc.PredCalc;
import predcalc.impl.ILambda2Pred;
import predcalc.impl.IPredCalc;
import predcalc.test.TestData;
import semante.prover.Prover;
import semante.prover.impl.IProver;
import semante.settings.Settings;
import semante.settings.impl.ISettings;

public abstract class ATestCase {

	protected STL 			lcalc;
	protected PredCalc 		pcalc;
	protected Lambda2Pred 	stl2p;
	protected Settings		settings;
	protected Prover		prover;

	@Before
	public final void setUpPipeline() throws IOException {
		lcalc    = new STL();
		pcalc    = new IPredCalc();
		stl2p    = new ILambda2Pred(pcalc, lcalc);
		settings = new ISettings();
		prover   = new IProver(settings, pcalc);

	}
	
	protected String betaReduce(String in) {
		return lcalc.format(lcalc.betaReduce(lcalc.parse(in)));
	}
	
	protected void runTest(String testName, TestData testData) {
		String proverInput = prover.toProverInput(
				stl2p.smash(lcalc.parse(testData.getReducedText())),
				stl2p.smash(lcalc.parse(testData.getReducedHypothesis())),"");
		String expectedPredCalc = 
				"formulas(assumptions).\n" + 
				"% Pragmatics:\n" +
				(!testData.getExpectedPredCalc().getPragmatics().isEmpty() ?  testData.getExpectedPredCalc().getPragmatics() + "\n" : "") +
				"\n"+
				"% Semantics:\n" +
				testData.getExpectedPredCalc().getSemantics() + "\n" +
				"\n" + 
				"end_of_list.\n" + 
				"\n" + 
				"formulas(goals).\n" +
				testData.getExpectedPredCalc().getGoals() + "\n" +
				"end_of_list.";
		if (!proverInput.equals(expectedPredCalc)) {
			System.out.println("Result: [\n" + proverInput + "\n]");
			System.out.println("Expectation: [\n" + expectedPredCalc + "\n]");
		}
		assertEquals(proverInput,expectedPredCalc);
	}
}
