package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestBarePlurals extends AbsPipelineTest {

	@Test
	public final void bare01() throws Exception {

		// create the vocabulary for the text;
		val emptydet = word("EMPTYDET", "");
		val people = word("N", "people");
		val ran = word("V_1", "ran");
		val fast = word("MOD_R", "fast");

		// create the tree structure for the text;
		val tt = _(_(emptydet, people), _(ran, fast));

		// create the tree structure for the hypothesis;
		val th = _(_(emptydet, people), ran);

		assertProof(tt, th);
	}

	@Test
	public final void bare02() throws Exception {

		// create the vocabulary for the text;
		val emptydet = word("EMPTYDET", "");
		val t01_john = word("NP", "john");
		val t02_wrote = word("V_2", "wrote");
		val t03_a = word("A", "a");
		val t04_book = word("N", "book");
		val t05_of = word("P_R", "of");
		val t06_short = word("MOD_R", "short");
		val t07_stories = word("N", "stories");

		// create the tree structure for the text;
		val tt = _(
				t01_john,
				_(t02_wrote,
						_(t03_a,
								_(t04_book,
										_(t05_of,
												_(emptydet,
														_(t06_short,
																t07_stories)))))));

		// create the tree structure for the hypothesis;
		val th = _(t01_john, _(t02_wrote, _(t03_a, t04_book)));

		assertProof(tt, th);
	}

}
