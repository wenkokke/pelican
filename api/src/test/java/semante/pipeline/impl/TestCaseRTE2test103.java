package semante.pipeline.impl;

import semante.pipeline.Entailment;
import org.junit.Test;
import lombok.val;

public final class TestCaseRTE2test103 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE2test103());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE2test103",createRTE2test103());
		}

		public final Entailment createRTE2test103() throws Exception {

			// create the vocabulary for the text;
			val t00_an = word("A","an");
			val t01_everest = word("MOD_R","everest");
			val t02_summiter = word("N_1","summiter");
			val t03_who = word("WHO_A","who");
			val t04_is = word("IS","is");
			val t05_david_hiddleston = word("NP","david hiddleston");
			val t06_has = word("V_AUX","has");
			val t07_passed_away = word("V_1","passed away");
			val t08_in = word("P_R","in");
			val t09_an = word("A","an");
			val t10_avalanche = word("N_1","avalanche");
			val t11_of = word("P_R","of");
			val t12_mt_tasman = word("NP","mt tasman");

			// create the vocabulary for the hypothesis;
			val h00_a = word("A","a");
			val h01_person = word("N_1","person");
			val h02_died = word("V_1","died");
			val h03_in = word("P_R","in");
			val h04_an = word("A","an");
			val h05_avalanche = word("N_1","avalanche");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_an
						,
						_(
							t01_everest
							,
							t02_summiter
						)
					)
					,
					_(
						t03_who
						,
						_(
							t04_is
							,
							t05_david_hiddleston
						)
					)
				)
				,
				_(
					t06_has
					,
					_(
						t07_passed_away
						,
						_(
							t08_in
							,
							_(
								t09_an
								,
								_(
									t10_avalanche
									,
									_(
										t11_of
										,
										t12_mt_tasman
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
				_(
					h00_a
					,
					h01_person
				)
				,
				_(
					h02_died
					,
					_(
						h03_in
						,
						_(
							h04_an
							,
							h05_avalanche
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				"all x (summiter(x) -> person(x)).",
				"all x (all y (in_died(x,y) -> died(x))).",
				"all x (all y (in_passed_away(x,y) -> in_died(x,y)))."
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}

