package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0024 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0024() throws Exception {

			// create the vocabulary for the text;
			val t01_num2channel = word("NP_D","num2channel",1);
			val t51_app = word("WHO_A","APP",51);
			val t03_the = word("THE","the",3);
			val t04_largest = word("MR","largest",4);
			val t05_internet = word("MI","Internet",5);
			val t06_forum = word("N","forum",6);
			val t07_in = word("P_R","in",7);
			val t08_the = word("THE","the",8);
			val t09_world = word("N","world",9);
			val t11_has = word("V_AUX","has",11);
			val t12_been = word("IS","been",12);
			val t13_shuttered = word("V_1","shuttered",13);
			val t14_by = word("P_R","by",14);
			val t15_a = word("A","a",15);
			val t16_japanese = word("MI","Japanese",16);
			val t17_court = word("N","court",17);
			val t55_app = word("WHO_A","APP",55);
			val t18_ruling = word("GER_1","ruling",18);
			val t19_in = word("P_R","in",19);
			val t20_a = word("A","a",20);
			val t21_civil = word("MR","civil",21);
			val t43_slander_case = word("N","slander_case",43);

			// create the vocabulary for the hypothesis;
			val h01_num2channel = word("NP_D","num2channel",1);
			val h02_is = word("IS","is",2);
			val h03_an = word("A","an",3);
			val h04_internet = word("MI","Internet",4);
			val h05_forum = word("N","forum",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_num2channel
					,
					_(
						t51_app
						,
						_(
							t03_the
							,
							_(
								t04_largest
								,
								_(
									t05_internet
									,
									_(
										t06_forum
										,
										_(
											t07_in
											,
											_(
												t08_the
												,
												t09_world
												,
												27
											)
											,
											28
										)
										,
										46
									)
									,
									54
								)
								,
								47
							)
							,
							48
						)
						,
						52
					)
					,
					49
				)
				,
				_(
					t11_has
					,
					_(
						t12_been
						,
						_(
							t13_shuttered
							,
							_(
								t14_by
								,
								_(
									_(
										t15_a
										,
										_(
											t16_japanese
											,
											t17_court
											,
											41
										)
										,
										42
									)
									,
									_(
										t55_app
										,
										_(
											t18_ruling
											,
											_(
												t19_in
												,
												_(
													t20_a
													,
													_(
														t21_civil
														,
														t43_slander_case
														,
														44
													)
													,
													45
												)
												,
												33
											)
											,
											34
										)
										,
										56
									)
									,
									53
								)
								,
								35
							)
							,
							36
						)
						,
						37
					)
					,
					38
				)
				,
				50
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_num2channel
				,
				_(
					h02_is
					,
					_(
						h03_an
						,
						_(
							h04_internet
							,
							h05_forum
							,
							11
						)
						,
						12
					)
					,
					9
				)
				,
				13
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
