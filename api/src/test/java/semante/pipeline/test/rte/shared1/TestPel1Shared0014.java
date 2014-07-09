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
			val t69_daily_planet = word("NP_D","Daily_Planet",69);
			val t84_app = word("WHO_A","APP",84);
			val t10_the = word("THE","the",10);
			val t11_newspaper = word("N","newspaper",11);
			val t12_that = word("WHO_R","that",12);
			val t13_employs = word("V_2","employs",13);
			val t14_the = word("THE","the",14);
			val t82_man_of_steel = word("NP_D","Man_of_Steel",82);
			val t18_s = word("POSS","s",18);
			val t46_alter_ego = word("N","alter_ego",46);
			val t90_app = word("WHO_A","APP",90);
			val t47_clark_kent = word("NP_D","Clark_Kent",47);
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
			val h13_daily_planet = word("NP_D","Daily_Planet",13);
			val h17_app = word("WHO_A","APP",17);
			val h15_det = word("A","DET",15);
			val h04_newspaper = word("N","newspaper",4);
			val h05_employs = word("V_2","employs",5);
			val h09_clark_kent = word("NP_D","Clark_Kent",9);

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
											t69_daily_planet
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
																				t82_man_of_steel
																				,
																				45
																			)
																			,
																			t18_s
																			,
																			58
																		)
																		,
																		t46_alter_ego
																		,
																		83
																	)
																	,
																	_(
																		t90_app
																		,
																		t47_clark_kent
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
						h13_daily_planet
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
					h09_clark_kent
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
