package semante.pipeline.possessives;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCase04 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createEntailment());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("TestCase04",createEntailment());
		}

		public final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_paul  = word("NP_D","Paul");
			val t01_hates = word("V_2","hates");
			val t02_john  = word("NP_D","John");
			val t03_s     = word("GEN","'s");
			val t04_wife  = word("N_2","wife");
			val t05_app   = word("WHO_A","APP");
			val t06_yoko  = word("$NPC_1$","Yoko");
			val t07_ono   = word("NP_D","Ono");

			// create the vocabulary for the hypothesis;
			val h00_paul  = word("NP_D","Paul");
			val h01_hates = word("V_2","hates");
			val h02_the   = word("THE","the");
			val h03_wife  = word("N_2","wife");
			val h04_of    = word("OF","of");
			val h05_john  = word("NP_D","John");

			// create the tree structure for the text;
			val tt =
			_(
				t00_paul
				,
				_(
					t01_hates
					,
					_(
						_(
							_(
								t02_john
								,
								t03_s
							)
							,
							t04_wife
						)
						,
						_(
							t05_app
							,
							_(
								t06_yoko
								,
								t07_ono
							)
						)
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
							h03_wife
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
