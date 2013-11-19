package predcalc.test.impl.custom;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCase17 extends ATestCase {

	// Test case for:
	// T: [[[[Jan Ferrer] Smith] Spector] David] - covers NPC_4
	// H: [[Jan [Spector Smith]] [David Ferrer]] - covers NPC_1, NPC_2

	
	@Test
	public final void createTestCase() throws Exception {
		runTest("Test17",createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = 		betaReduce("(((((\\P0:(et)t.(\\P1:(et)t.(\\P2:(et)t.(\\P3:(et)t.(\\A0:et.(A0:et ((((Jan:((et)t)((et)t)((et)t)((et)t)e P0:(et)t) P1:(et)t) P2:(et)t) P3:(et)t))))))) (\\A1:et.(A1:et Ferrer:e))) (\\A2:et.(A2:et Smith:e))) (\\A3:et.(A3:et Spector:e))) (\\A4:et.(A4:et David:e))) ((\\P4:(et)t.(\\x0:e.(P4:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A5:et.(\\A6:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A5:et x2:e)) (A6:et x2:e)))))) man:et))");
		String hypothesis = betaReduce("(((\\P0:(et)t.(\\P1:(et)t.(\\A0:et.(A0:et ((Jan:((et)t)((et)t)e P0:(et)t) P1:(et)t))))) ((\\P2:(et)t.(\\A1:et.(A1:et (Spector:((et)t)e P2:(et)t)))) (\\A2:et.(A2:et Smith:e)))) ((\\P3:(et)t.(\\A3:et.(A3:et (David:((et)t)e P3:(et)t)))) (\\A4:et.(A4:et Ferrer:e)))) ((\\P4:(et)t.(\\x0:e.(P4:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A5:et.(\\A6:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A5:et x2:e)) (A6:et x2:e)))))) man:et))");
		
		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"",
				"exists x0 (man(x0) & x0=Jan_Ferrer_Smith_Spector_David).",
				"exists x0 (man(x0) & x0=Jan_Spector_Smith_David_Ferrer).");
		return new ITestData(text, hypothesis, epc);
	}

}
