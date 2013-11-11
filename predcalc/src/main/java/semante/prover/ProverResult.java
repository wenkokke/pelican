package semante.prover;

public interface ProverResult {

	String getProverInput();
	
	ProverOutput getProverOutputPF();
	ProverOutput getProverOutputCMF();

}
