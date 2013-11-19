package predcalc.test.impl.custom;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCase05 extends ATestCase {

	@Test
	public final void createTestCase() throws Exception {
		runTest("Test05",createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = "(AND:ttt ((loves:eet (IOTA:(et)e girl:et)) John:e)) ((extremely:(et)et (\\x0:e.((loves:eet (IOTA:(et)e girl:et)) x0:e))) John:e)";
		String hypothesis = "EXISTS:(et)t (\\x0:e.((AND:ttt ((AND:ttt ((AND:ttt (man:et x0:e)) ((tall:(et)et man:et) x0:e))) ((fat:(et)et (\\x1:e.((AND:ttt (man:et x1:e)) ((tall:(et)et man:et) x1:e)))) x0:e))) ((EQ:eet x0:e) John:e)))";
		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"all x0 (girl(x0) <-> x0=c1).",
				"(loves(c1, John) & extremely_loves(c1, John)).",
				"exists x0 (((man(x0) & tall_man(x0)) & fat_man_tall_man(x0)) & x0=John).");
		return new ITestData(text, hypothesis, epc);
	}

}
