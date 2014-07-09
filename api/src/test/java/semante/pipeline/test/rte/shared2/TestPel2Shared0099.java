package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0099 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0099() throws Exception {

			// create the vocabulary for the text;
			val t04_an = word("A","an",4);
			val t05_increase = word("N","increase",5);
			val t06_of = word("P_R","of",6);
			val t07_seismic = word("MR","seismic",7);
			val t08_activity = word("NP_D","activity",8);
			val t01_in = word("P_R","In",1);
			val t25_august_num1999 = word("NP_D","August_num1999",25);
			val t09_marked = word("V_2","marked",9);
			val t10_the = word("THE","the",10);
			val t11_beginning = word("N","beginning",11);
			val t12_of = word("P_R","of",12);
			val t13_an = word("A","an",13);
			val t14_unusual = word("MI","unusual",14);
			val t15_long = word("MR","long",15);
			val t16_sequence = word("N","sequence",16);
			val t17_of = word("P_R","of",17);
			val t18_earthquakes = word("NP_D","earthquakes",18);
			val t19_which = word("WHO_A","which",19);
			val t20_lasted = word("V_1","lasted",20);
			val t21_until = word("P_R","until",21);
			val t35_november_num1999 = word("NP_D","November_num1999",35);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_unusual = word("MI","unusual",2);
			val h03_sequence = word("N","sequence",3);
			val h04_of = word("P_R","of",4);
			val h05_earthquakes = word("NP_D","earthquakes",5);
			val h06_lasted = word("V_1","lasted",6);
			val h07_until = word("P_R","until",7);
			val h15_november_num1999 = word("NP_D","November_num1999",15);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t04_an
					,
					_(
						t05_increase
						,
						_(
							t06_of
							,
							_(
								t07_seismic
								,
								t08_activity
								,
								28
							)
							,
							29
						)
						,
						27
					)
					,
					52
				)
				,
				_(
					_(
						t01_in
						,
						t25_august_num1999
						,
						26
					)
					,
					_(
						t09_marked
						,
						_(
							t10_the
							,
							_(
								t11_beginning
								,
								_(
									t12_of
									,
									_(
										_(
											t13_an
											,
											_(
												t14_unusual
												,
												_(
													t15_long
													,
													_(
														t16_sequence
														,
														_(
															t17_of
															,
															t18_earthquakes
															,
															41
														)
														,
														47
													)
													,
													57
												)
												,
												48
											)
											,
											54
										)
										,
										_(
											t19_which
											,
											_(
												t20_lasted
												,
												_(
													t21_until
													,
													t35_november_num1999
													,
													36
												)
												,
												37
											)
											,
											39
										)
										,
										42
									)
									,
									43
								)
								,
								31
							)
							,
							53
						)
						,
						45
					)
					,
					50
				)
				,
				55
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					_(
						h02_unusual
						,
						_(
							h03_sequence
							,
							_(
								h04_of
								,
								h05_earthquakes
								,
								13
							)
							,
							19
						)
						,
						23
					)
					,
					22
				)
				,
				_(
					h06_lasted
					,
					_(
						h07_until
						,
						h15_november_num1999
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
