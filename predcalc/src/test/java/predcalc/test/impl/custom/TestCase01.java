package predcalc.test.impl.custom;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCase01 extends ATestCase {

	@Test
	public final void createTestCase() throws Exception {
		runTest("Test01",createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = "(AND:ttt (ate:et jan:e)) (sat:et jan:e)";
		String hypothesis = "ate:et jan:e";
		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
									"",
									"(ate(jan) & sat(jan)).",
									"ate(jan).");
		return new ITestData(text, hypothesis, epc);
	}

}
