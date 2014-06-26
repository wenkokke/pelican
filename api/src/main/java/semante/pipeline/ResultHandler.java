package semante.pipeline;

import semante.prover.ProverResult;

public interface ResultHandler<ID> {
	Result<ID> getFinalResult();

	boolean isFinalResultSet();

	void handle(ProverResult result);
}
