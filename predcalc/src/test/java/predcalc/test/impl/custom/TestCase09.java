package predcalc.test.impl.custom;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCase09 extends ATestCase {

	// This tests a noun compounds case: 
	// T: [man [wolf dragon]], H: [man wolf]
	
	@Test
	public final void createTestCase() throws Exception {
		runTest("Test09",createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = 		betaReduce("(\\A0:et.(A0:et jan:e)) ((\\P0:(et)t.(\\x0:e.(P0:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A1:et.(\\A2:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A1:et x2:e)) (A2:et x2:e)))))) ((\\A3:et.(\\x3:e.((man:(et)et A3:et) x3:e))) ((\\A4:et.(\\x4:e.((wolf:(et)et A4:et) x4:e))) dragon:et))))");
		String hypothesis = betaReduce("(\\A0:et.(A0:et jan:e)) ((\\P0:(et)t.(\\x0:e.(P0:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A1:et.(\\A2:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A1:et x2:e)) (A2:et x2:e)))))) ((\\A3:et.(\\x3:e.((man:(et)et A3:et) x3:e))) wolf:et)))");

		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"",
				"exists x0 (man_wolf_dragon(x0) & x0=jan).",
				"exists x0 (man_wolf(x0) & x0=jan).");
		return new ITestData(text, hypothesis, epc);
	}

}
