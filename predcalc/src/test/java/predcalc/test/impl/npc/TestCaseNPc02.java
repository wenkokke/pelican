package predcalc.test.impl.npc;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCaseNPc02 extends ATestCase {

	// Test case for:
	// T: [Jan [David Ferrer]] - covers NPC_1
	// H: [[Jan David] Ferrer] - covers NPC_2
	
	@Test
	public final void createTestCase() throws Exception {
		runTest(Class.class.getSimpleName(),createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = 		betaReduce("((\\P0:(et)t.(\\A0:et.(A0:et (Jan:((et)t)e P0:(et)t)))) ((\\P1:(et)t.(\\A1:et.(A1:et (David:((et)t)e P1:(et)t)))) (\\A2:et.(A2:et Ferrer:e)))) ((\\P2:(et)t.(\\x0:e.(P2:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A3:et.(\\A4:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A3:et x2:e)) (A4:et x2:e)))))) man:et))");
		String hypothesis = betaReduce("(((\\P0:(et)t.(\\P1:(et)t.(\\A0:et.(A0:et ((Jan:((et)t)((et)t)e P0:(et)t) P1:(et)t))))) (\\A1:et.(A1:et David:e))) (\\A2:et.(A2:et Ferrer:e))) ((\\P2:(et)t.(\\x0:e.(P2:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A3:et.(\\A4:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A3:et x2:e)) (A4:et x2:e)))))) man:et))");
		
		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"",
				"exists x0 (man(x0) & x0=Jan_David_Ferrer).",
				"exists x0 (man(x0) & x0=Jan_David_Ferrer).");
		return new ITestData(text, hypothesis, epc);
	}

}
