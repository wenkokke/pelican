package semante.pipeline.gerunds;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseRTE3dev783 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createEntailment());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE3dev783",createEntailment());
		}

		public final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_soldier = word("N","soldier");
			val t02_app = word("WHO_A","APP");
			val t03_james = word("$NPC_1$","James");
			val t04_barker = word("NP_D","Barker");
			val t05_app = word("WHO_A","APP");
			val t06_the = word("THE","the");
			val t07_american = word("MI","American");
			val t08_soldier = word("N","soldier");
			val t09_app = word("WHO_R","APP");
			val t10_accused = word("V_1","accused");
			val t11_of = word("P_R","of");
			val t12_raping = word("GER_2","raping");
			val t13_the = word("THE","the");
			val t14_iraqi = word("MR","Iraqi");
			val t15_girl = word("N","girl");
			val t16_and = word("AND","and");
			val t17_killing = word("GER_2","killing");
			val t18_the = word("THE","the");
			val t19_family = word("N","family");
			val t20_in = word("P_R","in");
			val t21_the = word("THE","the");
			val t22_march = word("MR","March");
			val t23_2006 = word("NUMBER","2006");
			val t24_incident = word("N","incident");
			val t25_pleaded = word("V_2","pleaded");
			val t26_det = word("EMPTYDET","DET");
			val t27_guilty = word("N","guilty");
			val t28_to = word("P_R","to");
			val t29_murder = word("N","murder");
			val t30_on = word("P_R","on");
			val t31_wednesday = word("NP_D","Wednesday");
			val t32_in = word("P_R","in");
			val t33_fort = word("$NPC_1$","Fort");
			val t34_campbell = word("NP_D","Campbell");
			val t35_app = word("WHO_A","APP");
			val t36_app = word("IS","APP");
			val t37_in = word("P_R","in");
			val t38_kentucky = word("NP_D","Kentucky");

			// create the vocabulary for the hypothesis;
			val h00_james = word("$NPC_1$","James");
			val h01_barker = word("NP_D","Barker");
			val h02_is = word("IS","is");
			val h03_accused = word("V_1","accused");
			val h04_of = word("P_R","of");
			val h05_raping = word("GER_2","raping");
			val h06_an = word("A","an");
			val h07_iraqi = word("MR","Iraqi");
			val h08_girl = word("N","girl");
			val h09_and = word("AND","and");
			val h10_killing = word("GER_2","killing");
			val h11_the = word("THE","the");
			val h12_family = word("N","family");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t00_the
							,
							t01_soldier
						)
						,
						_(
							t02_app
							,
							_(
								t03_james
								,
								t04_barker
							)
						)
					)
					,
					_(
						t05_app
						,
						_(
							t06_the
							,
							_(
								_(
									t07_american
									,
									t08_soldier
								)
								,
								_(
									t09_app
									,
									_(
										t10_accused
										,
										_(
											t11_of
											,
											_(
												_(
													_(
														t12_raping
														,
														_(
															t13_the
															,
															_(
																t14_iraqi
																,
																t15_girl
															)
														)
													)
													,
													_(
														t16_and
														,
														_(
															t17_killing
															,
															_(
																t18_the
																,
																t19_family
															)
														)
													)
												)
												,
												_(
													t20_in
													,
													_(
														t21_the
														,
														_(
															t22_march
															,
															_(
																t23_2006
																,
																t24_incident
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
				,
				_(
					_(
						_(
							t25_pleaded
							,
							_(
								t26_det
								,
								_(
									t27_guilty
									,
									_(
										t28_to
										,
										t29_murder
									)
								)
							)
						)
						,
						_(
							t30_on
							,
							t31_wednesday
						)
					)
					,
					_(
						t32_in
						,
						_(
							_(
								t33_fort
								,
								t34_campbell
							)
							,
							_(
								t35_app
								,
								_(
									t36_app
									,
									_(
										t37_in
										,
										t38_kentucky
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
					h00_james
					,
					h01_barker
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
							_(
								_(
									h05_raping
									,
									_(
										h06_an
										,
										_(
											h07_iraqi
											,
											h08_girl
										)
									)
								)
								,
								_(
									h09_and
									,
									_(
										h10_killing
										,
										_(
											h11_the
											,
											h12_family
										)
									)
								)
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
