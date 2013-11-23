package semante.flattener.rte;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

import org.junit.Test;

import lombok.val;

public final class RTE4test927 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE4test927());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE4test927",createRTE4test927());
		}

		public final Entailment createRTE4test927() throws Exception {

			// create the vocabulary for the text;
			val t00_some = word("SOME","Some");
			val t01_people = word("N","people");
			val t02_were = word("IS","were");
			val t03_brutally = word("MR","brutally");
			val t04_killed = word("V_1","killed");
			val t05_and = word("AND","and");
			val t06_undressed = word("V_1","undressed");
			val t07_in = word("P_R","in");
			val t08_vencesla = word("NP_D","Vencesla");
			val t09_which = word("WHO_A","which");
			val t10_is = word("IS","is");
			val t11_in = word("P_R","in");
			val t12_brazil = word("NP_D","Brazil");
			val t13_by = word("P_R","by");
			val t14_the = word("THE","the");
			val t15_rioters = word("N","rioters");
			val t16_during = word("P_R","during");
			val t17_a = word("A","a");
			val t18_violent = word("MR","violent");
			val t19_riot = word("N","riot");
			val t20_in = word("P_R","in");
			val t21_the = word("THE","the");
			val t22_ferreira = word("NP_D","Ferreira");
			val t23_app = word("WHO_A","APP");
			val t24_a = word("A","a");
			val t25_prison = word("N","prison");

			// create the vocabulary for the hypothesis;
			val h00_some = word("SOME","Some");
			val h01_people = word("N","people");
			val h02_were = word("IS","were");
			val h03_killed = word("V_1","killed");
			val h04_in = word("P_R","in");
			val h05_brazil = word("NP_D","Brazil");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_some
					,
					t01_people
				)
				,
				_(
					t02_were
					,
					_(
						t03_brutally
						,
						_(
							_(
								_(
									_(
										_(
											t04_killed
											,
											t05_and
										)
										,
										t06_undressed
									)
									,
									_(
										t07_in
										,
										_(
											t08_vencesla
											,
											_(
												t09_which
												,
												_(
													t10_is
													,
													_(
														t11_in
														,
														t12_brazil
													)
												)
											)
										)
									)
								)
								,
								_(
									t13_by
									,
									_(
										t14_the
										,
										t15_rioters
									)
								)
							)
							,
							_(
								t16_during
								,
								_(
									t17_a
									,
									_(
										t18_violent
										,
										_(
											t19_riot
											,
											_(
												t20_in
												,
												_(
													_(
														t21_the
														,
														t22_ferreira
													)
													,
													_(
														t23_app
														,
														_(
															t24_a
															,
															t25_prison
														)
													)
												)
											)
										)
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
					h00_some
					,
					h01_people
				)
				,
				_(
					h02_were
					,
					_(
						h03_killed
						,
						_(
							h04_in
							,
							h05_brazil
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
