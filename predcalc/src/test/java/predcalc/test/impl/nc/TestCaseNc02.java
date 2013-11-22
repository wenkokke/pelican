package predcalc.test.impl.nc;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCaseNc02 extends ATestCase {

	// This tests a noun compounds case: 
	// T: [[[man wolf][dragon wizard]] coacher], H: [man wolf]
	
	@Test
	public final void createTestCase() throws Exception {
		runTest(Class.class.getSimpleName(),createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = 		betaReduce("((\\P0:(et)t.(\\P1:(et)t.(P0:(et)t (\\x0:e.(P1:(et)t (\\x1:e.((EQ:eet x0:e) x1:e))))))) ((\\A0:et.(\\A1:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A0:et x2:e)) (A1:et x2:e)))))) ((((\\A2:et.(\\A3:et.(\\A4:et.(\\x3:e.((((man:(et)(et)(et)et A2:et) A3:et) A4:et) x3:e))))) wolf:et) ((\\A5:et.(\\x4:e.((dragon:(et)et A5:et) x4:e))) wizard:et)) coucher:et))) (\\A6:et.(A6:et jan:e))");
		String hypothesis = betaReduce("((\\P0:(et)t.(\\P1:(et)t.(P0:(et)t (\\x0:e.(P1:(et)t (\\x1:e.((EQ:eet x0:e) x1:e))))))) ((\\A0:et.(\\A1:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A0:et x2:e)) (A1:et x2:e)))))) ((\\A2:et.(\\x3:e.((man:(et)et A2:et) x3:e))) wolf:et))) (\\A3:et.(A3:et jan:e))");

		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"",
				"exists x0 (man_wolf_dragon_wizard_coucher(x0) & x0=jan).",
				"exists x0 (man_wolf(x0) & x0=jan).");
		return new ITestData(text, hypothesis, epc);
	}

}
