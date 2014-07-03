package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestPragmatics extends AbsPipelineTest {

	@Test
	public final void jan01() throws Exception {
					  
		// create the vocabulary for the text;
		val t01_the       = word("THE", "the",1);
		val t02_student   = word("N", "student",2);
		val t03_who       = word("WHO_A", "who",3);
		val t04_is        = word("IS", "is",4);
		val t05_jan       = word("NP", "jan",5);
		val t06_sat       = word("V_1", "sat",6);

		// create the vocabulary for the hypothesis;
		val t02_professor = word("N", "professor",1);

		// create the tree structure for the text;
		val tt = _(_(_(t03_who, _(t04_is , t05_jan,7),8) , _(t01_the, t02_student,9),10) , t06_sat,11);

		// create the tree structure for the hypothesis;
		val th = _(_(_(t03_who, _(t04_is , t05_jan,2),3) , _(t01_the, t02_professor,4),5) , t06_sat,6);

		// return the new entailment;
		assertNoProof(tt,th);
	}
}
