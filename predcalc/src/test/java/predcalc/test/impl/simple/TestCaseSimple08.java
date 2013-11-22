package predcalc.test.impl.simple;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCaseSimple08 extends ATestCase {

	@Test
	public final void createTestCase() throws Exception {
		runTest(Class.class.getSimpleName(),createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = 		"EXISTS:(et)t (\\x0:e.((AND:ttt ((AND:ttt ((AND:ttt (american:et x0:e)) ((cuban:(et)et american:et) x0:e))) ((AND:ttt ((AND:ttt ((loves:eet Mary:e) x0:e)) (accused:et x0:e))) (((of:e(et)et espionage:e) (\\x1:e.((AND:ttt ((loves:eet Mary:e) x1:e)) (accused:et x1:e)))) x0:e)))) (pleads_innocent:et x0:e)))";
		String hypothesis = "EXISTS:(et)t (\\x0:e.((AND:ttt (american:et x0:e)) ((AND:ttt (accused:et x0:e)) (((of:e(et)et espionage:e) accused:et) x0:e))))";
		
		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"",
				"exists x0 (((american(x0) & cuban_american(x0)) & ((loves(Mary, x0) & accused(x0)) & of_loves_accused(Mary, espionage, x0))) & pleads_innocent(x0)).",
				"exists x0 (american(x0) & (accused(x0) & of_accused(espionage, x0))).");
		return new ITestData(text, hypothesis, epc);
	}

}
