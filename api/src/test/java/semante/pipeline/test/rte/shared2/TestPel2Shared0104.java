package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0104 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0104() throws Exception {

			// create the vocabulary for the text;
			val t56_det = word("EMPTYDET","DET",56);
			val t01_election = word("MR","Election",1);
			val t02_officials = word("N","officials",2);
			val t03_said = word("FACT","said",3);
			val t04_that = word("IGNORE","that",4);
			val t05_the = word("THE","the",5);
			val t06_secondlargest = word("MR","secondLargest",6);
			val t07_nation = word("N","nation",7);
			val t08_in = word("P_R","in",8);
			val t09_the = word("THE","the",9);
			val t10_middleeast = word("NP_D","MiddleEast",10);
			val t58_app = word("WHO_A","APP",58);
			val t12_iran = word("NP_D","Iran",12);
			val t14_will = word("V_AUX","will",14);
			val t15_hold = word("V_2","hold",15);
			val t16_the = word("THE","the",16);
			val t17_first = word("MR","first",17);
			val t18_runoff = word("MR","runoff",18);
			val t19_presidential = word("MR","presidential",19);
			val t20_election = word("N","election",20);
			val t21_in = word("P_R","in",21);
			val t22_the = word("THE","the",22);
			val t23_country = word("N","country",23);
			val t24_s = word("POSS","s",24);
			val t25_history = word("N","history",25);

			// create the vocabulary for the hypothesis;
			val h01_iran = word("NP_D","Iran",1);
			val h02_will = word("IS","will",2);
			val h03_hold = word("V_2","hold",3);
			val h04_a = word("A","a",4);
			val h05_runoff = word("MR","runoff",5);
			val h06_presidential = word("MR","presidential",6);
			val h07_election = word("N","election",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t56_det
					,
					_(
						t01_election
						,
						t02_officials
						,
						27
					)
					,
					57
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
										t06_secondlargest
										,
										_(
											t07_nation
											,
											_(
												t08_in
												,
												_(
													t09_the
													,
													t10_middleeast
													,
													29
												)
												,
												46
											)
											,
											44
										)
										,
										61
									)
									,
									45
								)
								,
								_(
									t58_app
									,
									t12_iran
									,
									59
								)
								,
								33
							)
							,
							_(
								t14_will
								,
								_(
									_(
										t15_hold
										,
										_(
											t16_the
											,
											_(
												t17_first
												,
												_(
													t18_runoff
													,
													_(
														t19_presidential
														,
														t20_election
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
											50
										)
										,
										51
									)
									,
									_(
										t21_in
										,
										_(
											_(
												_(
													t22_the
													,
													t23_country
													,
													52
												)
												,
												t24_s
												,
												53
											)
											,
											t25_history
											,
											36
										)
										,
										37
									)
									,
									54
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
				55
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_iran
				,
				_(
					h02_will
					,
					_(
						h03_hold
						,
						_(
							h04_a
							,
							_(
								h05_runoff
								,
								_(
									h06_presidential
									,
									h07_election
									,
									14
								)
								,
								15
							)
							,
							16
						)
						,
						11
					)
					,
					12
				)
				,
				17
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
