package predcalc.test.impl.nc;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCaseNc04 extends ATestCase {

	// This tests a noun compounds case: 
	// T: [[[[king man] wolf][dragon wizard]] [coacher zipper]], H: [man [wolf dragon]]
	// This covers the compounds: NC1, NC2 and NC4 
	
	@Test
	public final void createTestCase() throws Exception {
		runTest(Class.class.getSimpleName(),createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = 		betaReduce("(\\A0:et.(A0:et jan:e)) ((\\P0:(et)t.(\\x0:e.(P0:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A1:et.(\\A2:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A1:et x2:e)) (A2:et x2:e)))))) (((((\\A3:et.(\\A4:et.(\\A5:et.(\\A6:et.(\\x3:e.(((((king:(et)(et)(et)(et)et A3:et) A4:et) A5:et) A6:et) x3:e)))))) man:et) wolf:et) ((\\A7:et.(\\x4:e.((dragon:(et)et A7:et) x4:e))) wizard:et)) ((\\A8:et.(\\x5:e.((coacher:(et)et A8:et) x5:e))) zipper:et))))");
		String hypothesis = betaReduce("(\\A0:et.(A0:et jan:e)) ((\\P0:(et)t.(\\x0:e.(P0:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) ((\\A1:et.(\\A2:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A1:et x2:e)) (A2:et x2:e)))))) (((\\A3:et.(\\A4:et.(\\x3:e.(((man:(et)(et)et A3:et) A4:et) x3:e)))) wolf:et) dragon:et)))");

		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"",
				"exists x0 (king_man_wolf_dragon_wizard_coacher_zipper(x0) & x0=jan).",
				"exists x0 (man_wolf_dragon(x0) & x0=jan).");
		return new ITestData(text, hypothesis, epc);
	}

}
