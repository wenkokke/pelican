package predcalc.test.impl.simple;

import org.junit.Test;

import predcalc.test.TestData;
import predcalc.test.TestData.ExpectedPredCalc;
import predcalc.test.impl.ATestCase;
import predcalc.test.impl.ITestData;

public class TestCaseSimple07 extends ATestCase {

	@Test
	public final void createTestCase() throws Exception {
		runTest(Class.class.getSimpleName(),createTest());
	}
	
	public final TestData createTest() throws Exception {
		String text = "EXISTS:(et)t (\\x0:e.((AND:ttt ((AND:ttt ((AND:ttt (man:et x0:e)) ((AND:ttt ((AND:ttt ((adores:eet Jane:e) x0:e)) ((loves:eet (IOTA:(et)e girl:et)) x0:e))) ((extremely:(et)et (\\x1:e.((AND:ttt ((adores:eet Jane:e) x1:e)) ((loves:eet (IOTA:(et)e girl:et)) x1:e)))) x0:e)))) ((nice:(et)et (\\x2:e.((AND:ttt (man:et x2:e)) ((AND:ttt ((AND:ttt ((adores:eet Jane:e) x2:e)) ((loves:eet (IOTA:(et)e girl:et)) x2:e))) ((extremely:(et)et (\\x3:e.((AND:ttt ((adores:eet Jane:e) x3:e)) ((loves:eet (IOTA:(et)e girl:et)) x3:e)))) x2:e))))) x0:e))) ((EQ:eet x0:e) John:e)))";
		String hypothesis = "EXISTS:(et)t (\\x0:e.((AND:ttt ((AND:ttt ((AND:ttt (man:et x0:e)) ((tall:(et)et man:et) x0:e))) ((fat:(et)et (\\x1:e.((AND:ttt (man:et x1:e)) ((tall:(et)et man:et) x1:e)))) x0:e))) ((EQ:eet x0:e) John:e)))";
		
		ExpectedPredCalc epc = new ITestData.IExpectedPredCalc(
				"all x0 (girl(x0) <-> x0=c1).",
				"exists x0 (((man(x0) & ((adores(Jane, x0) & loves(c1, x0)) & extremely_adores_loves(Jane, c1, x0))) & nice_man_extremely_adores_loves(Jane, c1, x0)) & x0=John).",
				"exists x0 (((man(x0) & tall_man(x0)) & fat_tall_man(x0)) & x0=John).");
		return new ITestData(text, hypothesis, epc);
	}

}
