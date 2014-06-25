package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0036 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0036() throws Exception {

			// create the vocabulary for the text;
			val t00_korean = word("MI","Korean");
			val t01_seoul = word("NP_D","Seoul");
			val t02_app = word("WHO_A","APP");
			val t03_formally = word("MI","formally");
			val t04_soultukpyolsi = word("NP_D","SoulTukpyolsi");
			val t05_app = word("WHO_A","APP");
			val t06_ = word("IGNORE","");
			val t07_special = word("MR","Special");
			val t08_city = word("N","City");
			val t09_of = word("P_R","of");
			val t10_seoul = word("NP_D","Seoul");
			val t11_ = word("IGNORE","");
			val t12_app = word("WHO_A","APP");
			val t13_capital = word("N","capital");
			val t14_of = word("P_R","of");
			val t15_the = word("THE","the");
			val t16_republic = word("N","Republic");
			val t17_of = word("P_R","of");
			val t18_korea = word("NP_D","Korea");
			val t19_app = word("WHO_A","APP");
			val t20_ = word("IGNORE","");
			val t21_south = word("$NPC_1$","South");
			val t22_korea = word("NP_D","Korea");
			val t23_ = word("IGNORE","");
			val t24_is_located = word("V_1","is_located");
			val t25_in = word("P_R","in");
			val t26_the = word("THE","the");
			val t27_northwestern = word("MR","northwestern");
			val t28_part = word("N","part");
			val t29_of = word("P_R","of");
			val t30_the = word("THE","the");
			val t31_country = word("N","country");
			val t32_on = word("P_R","on");
			val t33_the = word("THE","the");
			val t34_hangang = word("N","HanGang");
			val t35_app = word("WHO_A","APP");
			val t36_ = word("IGNORE","");
			val t37_han = word("$NPC_1$","Han");
			val t38_river = word("NP_D","River");
			val t39_ = word("IGNORE","");
			val t40_which = word("WHO_A","which");
			val t41_lies = word("V_2","lies");
			val t42_num37 = word("MI","num37");
			val t43_miles = word("NP_D","miles");
			val t44_app = word("WHO_A","APP");
			val t45_ = word("IGNORE","");
			val t46_num60 = word("MI","num60");
			val t47_kilometres = word("NP_D","kilometres");
			val t48_ = word("IGNORE","");
			val t49_from = word("P_R","from");
			val t50_the = word("THE","the");
			val t51_yellow = word("$NC_1$","Yellow");
			val t52_sea = word("N","Sea");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","The");
			val h01_capital = word("N","capital");
			val h02_of = word("P_R","of");
			val h03_south = word("$NPC_1$","South");
			val h04_korea = word("NP_D","Korea");
			val h05_is = word("IS","is");
			val h06_seoul = word("NP_D","Seoul");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t00_korean
							,
							t01_seoul
						)
						,
						_(
							t02_app
							,
							_(
								t03_formally
								,
								_(
									t04_soultukpyolsi
									,
									_(
										t05_app
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
														)
													)
												)
											)
											,
											t11_
										)
									)
								)
							)
						)
					)
					,
					_(
						t12_app
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
											)
										)
									)
									,
									_(
										t19_app
										,
										_(
											_(
												t20_
												,
												_(
													t21_south
													,
													t22_korea
												)
											)
											,
											t23_
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					t24_is_located
					,
					_(
						t25_in
						,
						_(
							t26_the
							,
							_(
								t27_northwestern
								,
								_(
									t28_part
									,
									_(
										t29_of
										,
										_(
											t30_the
											,
											_(
												t31_country
												,
												_(
													t32_on
													,
													_(
														_(
															t33_the
															,
															t34_hangang
														)
														,
														_(
															t35_app
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
																		)
																	)
																	,
																	t39_
																)
																,
																_(
																	t40_which
																	,
																	_(
																		_(
																			t41_lies
																			,
																			_(
																				_(
																					t42_num37
																					,
																					t43_miles
																				)
																				,
																				_(
																					t44_app
																					,
																					_(
																						_(
																							t45_
																							,
																							_(
																								t46_num60
																								,
																								t47_kilometres
																							)
																						)
																						,
																						t48_
																					)
																				)
																			)
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
																				)
																			)
																		)
																	)
																)
															)
														)
													)
												)
											)
										)
									)
								)
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_the
					,
					_(
						h01_capital
						,
						_(
							h02_of
							,
							_(
								h03_south
								,
								h04_korea
							)
						)
					)
				)
				,
				_(
					h05_is
					,
					h06_seoul
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

