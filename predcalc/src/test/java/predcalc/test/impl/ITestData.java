package predcalc.test.impl;

import predcalc.test.TestData;

public class ITestData implements TestData {

	private String text;
	private String hypothesis;
	private ExpectedPredCalc predcalc;
	
	public ITestData(String text, String hypothesis, ExpectedPredCalc predcalc) {
		super();
		this.text = text;
		this.hypothesis = hypothesis;
		this.predcalc = predcalc;
	}

	@Override
	public String getReducedText() {
		return text;
	}

	@Override
	public String getReducedHypothesis() {
		return hypothesis;
	}

	@Override
	public ExpectedPredCalc getExpectedPredCalc() {
		return predcalc;
	}

	public static class IExpectedPredCalc implements ExpectedPredCalc {

		private String pragmatics;
		private String semantics;
		private String goals;

		public IExpectedPredCalc(String pragmatics, String semantics,
				String goals) {
			super();
			this.pragmatics = pragmatics;
			this.semantics = semantics;
			this.goals = goals;
		}

		@Override
		public String getPragmatics() {
			return pragmatics;
		}

		@Override
		public String getSemantics() {
			return semantics;
		}

		@Override
		public String getGoals() {
			return goals;
		}
		
	}
	
}
