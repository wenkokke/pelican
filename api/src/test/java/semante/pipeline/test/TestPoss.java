package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestPoss extends AbsPipelineTest {

	@Test
	public final void poss01() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP", "John");
		val t01_s = word("GEN", "'s");
		val t02_wife = word("N_2", "wife");
		val t03_adores = word("V_2", "adores");
		val t04_paul = word("NP", "Paul");

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE", "The");
		val h01_wife = word("N_2", "wife");
		val h02_of = word("OF", "of");
		val h03_john = word("NP", "John");
		val h04_adores = word("V_2", "adores");
		val h05_paul = word("NP", "Paul");

		// create the tree structure for the text;
		val tt = _(_(_(t00_john, t01_s), t02_wife), _(t03_adores, t04_paul));

		// create the tree structure for the hypothesis;
		val th = _(_(h00_the, _(h01_wife, _(h02_of, h03_john))),
				_(h04_adores, h05_paul));

		assertProof(tt, th);
	}

	@Test
	public final void poss02() throws Exception {

		// create the vocabulary for the text;
		val t00_paul = word("NP", "Paul");
		val t01_hates = word("V_2", "hates");
		val t02_john = word("NP", "John");
		val t03_s = word("GEN", "'s");
		val t04_wife = word("N_2", "wife");

		// create the vocabulary for the hypothesis;
		val h00_paul = word("NP", "Paul");
		val h01_hates = word("V_2", "hates");
		val h02_the = word("THE", "the");
		val h03_wife = word("N_2", "wife");
		val h04_of = word("OF", "of");
		val h05_john = word("NP", "John");

		// create the tree structure for the text;
		val tt = _(t00_paul, _(t01_hates, _(_(t02_john, t03_s), t04_wife)));

		// create the tree structure for the hypothesis;
		val th = _(h00_paul,
				_(h01_hates, _(h02_the, _(h03_wife, _(h04_of, h05_john)))));

		assertProof(tt, th);
	}

	@Test
	public final void poss03() throws Exception {

		// create the vocabulary for the text;
		val t00_paul = word("NP", "Paul");
		val t01_hates = word("V_2", "hates");
		val t02_john = word("NP", "John");
		val t03_s = word("GEN", "'s");
		val t04_wife = word("N_2", "wife");
		val t05_app = word("WHO_A", "APP");
		val t06_yoko = word("$NPC_1$", "Yoko");
		val t07_ono = word("NP", "Ono");

		// create the vocabulary for the hypothesis;
		val h00_paul = word("NP", "Paul");
		val h01_hates = word("V_2", "hates");
		val h02_yoko = word("$NPC_1$", "Yoko");
		val h03_ono = word("NP", "Ono");

		// create the tree structure for the text;
		val tt = _(
				t00_paul,
				_(t01_hates,
						_(_(_(t02_john, t03_s), t04_wife),
								_(t05_app, _(t06_yoko, t07_ono)))));

		// create the tree structure for the hypothesis;
		val th = _(h00_paul, _(h01_hates, _(h02_yoko, h03_ono)));

		assertProof(tt, th);
	}

	@Test
	public final void poss04() throws Exception {

		// create the vocabulary for the text;
		val t00_paul = word("NP_D", "Paul");
		val t01_hates = word("V_2", "hates");
		val t02_john = word("NP_D", "John");
		val t03_s = word("GEN", "'s");
		val t04_wife = word("N_2", "wife");
		val t05_app = word("WHO_A", "APP");
		val t06_yoko = word("$NPC_1$", "Yoko");
		val t07_ono = word("NP_D", "Ono");

		// create the vocabulary for the hypothesis;
		val h00_paul = word("NP_D", "Paul");
		val h01_hates = word("V_2", "hates");
		val h02_the = word("THE", "the");
		val h03_wife = word("N_2", "wife");
		val h04_of = word("OF", "of");
		val h05_john = word("NP_D", "John");

		// create the tree structure for the text;
		val tt = _(
				t00_paul,
				_(t01_hates,
						_(_(_(t02_john, t03_s), t04_wife),
								_(t05_app, _(t06_yoko, t07_ono)))));

		// create the tree structure for the hypothesis;
		val th = _(h00_paul,
				_(h01_hates, _(h02_the, _(h03_wife, _(h04_of, h05_john)))));

		assertProof(tt, th);
	}

	@Test
	public final void poss05() throws Exception {

		// create the vocabulary for the text;
		val t00_sue = word("NP", "Paul");
		val t01_hates = word("V_2", "hates");
		val t02_john = word("NP", "John");
		val t03_s = word("GEN", "'s");
		val t04_dog = word("N", "dog");

		// create the vocabulary for the hypothesis;
		val h00_paul = word("NP", "Paul");
		val h01_hates = word("V_2", "hates");
		val h02_the = word("THE", "the");
		val h03_dog = word("N", "dog");
		val h04_of = word("P_R", "of");
		val h05_john = word("NP", "John");

		// create the tree structure for the text;
		val tt = _(t00_sue, _(t01_hates, _(_(t02_john, t03_s), t04_dog)));

		// create the tree structure for the hypothesis;
		val th = _(h00_paul,
				_(h01_hates, _(h02_the, _(h03_dog, _(h04_of, h05_john)))));

		assertProof(tt, th);
	}
}
