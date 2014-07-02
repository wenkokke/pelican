package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0036 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0036() throws Exception {

			// create the vocabulary for the text;
			val t01_korean = word("MI","Korean",1);
			val t02_seoul = word("NP_D","Seoul",2);
			val t119_app = word("WHO_A","APP",119);
			val t04_formally = word("MI","formally",4);
			val t05_soultukpyolsi = word("NP_D","SoulTukpyolsi",5);
			val t121_app = word("WHO_A","APP",121);
			val t06_ = word("IGNORE","",6);
			val t07_special = word("MR","Special",7);
			val t08_city = word("N","City",8);
			val t09_of = word("P_R","of",9);
			val t10_seoul = word("NP_D","Seoul",10);
			val t11_ = word("IGNORE","",11);
			val t124_app = word("WHO_A","APP",124);
			val t13_capital = word("N","capital",13);
			val t14_of = word("P_R","of",14);
			val t15_the = word("THE","the",15);
			val t16_republic = word("N","Republic",16);
			val t17_of = word("P_R","of",17);
			val t18_korea = word("NP_D","Korea",18);
			val t128_app = word("WHO_A","APP",128);
			val t19_ = word("IGNORE","",19);
			val t20_south = word("$NPC_1$","South",20);
			val t21_korea = word("NP_D","Korea",21);
			val t22_ = word("IGNORE","",22);
			val t97_is_located = word("V_1","is_located",97);
			val t26_in = word("P_R","in",26);
			val t27_the = word("THE","the",27);
			val t28_northwestern = word("MR","northwestern",28);
			val t29_part = word("N","part",29);
			val t30_of = word("P_R","of",30);
			val t31_the = word("THE","the",31);
			val t32_country = word("N","country",32);
			val t33_on = word("P_R","on",33);
			val t34_the = word("THE","the",34);
			val t35_hangang = word("N","HanGang",35);
			val t133_app = word("WHO_A","APP",133);
			val t36_ = word("IGNORE","",36);
			val t37_han = word("$NPC_1$","Han",37);
			val t38_river = word("NP_D","River",38);
			val t39_ = word("IGNORE","",39);
			val t41_which = word("WHO_A","which",41);
			val t42_lies = word("V_2","lies",42);
			val t43_num37 = word("MI","num37",43);
			val t44_miles = word("NP_D","miles",44);
			val t137_app = word("WHO_A","APP",137);
			val t45_ = word("IGNORE","",45);
			val t46_num60 = word("MI","num60",46);
			val t47_kilometres = word("NP_D","kilometres",47);
			val t48_ = word("IGNORE","",48);
			val t49_from = word("P_R","from",49);
			val t50_the = word("THE","the",50);
			val t51_yellow = word("$NC_1$","Yellow",51);
			val t52_sea = word("N","Sea",52);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_capital = word("N","capital",2);
			val h03_of = word("P_R","of",3);
			val h04_south = word("$NPC_1$","South",4);
			val h05_korea = word("NP_D","Korea",5);
			val h06_is = word("IS","is",6);
			val h07_seoul = word("NP_D","Seoul",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t01_korean
							,
							t02_seoul
							,
							54
						)
						,
						_(
							t119_app
							,
							_(
								t04_formally
								,
								_(
									t05_soultukpyolsi
									,
									_(
										t121_app
										,
										_(
											_(
												t06_
												,
												_(
													t07_special
													,
													_(
														t08_city
														,
														_(
															t09_of
															,
															t10_seoul
															,
															59
														)
														,
														57
													)
													,
													123
												)
												,
												111
											)
											,
											t11_
											,
											112
										)
										,
										122
									)
									,
									62
								)
								,
								113
							)
							,
							120
						)
						,
						118
					)
					,
					_(
						t124_app
						,
						_(
							t13_capital
							,
							_(
								t14_of
								,
								_(
									_(
										t15_the
										,
										_(
											t16_republic
											,
											_(
												t17_of
												,
												t18_korea
												,
												69
											)
											,
											64
										)
										,
										126
									)
									,
									_(
										t128_app
										,
										_(
											_(
												t19_
												,
												_(
													t20_south
													,
													t21_korea
													,
													66
												)
												,
												114
											)
											,
											t22_
											,
											115
										)
										,
										129
									)
									,
									70
								)
								,
								71
							)
							,
							72
						)
						,
						125
					)
					,
					116
				)
				,
				_(
					t97_is_located
					,
					_(
						t26_in
						,
						_(
							t27_the
							,
							_(
								t28_northwestern
								,
								_(
									t29_part
									,
									_(
										t30_of
										,
										_(
											t31_the
											,
											_(
												t32_country
												,
												_(
													t33_on
													,
													_(
														_(
															t34_the
															,
															t35_hangang
															,
															76
														)
														,
														_(
															t133_app
															,
															_(
																_(
																	_(
																		t36_
																		,
																		_(
																			t37_han
																			,
																			t38_river
																			,
																			77
																		)
																		,
																		101
																	)
																	,
																	t39_
																	,
																	102
																)
																,
																_(
																	t41_which
																	,
																	_(
																		_(
																			t42_lies
																			,
																			_(
																				_(
																					t43_num37
																					,
																					t44_miles
																					,
																					106
																				)
																				,
																				_(
																					t137_app
																					,
																					_(
																						_(
																							t45_
																							,
																							_(
																								t46_num60
																								,
																								t47_kilometres
																								,
																								81
																							)
																							,
																							103
																						)
																						,
																						t48_
																						,
																						104
																					)
																					,
																					138
																				)
																				,
																				86
																			)
																			,
																			87
																		)
																		,
																		_(
																			t49_from
																			,
																			_(
																				t50_the
																				,
																				_(
																					t51_yellow
																					,
																					t52_sea
																					,
																					107
																				)
																				,
																				108
																			)
																			,
																			85
																		)
																		,
																		88
																	)
																	,
																	89
																)
																,
																134
															)
															,
															135
														)
														,
														79
													)
													,
													91
												)
												,
												75
											)
											,
											132
										)
										,
										93
									)
									,
									99
								)
								,
								131
							)
							,
							100
						)
						,
						95
					)
					,
					130
				)
				,
				117
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					_(
						h02_capital
						,
						_(
							h03_of
							,
							_(
								h04_south
								,
								h05_korea
								,
								10
							)
							,
							11
						)
						,
						9
					)
					,
					17
				)
				,
				_(
					h06_is
					,
					h07_seoul
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

