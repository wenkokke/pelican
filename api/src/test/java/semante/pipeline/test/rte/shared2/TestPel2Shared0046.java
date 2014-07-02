package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0046 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0046() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_security = word("$NC_1$","security",2);
			val t03_authorities = word("N","authorities",3);
			val t04_have = word("V_AUX","have",4);
			val t05_declared = word("V_2","declared",5);
			val t06_a = word("A","a",6);
			val t07_state = word("N","state",7);
			val t08_of = word("P_R","of",8);
			val t66_det = word("EMPTYDET","DET",66);
			val t09_maximum = word("MR","maximum",9);
			val t10_emergency = word("N","emergency",10);
			val t11_in = word("P_R","in",11);
			val t12_guatemala = word("NP_D","Guatemala",12);
			val t14_which = word("WHO_A","which",14);
			val t15_sits = word("V_1","sits",15);
			val t16_directly = word("MR","directly",16);
			val t17_in = word("P_I","in",17);
			val t18_the = word("THE","the",18);
			val t19_path = word("N","path",19);
			val t20_of = word("P_R","of",20);
			val t21_the = word("THE","the",21);
			val t22_hurricane = word("N","hurricane",22);
			val t24_closing = word("GER_2","closing",24);
			val t71_det = word("THE","DET",71);
			val t25_airports = word("N","airports",25);
			val t26_across = word("P_R","across",26);
			val t27_the = word("THE","the",27);
			val t28_country = word("N","country",28);

			// create the vocabulary for the hypothesis;
			val h01_guatemala = word("NP_D","Guatemala",1);
			val h02_sits = word("V_1","sits",2);
			val h03_in = word("P_I","in",3);
			val h04_the = word("THE","the",4);
			val h05_path = word("N","path",5);
			val h06_of = word("P_R","of",6);
			val h07_a = word("A","a",7);
			val h08_hurricane = word("N","hurricane",8);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_security
						,
						t03_authorities
						,
						62
					)
					,
					63
				)
				,
				_(
					t04_have
					,
					_(
						_(
							t05_declared
							,
							_(
								t06_a
								,
								_(
									_(
										t07_state
										,
										_(
											t08_of
											,
											_(
												t66_det
												,
												_(
													t09_maximum
													,
													t10_emergency
													,
													32
												)
												,
												67
											)
											,
											33
										)
										,
										31
									)
									,
									_(
										t11_in
										,
										_(
											t12_guatemala
											,
											_(
												t14_which
												,
												_(
													_(
														t15_sits
														,
														t16_directly
														,
														57
													)
													,
													_(
														t17_in
														,
														_(
															t18_the
															,
															_(
																t19_path
																,
																_(
																	t20_of
																	,
																	_(
																		t21_the
																		,
																		t22_hurricane
																		,
																		39
																	)
																	,
																	45
																)
																,
																38
															)
															,
															69
														)
														,
														47
													)
													,
													59
												)
												,
												50
											)
											,
											60
										)
										,
										52
									)
									,
									65
								)
								,
								68
							)
							,
							56
						)
						,
						_(
							t24_closing
							,
							_(
								t71_det
								,
								_(
									t25_airports
									,
									_(
										t26_across
										,
										_(
											t27_the
											,
											t28_country
											,
											41
										)
										,
										42
									)
									,
									72
								)
								,
								73
							)
							,
							40
						)
						,
						61
					)
					,
					54
				)
				,
				64
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_guatemala
				,
				_(
					h02_sits
					,
					_(
						h03_in
						,
						_(
							h04_the
							,
							_(
								h05_path
								,
								_(
									h06_of
									,
									_(
										h07_a
										,
										h08_hurricane
										,
										11
									)
									,
									12
								)
								,
								10
							)
							,
							17
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
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

