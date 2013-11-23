package semante.pipeline.jan;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseJan08 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createJan08());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("Jan08",createJan08());
		}

		public final Entailment createJan08() throws Exception {

			// create the vocabulary for the text;
			val t00_jan = word("NP","jan");
			val t01_is = word("IS","is");
			val t02_a = word("A","a");
			val t03_tall = word("MOD_R","tall");
			val t04_black = word("MOD_I","black");
			val t05_fat = word("MOD_R","fat");
			val t06_dutch = word("MOD_I","dutch");
			val t07_man = word("N_1","man");

			// create the vocabulary for the hypothesis;
			val h00_jan = word("NP","jan");
			val h01_is = word("IS","is");
			val h02_a = word("A","a");
			val h03_black = word("MOD_I","black");
			val h04_man = word("N_1","man");

			// create the tree structure for the text;
			val tt =
			_(
				t00_jan
				,
				_(
					t01_is
					,
					_(
						t02_a
						,
						_(
							t03_tall
							,
							_(
								t04_black
								,
								_(
									t05_fat
									,
									_(
										t06_dutch
										,
										t07_man
									)
								)
							)
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
					h01_is
					,
					_(
						h02_a
						,
						_(
							h03_black
							,
							h04_man
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

