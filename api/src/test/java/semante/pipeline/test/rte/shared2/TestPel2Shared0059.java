package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0059 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0059() throws Exception {

			// create the vocabulary for the text;
			val t21_andy_grove = word("NP_D","Andy_Grove",21);
			val t42_app = word("WHO_A","APP",42);
			val t04_intel = word("NP_D","Intel",4);
			val t05_s = word("POSS","s",5);
			val t06_chairman = word("N","chairman",6);
			val t08_talked = word("V_1","talked",8);
			val t09_about = word("P_R","about",9);
			val t10_the = word("THE","the",10);
			val t11_toughest = word("MR","toughest",11);
			val t35_business_challenge = word("N","business_challenge",35);
			val t44_app = word("WHO_R","APP",44);
			val t14_facing = word("V_2","facing",14);
			val t15_intel = word("NP_D","Intel",15);
			val t16_with = word("P_R","with",16);
			val t17_a = word("A","a",17);
			val t18_pensive = word("MR","pensive",18);
			val t19_look = word("N","look",19);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_chairman = word("N","chairman",2);
			val h03_of = word("P_R","of",3);
			val h04_intel = word("NP_D","Intel",4);
			val h05_talked = word("V_1","talked",5);
			val h06_about = word("P_R","about",6);
			val h07_a = word("A","a",7);
			val h19_business_challenge = word("N","business_challenge",19);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t21_andy_grove
					,
					_(
						t42_app
						,
						_(
							_(
								t04_intel
								,
								t05_s
								,
								22
							)
							,
							t06_chairman
							,
							23
						)
						,
						43
					)
					,
					40
				)
				,
				_(
					_(
						t08_talked
						,
						_(
							t09_about
							,
							_(
								t10_the
								,
								_(
									t11_toughest
									,
									_(
										t35_business_challenge
										,
										_(
											t44_app
											,
											_(
												t14_facing
												,
												t15_intel
												,
												30
											)
											,
											45
										)
										,
										36
									)
									,
									46
								)
								,
								37
							)
							,
							32
						)
						,
						33
					)
					,
					_(
						t16_with
						,
						_(
							t17_a
							,
							_(
								t18_pensive
								,
								t19_look
								,
								38
							)
							,
							39
						)
						,
						28
					)
					,
					41
				)
				,
				47
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					_(
						h02_chairman
						,
						_(
							h03_of
							,
							h04_intel
							,
							13
						)
						,
						11
					)
					,
					22
				)
				,
				_(
					h05_talked
					,
					_(
						h06_about
						,
						_(
							h07_a
							,
							h19_business_challenge
							,
							20
						)
						,
						16
					)
					,
					17
				)
				,
				21
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
