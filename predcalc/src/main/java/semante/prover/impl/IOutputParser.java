package semante.prover.impl;

import lombok.extern.slf4j.Slf4j;
import semante.prover.OutputParser;
import semante.prover.ProverArgs.ResultType;

public class IOutputParser {

	@Slf4j
	public static class IProver9OutputRecognizer implements OutputParser {

		@Override
		public ResultType recognizeOutput(String output) {
			log.trace("Prover9 output=[" + output + "]");
			ResultType res = ResultType.Undetermined;
			if (output.matches(".*Exiting with [0-9]+ proofs?.*")) {
				res = ResultType.ProofFound;
			} else if (output.contains("Prover9") && output.contains("Exiting with failure.")) {
				res = ResultType.NoProofCanBeFound;
			}
			return res;
		}
	}

	@Slf4j
	public static class IMace4OutputRecognizer implements OutputParser {

		@Override
		public ResultType recognizeOutput(String output) {
			log.trace("Mace4 output=[" + output + "]");
			ResultType res = ResultType.Undetermined;
			if (output.matches(".*Exiting with [0-9]+ models?.*")) {
				res = ResultType.CounterexampleFound;
			} else if (output.contains("Mace4") && output.contains("Exiting with failure.")) {
				res = ResultType.NoCounterexampleCanBeFound;
			}
			return res;
		}
	}

}
