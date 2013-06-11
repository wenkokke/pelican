package semante.pipeline.impl;

import semante.pipeline.Entailment;
import org.junit.Test;
import lombok.val;

public final class TestCaseRTE1dev648 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1dev648());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1dev648",createRTE1dev648());
		}

		public final Entailment createRTE1dev648() throws Exception {

			// create the vocabulary for the text;
			val t00_yoko_ono = word("NP","Yoko Ono");
			val t01_who = word("WHO_A","who");
			val t02_is = word("IS","is");
			val t03_the = word("THE","the");
			val t04_widow = word("N_2","widow");
			val t05_of = word("OF","of");
			val t06_the = word("THE","the");
			val t07_murdered = word("MOD_R","murdered");
			val t08_beatles = word("MOD_R","beatles");
			val t09_star = word("N_1","star");
			val t10_who = word("WHO_A","who");
			val t11_is = word("IS","is");
			val t12_john_lennon = word("NP","John Lennon");
			val t13_has = word("V_AUX","has");
			val t14_plastered = word("V_2","plastered");
			val t15_the = word("THE","the");
			val t16_small = word("MOD_R","small");
			val t17_german = word("MOD_R","german");
			val t18_town = word("N_1","town");
			val t19_which = word("WHO_A","which");
			val t20_is = word("IS","is");
			val t21_langenhagen = word("N_1","langenhagen");
			val t22_with = word("P_R","with");
			val t23_backsides = word("NP","backsides");

			// create the vocabulary for the hypothesis;
			val h00_yoko_ono = word("NP","Yoko Ono");
			val h01_was = word("IS","was");
			val h02_john_lennon = word("NP","John Lennon");
			val h03_s = word("GEN","'s");
			val h04_wife = word("N_2","wife");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_yoko_ono
					,
					_(
						t01_who
						,
						_(
							t02_is
							,
							_(
								t03_the
								,
								_(
									t04_widow
									,
									_(
										t05_of
										,
										_(
											_(
												t06_the
												,
												_(
													t07_murdered
													,
													_(
														t08_beatles
														,
														t09_star
													)
												)
											)
											,
											_(
												t10_who
												,
												_(
													t11_is
													,
													t12_john_lennon
												)
											)
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					_(
						_(
							t13_has
							,
							t14_plastered
						)
						,
						_(
							_(
								t15_the
								,
								_(
									t16_small
									,
									_(
										t17_german
										,
										t18_town
									)
								)
							)
							,
							_(
								t19_which
								,
								_(
									t20_is
									,
									t21_langenhagen
								)
							)
						)
					)
					,
					_(
						t22_with
						,
						t23_backsides
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_yoko_ono
				,
				_(
					h01_was
					,
					_(
						_(
							h02_john_lennon
							,
							h03_s
						)
						,
						h04_wife
					)
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				"all x (all y (widow(x,y) -> wife(x,y)))."
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}

