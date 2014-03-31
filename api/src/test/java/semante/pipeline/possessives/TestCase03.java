package semante.pipeline.possessives;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCase03 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createEntailment());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("TestCase03",createEntailment());
		}

		public final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_paul  = word("NP","Paul");
			val t01_hates = word("V_2","hates");
			val t02_john  = word("NP","John");
			val t03_s     = word("GEN","'s");
			val t04_wife  = word("N_2","wife");
			val t05_app   = word("WHO_A","APP");
			val t06_yoko  = word("$NPC_1$","Yoko");
			val t07_ono   = word("NP","Ono");

			// create the vocabulary for the hypothesis;
			val h00_paul  = word("NP","Paul");
			val h01_hates = word("V_2","hates");
			val h02_yoko  = word("$NPC_1$","Yoko");
			val h03_ono   = word("NP","Ono");

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
						h02_yoko
						,
						h03_ono
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
