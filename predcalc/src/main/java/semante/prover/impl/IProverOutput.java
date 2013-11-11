package semante.prover.impl;

import semante.prover.ProverOutput;

public class IProverOutput implements ProverOutput {

	String output;
	ResultType resultType;
	
	public IProverOutput(String output, ResultType resultType) {
		super();
		this.output = output;
		this.resultType = resultType;
	}
	
	public String getOutput() {
		return output;
	}
	
	public ResultType getResultType() {
		return resultType;
	}

}
