package semante.pipeline.possessives;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCase05 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createEntailment());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("TestCase05",createEntailment());
		}

		public final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_sue   = word("NP","Paul");
			val t01_hates = word("V_2","hates");
			val t02_john  = word("NP","John");
			val t03_s     = word("GEN","'s");
			val t04_dog   = word("N","dog");

			// create the vocabulary for the hypothesis;
			val h00_paul  = word("NP","Paul");
			val h01_hates = word("V_2","hates");
			val h02_the   = word("THE","the");
			val h03_dog   = word("N","dog");
			val h04_of    = word("P_R","of");
			val h05_john  = word("NP","John");

			// create the tree structure for the text;
			val tt =
			_(
				t00_sue
				,
				_(
					t01_hates
					,
					_(
						_(
							t02_john
							,
							t03_s
						)
						,
						t04_dog
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_paul
				,
				_(
					h01_hates
					,
					_(
						h02_the
						,
						_(
							h03_dog
							,
							_(
								h04_of
								,
								h05_john
							)
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
