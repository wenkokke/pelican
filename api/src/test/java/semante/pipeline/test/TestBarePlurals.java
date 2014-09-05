package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestBarePlurals extends AbsPipelineTest<Integer> {

	@Test
	public final void bare01() throws Exception {

		// create the vocabulary for the text;
		val emptydet = word("EMPTYDET", "",1);
		val people = word("N", "people",2);
		val ran = word("V_1", "ran",3);
		val fast = word("MOD_R", "fast",4);

		// create the vocabulary for the hypothesis;
		val hpeople = word("N", "people",1);
		val hran = word("V_1", "ran",2);
		val hemptydet = word("EMPTYDET", "",3);
		
		// create the tree structure for the text;
		val tt = _(_(emptydet, people,5), _(ran, fast,6),7);

		// create the tree structure for the hypothesis;
		val th = _(_(hemptydet, hpeople,4), hran,5);

		assertProof(tt, th);
	}

	@Test
	public final void bare02() throws Exception {

		// create the vocabulary for the text;
		val emptydet = word("EMPTYDET", "",1);
		val t01_john = word("NP", "john",2);
		val t02_wrote = word("V_2", "wrote",3);
		val t03_a = word("A", "a",4);
		val t04_book = word("N", "book",5);
		val t05_of = word("P_R", "of",6);
		val t06_short = word("MOD_R", "short",7);
		val t07_stories = word("N", "stories",8);

		// create the vocabulary for the hypothesis;
		val h01_john = word("NP", "john",1);
		val h02_wrote = word("V_2", "wrote",2);
		val h03_a = word("A", "a",3);
		val h04_book = word("N", "book",4);
		
		// create the tree structure for the text;
		val tt = _(
				t01_john,
				_(t02_wrote,
						_(t03_a,
								_(t04_book,
										_(t05_of,
												_(emptydet,
														_(t06_short,
																t07_stories,9),10),11),12),13),14),15);

		// create the tree structure for the hypothesis;
		val th = _(h01_john, _(h02_wrote, _(h03_a, h04_book,5),6),7);

		assertProof(tt, th);
	}
}
