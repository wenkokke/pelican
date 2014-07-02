package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0019 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0019() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_bills = word("NP_D","Bills",2);
			val t74_app = word("WHO_A","APP",74);
			val t04_two = word("NUMBER","two",4);
			val t05_rich = word("MR","rich",5);
			val t06_brothers = word("N","brothers",6);
			val t07_from = word("P_R","from",7);
			val t08_texas = word("NP_D","Texas",8);
			val t10_are = word("IS","are",10);
			val t41_handing_over = word("V_2","handing_over",41);
			val t13_the = word("THE","the",13);
			val t14_reins = word("N","reins",14);
			val t15_to = word("P_R","to",15);
			val t16_the = word("THE","the",16);
			val t17_former = word("MR","former",17);
			val t18_quarterback = word("N","quarterback",18);
			val t77_app = word("WHO_A","APP",77);
			val t20_j = word("$NPC_1$","J",20);
			val t21_p = word("$NPC_1$","P",21);
			val t22_losman = word("NP_D","Losman",22);
			val t24_who = word("WHO_A","who",24);
			val t25_missed = word("V_2","missed",25);
			val t26_the = word("THE","the",26);
			val t27_last = word("MR","last",27);
			val t28_season = word("N","season",28);
			val t29_with = word("P_R","with",29);
			val t30_a = word("A","a",30);
			val t31_broken = word("MR","broken",31);
			val t32_leg = word("N","leg",32);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_two = word("NUMBER","two",2);
			val h03_brothers = word("N","brothers",3);
			val h04_are = word("IS","are",4);
			val h19_handing_over = word("V_2","handing_over",19);
			val h07_the = word("THE","the",7);
			val h08_reins = word("N","reins",8);
			val h09_to = word("P_R","to",9);
			val h10_a = word("A","a",10);
			val h11_former = word("MR","former",11);
			val h12_quarterback = word("N","quarterback",12);
			val h36_app = word("WHO_A","APP",36);
			val h13_j = word("$NPC_1$","J",13);
			val h14_p = word("$NPC_1$","P",14);
			val h15_losman = word("NP_D","Losman",15);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						t02_bills
						,
						34
					)
					,
					_(
						t74_app
						,
						_(
							t04_two
							,
							_(
								t05_rich
								,
								_(
									t06_brothers
									,
									_(
										t07_from
										,
										t08_texas
										,
										37
									)
									,
									70
								)
								,
								79
							)
							,
							71
						)
						,
						75
					)
					,
					72
				)
				,
				_(
					t10_are
					,
					_(
						_(
							t41_handing_over
							,
							_(
								t13_the
								,
								t14_reins
								,
								40
							)
							,
							76
						)
						,
						_(
							t15_to
							,
							_(
								_(
									_(
										t16_the
										,
										_(
											t17_former
											,
											t18_quarterback
											,
											57
										)
										,
										58
									)
									,
									_(
										t77_app
										,
										_(
											t20_j
											,
											_(
												t21_p
												,
												t22_losman
												,
												59
											)
											,
											60
										)
										,
										78
									)
									,
									61
								)
								,
								_(
									t24_who
									,
									_(
										_(
											t25_missed
											,
											_(
												t26_the
												,
												_(
													t27_last
													,
													t28_season
													,
													62
												)
												,
												63
											)
											,
											64
										)
										,
										_(
											t29_with
											,
											_(
												t30_a
												,
												_(
													t31_broken
													,
													t32_leg
													,
													65
												)
												,
												66
											)
											,
											47
										)
										,
										67
									)
									,
									50
								)
								,
								68
							)
							,
							52
						)
						,
						69
					)
					,
					54
				)
				,
				73
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					_(
						h02_two
						,
						h03_brothers
						,
						32
					)
					,
					33
				)
				,
				_(
					h04_are
					,
					_(
						_(
							h19_handing_over
							,
							_(
								h07_the
								,
								h08_reins
								,
								18
							)
							,
							35
						)
						,
						_(
							h09_to
							,
							_(
								_(
									h10_a
									,
									_(
										h11_former
										,
										h12_quarterback
										,
										29
									)
									,
									30
								)
								,
								_(
									h36_app
									,
									_(
										h13_j
										,
										_(
											h14_p
											,
											h15_losman
											,
											26
										)
										,
										27
									)
									,
									37
								)
								,
								21
							)
							,
							38
						)
						,
						31
					)
					,
					23
				)
				,
				34
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

