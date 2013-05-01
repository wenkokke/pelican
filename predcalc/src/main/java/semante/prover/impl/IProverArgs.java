package semante.prover.impl;

import semante.prover.ProverArgs;

public class IProverArgs implements ProverArgs {

	private static final long serialVersionUID = -2717523929224877056L;

	private String textFormula;
	private String hypothesisFormula;
	private ResultType resultType;
	
	public IProverArgs(String textFormula, String hypothesisFormula) {
		super();
		this.textFormula = textFormula;
		this.hypothesisFormula = hypothesisFormula;
	}

	public IProverArgs() {
	}

	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	public String getTextFormula() {
		return textFormula;
	}

	public String getHypothesisFormula() {
		return hypothesisFormula;
	}

}
