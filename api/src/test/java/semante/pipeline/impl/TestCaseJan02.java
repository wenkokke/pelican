package semante.pipeline.impl;

import semante.pipeline.Entailment;
import org.junit.Test;
import lombok.val;

public final class TestCaseJan02 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createJan02());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("Jan02",createJan02());
		}

		public final Entailment createJan02() throws Exception {

			// create the vocabulary for the text;
			val t00_jan = word("NP","jan");
			val t01_is = word("IS","is");
			val t02_a = word("A","a");
			val t03_black = word("MOD_I","black");
			val t04_dutch = word("MOD_I","dutch");
			val t05_man = word("N_1","man");

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
							t03_black
							,
							_(
								t04_dutch
								,
								t05_man
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

