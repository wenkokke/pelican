package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestFacts extends AbsPipelineTest<Integer> {

	@Test
	public final void fact01() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP", "John",1);
		val t01_said = word("FACT", "said",2);
		val t02_that = word("IGNORE", "that",3);
		val t03_the = word("THE", "the",4);
		val t04_car = word("N", "car",5);
		val t05_crashed = word("V_1", "crashed",6);
		val t06_into = word("P_R", "into",7);
		val t07_the = word("THE", "the",8);
		val t08_dom = word("$NC_1$", "Dom",9);
		val t09_tower = word("N", "Tower",10);

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE", "The",1);
		val h01_car = word("N", "car",2);
		val h02_crashed = word("V_1", "crashed",3);
		val h03_into = word("P_R", "into",4);
		val h04_the = word("THE", "the",5);
		val h05_dom = word("$NC_1$", "Dom",6);
		val h06_tower = word("N", "Tower",7);

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(t01_said,
						_(t02_that,
								_(_(t03_the, t04_car,11),
										_(t05_crashed,
												_(t06_into,
														_(t07_the,
																_(t08_dom,
																		t09_tower,12),13),14),15),16),17),18),19);

		// create the tree structure for the hypothesis;
		val th = _(_(h00_the, h01_car,8),
				_(h02_crashed, _(h03_into, _(h04_the, _(h05_dom, h06_tower,9),10),11),12),13);

		assertProof(tt, th);
	}

	@Test
	public final void fact02() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D", "John",1);
		val t01_loves = word("V_2", "loves",2);
		val t02_a = word("A", "a",3);
		val t03_senior = word("MR", "senior",4);
		val t04_coalition = word("MR", "coalition",5);
		val t05_official = word("N", "official",6);
		val t06_in = word("P_R", "in",7);
		val t07_iraq = word("NP_D", "Iraq",8);
		val t08_who = word("WHO_R", "who",9);
		val t09_said = word("FACT", "said",10);
		val t10_the = word("THE", "the",11);
		val t11_body = word("N", "body",12);
		val t12_was = word("IS", "was",13);
		val t13_found = word("V_1", "found",14);
		val t14_by = word("P_R", "by",15);
		val t15_the = word("THE", "the",16);
		val t16_police = word("N", "police",17);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D", "John",1);
		val h01_loves = word("V_2", "loves",2);
		val h02_an = word("A", "an",3);
		val h03_official = word("N", "official",4);

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(t01_loves,
						_(t02_a,
								_(_(_(t03_senior,
										_(t04_coalition, t05_official,18),19),
										_(t06_in, t07_iraq,20),21),
										_(t08_who,
												_(t09_said,
														_(_(t10_the, t11_body,22),
																_(t12_was,
																		_(t13_found,
																				_(t14_by,
																						_(t15_the,
																								t16_police,23),24),25),26),27),28),29),30),31),32),33);

		// create the tree structure for the hypothesis;
		val th = _(h00_john, _(h01_loves, _(h02_an, h03_official,5),6),7);

		assertProof(tt, th);
	}

	@Test
	public final void fact03() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D", "John",1);
		val t01_loves = word("V_2", "loves",2);
		val t02_a = word("A", "a",3);
		val t03_senior = word("MR", "senior",4);
		val t04_coalition = word("MR", "coalition",5);
		val t05_official = word("N", "official",6);
		val t06_in = word("P_R", "in",7);
		val t07_iraq = word("NP_D", "Iraq",8);
		val t08_who = word("WHO_R", "who",9);
		val t09_said = word("NOFACT", "said",10);
		val t10_the = word("THE", "the",11);
		val t11_body = word("N", "body",12);
		val t12_was = word("IS", "was",13);
		val t13_found = word("V_1", "found",14);
		val t14_by = word("P_R", "by",15);
		val t15_the = word("THE", "the",16);
		val t16_police = word("N", "police",17);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D", "John",1);
		val h01_loves = word("V_2", "loves",2);
		val h02_an = word("A", "an",3);
		val h03_official = word("N", "official",4);

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(t01_loves,
						_(t02_a,
								_(_(_(t03_senior,
										_(t04_coalition, t05_official,18),19),
										_(t06_in, t07_iraq,20),21),
										_(t08_who,
												_(t09_said,
														_(_(t10_the, t11_body,22),
																_(t12_was,
																		_(t13_found,
																				_(t14_by,
																						_(t15_the,
																								t16_police,23),24),25),26),27),28),29),30),31),32),33);

		// create the tree structure for the hypothesis;
		val th = _(h00_john, _(h01_loves, _(h02_an, h03_official,5),6),7);

		assertProof(tt, th);
	}

	@Test
	public final void fact04() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D", "John",1);
		val t01_loves = word("V_2", "loves",2);
		val t02_a = word("A", "a",3);
		val t03_senior = word("MR", "senior",4);
		val t04_coalition = word("MR", "coalition",5);
		val t05_official = word("N", "official",6);
		val t06_in = word("P_R", "in",7);
		val t07_iraq = word("NP_D", "Iraq",8);
		val t08_who = word("WHO_R", "who",9);
		val t09_said = word("FACT", "said",10);
		val t10_the = word("THE", "the",11);
		val t11_body = word("N", "body",12);
		val t12_was = word("IS", "was",13);
		val t13_found = word("V_1", "found",14);
		val t14_by = word("P_R", "by",15);
		val t15_the = word("THE", "the",16);
		val t16_police = word("N", "police",17);

		// create the vocabulary for the hypothesis;
		val h04_a = word("A", "a",1);
		val h05_body = word("N", "body",2);
		val h06_was = word("IS", "was",3);
		val h07_found = word("V_1", "found",4);

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(t01_loves,
						_(t02_a,
								_(_(_(t03_senior,
										_(t04_coalition, t05_official,18),19),
										_(t06_in, t07_iraq,20),21),
										_(t08_who,
												_(t09_said,
														_(_(t10_the, t11_body,22),
																_(t12_was,
																		_(t13_found,
																				_(t14_by,
																						_(t15_the,
																								t16_police,23),24),25),26),27),28),29),30),31),32),33);

		// create the tree structure for the hypothesis;
		val th = _(_(h04_a, h05_body,5), _(h06_was, h07_found,6),7);

		assertProof(tt, th);
	}
}
