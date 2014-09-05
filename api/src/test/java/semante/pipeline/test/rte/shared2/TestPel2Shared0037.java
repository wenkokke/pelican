package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0037 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0037() throws Exception {

			// create the vocabulary for the text;
			val t31_dan_rather = word("NP_D","Dan_Rather",31);
			val t64_app = word("WHO_A","APP",64);
			val t04_a = word("A","a",4);
			val t05_respected = word("MI","respected",5);
			val t57_news_anchorman = word("N","news_anchorman",57);
			val t08_in = word("P_R","in",8);
			val t09_the = word("THE","the",9);
			val t60_united_states = word("NP_D","United_States",60);
			val t13_has = word("V_AUX","has",13);
			val t14_announced = word("V_2","announced",14);
			val t15_that = word("IGNORE","that",15);
			val t16_he = word("NP_D","he",16);
			val t17_will = word("V_AUX","will",17);
			val t18_retire = word("V_1","retire",18);
			val t19_as = word("P_R","as",19);
			val t20_the = word("THE","the",20);
			val t21_lead = word("N","lead",21);
			val t22_of = word("P_R","of",22);
			val t23_the = word("THE","the",23);
			val t53_cbs_main_news_program = word("N","CBS_main_news_program",53);
			val t28_next = word("P_R","next",28);
			val t29_year = word("NP_D","year",29);

			// create the vocabulary for the hypothesis;
			val h06_dan_rather = word("NP_D","Dan_Rather",6);
			val h03_is = word("IS","is",3);
			val h04_respected = word("MI","respected",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t31_dan_rather
					,
					_(
						t64_app
						,
						_(
							t04_a
							,
							_(
								_(
									t05_respected
									,
									t57_news_anchorman
									,
									58
								)
								,
								_(
									t08_in
									,
									_(
										t09_the
										,
										t60_united_states
										,
										61
									)
									,
									34
								)
								,
								35
							)
							,
							66
						)
						,
						65
					)
					,
					62
				)
				,
				_(
					t13_has
					,
					_(
						t14_announced
						,
						_(
							t15_that
							,
							_(
								t16_he
								,
								_(
									_(
										_(
											t17_will
											,
											t18_retire
											,
											55
										)
										,
										_(
											t19_as
											,
											_(
												t20_the
												,
												_(
													t21_lead
													,
													_(
														t22_of
														,
														_(
															t23_the
															,
															t53_cbs_main_news_program
															,
															54
														)
														,
														40
													)
													,
													38
												)
												,
												68
											)
											,
											42
										)
										,
										67
									)
									,
									_(
										t28_next
										,
										t29_year
										,
										43
									)
									,
									56
								)
								,
								46
							)
							,
							47
						)
						,
						48
					)
					,
					49
				)
				,
				63
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h06_dan_rather
				,
				_(
					h03_is
					,
					h04_respected
					,
					8
				)
				,
				10
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
