package semante.prover;

import java.io.Serializable;

public interface ProverArgs extends Serializable {

	enum ResultType {ProofFound,NoProofCanBeFound,CounterexampleFound,NoCounterexampleCanBeFound,Undetermined} 
	
	String getTextFormula();
	String getHypothesisFormula();
	
	ResultType getResultType();
	
}
