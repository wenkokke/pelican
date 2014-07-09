package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0017 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0017() throws Exception {

			// create the vocabulary for the text;
			val t25_mahmoud_ahmadinejad = word("NP_D","Mahmoud_AhmadiNejad",25);
			val t53_app = word("WHO_A","APP",53);
			val t04_iran = word("NP_D","Iran",4);
			val t05_s = word("POSS","s",5);
			val t06_fundamentalist = word("MR","fundamentalist",6);
			val t07_president = word("N","president",7);
			val t09_said = word("FACT","said",9);
			val t10_that = word("IGNORE","that",10);
			val t11_israel = word("NP_D","Israel",11);
			val t12_will = word("V_AUX","will",12);
			val t13_be = word("IS","be",13);
			val t14_wiped = word("V_1","wiped",14);
			val t15_off = word("P_R","off",15);
			val t16_the = word("THE","the",16);
			val t17_map = word("N","map",17);
			val t18_at = word("P_R","at",18);
			val t19_an = word("A","an",19);
			val t20_antizionist = word("MR","antiZionist",20);
			val t21_conference = word("N","conference",21);
			val t22_in = word("P_R","in",22);
			val t23_tehran = word("NP_D","Tehran",23);

			// create the vocabulary for the hypothesis;
			val h06_mahmoud_ahmadinejad = word("NP_D","Mahmoud_AhmadiNejad",6);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_president = word("N","president",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t25_mahmoud_ahmadinejad
					,
					_(
						t53_app
						,
						_(
							_(
								t04_iran
								,
								t05_s
								,
								26
							)
							,
							_(
								t06_fundamentalist
								,
								t07_president
								,
								49
							)
							,
							50
						)
						,
						54
					)
					,
					51
				)
				,
				_(
					_(
						t09_said
						,
						_(
							t10_that
							,
							_(
								t11_israel
								,
								_(
									t12_will
									,
									_(
										t13_be
										,
										_(
											t14_wiped
											,
											_(
												t15_off
												,
												_(
													t16_the
													,
													t17_map
													,
													31
												)
												,
												30
											)
											,
											44
										)
										,
										38
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
					_(
						t18_at
						,
						_(
							t19_an
							,
							_(
								t20_antizionist
								,
								_(
									t21_conference
									,
									_(
										t22_in
										,
										t23_tehran
										,
										34
									)
									,
									46
								)
								,
								57
							)
							,
							47
						)
						,
						36
					)
					,
					52
				)
				,
				56
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h06_mahmoud_ahmadinejad
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						h05_president
						,
						7
					)
					,
					8
				)
				,
				9
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
