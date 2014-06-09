package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestPragmatics extends AbsPipelineTest {

	@Test
	@SuppressWarnings("unchecked")
	public final void jan01() throws Exception {

		// create the vocabulary for the text;
		val t01_the       = word("THE", "the");
		val t02_student   = word("N", "student");
		val t03_who       = word("WHO_A", "who");
		val t04_is        = word("IS", "is");
		val t05_jan       = word("NP", "jan");
		val t06_sat       = word("V_1", "sat");

		// create the vocabulary for the hypothesis;
		val t02_professor = word("N", "professor");

		// create the tree structure for the text;
		val tt = _(_(_(t03_who, _(t04_is , t05_jan)) , _(t01_the, t02_student)) , t06_sat);

		// create the tree structure for the hypothesis;
		val th = _(_(_(t03_who, _(t04_is , t05_jan)) , _(t01_the, t02_professor)) , t06_sat);

		// return the new entailment;
		assertProof(tt, th);
	}
}
