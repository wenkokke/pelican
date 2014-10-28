package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestQuantMod extends AbsPipelineTest<Integer> {

	@Test
	public final void indefModByPP() throws Exception {

		// create the vocabulary for the text;
		val t00_jan    = word("NP", "jan",1);
		val t01_is     = word("IS", "is",2);
		val t02_a      = word("A", "a",3);
		val t03_man    = word("N_1", "man",4);
		val t04_from   = word("P_R", "from",5);
		val t05_Brasil = word("NP", "Brasil",6);

		// create the vocabulary for the hypothesis;
		val h00_jan    = word("NP", "jan",1);
		val h01_is     = word("IS", "is",2);
		val h02_a      = word("A", "a",3);
		val h03_man    = word("N_1", "man",4);

		// create the tree structure for the text;
		val tt = _(t00_jan,
				_(t01_is, _(t02_a, _(t03_man, _(t04_from, t05_Brasil,7),8),9),10),11);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, h03_man,5),6),7);

		assertProof(tt, th);
	}
	
	@Test
	public final void indefModByConstant() throws Exception {

		// create the vocabulary for the text;
		val t00_jan    = word("NP",  "jan"    ,1);
		val t01_is     = word("IS",  "is"     ,2);
		val t04_almost = word("MR",  "almost" ,5);
		val t02_a      = word("A",   "a"      ,3);
		val t03_man    = word("N_1", "man"    ,4);
		
		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP",     "jan" ,1);
		val h01_is  = word("IS",     "is"  ,2);
		val h02_a   = word("A",      "a"   ,3);
		val h03_man = word("N_1",    "man" ,4);

		// create the tree structure for the text;
		val tt = _(t00_jan, _(t01_is, _(t04_almost, _(t02_a, t03_man, 8),9),10),11);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, h03_man,5),6),7);

		assertException(tt, th);
	}

	@Test
	public final void everyModByConstant() throws Exception {

		// create the vocabulary for the text;
		val t04_almost  = word("MR", "almost",1);
		val t00_every   = word("EVERY", "every",2);
		val t03_man     = word("N_1", "man",3);
		val t02_arrived = word("V_1", "arrived",4);

		// create the vocabulary for the hypothesis;
		val h00_jan = word("NP", "jan",1);
		val h01_is  = word("IS", "is",2);
		val h02_a   = word("A", "a",3);
		val h03_man = word("N_1", "man",4);

		// create the tree structure for the text;
		val tt = _(_(t04_almost,_(t00_every, t03_man,5),6),t02_arrived,7);

		// create the tree structure for the hypothesis;
		val th = _(h00_jan, _(h01_is, _(h02_a, h03_man,5),6),7);

		assertException(tt, th);
	}
	
}
