package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;
import semante.pipeline.ResultType;

public final class TestJanPhrases extends AbsPipelineTest {

	@Test
	public final void jan01() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan");
		val t01_sat = word("V_1", "sat");
		val t02_and = word("AND", "and");
		val t03_ate = word("V_1", "ate");

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan");
		val h01_ate = word("V_1", "ate");

		// create the tree structure for the text;
		val tt = _(t00_jan, _(t01_sat, _(t02_and, t03_ate)));

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, h01_ate);

		// return the new entailment;
		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan02() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan");
		val t01_is = word("IS", "is");
		val t02_a = word("A", "a");
		val t03_black = word("MOD_I", "black");
		val t04_dutch = word("MOD_I", "dutch");
		val t05_man = word("N_1", "man");

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan");
		val h01_is = word("IS", "is");
		val h02_a = word("A", "a");
		val h03_black = word("MOD_I", "black");
		val h04_man = word("N_1", "man");

		// create the tree structure for the text;
		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_black, _(t04_dutch, t05_man)))));

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, _(h03_black, h04_man))));

		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan03() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan");
		val t01_is = word("IS", "is");
		val t02_a = word("A", "a");
		val t03_black = word("MOD_I", "black");
		val t04_dutch = word("MOD_I", "dutch");
		val t05_man = word("N_1", "man");

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan");
		val h01_is = word("IS", "is");
		val h02_a = word("A", "a");
		val h03_dutch = word("MOD_I", "dutch");
		val h04_man = word("N_1", "man");

		// create the tree structure for the text;
		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_black, _(t04_dutch, t05_man)))));

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, _(h03_dutch, h04_man))));

		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan04() throws Exception {
		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan");
		val t01_is = word("IS", "is");
		val t02_a = word("A", "a");
		val t03_short = word("MOD_R", "short");
		val t04_dutch = word("MOD_I", "dutch");
		val t05_man = word("N_1", "man");

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan");
		val h01_is = word("IS", "is");
		val h02_a = word("A", "a");
		val h03_dutch = word("MOD_I", "dutch");
		val h04_man = word("N_1", "man");

		// create the tree structure for the text;
		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_short, _(t04_dutch, t05_man)))));

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, _(h03_dutch, h04_man))));

		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan05() throws Exception {

		// create the vocabulary for the text;
		val t00_the = word("THE", "the");
		val t01_largest = word("MOD_R", "largest");
		val t02_search_engine = word("N_1", "search_engine");
		val t03_on_the_web = word("MOD_I", "on_the_web");
		val t04_who = word("WHO_A", "who");
		val t05_is = word("IS", "is");
		val t06_google = word("NP", "google");
		val t07_is = word("IS", "is");
		val t08_popular = word("MOD_R", "popular");

		// create the vocabulary for the hypothesis;
		val h00_google = word("NP", "google");
		val h01_is = word("IS", "is");
		val h02_on_the_web = word("MOD_I", "on_the_web");

		// create the tree structure for the text;
		val tt = _(
				_(_(t00_the,
						_(t01_largest, _(t02_search_engine, t03_on_the_web))),
						_(t04_who, _(t05_is, t06_google))),
				_(t07_is, t08_popular));

		// create the tree structure for the hypothesis;
		val th = _(h00_google, _(h01_is, h02_on_the_web));

		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan06() throws Exception {

		// create the vocabulary for the text;
		val t00_dan = word("NP", "dan");
		val t01_who = word("WHO_A", "who");
		val t02_is = word("IS", "is");
		val t03_kind = word("MOD_R", "kind");
		val t04_gave = word("V_3", "gave");
		val t05_mary = word("NP", "mary");
		val t06_a = word("A", "a");
		val t07_brand_new = word("MOD_I", "brand new");
		val t08_book = word("N_1", "book");

		// create the vocabulary for the hypothesis;
		val h00_dan = word("NP", "dan");
		val h01_gave = word("V_3", "gave");
		val h02_mary = word("NP", "mary");
		val h03_a = word("A", "a");
		val h04_book = word("N_1", "book");

		// create the tree structure for the text;
		val tt = _(_(t00_dan, _(t01_who, _(t02_is, t03_kind))),
				_(_(t04_gave, t05_mary), _(t06_a, _(t07_brand_new, t08_book))));

		// create the tree structure for the hypothesis;
		val th = _(h00_dan, _(_(h01_gave, h02_mary), _(h03_a, h04_book)));

		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan07() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan");
		val t01_is = word("IS", "is");
		val t02_a = word("A", "a");
		val t03_tall = word("MOD_R", "tall");
		val t04_black = word("MOD_I", "black");
		val t05_fat = word("MOD_R", "fat");
		val t06_dutch = word("MOD_I", "dutch");
		val t07_man = word("N_1", "man");

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan");
		val h01_is = word("IS", "is");
		val h02_a = word("A", "a");
		val h03_black = word("MOD_I", "black");
		val h04_man = word("N_1", "man");

		// create the tree structure for the text;
		val tt = _(
				t00_jan,
				_(t01_is,
						_(t02_a,
								_(t03_tall,
										_(t04_black,
												_(t05_fat,
														_(t06_dutch, t07_man)))))));

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, _(h03_black, h04_man))));

		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan08() throws Exception {
		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan");
		val t01_found = word("V_2", "found");
		val t02_the = word("THE", "the");
		val t03_book = word("N_1", "book");
		val t04_and = word("AND", "and");
		val t05_every = word("EVERY", "every");
		val t06_book = word("N_1", "book");
		val t07_is = word("IS", "is");
		val t08_blue = word("MOD_I", "blue");

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan");
		val h01_found = word("V_2", "found");
		val h02_the = word("THE", "the");
		val h03_blue = word("MOD_I", "blue");
		val h04_book = word("N_1", "book");

		// create the tree structure for the text;
		val tt = _(_(t00_jan, _(t01_found, _(t02_the, t03_book))),
				_(t04_and, _(_(t05_every, t06_book), _(t07_is, t08_blue))));

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_found, _(h02_the, _(h03_blue, h04_book))));
		
		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan09() throws Exception {
		// create the vocabulary for the text;
		val t00_the = word("THE", "the");
		val t01_man = word("N_1", "man");
		val t02_who = word("WHO_R", "who");
		val t03_killed = word("V_2", "killed");
		val t04_john = word("NP", "john");
		val t05_loves = word("V_2", "loves");
		val t06_the = word("THE", "the");
		val t07_woman = word("N_1", "woman");
		val t08_who = word("WHO_R", "who");
		val t09_killed = word("V_2", "killed");
		val t10_mary = word("NP", "mary");

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE", "the");
		val h01_man = word("N_1", "man");
		val h02_loves = word("V_2", "loves");
		val h03_the = word("THE", "the");
		val h04_woman = word("N_1", "woman");
		val h05_who = word("WHO_R", "who");
		val h06_killed = word("V_2", "killed");
		val h07_mary = word("NP", "mary");

		// create the tree structure for the text;
		val tt = _(
				_(t00_the, _(t01_man, _(t02_who, _(t03_killed, t04_john)))),
				_(t05_loves,
						_(t06_the,
								_(t07_woman,
										_(t08_who, _(t09_killed, t10_mary))))));

		// create the tree structure for the hypothesis;
		val th = _(
				_(h00_the, h01_man),
				_(h02_loves,
						_(h03_the,
								_(h04_woman,
										_(h05_who, _(h06_killed, h07_mary))))));

		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan10() throws Exception {
		// create the vocabulary for the text;
		val jan = word("NP", "jan");
		val quickly = word("MOD_R", "quickly");
		val ate = word("V_2", "ate");
		val an = word("A", "an");
		val egg = word("N_1", "egg");
		val in = word("P_R", "in");
		val brasil = word("NP", "brasil");

		// create the tree structure for the text;
		val tt = _(jan, _(quickly, _(ate, _(an, egg))));

		// create the tree structure for the hypothesis;
		val th = _(jan, _(ate, _(an, egg)));

		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan11() throws Exception {

		// create the vocabulary for the text;
		val t_jan = word("NP", "jan");
		val t_quickly = word("MOD_R", "quickly");
		val t_ate = word("V_2", "ate");
		val t_an = word("A", "an");
		val t_egg = word("N_1", "egg");

		// create the tree structure for the text;
		val tt = _(t_jan, _(t_quickly, _(t_ate, _(t_an, t_egg))));

		// create the vocabulary for the text;
		val h_jan = word("NP", "jan");
		val h_quickly = word("MOD_R", "quickly");
		val h_ate = word("V_2", "ate");
		val h_an = word("A", "an");
		val h_egg = word("N_1", "egg");

		// create the tree structure for the hypothesis;
		val th = _(h_jan, _(h_quickly, _(h_ate, _(h_an, h_egg))));

		assertProof(tt, th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

	@Test
	public final void jan13() throws Exception {
		// create the vocabulary for the text;
		val jan = word("NP", "jan");
		val quickly = word("MOD_R", "quickly");
		val ate = word("V_2", "ate");
		val an = word("A", "an");
		val egg = word("N_1", "egg");
		val in = word("P_R", "in");
		val the = word("THE", "the");
		val a = word("A", "a");
		val garden = word("N", "garden");

		// create the tree structure for the text;
		val tt = _(jan,
				_(_(quickly, _(ate, _(an, egg))), _(in, _(the, garden))));

		// create the tree structure for the hypothesis;
		val th = _(jan, _(_(quickly, _(ate, _(an, egg))), _(in, _(a, garden))));
		
		assertProof(tt,th);
		testTestCaseCreator(tt, th, ResultType.Proof);
	}

}
