package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestJanPhrases<ID> extends AbsPipelineTest<Integer> {

	@Test
	public final void jan01() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_sat = word("V_1", "sat",2);
		val t02_and = word("AND", "and",3);
		val t03_ate = word("V_1", "ate",4);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_ate = word("V_1", "ate",2);

		// create the tree structure for the text;
		val tt = _(t00_jan, _(t01_sat, _(t02_and, t03_ate,5),6),7);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, h01_ate,3);

		// return the new entailment;
		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan02() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("IS", "is",2);
		val t02_a = word("A", "a",3);
		val t03_black = word("MOD_I", "black",4);
		val t04_dutch = word("MOD_I", "dutch",5);
		val t05_man = word("N_1", "man",6);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("IS", "is",2);
		val h02_a = word("A", "a",3);
		val h03_black = word("MOD_I", "black",4);
		val h04_man = word("N_1", "man",5);

		// create the tree structure for the text;
		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_black, _(t04_dutch, t05_man,7),8),9),10),11);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, _(h03_black, h04_man,6),7),8),9);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan03() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("IS", "is",2);
		val t02_a = word("A", "a",3);
		val t03_black = word("MOD_I", "black",4);
		val t04_dutch = word("MOD_I", "dutch",5);
		val t05_man = word("N_1", "man",6);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("IS", "is",2);
		val h02_a = word("A", "a",3);
		val h03_dutch = word("MOD_I", "dutch",4);
		val h04_man = word("N_1", "man",5);

		// create the tree structure for the text;
		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_black, _(t04_dutch, t05_man,7),8),9),10),11);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, _(h03_dutch, h04_man,6),7),8),9);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan04() throws Exception {
		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("IS", "is",2);
		val t02_a = word("A", "a",3);
		val t03_short = word("MOD_R", "short",4);
		val t04_dutch = word("MOD_I", "dutch",5);
		val t05_man = word("N_1", "man",6);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("IS", "is",2);
		val h02_a = word("A", "a",3);
		val h03_dutch = word("MOD_I", "dutch",4);
		val h04_man = word("N_1", "man",5);

		// create the tree structure for the text;
		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_short, _(t04_dutch, t05_man,7),8),9),10),11);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, _(h03_dutch, h04_man,6),7),8),9);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan05() throws Exception {

		// create the vocabulary for the text;
		val t00_the = word("THE", "the",1);
		val t01_largest = word("MOD_R", "largest",2);
		val t02_search_engine = word("N_1", "search_engine",3);
		val t03_on_the_web = word("MOD_I", "on_the_web",4);
		val t04_who = word("WHO_A", "which",5);
		val t05_is = word("IS", "is",6);
		val t06_google = word("NP", "google",7);
		val t07_is = word("IS", "is",8);
		val t08_popular = word("MOD_R", "popular",9);

		// create the vocabulary for the hypothesis;
		val h00_google = word("NP", "google",1);
		val h01_is = word("IS", "is",2);
		val h02_on_the_web = word("MOD_I", "on_the_web",3);

		// create the tree structure for the text;
		val tt = _(
				_(_(t00_the,
						_(t01_largest, _(t02_search_engine, t03_on_the_web,10),11),12),
						_(t04_who, _(t05_is, t06_google,13),14),15),
				_(t07_is, t08_popular,16),17);

		// create the tree structure for the hypothesis;
		val th = _(h00_google, _(h01_is, h02_on_the_web,4),5);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan06() throws Exception {

		// create the vocabulary for the text;
		val t00_dan = word("NP", "dan",1);
		val t01_who = word("WHO_A", "who",2);
		val t02_is = word("IS", "is",3);
		val t03_kind = word("MOD_R", "kind",4);
		val t04_gave = word("V_3", "gave",5);
		val t05_mary = word("NP", "mary",6);
		val t06_a = word("A", "a",7);
		val t07_brand_new = word("MOD_I", "brand new",8);
		val t08_book = word("N_1", "book",9);

		// create the vocabulary for the hypothesis;
		val h00_dan = word("NP", "dan",1);
		val h01_gave = word("V_3", "gave",2);
		val h02_mary = word("NP", "mary",3);
		val h03_a = word("A", "a",4);
		val h04_book = word("N_1", "book",5);

		// create the tree structure for the text;
		val tt = _(_(t00_dan, _(t01_who, _(t02_is, t03_kind,10),11),12),
				_(_(t04_gave, t05_mary,13), _(t06_a, _(t07_brand_new, t08_book,14),15),16),17);

		// create the tree structure for the hypothesis;
		val th = _(h00_dan, _(_(h01_gave, h02_mary,6), _(h03_a, h04_book,7),8),9);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan07() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("IS", "is",2);
		val t02_a = word("A", "a",3);
		val t03_tall = word("MOD_R", "tall",4);
		val t04_black = word("MOD_I", "black",5);
		val t05_fat = word("MOD_R", "fat",6);
		val t06_dutch = word("MOD_I", "dutch",7);
		val t07_man = word("N_1", "man",8);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("IS", "is",2);
		val h02_a = word("A", "a",3);
		val h03_black = word("MOD_I", "black",4);
		val h04_man = word("N_1", "man",5);

		// create the tree structure for the text;
		val tt = _(
				t00_jan,
				_(t01_is,
						_(t02_a,
								_(t03_tall,
										_(t04_black,
												_(t05_fat,
														_(t06_dutch, t07_man,9),10),11),12),13),14),15);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, _(h03_black, h04_man,6),7),8),9);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan08() throws Exception {
		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_found = word("V_2", "found",2);
		val t02_the = word("THE", "the",3);
		val t03_book = word("N_1", "book",4);
		val t04_and = word("AND", "and",5);
		val t05_every = word("EVERY", "every",6);
		val t06_book = word("N_1", "book",7);
		val t07_is = word("IS", "is",8);
		val t08_blue = word("MOD_I", "blue",9);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_found = word("V_2", "found",2);
		val h02_the = word("THE", "the",3);
		val h03_blue = word("MOD_I", "blue",4);
		val h04_book = word("N_1", "book",5);

		// create the tree structure for the text;
		val tt = _(_(t00_jan, _(t01_found, _(t02_the, t03_book,10),11),12),
				_(t04_and, _(_(t05_every, t06_book,13), _(t07_is, t08_blue,14),15),16),17);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_found, _(h02_the, _(h03_blue, h04_book,6),7),8),9);
		
		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan09() throws Exception {
		// create the vocabulary for the text;
		val t00_the = word("THE", "the",1);
		val t01_man = word("N_1", "man",2);
		val t02_who = word("WHO_R", "who",3);
		val t03_killed = word("V_2", "killed",4);
		val t04_john = word("NP", "john",5);
		val t05_loves = word("V_2", "loves",6);
		val t06_the = word("THE", "the",7);
		val t07_woman = word("N_1", "woman",8);
		val t08_who = word("WHO_R", "who",9);
		val t09_killed = word("V_2", "killed",10);
		val t10_mary = word("NP", "mary",11);

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE", "the",1);
		val h01_man = word("N_1", "man",2);
		val h02_loves = word("V_2", "loves",3);
		val h03_the = word("THE", "the",4);
		val h04_woman = word("N_1", "woman",5);
		val h05_who = word("WHO_R", "who",6);
		val h06_killed = word("V_2", "killed",7);
		val h07_mary = word("NP", "mary",8);

		// create the tree structure for the text;
		val tt = _(
				_(t00_the, _(t01_man, _(t02_who, _(t03_killed, t04_john,12),13),14),15),
				_(t05_loves,
						_(t06_the,
								_(t07_woman,
										_(t08_who, _(t09_killed, t10_mary,16),17),18),19),20),21);

		// create the tree structure for the hypothesis;
		val th = _(
				_(h00_the, h01_man,9),
				_(h02_loves,
						_(h03_the,
								_(h04_woman,
										_(h05_who, _(h06_killed, h07_mary,10),11),12),13),14),15);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan10() throws Exception {
		// create the vocabulary for the text;
		val jan = word("NP", "jan",1);
		val quickly = word("MOD_R", "quickly",2);
		val ate = word("V_2", "ate",3);
		val an = word("A", "an",4);
		val egg = word("N_1", "egg",5);

		// create the vocabulary for the hypothesis;
		val h_jan = word("NP", "jan",1);
		val h_ate = word("V_2", "ate",2);
		val h_an = word("A", "an",3);
		val h_egg = word("N_1", "egg",4);
		
		// create the tree structure for the text;
		val tt = _(jan, _(quickly, _(ate, _(an, egg,6),7),8),9);

		// create the tree structure for the hypothesis;
		val th = _(h_jan, _(h_ate, _(h_an, h_egg,5),6),7);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan11() throws Exception {

		// create the vocabulary for the text;
		val t_jan = word("NP", "jan",1);
		val t_quickly = word("MOD_R", "quickly",2);
		val t_ate = word("V_2", "ate",3);
		val t_an = word("A", "an",4);
		val t_egg = word("N_1", "egg",5);

		// create the vocabulary for the hypothesis;
		val h_jan = word("NP", "jan",1);
		val h_quickly = word("MOD_R", "quickly",2);
		val h_ate = word("V_2", "ate",3);
		val h_an = word("A", "an",4);
		val h_egg = word("N_1", "egg",5);
		
		// create the tree structure for the text;
		val tt = _(t_jan, _(t_quickly, _(t_ate, _(t_an, t_egg,6),7),8),9);

		// create the tree structure for the hypothesis;
		val th = _(h_jan, _(h_quickly, _(h_ate, _(h_an, h_egg,6),7),8),9);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan13() throws Exception {
		// create the vocabulary for the text;
		val jan = word("NP", "jan",1);
		val quickly = word("MOD_R", "quickly",2);
		val ate = word("V_2", "ate",3);
		val an = word("A", "an",4);
		val egg = word("N_1", "egg",5);
		val in = word("P_R", "in",6);
		val the = word("THE", "the",7);
		val garden = word("N", "garden",8);

		// create the vocabulary for the hypothesis;
		val h_jan = word("NP", "jan",1);
		val h_quickly = word("MOD_R", "quickly",2);
		val h_ate = word("V_2", "ate",3);
		val h_an = word("A", "an",4);
		val h_egg = word("N_1", "egg",5);
		val h_in = word("P_R", "in",6);
		val h_a = word("A", "a",7);
		val h_garden = word("N", "garden",8);
		
		
		// create the tree structure for the text;
		val tt = _(jan,
				_(_(quickly, _(ate, _(an, egg,9),10),11), _(in, _(the, garden,12),13),14),15);

		// create the tree structure for the hypothesis;
		val th = _(h_jan, _(_(h_quickly, _(h_ate, _(h_an, h_egg,9),10),11), _(h_in, _(h_a, h_garden,12),13),14),15);
		
		assertProof(tt,th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan14() throws Exception {

		// create the vocabulary for the text;
		val t00_the    = word("THE", "the",1);
		val t01_tall   = word("MOD_R", "tall",2);
		val t03_dutch  = word("MOD_I", "Dutch",3);
		val t02_man    = word("N_1", "man",4);
		val t04_who    = word("WHO_A", "who",5);
		val t05_is     = word("IS", "is",6);
		val t06_john   = word("NP", "John",7);
		val t08_smiled = word("V_1", "smiled",8);

		// create the vocabulary for the hypothesis;
		val h00_john   = word("NP", "John",1);
		val h02_is     = word("IS", "is",2);
		val h03_dutch  = word("MOD_I", "Dutch",3);

		// create the tree structure for the text;
		val tt = _(
				_(_(t00_the,
						_(t01_tall, _(t03_dutch, t02_man,9),10),11),
						_(t04_who, _(t05_is, t06_john,12),13),14),
				t08_smiled,15);

		// create the tree structure for the hypothesis;
		val th = _(h00_john, _(h02_is,h03_dutch,4),5);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void jan15() throws Exception {

		// create the vocabulary for the text;
		val t00_the    = word("THE", "the",1);
		val t03_dutch  = word("MOD_I", "Dutch",2);
		val t02_man    = word("N_1", "man",3);
		val t04_who    = word("WHO_A", "who",4);
		val t05_is     = word("IS", "is",5);
		val t06_john   = word("NP", "John",6);
		val t08_smiled = word("V_1", "smiled",7);

		// create the vocabulary for the hypothesis;
		val h00_john   = word("NP", "John",1);
		val h02_is     = word("IS", "is",2);
		val h03_dutch  = word("MOD_I", "Dutch",3);

		// create the tree structure for the text;
		val tt = _(
				_(_(t00_the, _(t03_dutch, t02_man,8),9),
						_(t04_who, _(t05_is, t06_john,10),11),12), t08_smiled,13);

		// create the tree structure for the hypothesis;
		val th = _(h00_john, _(h02_is,h03_dutch,4),5);

		assertProof(tt, th);
		testTestCaseCreator(tt, th, Proof);
	}
}
