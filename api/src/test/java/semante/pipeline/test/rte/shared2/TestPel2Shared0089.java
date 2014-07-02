package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0089 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0089() throws Exception {

			// create the vocabulary for the text;
			val t01_diehard = word("MR","DieHard",1);
			val t59_det = word("EMPTYDET","DET",59);
			val t02_fans = word("N","fans",2);
			val t03_will = word("V_AUX","will",3);
			val t04_remember = word("FACT","remember",4);
			val t05_that = word("IGNORE","that",5);
			val t06_the = word("THE","the",6);
			val t07_two = word("NUMBER","two",7);
			val t08_firsthalf = word("MR","firstHalf",8);
			val t09_goals = word("N","goals",9);
			val t10_from = word("P_R","from",10);
			val t55_det = word("THE","DET",55);
			val t11_captain = word("N","captain",11);
			val t57_app = word("WHO_A","APP",57);
			val t12_paolo = word("$NPC_1$","Paolo",12);
			val t13_maldini = word("NP_D","Maldini",13);
			val t14_earned = word("V_2","earned",14);
			val t15_a = word("A","a",15);
			val t16_victory = word("N","victory",16);
			val t17_over = word("P_R","over",17);
			val t18_the = word("THE","the",18);
			val t19_intimidating = word("MR","intimidating",19);
			val t20_opponent = word("N","opponent",20);
			val t53_app = word("WHO_A","APP",53);
			val t22_reggina = word("NP_D","Reggina",22);

			// create the vocabulary for the hypothesis;
			val h01_two = word("NUMBER","Two",1);
			val h02_firsthalf = word("MR","firstHalf",2);
			val h03_goals = word("N","goals",3);
			val h04_from = word("P_R","from",4);
			val h05_paolo = word("$NPC_1$","Paolo",5);
			val h06_maldini = word("NP_D","Maldini",6);
			val h07_earned = word("V_2","earned",7);
			val h08_a = word("A","a",8);
			val h09_victory = word("N","victory",9);
			val h10_over = word("P_R","over",10);
			val h11_an = word("A","an",11);
			val h12_intimidating = word("MR","intimidating",12);
			val h13_opponent = word("N","opponent",13);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_diehard
					,
					_(
						t59_det
						,
						t02_fans
						,
						60
					)
					,
					24
				)
				,
				_(
					t03_will
					,
					_(
						t04_remember
						,
						_(
							t05_that
							,
							_(
								_(
									t06_the
									,
									_(
										t07_two
										,
										_(
											t08_firsthalf
											,
											_(
												t09_goals
												,
												_(
													t10_from
													,
													_(
														_(
															t55_det
															,
															t11_captain
															,
															56
														)
														,
														_(
															t57_app
															,
															_(
																t12_paolo
																,
																t13_maldini
																,
																43
															)
															,
															58
														)
														,
														44
													)
													,
													27
												)
												,
												40
											)
											,
											51
										)
										,
										41
									)
									,
									42
								)
								,
								_(
									t14_earned
									,
									_(
										t15_a
										,
										_(
											t16_victory
											,
											_(
												t17_over
												,
												_(
													_(
														t18_the
														,
														_(
															t19_intimidating
															,
															t20_opponent
															,
															46
														)
														,
														47
													)
													,
													_(
														t53_app
														,
														t22_reggina
														,
														54
													)
													,
													48
												)
												,
												33
											)
											,
											29
										)
										,
										52
									)
									,
									45
								)
								,
								35
							)
							,
							36
						)
						,
						37
					)
					,
					38
				)
				,
				50
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_two
					,
					_(
						h02_firsthalf
						,
						_(
							h03_goals
							,
							_(
								h04_from
								,
								_(
									h05_paolo
									,
									h06_maldini
									,
									16
								)
								,
								17
							)
							,
							28
						)
						,
						31
					)
					,
					29
				)
				,
				_(
					h07_earned
					,
					_(
						h08_a
						,
						_(
							h09_victory
							,
							_(
								h10_over
								,
								_(
									h11_an
									,
									_(
										h12_intimidating
										,
										h13_opponent
										,
										25
									)
									,
									26
								)
								,
								21
							)
							,
							19
						)
						,
						32
					)
					,
					24
				)
				,
				30
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

