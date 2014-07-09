package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0122 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0122() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t78_det = word("THE","DET",78);
			val t02_victim = word("N","victim",2);
			val t03_s = word("POSS","s",3);
			val t04_relatives = word("N","relatives",4);
			val t05_reported = word("FACT","reported",5);
			val t06_that = word("IGNORE","that",6);
			val t07_the = word("THE","the",7);
			val t08_body = word("N","body",8);
			val t09_of = word("P_R","of",9);
			val t10_the = word("THE","the",10);
			val t11_undersecretary = word("N","undersecretary",11);
			val t12_of = word("P_R","of",12);
			val t13_the = word("THE","the",13);
			val t14_national = word("MR","national",14);
			val t15_revolutionary = word("MR","revolutionary",15);
			val t16_movement = word("N","movement",16);
			val t84_app = word("WHO_A","APP",84);
			val t17_ = word("IGNORE","",17);
			val t18_mnr = word("NP_D","MNR",18);
			val t19_ = word("IGNORE","",19);
			val t82_app = word("WHO_A","APP",82);
			val t45_hector_oqueli = word("NP_D","Hector_Oqueli",45);
			val t23_was = word("IS","was",23);
			val t24_found = word("V_1","found",24);
			val t25_in = word("P_R","in",25);
			val t72_cuilapa_guatemala = word("NP_D","Cuilapa_Guatemala",72);
			val t30_near = word("P_R","near",30);
			val t31_the = word("THE","the",31);
			val t32_border = word("N","border",32);
			val t33_with = word("P_R","with",33);
			val t56_el_salvador = word("NP_D","El_Salvador",56);

			// create the vocabulary for the hypothesis;
			val h09_hector_oqueli = word("NP_D","Hector_Oqueli",9);
			val h03_was = word("IS","was",3);
			val h04_the = word("THE","the",4);
			val h05_undersecretary = word("N","undersecretary",5);
			val h06_of = word("P_R","of",6);
			val h07_mnr = word("NP_D","MNR",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						_(
							_(
								t78_det
								,
								t02_victim
								,
								75
							)
							,
							t03_s
							,
							80
						)
						,
						t04_relatives
						,
						79
					)
					,
					76
				)
				,
				_(
					t05_reported
					,
					_(
						t06_that
						,
						_(
							_(
								t07_the
								,
								_(
									t08_body
									,
									_(
										t09_of
										,
										_(
											_(
												t10_the
												,
												_(
													t11_undersecretary
													,
													_(
														t12_of
														,
														_(
															_(
																t13_the
																,
																_(
																	t14_national
																	,
																	_(
																		t15_revolutionary
																		,
																		t16_movement
																		,
																		66
																	)
																	,
																	67
																)
																,
																68
															)
															,
															_(
																t84_app
																,
																_(
																	_(
																		t17_
																		,
																		t18_mnr
																		,
																		69
																	)
																	,
																	t19_
																	,
																	70
																)
																,
																85
															)
															,
															47
														)
														,
														89
													)
													,
													40
												)
												,
												88
											)
											,
											_(
												t82_app
												,
												t45_hector_oqueli
												,
												83
											)
											,
											90
										)
										,
										86
									)
									,
									39
								)
								,
								81
							)
							,
							_(
								t23_was
								,
								_(
									_(
										t24_found
										,
										_(
											t25_in
											,
											t72_cuilapa_guatemala
											,
											54
										)
										,
										73
									)
									,
									_(
										t30_near
										,
										_(
											t31_the
											,
											_(
												t32_border
												,
												_(
													t33_with
													,
													t56_el_salvador
													,
													57
												)
												,
												55
											)
											,
											91
										)
										,
										59
									)
									,
									74
								)
								,
								61
							)
							,
							62
						)
						,
						63
					)
					,
					64
				)
				,
				77
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h09_hector_oqueli
				,
				_(
					h03_was
					,
					_(
						h04_the
						,
						_(
							h05_undersecretary
							,
							_(
								h06_of
								,
								h07_mnr
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
