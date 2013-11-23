package semante.pipeline.test.features;

import lombok.val;

import org.junit.Test;

import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

public final class TestCaseFeatures03 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createJan01());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("Features01",createJan01());
		}

		public final Entailment createJan01() throws Exception {

			// create the vocabulary for the text;
			val t00_jan 	= word("NP","jan");
			val t01_is 		= word("IS","is");
			val t02_a 		= word("A","a");
			val t03_tall	= word("MOD_R","tall");
			val t04_man 	= word("N_1","man");
			val t05_from 	= word("P_I","from");
			val t06_Brasil 	= word("NP","Brasil");

			// create the vocabulary for the hypothesis;
			val h00_jan 	= word("NP","jan");
			val h01_is 		= word("IS","is");
			val h02_from 	= word("P_I","from");
			val h03_Brasil 	= word("NP","Brasil");

			
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
							_(
								t03_tall
								,
								t04_man
							)
							,
							_(
								t05_from
								,
								t06_Brasil
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
						h02_from
							,
						h03_Brasil
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

