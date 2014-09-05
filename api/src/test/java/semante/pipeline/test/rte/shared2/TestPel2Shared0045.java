package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0045 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0045() throws Exception {

			// create the vocabulary for the text;
			val t01_an = word("A","An",1);
			val t02_iraqi = word("MI","Iraqi",2);
			val t03_official = word("N","official",3);
			val t04_reported = word("FACT","reported",4);
			val t05_that = word("IGNORE","that",5);
			val t06_the = word("THE","the",6);
			val t07_iraqi = word("MI","Iraqi",7);
			val t08_civilians = word("N","civilians",8);
			val t09_who = word("WHO_R","who",9);
			val t10_were = word("IS","were",10);
			val t11_killed = word("V_1","killed",11);
			val t12_by = word("P_R","by",12);
			val t13_the = word("THE","the",13);
			val t14_american = word("MI","American",14);
			val t15_bombing = word("N","bombing",15);
			val t16_on = word("P_R","on",16);
			val t17_iraq = word("NP_D","Iraq",17);
			val t18_were = word("IS","were",18);
			val t19_buried = word("V_1","buried",19);
			val t20_today = word("MR","today",20);
			val t21_in = word("P_R","in",21);
			val t22_baghdad = word("NP_D","Baghdad",22);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_iraqi = word("MI","Iraqi",2);
			val h03_civilians = word("N","civilians",3);
			val h04_were = word("IS","were",4);
			val h05_killed = word("V_1","killed",5);
			val h06_by = word("P_R","by",6);
			val h07_a = word("A","a",7);
			val h08_bombing = word("N","bombing",8);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_an
					,
					_(
						t02_iraqi
						,
						t03_official
						,
						45
					)
					,
					46
				)
				,
				_(
					t04_reported
					,
					_(
						t05_that
						,
						_(
							_(
								t06_the
								,
								_(
									t07_iraqi
									,
									_(
										t08_civilians
										,
										_(
											t09_who
											,
											_(
												t10_were
												,
												_(
													t11_killed
													,
													_(
														t12_by
														,
														_(
															t13_the
															,
															_(
																t14_american
																,
																_(
																	t15_bombing
																	,
																	_(
																		t16_on
																		,
																		t17_iraq
																		,
																		28
																	)
																	,
																	49
																)
																,
																53
															)
															,
															50
														)
														,
														30
													)
													,
													31
												)
												,
												32
											)
											,
											34
										)
										,
										47
									)
									,
									54
								)
								,
								48
							)
							,
							_(
								t18_were
								,
								_(
									_(
										t19_buried
										,
										t20_today
										,
										51
									)
									,
									_(
										t21_in
										,
										t22_baghdad
										,
										38
									)
									,
									52
								)
								,
								40
							)
							,
							41
						)
						,
						42
					)
					,
					43
				)
				,
				44
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					_(
						h02_iraqi
						,
						h03_civilians
						,
						15
					)
					,
					16
				)
				,
				_(
					h04_were
					,
					_(
						h05_killed
						,
						_(
							h06_by
							,
							_(
								h07_a
								,
								h08_bombing
								,
								10
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
				14
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
