package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0107 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0107() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_poll = word("N","poll",2);
			val t03_showed = word("FACT","showed",3);
			val t04_that = word("IGNORE","that",4);
			val t05_americans = word("NP_D","Americans",5);
			val t06_reprobate = word("V_2","reprobate",6);
			val t07_the = word("THE","the",7);
			val t08_long = word("MR","long",8);
			val t09_war = word("N","war",9);
			val t10_in = word("P_R","in",10);
			val t11_iraq = word("NP_D","Iraq",11);
			val t12_which = word("WHO_A","which",12);
			val t13_had = word("V_2","had",13);
			val t14_a = word("A","a",14);
			val t15_great = word("MR","great",15);
			val t16_number = word("N","number",16);
			val t17_of = word("P_R","of",17);
			val t48_det = word("EMPTYDET","DET",48);
			val t18_us = word("MI","US",18);
			val t19_casualties = word("N","casualties",19);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_war = word("N","war",2);
			val h03_in = word("P_R","in",3);
			val h04_iraq = word("NP_D","Iraq",4);
			val h05_had = word("V_2","had",5);
			val h06_a = word("A","a",6);
			val h07_great = word("MR","great",7);
			val h08_number = word("N","number",8);
			val h09_of = word("P_R","of",9);
			val h27_det = word("EMPTYDET","DET",27);
			val h10_us = word("MI","US",10);
			val h11_casualties = word("N","casualties",11);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_poll
					,
					21
				)
				,
				_(
					t03_showed
					,
					_(
						t04_that
						,
						_(
							t05_americans
							,
							_(
								t06_reprobate
								,
								_(
									_(
										t07_the
										,
										_(
											t08_long
											,
											_(
												t09_war
												,
												_(
													t10_in
													,
													t11_iraq
													,
													25
												)
												,
												40
											)
											,
											47
										)
										,
										41
									)
									,
									_(
										t12_which
										,
										_(
											t13_had
											,
											_(
												t14_a
												,
												_(
													t15_great
													,
													_(
														t16_number
														,
														_(
															t17_of
															,
															_(
																t48_det
																,
																_(
																	t18_us
																	,
																	t19_casualties
																	,
																	28
																)
																,
																49
															)
															,
															29
														)
														,
														43
													)
													,
													50
												)
												,
												44
											)
											,
											31
										)
										,
										33
									)
									,
									45
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
				46
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_war
						,
						_(
							h03_in
							,
							h04_iraq
							,
							15
						)
						,
						13
					)
					,
					26
				)
				,
				_(
					h05_had
					,
					_(
						h06_a
						,
						_(
							h07_great
							,
							_(
								h08_number
								,
								_(
									h09_of
									,
									_(
										h27_det
										,
										_(
											h10_us
											,
											h11_casualties
											,
											18
										)
										,
										28
									)
									,
									19
								)
								,
								23
							)
							,
							29
						)
						,
						24
					)
					,
					21
				)
				,
				25
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

