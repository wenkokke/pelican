package semante.pipeline.jan;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseJan07 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createJan07());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("Jan07",createJan07());
		}

		public final Entailment createJan07() throws Exception {

			// create the vocabulary for the text;
			val t00_dan = word("NP","dan");
			val t01_who = word("WHO_A","who");
			val t02_is = word("IS","is");
			val t03_kind = word("MOD_R","kind");
			val t04_gave = word("V_3","gave");
			val t05_mary = word("NP","mary");
			val t06_a = word("A","a");
			val t07_brand_new = word("MOD_I","brand new");
			val t08_book = word("N_1","book");

			// create the vocabulary for the hypothesis;
			val h00_dan = word("NP","dan");
			val h01_gave = word("V_3","gave");
			val h02_mary = word("NP","mary");
			val h03_a = word("A","a");
			val h04_book = word("N_1","book");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_dan
					,
					_(
						t01_who
						,
						_(
							t02_is
							,
							t03_kind
						)
					)
				)
				,
				_(
					_(
						t04_gave
						,
						t05_mary
					)
					,
					_(
						t06_a
						,
						_(
							t07_brand_new
							,
							t08_book
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_dan
				,
				_(
					_(
						h01_gave
						,
						h02_mary
					)
					,
					_(
						h03_a
						,
						h04_book
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

