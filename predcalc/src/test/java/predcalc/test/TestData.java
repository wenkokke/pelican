package predcalc.test;


public interface TestData {

	String getReducedText();
	String getReducedHypothesis();
	
	ExpectedPredCalc getExpectedPredCalc();
	
	public interface ExpectedPredCalc {
		String getPragmatics();
		String getSemantics();
		String getGoals();
	}
}
