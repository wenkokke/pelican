package semante.flattener.test;

import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

import org.junit.Test;
import lombok.val;

public final class RTE1dev1005 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1dev1005());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1dev1005",createRTE1dev1005());
		}

		public final Entailment createRTE1dev1005() throws Exception {

			// create the vocabulary for the text;
			val t00_jessica = word("NP_D","Jessica");
			val t01_app = word("WHO_A","APP");
			val t02_a = word("A","a");
			val t03_professor = word("N","professor");
			val t04_at = word("P_R","at");
			val t05_michigan = word("NP_D","Michigan");
			val t06_has = word("V_AUX","has");
			val t07_specialized = word("V_1","specialized");
			val t08_in = word("P_R","in");
			val t09_the = word("THE","the");
			val t10_claw = word("NP_D","Claw");
			val t11_for = word("P_R","for");
			val t12_some = word("SOME","some");
			val t13_years = word("N","years");

			// create the vocabulary for the hypothesis;
			val h00_jessica = word("NP_D","Jessica");
			val h01_is = word("IS","is");
			val h02_a = word("A","a");
			val h03_professor = word("N","professor");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_jessica
					,
					_(
						t01_app
						,
						_(
							t02_a
							,
							_(
								t03_professor
								,
								_(
									t04_at
									,
									t05_michigan
								)
							)
						)
					)
				)
				,
				_(
					t06_has
					,
					_(
						_(
							t07_specialized
							,
							_(
								t08_in
								,
								_(
									t09_the
									,
									t10_claw
								)
							)
						)
						,
						_(
							t11_for
							,
							_(
								t12_some
								,
								t13_years
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_jessica
				,
				_(
					h01_is
					,
					_(
						h02_a
						,
						h03_professor
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
