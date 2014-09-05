package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestGerunds extends AbsPipelineTest<Integer> {

	@Test
	public final void ger01() throws Exception {

		// create the vocabulary for the text:
		val t00_john = word("NP", "John",1);
		val t01_travelled = word("V_1", "travelled",2);
		val t02_to = word("P_R", "to",3);
		val t03_Boston = word("NP", "Boston",4);
		val t04_by = word("P_R", "by",5);
		val t05_walking = word("GER_1", "walking",6);

		// create the vocabulary for the hypothesis:
		val h00_john = word("NP", "John",1);
		val h01_travelled = word("V_1", "travelled",2);

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(_(t01_travelled, _(t02_to, t03_Boston,7),8),
						_(t04_by, t05_walking,9),10),11);

		// create the tree structure for the hypothesis;
		val th = _(h00_john, h01_travelled,3);

		assertProof(tt, th);
	}

	@Test
	public final void ger02() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP", "John",1);
		val t01_toured = word("V_2", "toured",2);
		val t02_brussels = word("NP", "Brussels",3);
		val t03_visiting = word("GER_2", "visiting",4);
		val t04_the = word("THE", "the",5);
		val t05_headquarters = word("N", "headquarters",6);
		val t06_of = word("P_R", "of",7);
		val t07_the = word("THE", "the",8);
		val t08_eu = word("N", "EU",9);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP", "John",1);
		val h01_toured = word("V_2", "toured",2);
		val h02_brussels = word("NP", "Brussels",3);
		val h03_visiting = word("GER_2", "visiting",4);
		val h04_some = word("SOME", "some",5);
		val h05_headquarters = word("N", "headquarters",6);

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(_(t01_toured, t02_brussels,10),
						_(t03_visiting,
								_(t04_the,
										_(t05_headquarters,
												_(t06_of, _(t07_the, t08_eu,11),12),13),14),15),16),17);

		// create the tree structure for the hypothesis;
		val th = _(
				h00_john,
				_(_(h01_toured, h02_brussels,7),
						_(h03_visiting, _(h04_some, h05_headquarters,8),9),10),11);

		assertProof(tt, th);
	}

	@Test
	public final void ger03() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D", "John",1);
		val t01_finished = word("V_2", "finished",2);
		val t02_the = word("THE", "the",3);
		val t03_affair = word("N", "affair",4);
		val t04_by = word("P_R", "by",5);
		val t05_writing = word("GER_2", "writing",6);
		val t06_a = word("A", "a",7);
		val t07_letter = word("N", "letter",8);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D", "John",1);
		val h01_finished = word("V_2", "finished",2);
		val h02_the = word("THE", "the",3);
		val h03_affair = word("N", "affair",4);

		// create the tree structure for the text;
		val tt = _(
				t00_john,
				_(_(t01_finished, _(t02_the, t03_affair,9),10),
						_(t04_by, _(t05_writing, _(t06_a, t07_letter,11),12),13),14),15);

		// create the tree structure for the hypothesis;
		val th = _(h00_john, _(h01_finished, _(h02_the, h03_affair,5),6),7);

		assertProof(tt, th);
	}

	@Test
	public final void ger04() throws Exception {

		// create the vocabulary for the text:
		val t00_john = word("NP", "John",1);
		val t01_travelled = word("V_1", "travelled",2);
		val t02_walking = word("GER_1", "walking",3);

		// create the vocabulary for the hypothesis:
		val h00_john = word("NP", "John",1);
		val h01_travelled = word("V_1", "travelled",2);

		// create the tree structure for the text;
		val tt = _(t00_john, _(t01_travelled, t02_walking,4),5);

		// create the tree structure for the hypothesis;
		val th = _(h00_john, h01_travelled,3);

		assertProof(tt, th);
	}

	@Test
	public final void ger05() throws Exception {

		// create the vocabulary for the text:
		val john = word("NP", "John",1);
		val is = word("IS", "is",2);
		val accused = word("V_1", "accused",3);
		val of = word("P_R", "of",4);
		val killing = word("GER_2", "killing",5);
		val the = word("THE", "the",6);
		val girl = word("N", "girl",7);

		// create the vocabulary for the hypothesis:
		val a = word("A", "a",1);

		// create the tree structure for the text;
		val tt = _(john, _(is, _(accused, _(of, _(killing, _(the, girl,8),9),10),11),12),13);

		// create the tree structure for the hypothesis;
		val th = _(john, _(is, _(accused, _(of, _(killing, _(a, girl,2),3),4),5),6),7);

		assertNoProof(tt, th);
	}

	@Test // This test is now no longer valid, due to an illegal use of NP conjunctions.
	public final void ger06() throws Exception {

		// create the vocabulary for the text;
		val t00_the = word("THE", "The",1);
		val t01_soldier = word("N", "soldier",2);
		val t02_app = word("WHO_A", "APP",3);
		val t03_james = word("$NPC_1$", "James",4);
		val t04_barker = word("NP_D", "Barker",5);
		val t05_app = word("WHO_A", "APP",6);
		val t06_the = word("THE", "the",7);
		val t07_american = word("MI", "American",8);
		val t08_soldier = word("N", "soldier",9);
		val t09_app = word("WHO_R", "APP",10);
		val t10_accused = word("V_1", "accused",11);
		val t11_of = word("P_R", "of",12);
		val t12_raping = word("GER_2", "raping",13);
		val t13_the = word("THE", "the",14);
		val t14_iraqi = word("MR", "Iraqi",15);
		val t15_girl = word("N", "girl",16);
		val t16_and = word("AND", "and",17);
		val t17_killing = word("GER_2", "killing",18);
		val t18_the = word("THE", "the",19);
		val t19_family = word("N", "family",20);
		val t20_in = word("P_R", "in",21);
		val t21_the = word("THE", "the",22);
		val t22_march = word("MR", "March",23);
		val t23_2006 = word("NUMBER", "2006",24);
		val t24_incident = word("N", "incident",25);
		val t25_pleaded = word("V_2", "pleaded",26);
		val t26_det = word("EMPTYDET", "DET",27);
		val t27_guilty = word("N", "guilty",28);
		val t28_to = word("P_R", "to",29);
		val t29_murder = word("N", "murder",30);
		val t30_on = word("P_R", "on",31);
		val t31_wednesday = word("NP_D", "Wednesday",32);
		val t32_in = word("P_R", "in",33);
		val t33_fort = word("$NPC_1$", "Fort",34);
		val t34_campbell = word("NP_D", "Campbell",35);
		val t35_app = word("WHO_A", "APP",36);
		val t36_app = word("IS", "APP",37);
		val t37_in = word("P_R", "in",38);
		val t38_kentucky = word("NP_D", "Kentucky",39);

		// create the vocabulary for the hypothesis;
		val h00_james = word("$NPC_1$", "James",1);
		val h01_barker = word("NP_D", "Barker",2);
		val h02_is = word("IS", "is",3);
		val h03_accused = word("V_1", "accused",4);
		val h04_of = word("P_R", "of",5);
		val h05_raping = word("GER_2", "raping",6);
		val h06_an = word("A", "an",7);
		val h07_iraqi = word("MR", "Iraqi",8);
		val h08_girl = word("N", "girl",9);
		val h09_and = word("AND", "and",10);
		val h10_killing = word("GER_2", "killing",11);
		val h11_the = word("THE", "the",12);
		val h12_family = word("N", "family",13);

		// create the tree structure for the text;
		val tt = _(
				_(_(_(t00_the, t01_soldier,40),
						_(t02_app, _(t03_james, t04_barker,41),42),43),
						_(t05_app,
								_(t06_the,
										_(_(t07_american, t08_soldier,44),
												_(t09_app,
														_(t10_accused,
																_(t11_of,
																		_(_(_(t12_raping,
																				_(t13_the,
																						_(t14_iraqi,
																								t15_girl,45),46),47),
																				_(t16_and,
																						_(t17_killing,
																								_(t18_the,
																										t19_family,48),49),50),51),
																				_(t20_in,
																						_(t21_the,
																								_(t22_march,
																										_(t23_2006,
																												t24_incident,52),53),54),55),56),57),58),59),60),61),62),63),
				_(_(_(t25_pleaded,
						_(t26_det, _(t27_guilty, _(t28_to, t29_murder,64),65),66),67),
						_(t30_on, t31_wednesday,68),69),
						_(t32_in,
								_(_(t33_fort, t34_campbell,70),
										_(t35_app,
												_(t36_app,
														_(t37_in, t38_kentucky,71),72),73),74),75),76),77);

		// create the tree structure for the hypothesis;
		val th = _(
				_(h00_james, h01_barker,14),
				_(h02_is,
						_(h03_accused,
								_(h04_of,
										_(_(h05_raping,
												_(h06_an,
														_(h07_iraqi, h08_girl,15),16),17),
												_(h09_and,
														_(h10_killing,
																_(h11_the,
																		h12_family,18),19),20),21),22),23),24),25);

		assertNoProof(tt, th);
	}
}
