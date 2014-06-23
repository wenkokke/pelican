package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0022 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0022() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_theory = word("N","theory");
			val t02_app = word("WHO_A","APP");
			val t03_a = word("A","a");
			val t04_concept = word("N","concept");
			val t05_app = word("WHO_R","APP");
			val t06_first = word("MR","first");
			val t07_proposed = word("V_1","proposed");
			val t08_by = word("P_R","by");
			val t09_albert = word("$NPC_1$","Albert");
			val t10_einstein = word("NP_D","Einstein");
			val t11_and = word("AND","and");
			val t12_often = word("MR","often");
			val t13_reiterated = word("V_1","reiterated");
			val t14_by = word("P_R","by");
			val t15_det = word("THE","DET");
			val t16_british = word("MR","British");
			val t17_astrophysicist = word("N","astrophysicist");
			val t18_app = word("WHO_A","APP");
			val t19_stephen = word("$NPC_1$","Stephen");
			val t20_hawking = word("NP_D","Hawking");
			val t21_seeks = word("V_2","seeks");
			val t22_a = word("A","a");
			val t23_single = word("MR","single");
			val t24_set = word("N","set");
			val t25_of = word("P_R","of");
			val t26_equations = word("N","equations");
			val t27_that = word("WHO_R","that");
			val t28_can = word("V_AUX","can");
			val t29_explain = word("V_2","explain");
			val t30_the = word("THE","the");
			val t31_fundamental = word("MR","fundamental");
			val t32_force = word("N","force");
			val t33_in = word("P_R","in");
			val t34_the = word("THE","the");
			val t35_universe = word("N","universe");
			val t36_app = word("WHO_A","APP");
			val t37_the = word("THE","the");
			val t38_strong = word("MR","strong");
			val t39_interactive = word("MR","interactive");
			val t40_forces = word("N","forces");
			val t41_among = word("P_R","among");
			val t42_subnuclear = word("MR","subnuclear");
			val t43_particles = word("N","particles");

			// create the vocabulary for the hypothesis;
			val h00_stephen = word("$NPC_1$","Stephen");
			val h01_hawking = word("NP_D","Hawking");
			val h02_is = word("IS","is");
			val h03_a = word("A","a");
			val h04_physicist = word("N","physicist");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						t01_theory
					)
					,
					_(
						t02_app
						,
						_(
							t03_a
							,
							_(
								t04_concept
								,
								_(
									t05_app
									,
									_(
										_(
											_(
												t06_first
												,
												_(
													t07_proposed
													,
													_(
														t08_by
														,
														_(
															t09_albert
															,
															t10_einstein
														)
													)
												)
											)
											,
											t11_and
										)
										,
										_(
											t12_often
											,
											_(
												t13_reiterated
												,
												_(
													t14_by
													,
													_(
														_(
															t15_det
															,
															_(
																t16_british
																,
																t17_astrophysicist
															)
														)
														,
														_(
															t18_app
															,
															_(
																t19_stephen
																,
																t20_hawking
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
					t21_seeks
					,
					_(
						t22_a
						,
						_(
							t23_single
							,
							_(
								t24_set
								,
								_(
									t25_of
									,
									_(
										t26_equations
										,
										_(
											t27_that
											,
											_(
												t28_can
												,
												_(
													t29_explain
													,
													_(
														_(
															t30_the
															,
															_(
																_(
																	t31_fundamental
																	,
																	t32_force
																)
																,
																_(
																	t33_in
																	,
																	_(
																		t34_the
																		,
																		t35_universe
																	)
																)
															)
														)
														,
														_(
															t36_app
															,
															_(
																t37_the
																,
																_(
																	t38_strong
																	,
																	_(
																		t39_interactive
																		,
																		_(
																			t40_forces
																			,
																			_(
																				t41_among
																				,
																				_(
																					t42_subnuclear
																					,
																					t43_particles
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
					h00_stephen
					,
					h01_hawking
				)
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						h04_physicist
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_astrophysicist = word("N","astrophysicist");
			val sh000_astrophysicist = word("N","astrophysicist");
val st0 = 
			st000_astrophysicist
			;
val sh0 = 
			sh000_astrophysicist
			;
val ss0 = subs(st0, sh0);
val subs = of(
			ss0
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, subs, Proof);
		}

}

