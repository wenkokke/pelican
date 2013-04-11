package semante.prover;

import semante.prover.ProverArgs.ResultType;


public interface OutputParser {

	ResultType recognizeOutput(String output); 
}
