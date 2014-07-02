package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0016 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0016() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_num3 = word("MR","num3",2);
			val t03_percent = word("$NC_1$","percent",3);
			val t04_growth = word("$NC_1$","growth",4);
			val t05_rate = word("N","rate",5);
			val t06_that = word("WHO_A","that",6);
			val t07_was = word("IS","was",7);
			val t08_predicted = word("V_1","predicted",8);
			val t09_for = word("P_R","for",9);
			val t10_the = word("THE","the",10);
			val t11_fourth = word("MR","fourth",11);
			val t12_quarter = word("NP_D","quarter",12);
			val t13_s = word("POSS","s",13);
			val t14_performance = word("N","performance",14);
			val t15_was = word("IS","was",15);
			val t16_slower = word("MR","slower",16);
			val t17_in = word("P_R","in",17);
			val t46_det = word("EMPTYDET","DET",46);
			val t18_reality = word("N","reality",18);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_num3 = word("MR","num3",2);
			val h03_percent = word("$NC_1$","percent",3);
			val h04_growth = word("$NC_1$","growth",4);
			val h05_rate = word("N","rate",5);
			val h06_was = word("IS","was",6);
			val h07_predicted = word("V_1","predicted",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_num3
							,
							_(
								t03_percent
								,
								_(
									t04_growth
									,
									t05_rate
									,
									37
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
					_(
						t06_that
						,
						_(
							t07_was
							,
							_(
								t08_predicted
								,
								_(
									t09_for
									,
									_(
										t10_the
										,
										_(
											_(
												_(
													t11_fourth
													,
													t12_quarter
													,
													41
												)
												,
												t13_s
												,
												42
											)
											,
											t14_performance
											,
											23
										)
										,
										24
									)
									,
									45
								)
								,
								25
							)
							,
							26
						)
						,
						28
					)
					,
					29
				)
				,
				_(
					_(
						t15_was
						,
						t16_slower
						,
						35
					)
					,
					_(
						t17_in
						,
						_(
							t46_det
							,
							t18_reality
							,
							47
						)
						,
						32
					)
					,
					36
				)
				,
				44
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_num3
						,
						_(
							h03_percent
							,
							_(
								h04_growth
								,
								h05_rate
								,
								13
							)
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
				_(
					h06_was
					,
					h07_predicted
					,
					11
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

