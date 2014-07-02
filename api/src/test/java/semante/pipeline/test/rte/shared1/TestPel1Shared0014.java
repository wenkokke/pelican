package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0014 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0014() throws Exception {

			// create the vocabulary for the text;
			val t01_white = word("NP_D","White",1);
			val t02_is = word("IS","is",2);
			val t03_the = word("THE","the",3);
			val t04_editor = word("N","editor",4);
			val t05_of = word("P_R","of",5);
			val t06_the = word("THE","the",6);
			val t07_daily = word("$NPC_1$","Daily",7);
			val t08_planet = word("NP_D","Planet",8);
			val t84_app = word("WHO_A","APP",84);
			val t10_the = word("THE","the",10);
			val t11_newspaper = word("N","newspaper",11);
			val t12_that = word("WHO_R","that",12);
			val t13_employs = word("V_2","employs",13);
			val t14_the = word("THE","the",14);
			val t15_man = word("$NPC_1$","Man",15);
			val t16_of = word("$NPC_1$","of",16);
			val t17_steel = word("NP_D","Steel",17);
			val t18_s = word("POSS","s",18);
			val t19_alter = word("$NC_1$","alter",19);
			val t20_ego = word("N","ego",20);
			val t90_app = word("WHO_A","APP",90);
			val t22_clark = word("$NPC_1$","Clark",22);
			val t23_kent = word("NP_D","Kent",23);
			val t25_with = word("P_R","with",25);
			val t26_a = word("A","a",26);
			val t27_job = word("N","job",27);
			val t28_as = word("P_R","as",28);
			val t29_a = word("A","a",29);
			val t30_mildmannered = word("MR","mildMannered",30);
			val t31_reporter = word("N","reporter",31);
			val t33_in = word("P_R","in",33);
			val t34_the = word("THE","the",34);
			val t35_superman = word("MR","Superman",35);
			val t36_canon = word("N","canon",36);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_daily = word("$NPC_1$","Daily",2);
			val h03_planet = word("NP_D","Planet",3);
			val h17_app = word("WHO_A","APP",17);
			val h15_det = word("A","DET",15);
			val h04_newspaper = word("N","newspaper",4);
			val h05_employs = word("V_2","employs",5);
			val h06_clark = word("$NPC_1$","Clark",6);
			val h07_kent = word("NP_D","Kent",7);

			// create the tree structure for the text;
			val tt =
			_(
				t01_white
				,
				_(
					_(
						t02_is
						,
						_(
							t03_the
							,
							_(
								t04_editor
								,
								_(
									t05_of
									,
									_(
										_(
											t06_the
											,
											_(
												t07_daily
												,
												t08_planet
												,
												69
											)
											,
											70
										)
										,
										_(
											t84_app
											,
											_(
												t10_the
												,
												_(
													t11_newspaper
													,
													_(
														t12_that
														,
														_(
															_(
																t13_employs
																,
																_(
																	_(
																		_(
																			_(
																				t14_the
																				,
																				_(
																					t15_man
																					,
																					_(
																						t16_of
																						,
																						t17_steel
																						,
																						44
																					)
																					,
																					82
																				)
																				,
																				45
																			)
																			,
																			t18_s
																			,
																			58
																		)
																		,
																		_(
																			t19_alter
																			,
																			t20_ego
																			,
																			46
																		)
																		,
																		83
																	)
																	,
																	_(
																		t90_app
																		,
																		_(
																			t22_clark
																			,
																			t23_kent
																			,
																			47
																		)
																		,
																		91
																	)
																	,
																	80
																)
																,
																60
															)
															,
															_(
																t25_with
																,
																_(
																	t26_a
																	,
																	_(
																		t27_job
																		,
																		_(
																			t28_as
																			,
																			_(
																				t29_a
																				,
																				_(
																					t30_mildmannered
																					,
																					t31_reporter
																					,
																					72
																				)
																				,
																				73
																			)
																			,
																			51
																		)
																		,
																		49
																	)
																	,
																	92
																)
																,
																53
															)
															,
															61
														)
														,
														62
													)
													,
													40
												)
												,
												86
											)
											,
											85
										)
										,
										78
									)
									,
									65
								)
								,
								38
							)
							,
							87
						)
						,
						67
					)
					,
					_(
						t33_in
						,
						_(
							t34_the
							,
							_(
								t35_superman
								,
								t36_canon
								,
								75
							)
							,
							76
						)
						,
						55
					)
					,
					68
				)
				,
				88
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h01_the
						,
						_(
							h02_daily
							,
							h03_planet
							,
							13
						)
						,
						14
					)
					,
					_(
						h17_app
						,
						_(
							h15_det
							,
							h04_newspaper
							,
							16
						)
						,
						18
					)
					,
					11
				)
				,
				_(
					h05_employs
					,
					_(
						h06_clark
						,
						h07_kent
						,
						9
					)
					,
					10
				)
				,
				19
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

