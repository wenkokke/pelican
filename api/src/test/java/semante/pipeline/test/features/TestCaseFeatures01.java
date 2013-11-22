package semante.pipeline.test.features;

import lombok.val;

import org.junit.Test;

import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

public final class TestCaseFeatures01 extends ATestCase {

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
			val t03_man 	= word("N_1","man");
			val t04_from 	= word("P_I","from");
			val t05_Brasil 	= word("NP","Brasil");

			// create the vocabulary for the hypothesis;
			val h00_jan 	= word("NP","jan");
			val h01_is 		= word("IS","is");
			val h02_a 		= word("A","a");
			val h03_man 	= word("N_1","man");

			
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
							t03_man
							,
							_(
								t04_from
								,
								t05_Brasil
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
						h03_man
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

