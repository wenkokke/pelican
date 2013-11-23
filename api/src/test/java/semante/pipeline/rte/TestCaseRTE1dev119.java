package semante.pipeline.rte;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseRTE1dev119 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1dev119());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1dev119",createRTE1dev119());
		}

		public final Entailment createRTE1dev119() throws Exception {

			// create the vocabulary for the text;
			val t00_a = word("A","a");
			val t01_cuban = word("MOD_R","cuban");
			val t02_american = word("N_1","american");
			val t03_who = word("WHO_R","who");
			val t04_is = word("IS","is");
			val t05_accused = word("V_1","accused");
			val t06_of = word("P_R","of");
			val t07_espionage = word("NP","espionage");
			val t08_pleads_innocent = word("V_1","pleads innocent");

			// create the vocabulary for the hypothesis;
			val h00_an = word("A","an");
			val h01_american = word("N_1","american");
			val h02_is = word("V_AUX","is");
			val h03_accused = word("V_1","accused");
			val h04_of = word("P_R","of");
			val h05_espionage = word("NP","espionage");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_a
					,
					_(
						_(
							t01_cuban
							,
							t02_american
						)
						,
						_(
							t03_who
							,
							_(
								t04_is
								,
								_(
									t05_accused
									,
									_(
										t06_of
										,
										t07_espionage
									)
								)
							)
						)
					)
				)
				,
				t08_pleads_innocent
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_an
					,
					h01_american
				)
				,
				_(
					h02_is
					,
					_(
						h03_accused
						,
						_(
							h04_of
							,
							h05_espionage
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

