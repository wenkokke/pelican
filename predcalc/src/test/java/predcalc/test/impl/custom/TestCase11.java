package predcalc.test.impl.custom;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCase11 extends ATestCase {

	// This tests a noun compounds case: 
	// T: [[[man wolf][dragon wizard]] [coacher zipper]], H: [man [wolf dragon]]
	// This covers the compounds: NC1, NC2 and NC3 

	@Test
	public final void createTestCase() throws Exception {
		runTest("Test11",createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = 		betaReduce("(\\A0:et.(A0:et jan:e)) ((\\P0:(et)t.(\\x0:e.(P0:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A1:et.(\\A2:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A1:et x2:e)) (A2:et x2:e)))))) ((((\\A3:et.(\\A4:et.(\\A5:et.(\\x3:e.((((man:(et)(et)(et)et A3:et) A4:et) A5:et) x3:e))))) wolf:et) ((\\A6:et.(\\x4:e.((dragon:(et)et A6:et) x4:e))) wizard:et)) ((\\A7:et.(\\x5:e.((coacher:(et)et A7:et) x5:e))) zipper:et))))");
		String hypothesis = betaReduce("(\\A0:et.(A0:et jan:e)) ((\\P0:(et)t.(\\x0:e.(P0:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A1:et.(\\A2:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A1:et x2:e)) (A2:et x2:e)))))) (((\\A3:et.(\\A4:et.(\\x3:e.(((man:(et)(et)et A3:et) A4:et) x3:e)))) wolf:et) dragon:et)))");

		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"",
				"exists x0 (man_wolf_dragon_wizard_coacher_zipper(x0) & x0=jan).",
				"exists x0 (man_wolf_dragon(x0) & x0=jan).");
		return new ITestData(text, hypothesis, epc);
	}

}
