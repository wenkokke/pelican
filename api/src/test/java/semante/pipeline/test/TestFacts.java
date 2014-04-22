package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestFacts extends AbsPipelineTest {

	@Test
	public final void fact01() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP", "John");
		val t01_said = word("FACT", "said");
		val t02_that = word("IGNORE", "that");
		val t03_the = word("THE", "the");
		val t04_car = word("N", "car");
		val t05_crashed = word("V_1", "crashed");
		val t06_into = word("P_R", "into");
		val t07_the = word("THE", "the");
		val t08_dom = word("$NC_1$", "Dom");
		val t09_tower = word("N", "Tower");

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE", "The");
		val h01_car = word("N", "car");
		val h02_crashed = word("V_1", "crashed");
		val h03_into = word("P_R", "into");
		val h04_the = word("THE", "the");
		val h05_dom = word("$NC_1$", "Dom");
		val h06_tower = word("N", "Tower");

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(t01_said,
						_(t02_that,
								_(_(t03_the, t04_car),
										_(t05_crashed,
												_(t06_into,
														_(t07_the,
																_(t08_dom,
																		t09_tower))))))));

		// create the tree structure for the hypothesis;
		val th = _(_(h00_the, h01_car),
				_(h02_crashed, _(h03_into, _(h04_the, _(h05_dom, h06_tower)))));

		assertProof(tt, th);
	}

	@Test
	public final void fact02() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D", "John");
		val t01_loves = word("V_2", "loves");
		val t02_a = word("A", "a");
		val t03_senior = word("MR", "senior");
		val t04_coalition = word("MR", "coalition");
		val t05_official = word("N", "official");
		val t06_in = word("P_R", "in");
		val t07_iraq = word("NP_D", "Iraq");
		val t08_who = word("WHO_R", "who");
		val t09_said = word("FACT", "said");
		val t10_the = word("THE", "the");
		val t11_body = word("N", "body");
		val t12_was = word("IS", "was");
		val t13_found = word("V_1", "found");
		val t14_by = word("P_R", "by");
		val t15_the = word("THE", "the");
		val t16_police = word("N", "police");

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D", "John");
		val h01_loves = word("V_2", "loves");
		val h02_an = word("A", "an");
		val h03_official = word("N", "official");

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(t01_loves,
						_(t02_a,
								_(_(_(t03_senior,
										_(t04_coalition, t05_official)),
										_(t06_in, t07_iraq)),
										_(t08_who,
												_(t09_said,
														_(_(t10_the, t11_body),
																_(t12_was,
																		_(t13_found,
																				_(t14_by,
																						_(t15_the,
																								t16_police)))))))))));

		// create the tree structure for the hypothesis;
		val th = _(h00_john, _(h01_loves, _(h02_an, h03_official)));

		assertProof(tt, th);
	}

	@Test
	public final void fact03() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D", "John");
		val t01_loves = word("V_2", "loves");
		val t02_a = word("A", "a");
		val t03_senior = word("MR", "senior");
		val t04_coalition = word("MR", "coalition");
		val t05_official = word("N", "official");
		val t06_in = word("P_R", "in");
		val t07_iraq = word("NP_D", "Iraq");
		val t08_who = word("WHO_R", "who");
		val t09_said = word("NOFACT", "said");
		val t10_the = word("THE", "the");
		val t11_body = word("N", "body");
		val t12_was = word("IS", "was");
		val t13_found = word("V_1", "found");
		val t14_by = word("P_R", "by");
		val t15_the = word("THE", "the");
		val t16_police = word("N", "police");

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D", "John");
		val h01_loves = word("V_2", "loves");
		val h02_an = word("A", "an");
		val h03_official = word("N", "official");

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(t01_loves,
						_(t02_a,
								_(_(_(t03_senior,
										_(t04_coalition, t05_official)),
										_(t06_in, t07_iraq)),
										_(t08_who,
												_(t09_said,
														_(_(t10_the, t11_body),
																_(t12_was,
																		_(t13_found,
																				_(t14_by,
																						_(t15_the,
																								t16_police)))))))))));

		// create the tree structure for the hypothesis;
		val th = _(h00_john, _(h01_loves, _(h02_an, h03_official)));

		assertProof(tt, th);
	}

	@Test
	public final void fact04() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D", "John");
		val t01_loves = word("V_2", "loves");
		val t02_a = word("A", "a");
		val t03_senior = word("MR", "senior");
		val t04_coalition = word("MR", "coalition");
		val t05_official = word("N", "official");
		val t06_in = word("P_R", "in");
		val t07_iraq = word("NP_D", "Iraq");
		val t08_who = word("WHO_R", "who");
		val t09_said = word("FACT", "said");
		val t10_the = word("THE", "the");
		val t11_body = word("N", "body");
		val t12_was = word("IS", "was");
		val t13_found = word("V_1", "found");
		val t14_by = word("P_R", "by");
		val t15_the = word("THE", "the");
		val t16_police = word("N", "police");

		// create the vocabulary for the hypothesis;
		val h04_a = word("A", "a");
		val h05_body = word("N", "body");
		val h06_was = word("IS", "was");
		val h07_found = word("V_1", "found");

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(t01_loves,
						_(t02_a,
								_(_(_(t03_senior,
										_(t04_coalition, t05_official)),
										_(t06_in, t07_iraq)),
										_(t08_who,
												_(t09_said,
														_(_(t10_the, t11_body),
																_(t12_was,
																		_(t13_found,
																				_(t14_by,
																						_(t15_the,
																								t16_police)))))))))));

		// create the tree structure for the hypothesis;
		val th = _(_(h04_a, h05_body), _(h06_was, h07_found));

		assertProof(tt, th);
	}

}
