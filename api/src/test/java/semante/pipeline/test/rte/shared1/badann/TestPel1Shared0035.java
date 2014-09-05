package semante.pipeline.test.rte.shared1.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0035 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0035() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_chief = word("N","chief",2);
			val t03_of = word("P_R","of",3);
			val t04_the = word("THE","the",4);
			val t05_german = word("MR","German",5);
			val t72_central_bank = word("NP_D","Central_Bank",72);
			val t82_app = word("WHO_A","APP",82);
			val t08_ = word("IGNORE","",8);
			val t09_bundesbank = word("NP_D","Bundesbank",9);
			val t10_ = word("IGNORE","",10);
			val t85_app = word("WHO_A","APP",85);
			val t42_hans_tietmeyer = word("NP_D","Hans_Tietmeyer",42);
			val t15_justified = word("V_2","justified",15);
			val t16_this = word("THE","this",16);
			val t17_measure = word("N","measure",17);
			val t18_by = word("P_R","by",18);
			val t19_citing = word("GER_2","citing",19);
			val t20_the = word("THE","the",20);
			val t21_early = word("MR","early",21);
			val t22_signs = word("N","signs",22);
			val t23_of = word("P_R","of",23);
			val t24_economic = word("MR","economic",24);
			val t25_slowdown = word("N","slowdown",25);
			val t26_which = word("WHO_A","which",26);
			val t27_affect = word("V_2","affect",27);
			val t28_the = word("THE","the",28);
			val t29_countries = word("N","countries",29);
			val t30_that = word("WHO_R","that",30);
			val t31_will = word("IS","will",31);
			val t32_adopt = word("V_2","adopt",32);
			val t33_the = word("THE","the",33);
			val t34_euro = word("N","Euro",34);

			// create the vocabulary for the hypothesis;
			val h13_hans_tietmeyer = word("NP_D","Hans_Tietmeyer",13);
			val h25_app = word("WHO_A","APP",25);
			val h04_the = word("THE","the",4);
			val h05_head = word("N","head",5);
			val h06_of = word("P_R","of",6);
			val h07_bundesbank = word("NP_D","Bundesbank",7);
			val h09_justified = word("V_2","justified",9);
			val h10_the = word("THE","the",10);
			val h11_measure = word("N","measure",11);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_chief
							,
							_(
								t03_of
								,
								_(
									_(
										t04_the
										,
										_(
											t05_german
											,
											t72_central_bank
											,
											73
										)
										,
										74
									)
									,
									_(
										t82_app
										,
										_(
											_(
												t08_
												,
												t09_bundesbank
												,
												76
											)
											,
											t10_
											,
											77
										)
										,
										83
									)
									,
									38
								)
								,
								84
							)
							,
							75
						)
						,
						81
					)
					,
					_(
						t85_app
						,
						t42_hans_tietmeyer
						,
						86
					)
					,
					79
				)
				,
				_(
					_(
						t15_justified
						,
						_(
							t16_this
							,
							t17_measure
							,
							44
						)
						,
						66
					)
					,
					_(
						t18_by
						,
						_(
							t19_citing
							,
							_(
								_(
									t20_the
									,
									_(
										t21_early
										,
										_(
											t22_signs
											,
											_(
												t23_of
												,
												_(
													t24_economic
													,
													t25_slowdown
													,
													46
												)
												,
												47
											)
											,
											67
										)
										,
										87
									)
									,
									68
								)
								,
								_(
									t26_which
									,
									_(
										t27_affect
										,
										_(
											t28_the
											,
											_(
												t29_countries
												,
												_(
													t30_that
													,
													_(
														t31_will
														,
														_(
															t32_adopt
															,
															_(
																t33_the
																,
																t34_euro
																,
																51
															)
															,
															52
														)
														,
														53
													)
													,
													55
												)
												,
												49
											)
											,
											89
										)
										,
										56
									)
									,
									59
								)
								,
								70
							)
							,
							61
						)
						,
						63
					)
					,
					71
				)
				,
				80
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h13_hans_tietmeyer
					,
					_(
						h25_app
						,
						_(
							h04_the
							,
							_(
								h05_head
								,
								_(
									h06_of
									,
									h07_bundesbank
									,
									16
								)
								,
								14
							)
							,
							24
						)
						,
						26
					)
					,
					22
				)
				,
				_(
					h09_justified
					,
					_(
						h10_the
						,
						h11_measure
						,
						19
					)
					,
					20
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
