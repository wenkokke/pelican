package semante.flattener.rte;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class RTE1dev119 extends APipelineTest {

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
			val t00_a = word("A","A");
			val t01_cuban = word("MR","Cuban");
			val t02_american = word("N","American");
			val t03_who = word("WHO_R","who");
			val t04_is = word("IS","is");
			val t05_accused = word("V_1","accused");
			val t06_of = word("P_R","of");
			val t07_espionage = word("NP_D","Espionage");
			val t08_is = word("IS","is");
			val t09_innocent = word("MR","innocent");

			// create the vocabulary for the hypothesis;
			val h00_an = word("A","An");
			val h01_american = word("N","American");
			val h02_is = word("IS","is");
			val h03_accused = word("V_1","accused");
			val h04_of = word("P_R","of");
			val h05_espionage = word("NP_D","Espionage");

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
				_(
					t08_is
					,
					t09_innocent
				)
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
