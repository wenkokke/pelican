package semante.pipeline.test.rte;

import org.junit.Test;

import lombok.val;
import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

public final class TestCaseRTE1dev121 extends ATestCase {

//		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1dev121());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1dev121",createRTE1dev121());
		}

		public final Entailment createRTE1dev121() throws Exception {

			// create the vocabulary for the text;
			val t00_a = word("A","a");
			val t01_syrian = word("MOD_R","syrian");
			val t02_airman = word("N_1","airman");
			val t03_accused = word("V_1","accused");
			val t04_of = word("P_R","of");
			val t05_espionage = word("NP","espionage");
			val t06_complained = word("V_1","complained");
			val t07_about = word("P_R","about");
			val t08_prisoner_treatment = word("NP","prisoner_treatment");
			val t10_at = word("P_R","at");
			val t11_the = word("THE","the");
			val t12_us = word("MOD_R","us");
			val t13_base = word("N_1","base");
			val t14_in = word("P_R","in");
			val t15_guantanamo = word("NP","guantanamo");
//			val t16_in = word("P_R","in");
//			val t17_cuba = word("NP","cuba");

			// create the vocabulary for the hypothesis;
			val h00_american = word("MOD_R","american");
			val h01_accused = word("V_1","accused");
			val h02_of = word("P_R","of");
			val h03_espionage = word("NP","espionage");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_a
						,
						_(
							t01_syrian
							,
							t02_airman
						)
					)
					,
					_(
						t03_accused
						,
						_(
							t04_of
							,
							t05_espionage
						)
					)
				)
				,
				_(
					_(
						t06_complained
						,
						_(
							t07_about
							,
							t08_prisoner_treatment
						)
					)
					,
					_(
						t10_at
						,
						_(
							_(
								t11_the
								,
								_(
									t12_us
									,
									t13_base
								)
							)
							,
							_(
								t14_in
								,
								t15_guantanamo
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_american
				,
				_(
					h01_accused
					,
					_(
						h02_of
						,
						h03_espionage
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

