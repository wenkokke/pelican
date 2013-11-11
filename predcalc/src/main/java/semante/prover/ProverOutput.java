package semante.prover;

public interface ProverOutput {

	public enum ResultType {ProofFound,NoProofCanBeFound,Interuppted,Error,NotRun,Unset} 
	
	String getOutput();
	
	ResultType getResultType(); 
}
