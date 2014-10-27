package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestFeats extends AbsPipelineTest<Integer> {

	@Test
	public final void feat01() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("IS", "is",2);
		val t02_a = word("A", "a",3);
		val t03_man = word("N_1", "man",4);
		val t04_from = word("P_R", "from",5);
		val t05_Brasil = word("NP", "Brasil",6);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("IS", "is",2);
		val h02_a = word("A", "a",3);
		val h03_man = word("N_1", "man",4);

		// create the tree structure for the text;
		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_man, _(t04_from, t05_Brasil,7),8),9),10),11);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, h03_man,5),6),7);

		assertProof(tt, th);
	}

	@Test
	public final void feat02() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("IS", "is",2);
		val t02_a = word("A", "a",3);
		val t03_man = word("N_1", "man",4);
		val t04_from = word("P_I", "from",5);
		val t05_Brasil = word("NP", "Brasil",6);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("IS", "is",2);
		val h02_from = word("P_I", "from",3);
		val h03_Brasil = word("NP", "Brasil",4);

		// create the tree structure for the text;
		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_man, _(t04_from, t05_Brasil,7),8),9),10),11);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_from, h03_Brasil,5),6),7);

		assertProof(tt, th);
	}

	@Test
	public final void feat03() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("IS", "is",2);
		val t02_a = word("A", "a",3);
		val t03_tall = word("MOD_R", "tall",4);
		val t04_man = word("N_1", "man",5);
		val t05_from = word("P_I", "from",6);
		val t06_Brasil = word("NP", "Brasil",7);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("IS", "is",2);
		val h02_from = word("P_I", "from",3);
		val h03_Brasil = word("NP", "Brasil",4);

		// create the tree structure for the text;
		val tt = _(
				t00_jan,
				_(t01_is,
						_(t02_a,
								_(_(t03_tall, t04_man,8), _(t05_from, t06_Brasil,9),10),11),12),13);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_from, h03_Brasil,5),6),7);

		assertProof(tt, th);
	}

	@Test
	public final void feat04() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("IS", "is",2);
		val t02_a = word("A", "a",3);
		val t03_tall = word("MOD_R", "tall",4);
		val t04_man = word("N_1", "man",5);
		val t05_from = word("P_I", "from",6);
		val t06_Brasil = word("NP", "Brasil",7);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("IS", "is",2);
		val h02_from = word("P_I", "from",3);
		val h03_Brasil = word("NP", "Brasil",4);

		// create the tree structure for the text;
		val tt = _(
				t00_jan,
				_(t01_is,
						_(t02_a,
								_(t03_tall, _(t04_man, _(t05_from, t06_Brasil,8),9),10),11),12),13);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_from, h03_Brasil,5),6),7);

		assertProof(tt, th);
	}
	
	@Test
	public final void feat05() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("V_2", "ate",2);
		val t02_a = word("A", "a",3);
		val t03_man = word("N_1", "cake",4);
		val t04_from = word("P_R", "with",5);
		val t05_Brasil = word("NP", "pleasure",6);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("V_2", "ate",2);
		val h02_a = word("A", "a",3);
		val h03_man = word("N_1", "cake",4);

		// create the tree structure for the text;
		val a = _(t01_is, _(t02_a, t03_man, 6), 7);
		val b = _(t04_from, t05_Brasil,8);
		
		val tt = _(t00_jan, _(a,b,9),10);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, h03_man,5),6),7);

		assertProof(tt, th);
	}

	@Test
	public final void feat06() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("V_2", "ate",2);
		val t02_a = word("THE", "the",3);
		val t03_man = word("N_1", "cake",4);
		val t04_from = word("P_R", "with",5);
		val t05_Brasil = word("NP", "Bill",6);
//		val t06 = word("P_R", "in",7);
//		val t07 = word("NP", "Scotland",8);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("V_2", "ate",2);
		val h02_a = word("THE", "the",3);
		val h03_man = word("N_1", "cake",4);

		// create the tree structure for the text;
		val a = _(t01_is, _(t02_a, t03_man, 6), 7);
		val b = _(t04_from, t05_Brasil,8);
		
		val tt = _(t00_jan, _(a,b,9),10);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, h03_man,5),6),7);

		assertProof(tt, th);
	}
	
	@Test
	public final void feat07() throws Exception {

		// create the vocabulary for the text;
		val t00_jan = word("NP", "jan",1);
		val t01_is = word("V_2", "ate",2);
		val t02_a = word("THE", "the",3);
		val t03_man = word("N_1", "cake",4);
		val t04_from = word("P_R", "with",5);
		val t05_Brasil = word("NP", "Bill",6);
		val t06 = word("P_R", "in",7);
		val t07 = word("NP", "Scotland",8);
		val t08 = word("P_R", "during",9);
		val t09 = word("NP", "Summertime",10);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is = word("V_2", "ate",2);
		val h02_a = word("THE", "the",3);
		val h03_man = word("N_1", "cake",4);

		// create the tree structure for the text;
		val a = _(t01_is, _(t02_a, t03_man, 16), 17);
		val b = _(t04_from, t05_Brasil,18);
		val c = _(t06, t07,19);
		val d = _(t08, t09,29);
		
		val tt = _(t00_jan, _(_(_(a,b,20),c,21),d,22),23);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, h03_man,5),6),7);

		assertProof(tt, th);
	}

}
