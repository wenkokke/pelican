package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0065 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0065() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_commission = word("N","commission",2);
			val t03_said = word("FACT","said",3);
			val t04_that = word("IGNORE","that",4);
			val t05_the = word("THE","the",5);
			val t06_united = word("MR","United",6);
			val t07_iraqi = word("MI","Iraqi",7);
			val t08_alliance = word("N","Alliance",8);
			val t10_which = word("WHO_A","which",10);
			val t11_is = word("IS","is",11);
			val t12_endorsed = word("V_1","endorsed",12);
			val t13_by = word("P_R","by",13);
			val t58_det = word("THE","DET",58);
			val t14_top = word("MR","top",14);
			val t15_iraqi = word("MI","Iraqi",15);
			val t16_shiite = word("MI","Shiite",16);
			val t17_clerics = word("N","clerics",17);
			val t19_captured = word("V_2","captured",19);
			val t20_a = word("A","a",20);
			val t21_majority = word("N","majority",21);
			val t22_of = word("P_R","of",22);
			val t23_the = word("THE","the",23);
			val t24_num3 = word("NUMBER","num3",24);
			val t25_million = word("MR","million",25);
			val t26_votes = word("N","votes",26);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h02_alliance = word("N","alliance",2);
			val h03_is = word("IS","is",3);
			val h04_endorsed = word("V_1","endorsed",4);
			val h05_by = word("P_R","by",5);
			val h16_det = word("THE","DET",16);
			val h06_iraqi = word("MI","Iraqi",6);
			val h07_clerics = word("N","clerics",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_commission
					,
					28
				)
				,
				_(
					t03_said
					,
					_(
						t04_that
						,
						_(
							_(
								_(
									t05_the
									,
									_(
										t06_united
										,
										_(
											t07_iraqi
											,
											t08_alliance
											,
											30
										)
										,
										29
									)
									,
									57
								)
								,
								_(
									t10_which
									,
									_(
										t11_is
										,
										_(
											t12_endorsed
											,
											_(
												t13_by
												,
												_(
													t58_det
													,
													_(
														t14_top
														,
														_(
															t15_iraqi
															,
															_(
																t16_shiite
																,
																t17_clerics
																,
																50
															)
															,
															51
														)
														,
														52
													)
													,
													59
												)
												,
												34
											)
											,
											35
										)
										,
										36
									)
									,
									38
								)
								,
								53
							)
							,
							_(
								t19_captured
								,
								_(
									t20_a
									,
									_(
										t21_majority
										,
										_(
											t22_of
											,
											_(
												t23_the
												,
												_(
													t24_num3
													,
													_(
														t25_million
														,
														t26_votes
														,
														41
													)
													,
													66
												)
												,
												55
											)
											,
											43
										)
										,
										60
									)
									,
									45
								)
								,
								61
							)
							,
							65
						)
						,
						47
					)
					,
					48
				)
				,
				56
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_an
					,
					h02_alliance
					,
					9
				)
				,
				_(
					h03_is
					,
					_(
						h04_endorsed
						,
						_(
							h05_by
							,
							_(
								h16_det
								,
								_(
									h06_iraqi
									,
									h07_clerics
									,
									10
								)
								,
								17
							)
							,
							11
						)
						,
						12
					)
					,
					13
				)
				,
				15
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
