package predcalc.test.impl.custom;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCase13 extends ATestCase {

	@Test
	public final void createTestCase() throws Exception {
		runTest("Test13",createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = 		betaReduce("((\\P0:(et)t.(\\A0:et.(A0:et (Jan:((et)t)e P0:(et)t)))) (\\A1:et.(A1:et David:e))) ((\\P1:(et)t.(\\x0:e.(P1:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A2:et.(\\A3:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A2:et x2:e)) (A3:et x2:e)))))) man:et))");
		String hypothesis = betaReduce("(\\A0:et.(A0:et Jan:e)) ((\\P0:(et)t.(\\x0:e.(P0:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A1:et.(\\A2:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A1:et x2:e)) (A2:et x2:e)))))) man:et))");

		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"",
				"exists x0 (man(x0) & x0=Jan_David).",
				"exists x0 (man(x0) & x0=Jan).");
		return new ITestData(text, hypothesis, epc);
	}

}
