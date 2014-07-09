package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0015 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0015() throws Exception {

			// create the vocabulary for the text;
			val t01_hakimi = word("NP_D","Hakimi",1);
			val t02_also = word("MR","also",2);
			val t03_claimed = word("FACT","claimed",3);
			val t04_that = word("IGNORE","that",4);
			val t05_the = word("THE","the",5);
			val t06_rebels = word("N","rebels",6);
			val t07_killed = word("V_2","killed",7);
			val t26_mawlavi_waqifi = word("NP_D","Mawlavi_Waqifi",26);
			val t51_app = word("WHO_A","APP",51);
			val t11_a = word("A","a",11);
			val t12_religious = word("MR","religious",12);
			val t13_leader = word("N","leader",13);
			val t14_who = word("WHO_A","who",14);
			val t15_lived = word("V_1","lived",15);
			val t16_in = word("P_R","in",16);
			val t17_the = word("THE","the",17);
			val t18_southeastern = word("MI","southeastern",18);
			val t19_province = word("N","province",19);
			val t20_of = word("P_R","of",20);
			val t21_khost = word("NP_D","Khost",21);

			// create the vocabulary for the hypothesis;
			val h10_mawlavi_waqifi = word("NP_D","Mawlavi_Waqifi",10);
			val h03_lived = word("V_1","lived",3);
			val h04_in = word("P_R","in",4);
			val h05_a = word("A","a",5);
			val h06_province = word("N","province",6);
			val h07_of = word("P_R","of",7);
			val h08_khost = word("NP_D","Khost",8);

			// create the tree structure for the text;
			val tt =
			_(
				t01_hakimi
				,
				_(
					t02_also
					,
					_(
						t03_claimed
						,
						_(
							t04_that
							,
							_(
								_(
									t05_the
									,
									t06_rebels
									,
									25
								)
								,
								_(
									t07_killed
									,
									_(
										t26_mawlavi_waqifi
										,
										_(
											t51_app
											,
											_(
												_(
													t11_a
													,
													_(
														t12_religious
														,
														t13_leader
														,
														44
													)
													,
													45
												)
												,
												_(
													t14_who
													,
													_(
														t15_lived
														,
														_(
															t16_in
															,
															_(
																t17_the
																,
																_(
																	t18_southeastern
																	,
																	_(
																		t19_province
																		,
																		_(
																			t20_of
																			,
																			t21_khost
																			,
																			31
																		)
																		,
																		46
																	)
																	,
																	55
																)
																,
																33
															)
															,
															56
														)
														,
														34
													)
													,
													36
												)
												,
												37
											)
											,
											52
										)
										,
										48
									)
									,
									39
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
					49
				)
				,
				50
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h10_mawlavi_waqifi
				,
				_(
					h03_lived
					,
					_(
						h04_in
						,
						_(
							h05_a
							,
							_(
								h06_province
								,
								_(
									h07_of
									,
									h08_khost
									,
									13
								)
								,
								11
							)
							,
							19
						)
						,
						15
					)
					,
					16
				)
				,
				18
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
