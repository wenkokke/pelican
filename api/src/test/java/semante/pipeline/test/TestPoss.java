package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestPoss extends AbsPipelineTest<Integer> {

	@Test
	public final void poss01() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP", "John",1);
		val t01_s = word("GEN", "'s",2);
		val t02_wife = word("N_2", "wife",3);
		val t03_adores = word("V_2", "adores",4);
		val t04_paul = word("NP", "Paul",5);

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE", "The",1);
		val h01_wife = word("N_2", "wife",2);
		val h02_of = word("OF", "of",3);
		val h03_john = word("NP", "John",4);
		val h04_adores = word("V_2", "adores",5);
		val h05_paul = word("NP", "Paul",6);

		// create the tree structure for the text;
		val tt = _(_(_(t00_john, t01_s,6), t02_wife,7), _(t03_adores, t04_paul,8),9);

		// create the tree structure for the hypothesis;
		val th = _(_(h00_the, _(h01_wife, _(h02_of, h03_john,7),8),9),
				_(h04_adores, h05_paul,10),11);

		assertProof(tt, th);
	}

	@Test
	public final void poss02() throws Exception {

		// create the vocabulary for the text;
		val t00_paul = word("NP", "Paul",1);
		val t01_hates = word("V_2", "hates",2);
		val t02_john = word("NP", "John",3);
		val t03_s = word("GEN", "'s",4);
		val t04_wife = word("N_2", "wife",5);

		// create the vocabulary for the hypothesis;
		val h00_paul = word("NP", "Paul",1);
		val h01_hates = word("V_2", "hates",2);
		val h02_the = word("THE", "the",3);
		val h03_wife = word("N_2", "wife",4);
		val h04_of = word("OF", "of",5);
		val h05_john = word("NP", "John",6);

		// create the tree structure for the text;
		val tt = _(t00_paul, _(t01_hates, _(_(t02_john, t03_s,6), t04_wife,7),8),9);

		// create the tree structure for the hypothesis;
		val th = _(h00_paul,
				_(h01_hates, _(h02_the, _(h03_wife, _(h04_of, h05_john,7),8),9),10),11);

		assertProof(tt, th);
	}

	@Test
	public final void poss03() throws Exception {

		// create the vocabulary for the text;
		val t00_paul = word("NP", "Paul",1);
		val t01_hates = word("V_2", "hates",2);
		val t02_john = word("NP", "John",3);
		val t03_s = word("GEN", "'s",4);
		val t04_wife = word("N_2", "wife",5);
		val t05_app = word("WHO_A", "APP",6);
		val t06_yoko = word("$NPC_1$", "Yoko",7);
		val t07_ono = word("NP", "Ono",8);

		// create the vocabulary for the hypothesis;
		val h00_paul = word("NP", "Paul",1);
		val h01_hates = word("V_2", "hates",2);
		val h02_yoko = word("$NPC_1$", "Yoko",3);
		val h03_ono = word("NP", "Ono",4);

		// create the tree structure for the text;
		val tt = _(
				t00_paul,
				_(t01_hates,
						_(_(_(t02_john, t03_s,9), t04_wife,10),
								_(t05_app, _(t06_yoko, t07_ono,11),12),13),14),15);

		// create the tree structure for the hypothesis;
		val th = _(h00_paul, _(h01_hates, _(h02_yoko, h03_ono,5),6),7);

		assertProof(tt, th);
	}

	@Test
	public final void poss04() throws Exception {

		// create the vocabulary for the text;
		val t00_paul = word("NP_D", "Paul",1);
		val t01_hates = word("V_2", "hates",2);
		val t02_john = word("NP_D", "John",3);
		val t03_s = word("GEN", "'s",4);
		val t04_wife = word("N_2", "wife",5);
		val t05_app = word("WHO_A", "APP",6);
		val t06_yoko = word("$NPC_1$", "Yoko",7);
		val t07_ono = word("NP_D", "Ono",8);

		// create the vocabulary for the hypothesis;
		val h00_paul = word("NP_D", "Paul",1);
		val h01_hates = word("V_2", "hates",2);
		val h02_the = word("THE", "the",3);
		val h03_wife = word("N_2", "wife",4);
		val h04_of = word("OF", "of",5);
		val h05_john = word("NP_D", "John",6);

		// create the tree structure for the text;
		val tt = _(
				t00_paul,
				_(t01_hates,
						_(_(_(t02_john, t03_s,9), t04_wife,10),
								_(t05_app, _(t06_yoko, t07_ono,11),12),13),14),15);

		// create the tree structure for the hypothesis;
		val th = _(h00_paul,
				_(h01_hates, _(h02_the, _(h03_wife, _(h04_of, h05_john,7),8),9),10),11);

		assertProof(tt, th);
	}

	@Test
	public final void poss05() throws Exception {

		// create the vocabulary for the text;
		val t00_sue = word("NP", "Paul",1);
		val t01_hates = word("V_2", "hates",2);
		val t02_john = word("NP", "John",3);
		val t03_s = word("GEN", "'s",4);
		val t04_dog = word("N", "dog",5);

		// create the vocabulary for the hypothesis;
		val h00_paul = word("NP", "Paul",1);
		val h01_hates = word("V_2", "hates",2);
		val h02_the = word("THE", "the",3);
		val h03_dog = word("N", "dog",4);
		val h04_of = word("P_R", "of",5);
		val h05_john = word("NP", "John",6);

		// create the tree structure for the text;
		val tt = _(t00_sue, _(t01_hates, _(_(t02_john, t03_s,6), t04_dog,7),8),9);

		// create the tree structure for the hypothesis;
		val th = _(h00_paul,
				_(h01_hates, _(h02_the, _(h03_dog, _(h04_of, h05_john,7),8),9),10),11);

		assertProof(tt, th);
	}
}
