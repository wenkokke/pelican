package semante.pipeline.test.rte.shared1.pass;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0014 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0014() throws Exception {

			// create the vocabulary for the text;
			val t00_white = word("NP_D","White");
			val t01_is = word("IS","is");
			val t02_the = word("THE","the");
			val t03_editor = word("N","editor");
			val t04_of = word("P_R","of");
			val t05_the = word("THE","the");
			val t06_daily = word("$NPC_1$","Daily");
			val t07_planet = word("NP_D","Planet");
			val t08_app = word("WHO_A","APP");
			val t09_the = word("THE","the");
			val t10_newspaper = word("N","newspaper");
			val t11_that = word("WHO_R","that");
			val t12_employs = word("V_2","employs");
			val t13_the = word("THE","the");
			val t14_man = word("$NPC_1$","Man");
			val t15_of = word("$NPC_1$","of");
			val t16_steel = word("NP_D","Steel");
			val t17_s = word("POSS","s");
			val t18_alter = word("$NC_1$","alter");
			val t19_ego = word("N","ego");
			val t20_app = word("WHO_A","APP");
			val t21_clark = word("$NPC_1$","Clark");
			val t22_kent = word("NP_D","Kent");
			val t23_with = word("P_R","with");
			val t24_a = word("A","a");
			val t25_job = word("N","job");
			val t26_as = word("P_R","as");
			val t27_a = word("A","a");
			val t28_mildmannered = word("MR","mildMannered");
			val t29_reporter = word("N","reporter");
			val t30_in = word("P_R","in");
			val t31_the = word("THE","the");
			val t32_superman = word("MR","Superman");
			val t33_canon = word("N","canon");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","The");
			val h01_daily = word("$NPC_1$","Daily");
			val h02_planet = word("NP_D","Planet");
			val h03_app = word("WHO_A","APP");
			val h04_det = word("A","DET");
			val h05_newspaper = word("N","newspaper");
			val h06_employs = word("V_2","employs");
			val h07_clark = word("$NPC_1$","Clark");
			val h08_kent = word("NP_D","Kent");

			// create the tree structure for the text;
			val tt =
			_(
				t00_white
				,
				_(
					_(
						t01_is
						,
						_(
							t02_the
							,
							_(
								t03_editor
								,
								_(
									t04_of
									,
									_(
										_(
											t05_the
											,
											_(
												t06_daily
												,
												t07_planet
											)
										)
										,
										_(
											t08_app
											,
											_(
												t09_the
												,
												_(
													t10_newspaper
													,
													_(
														t11_that
														,
														_(
															_(
																t12_employs
																,
																_(
																	_(
																		_(
																			_(
																				t13_the
																				,
																				_(
																					t14_man
																					,
																					_(
																						t15_of
																						,
																						t16_steel
																					)
																				)
																			)
																			,
																			t17_s
																		)
																		,
																		_(
																			t18_alter
																			,
																			t19_ego
																		)
																	)
																	,
																	_(
																		t20_app
																		,
																		_(
																			t21_clark
																			,
																			t22_kent
																		)
																	)
																)
															)
															,
															_(
																t23_with
																,
																_(
																	t24_a
																	,
																	_(
																		t25_job
																		,
																		_(
																			t26_as
																			,
																			_(
																				t27_a
																				,
																				_(
																					t28_mildmannered
																					,
																					t29_reporter
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
					,
					_(
						t30_in
						,
						_(
							t31_the
							,
							_(
								t32_superman
								,
								t33_canon
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
					_(
						h00_the
						,
						_(
							h01_daily
							,
							h02_planet
						)
					)
					,
					_(
						h03_app
						,
						_(
							h04_det
							,
							h05_newspaper
						)
					)
				)
				,
				_(
					h06_employs
					,
					_(
						h07_clark
						,
						h08_kent
					)
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

