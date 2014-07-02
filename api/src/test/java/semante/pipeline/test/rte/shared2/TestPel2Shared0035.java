package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0035 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0035() throws Exception {

			// create the vocabulary for the text;
			val t01_a = word("A","A",1);
			val t02_houston = word("$NC_1$","Houston",2);
			val t03_multimillionaire = word("N","multimillionaire",3);
			val t04_said = word("V_2","said",4);
			val t05_he = word("NP_D","he",5);
			val t06_is = word("IS","is",6);
			val t07_building = word("V_2","building",7);
			val t08_a = word("A","a",8);
			val t09_museum = word("N","museum",9);
			val t10_for = word("P_R","for",10);
			val t11_the = word("THE","the",11);
			val t12_light = word("MR","light",12);
			val t13_blue = word("MR","blue",13);
			val t14_num1975 = word("$NC_1$","num1975",14);
			val t15_ford = word("$NC_1$","Ford",15);
			val t16_escort = word("$NC_1$","Escort",16);
			val t17_gl = word("N","GL",17);
			val t18_which = word("WHO_A","which",18);
			val t19_was = word("IS","was",19);
			val t20_once = word("MR","once",20);
			val t21_owned = word("V_1","owned",21);
			val t22_by = word("P_R","by",22);
			val t23_pope = word("$NPC_1$","Pope",23);
			val t24_john = word("$NPC_1$","John",24);
			val t25_paul = word("$NPC_1$","Paul",25);
			val t26_ii = word("NP_D","II",26);
			val t27_in = word("P_R","in",27);
			val t28_his = word("THE","his",28);
			val t29_hometown = word("N","hometown",29);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_num1975 = word("$NC_1$","num1975",2);
			val h03_ford = word("$NC_1$","Ford",3);
			val h04_escort = word("$NC_1$","Escort",4);
			val h05_gl = word("N","GL",5);
			val h06_was = word("IS","was",6);
			val h07_once = word("MR","once",7);
			val h08_owned = word("V_1","owned",8);
			val h09_by = word("P_R","by",9);
			val h10_pope = word("$NPC_1$","Pope",10);
			val h11_john = word("$NPC_1$","John",11);
			val h12_paul = word("$NPC_1$","Paul",12);
			val h13_ii = word("NP_D","II",13);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_a
					,
					_(
						t02_houston
						,
						t03_multimillionaire
						,
						67
					)
					,
					68
				)
				,
				_(
					t04_said
					,
					_(
						t05_he
						,
						_(
							t06_is
							,
							_(
								t07_building
								,
								_(
									t08_a
									,
									_(
										t09_museum
										,
										_(
											t10_for
											,
											_(
												_(
													t11_the
													,
													_(
														t12_light
														,
														_(
															t13_blue
															,
															_(
																t14_num1975
																,
																_(
																	t15_ford
																	,
																	_(
																		t16_escort
																		,
																		t17_gl
																		,
																		58
																	)
																	,
																	73
																)
																,
																72
															)
															,
															34
														)
														,
														74
													)
													,
													59
												)
												,
												_(
													t18_which
													,
													_(
														_(
															t19_was
															,
															_(
																t20_once
																,
																_(
																	t21_owned
																	,
																	_(
																		t22_by
																		,
																		_(
																			t23_pope
																			,
																			_(
																				t24_john
																				,
																				_(
																					t25_paul
																					,
																					t26_ii
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
																		43
																	)
																	,
																	44
																)
																,
																65
															)
															,
															66
														)
														,
														_(
															t27_in
															,
															_(
																t28_his
																,
																t29_hometown
																,
																40
															)
															,
															41
														)
														,
														46
													)
													,
													47
												)
												,
												75
											)
											,
											50
										)
										,
										33
									)
									,
									70
								)
								,
								52
							)
							,
							53
						)
						,
						54
					)
					,
					56
				)
				,
				69
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_num1975
						,
						_(
							h03_ford
							,
							_(
								h04_escort
								,
								h05_gl
								,
								27
							)
							,
							28
						)
						,
						29
					)
					,
					30
				)
				,
				_(
					h06_was
					,
					_(
						h07_once
						,
						_(
							h08_owned
							,
							_(
								h09_by
								,
								_(
									h10_pope
									,
									_(
										h11_john
										,
										_(
											h12_paul
											,
											h13_ii
											,
											22
										)
										,
										23
									)
									,
									24
								)
								,
								18
							)
							,
							19
						)
						,
						25
					)
					,
					26
				)
				,
				31
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

