package semante.pipeline.impl;

import semante.pipeline.Result;
import semante.pipeline.ResultHandler;
import semante.prover.ProverResult;

public final class ISimpleResultHandler<ID> implements ResultHandler<ID> {

	boolean finalResultSet = false;
	Result<ID> finalResult = null;

	public void setFinalResult(Result<ID> result) {
		if (!finalResultSet) {
			finalResult = result;
			finalResultSet = true;
		}
	}

	@Override
	public void handle(ProverResult result) {
		switch (result.getProverOutputPF().getResultType()) {
		case ProofFound:
			setFinalResult(new IResult$Proof<ID>());
			break;
		case NoProofCanBeFound:
			break;
		case Error:
		case Interuppted:
		case NotRun:
		case Unset:
		default:
			// TODO convert this to returning IResult$Error()
			System.err.println("Unexpected error in prover execution: "
					+ result.getProverOutputPF().getOutput());
		}
	}

	@Override
	public Result<ID> getFinalResult() {
		return finalResultSet ? finalResult : new IResult$Unknown<ID>();
	}

	@Override
	public boolean isFinalResultSet() {
		return finalResultSet;
	}

}
