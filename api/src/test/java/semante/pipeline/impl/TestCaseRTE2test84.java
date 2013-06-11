package semante.pipeline.impl;

import semante.pipeline.Entailment;
import org.junit.Test;
import lombok.val;

public final class TestCaseRTE2test84 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE2test84());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE2test84",createRTE2test84());
		}

		public final Entailment createRTE2test84() throws Exception {

			// create the vocabulary for the text;
			val t00_salvadoran = word("MOD_R","salvadoran");
			val t01_reporter = word("N_1","reporter");
			val t02_mauricio_pineda = word("NP","mauricio pineda");
			val t03_a = word("A","a");
			val t04_sound = word("MOD_R","sound");
			val t05_technician = word("N_1","technician");
			val t06_for = word("P_R","for");
			val t07_the = word("THE","the");
			val t08_local = word("MOD_R","local");
			val t09_canal = word("N_1","canal");
			val t10_doce = word("NP","Doce");
			val t11_television = word("N_1","television");
			val t12_station = word("N_1","station");
			val t13_was = word("IS","was");
			val t14_shot = word("V_1","shot");
			val t15_and = word("AND","and");
			val t16_killed = word("V_1","killed");
			val t17_today = word("N_1","today");
			val t18_in = word("P_R","in");
			val t19_morazan = word("NP","morazan");
			val t20_department = word("N_1","department");
			val t21_in = word("P_R","in");
			val t22_the = word("THE","the");
			val t23_eastern = word("MOD_R","eastern");
			val t24_part = word("N_1","part");
			val t25_of = word("P_R","of");
			val t26_the = word("THE","the");
			val t27_country = word("N_1","country");

			// create the vocabulary for the hypothesis;
			val h00_mauricio_pineda = word("NP","mauricio pineda");
			val h01_was = word("IS","was");
			val h02_killed = word("V_1","killed");
			val h03_in = word("P_R","in");
			val h04_morazan = word("NP","morazan");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_salvadoran
						,
						_(
							t01_reporter
							,
							t02_mauricio_pineda
						)
					)
					,
					_(
						_(
							_(
								t03_a
								,
								_(
									t04_sound
									,
									t05_technician
								)
							)
							,
							_(
								t06_for
								,
								_(
									t07_the
									,
									_(
										t08_local
										,
										t09_canal
									)
								)
							)
						)
						,
						_(
							t10_doce
							,
							_(
								t11_television
								,
								t12_station
							)
						)
					)
				)
				,
				_(
					t13_was
					,
					_(
						_(
							_(
								_(
									t14_shot
									,
									t15_and
								)
								,
								t16_killed
							)
							,
							_(
								t17_today
								,
								_(
									t18_in
									,
									_(
										t19_morazan
										,
										t20_department
									)
								)
							)
						)
						,
						_(
							t21_in
							,
							_(
								_(
									t22_the
									,
									_(
										t23_eastern
										,
										t24_part
									)
								)
								,
								_(
									t25_of
									,
									_(
										t26_the
										,
										t27_country
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
				h00_mauricio_pineda
				,
				_(
					h01_was
					,
					_(
						h02_killed
						,
						_(
							h03_in
							,
							h04_morazan
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

