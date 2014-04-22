package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestFeats extends AbsPipelineTest {

	@Test
	public final void feat01() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan");
		val t01_is = word("IS", "is");
		val t02_a = word("A", "a");
		val t03_man = word("N_1", "man");
		val t04_from = word("P_I", "from");
		val t05_Brasil = word("NP", "Brasil");

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan");
		val h01_is = word("IS", "is");
		val h02_a = word("A", "a");
		val h03_man = word("N_1", "man");

		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_man, _(t04_from, t05_Brasil)))));

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, h03_man)));

		assertProof(tt, th);
	}

	@Test
	public final void feat02() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan");
		val t01_is = word("IS", "is");
		val t02_a = word("A", "a");
		val t03_man = word("N_1", "man");
		val t04_from = word("P_I", "from");
		val t05_Brasil = word("NP", "Brasil");

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan");
		val h01_is = word("IS", "is");
		val h02_from = word("P_I", "from");
		val h03_Brasil = word("NP", "Brasil");

		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_man, _(t04_from, t05_Brasil)))));

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_from, h03_Brasil)));

		assertProof(tt, th);
	}

	@Test
	public final void feat03() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan");
		val t01_is = word("IS", "is");
		val t02_a = word("A", "a");
		val t03_tall = word("MOD_R", "tall");
		val t04_man = word("N_1", "man");
		val t05_from = word("P_I", "from");
		val t06_Brasil = word("NP", "Brasil");

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan");
		val h01_is = word("IS", "is");
		val h02_from = word("P_I", "from");
		val h03_Brasil = word("NP", "Brasil");

		val tt = _(
				t00_jan,
				_(t01_is,
						_(t02_a,
								_(_(t03_tall, t04_man), _(t05_from, t06_Brasil)))));

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_from, h03_Brasil)));

		assertProof(tt, th);
	}

	@Test
	public final void feat04() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan");
		val t01_is = word("IS", "is");
		val t02_a = word("A", "a");
		val t03_tall = word("MOD_R", "tall");
		val t04_man = word("N_1", "man");
		val t05_from = word("P_I", "from");
		val t06_Brasil = word("NP", "Brasil");

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan");
		val h01_is = word("IS", "is");
		val h02_from = word("P_I", "from");
		val h03_Brasil = word("NP", "Brasil");

		val tt = _(
				t00_jan,
				_(t01_is,
						_(t02_a,
								_(t03_tall, _(t04_man, _(t05_from, t06_Brasil))))));

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_from, h03_Brasil)));

		assertProof(tt, th);
	}

}
