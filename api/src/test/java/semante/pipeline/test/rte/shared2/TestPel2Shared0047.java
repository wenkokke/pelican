package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0047 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0047() throws Exception {

			// create the vocabulary for the text;
			val t01_an = word("A","An",1);
			val t02_official = word("N","official",2);
			val t03_of = word("P_R","of",3);
			val t04_abyan = word("MR","Abyan",4);
			val t05_police = word("N","police",5);
			val t06_announced = word("FACT","announced",6);
			val t07_that = word("IGNORE","that",7);
			val t08_the = word("THE","the",8);
			val t09_hostages = word("N","hostages",9);
			val t10_are = word("IS","are",10);
			val t11_held = word("V_1","held",11);
			val t12_by = word("P_R","by",12);
			val t13_the = word("THE","the",13);
			val t14_yemeni = word("MI","Yemeni",14);
			val t15_group = word("N","group",15);
			val t71_app = word("WHO_A","APP",71);
			val t17_islamic = word("MI","Islamic",17);
			val t18_jihad = word("NP_D","Jihad",18);
			val t20_which = word("WHO_A","which",20);
			val t21_is = word("IS","is",21);
			val t22_demanding = word("V_2","demanding",22);
			val t23_the = word("THE","the",23);
			val t24_release = word("N","release",24);
			val t25_of = word("P_R","of",25);
			val t26_the = word("THE","the",26);
			val t27_leader = word("N","leader",27);
			val t28_and = word("AND","and",28);
			val t29_lifting = word("V_2","lifting",29);
			val t30_the = word("THE","the",30);
			val t31_embargo = word("N","embargo",31);
			val t32_on = word("P_R","on",32);
			val t33_iraq = word("NP_D","Iraq",33);

			// create the vocabulary for the hypothesis;
			val h01_islamic = word("MI","Islamic",1);
			val h02_jihad = word("NP_D","Jihad",2);
			val h03_is = word("IS","is",3);
			val h04_yemeni = word("MI","Yemeni",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_an
					,
					_(
						t02_official
						,
						_(
							t03_of
							,
							_(
								t04_abyan
								,
								t05_police
								,
								35
							)
							,
							36
						)
						,
						34
					)
					,
					70
				)
				,
				_(
					t06_announced
					,
					_(
						t07_that
						,
						_(
							_(
								t08_the
								,
								t09_hostages
								,
								38
							)
							,
							_(
								t10_are
								,
								_(
									t11_held
									,
									_(
										t12_by
										,
										_(
											_(
												t13_the
												,
												_(
													t14_yemeni
													,
													t15_group
													,
													64
												)
												,
												65
											)
											,
											_(
												t71_app
												,
												_(
													_(
														t17_islamic
														,
														t18_jihad
														,
														40
													)
													,
													_(
														t20_which
														,
														_(
															t21_is
															,
															_(
																_(
																	t22_demanding
																	,
																	_(
																		t23_the
																		,
																		_(
																			t24_release
																			,
																			_(
																				t25_of
																				,
																				_(
																					t26_the
																					,
																					t27_leader
																					,
																					43
																				)
																				,
																				44
																			)
																			,
																			42
																		)
																		,
																		74
																	)
																	,
																	46
																)
																,
																_(
																	t28_and
																	,
																	_(
																		t29_lifting
																		,
																		_(
																			t30_the
																			,
																			_(
																				t31_embargo
																				,
																				_(
																					t32_on
																					,
																					t33_iraq
																					,
																					49
																				)
																				,
																				47
																			)
																			,
																			75
																		)
																		,
																		51
																	)
																	,
																	67
																)
																,
																76
															)
															,
															53
														)
														,
														55
													)
													,
													72
												)
												,
												73
											)
											,
											66
										)
										,
										57
									)
									,
									58
								)
								,
								59
							)
							,
							60
						)
						,
						61
					)
					,
					62
				)
				,
				63
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_islamic
					,
					h02_jihad
					,
					5
				)
				,
				_(
					h03_is
					,
					h04_yemeni
					,
					7
				)
				,
				8
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
