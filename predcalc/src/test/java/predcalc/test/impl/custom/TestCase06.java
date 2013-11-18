package predcalc.test.impl.custom;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCase06 extends ATestCase {

	@Test
	public final void createTestCase() throws Exception {
		runTest("Test02",createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = "EXISTS:(et)t (\\x0:e.((AND:ttt (Korean:et x0:e)) ((AND:ttt ((ate:eet x0:e) (IOTA:(et)e eggs:et))) ((AND:ttt ((ate:eet (IOTA:(et)e meatballs:et)) (x0:e))) ((quickly:(et)et (\\x1:e.((ate:eet (IOTA:(et)e meatballs:et)) (x1:e)))) x0:e)))))";
		String hypothesis = "EXISTS:(et)t (\\x0:e.((AND:ttt (Korean:et x0:e)) ((AND:ttt ((ate:eet x0:e) (IOTA:(et)e meatballs:et))) ((AND:ttt ((ate:eet (IOTA:(et)e eggs:et)) (x0:e))) ((quickly:(et)et (\\x1:e.((ate:eet (IOTA:(et)e eggs:et)) (x1:e)))) x0:e)))))";
		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"all x0 (eggs(x0) <-> x0=c1).\n" +
				"all x0 (meatballs(x0) <-> x0=c2).\n" +
				"all x0 (meatballs(x0) <-> x0=c3).\n" +
				"all x0 (eggs(x0) <-> x0=c4).",
				"exists x0 (korean(x0) & (ate(x0, c1) & (ate(c2, x0) & quickly_ate(c2, x0)))).",
				"exists x0 (korean(x0) & (ate(x0, c3) & (ate(c4, x0) & quickly_ate(c4, x0)))).");
		return new ITestData(text, hypothesis, epc);
	}

}
