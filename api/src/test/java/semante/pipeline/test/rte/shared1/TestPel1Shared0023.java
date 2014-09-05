package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0023 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0023() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_soldier = word("N","soldier",2);
			val t75_app = word("WHO_A","APP",75);
			val t36_james_barker = word("NP_D","James_Barker",36);
			val t07_who = word("WHO_A","who",7);
			val t08_raped = word("V_2","raped",8);
			val t09_the = word("THE","the",9);
			val t10_iraqi = word("MI","Iraqi",10);
			val t11_girl = word("N","girl",11);
			val t12_and = word("AND","and",12);
			val t13_killed = word("V_2","killed",13);
			val t14_the = word("THE","the",14);
			val t15_family = word("N","family",15);
			val t16_in = word("P_R","in",16);
			val t17_the = word("THE","the",17);
			val t18_march = word("MR","March",18);
			val t19_num2006 = word("MR","num2006",19);
			val t20_incident = word("N","incident",20);
			val t22_pleaded = word("V_1","pleaded",22);
			val t23_guilty = word("MR","guilty",23);
			val t24_to = word("P_R","to",24);
			val t80_det = word("THE","DET",80);
			val t25_murder = word("N","murder",25);
			val t26_on = word("P_R","on",26);
			val t27_wednesday = word("NP_D","Wednesday",27);
			val t28_in = word("P_R","in",28);
			val t54_fort_campbell = word("NP_D","Fort_Campbell",54);
			val t32_in = word("P_R","in",32);
			val t33_kentucky = word("NP_D","Kentucky",33);

			// create the vocabulary for the hypothesis;
			val h12_james_barker = word("NP_D","James_Barker",12);
			val h03_raped = word("V_2","raped",3);
			val h04_an = word("A","an",4);
			val h05_iraqi = word("MI","Iraqi",5);
			val h06_girl = word("N","girl",6);
			val h07_and = word("AND","and",7);
			val h08_killed = word("V_2","killed",8);
			val h09_the = word("THE","the",9);
			val h10_family = word("N","family",10);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t01_the
							,
							t02_soldier
							,
							35
						)
						,
						_(
							t75_app
							,
							t36_james_barker
							,
							76
						)
						,
						63
					)
					,
					_(
						t07_who
						,
						_(
							_(
								_(
									_(
										t08_raped
										,
										_(
											t09_the
											,
											_(
												t10_iraqi
												,
												t11_girl
												,
												64
											)
											,
											65
										)
										,
										39
									)
									,
									t12_and
									,
									66
								)
								,
								_(
									t13_killed
									,
									_(
										t14_the
										,
										t15_family
										,
										40
									)
									,
									67
								)
								,
								72
							)
							,
							_(
								t16_in
								,
								_(
									t17_the
									,
									_(
										t18_march
										,
										_(
											t19_num2006
											,
											t20_incident
											,
											68
										)
										,
										69
									)
									,
									70
								)
								,
								42
							)
							,
							45
						)
						,
						46
					)
					,
					73
				)
				,
				_(
					_(
						_(
							_(
								_(
									t22_pleaded
									,
									t23_guilty
									,
									60
								)
								,
								_(
									t24_to
									,
									_(
										t80_det
										,
										t25_murder
										,
										81
									)
									,
									52
								)
								,
								61
							)
							,
							_(
								t26_on
								,
								t27_wednesday
								,
								50
							)
							,
							78
						)
						,
						_(
							t28_in
							,
							t54_fort_campbell
							,
							55
						)
						,
						79
					)
					,
					_(
						t32_in
						,
						t33_kentucky
						,
						57
					)
					,
					62
				)
				,
				74
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h12_james_barker
				,
				_(
					_(
						_(
							h03_raped
							,
							_(
								h04_an
								,
								_(
									h05_iraqi
									,
									h06_girl
									,
									19
								)
								,
								20
							)
							,
							14
						)
						,
						h07_and
						,
						21
					)
					,
					_(
						h08_killed
						,
						_(
							h09_the
							,
							h10_family
							,
							15
						)
						,
						16
					)
					,
					22
				)
				,
				23
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
