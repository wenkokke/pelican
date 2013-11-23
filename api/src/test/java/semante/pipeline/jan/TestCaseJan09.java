package semante.pipeline.jan;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseJan09 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createJan09());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("Jan09",createJan09());
		}

		public final Entailment createJan09() throws Exception {

			// create the vocabulary for the text;
			val t00_jan = word("NP","jan");
			val t01_found = word("V_2","found");
			val t02_the = word("THE","the");
			val t03_book = word("N_1","book");
			val t04_and = word("AND","and");
			val t05_every = word("EVERY","every");
			val t06_book = word("N_1","book");
			val t07_is = word("IS","is");
			val t08_blue = word("MOD_I","blue");

			// create the vocabulary for the hypothesis;
			val h00_jan = word("NP","jan");
			val h01_found = word("V_2","found");
			val h02_the = word("THE","the");
			val h03_blue = word("MOD_I","blue");
			val h04_book = word("N_1","book");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_jan
					,
					_(
						t01_found
						,
						_(
							t02_the
							,
							t03_book
						)
					)
				)
				,
				_(
					t04_and
					,
					_(
						_(
							t05_every
							,
							t06_book
						)
						,
						_(
							t07_is
							,
							t08_blue
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_jan
				,
				_(
					h01_found
					,
					_(
						h02_the
						,
						_(
							h03_blue
							,
							h04_book
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				""
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}

