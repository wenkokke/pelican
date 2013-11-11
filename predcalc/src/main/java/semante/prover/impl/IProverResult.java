package semante.prover.impl;

import semante.prover.ProverOutput;
import semante.prover.ProverResult;

public class IProverResult implements ProverResult {

	private String proverInput;
	private ProverOutput proverOutputCMF;
	private ProverOutput proverOutputPF;

	public IProverResult(String proverInput, ProverOutput proverOutputPF, ProverOutput proverOutputCMF) {
		super();
		this.proverInput = proverInput;
		this.proverOutputPF = proverOutputPF;
		this.proverOutputCMF = proverOutputCMF;
	}
	
	@Override
	public String getProverInput() {
		return proverInput;
	}

	@Override
	public ProverOutput getProverOutputPF() {
		return proverOutputPF;
	}

	@Override
	public ProverOutput getProverOutputCMF() {
		return proverOutputCMF;
	}
	
}
