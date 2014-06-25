package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0004 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0004() throws Exception {

			// create the vocabulary for the text;
			val t00_xochimilco = word("NP_D","Xochimilco");
			val t01_app = word("WHO_A","APP");
			val t02_another = word("A","another");
			val t03_small = word("MR","small");
			val t04_town = word("N","town");
			val t05_app = word("WHO_R","APP");
			val t06_subsumed = word("V_1","subsumed");
			val t07_by = word("P_R","by");
			val t08_the = word("THE","the");
			val t09_city = word("N","city");
			val t10_is = word("IS","is");
			val t11_a = word("A","a");
			val t12_popular = word("MI","popular");
			val t13_tourist = word("$NC_1$","tourist");
			val t14_destination = word("N","destination");
			val t15_at = word("P_I","at");
			val t16_the = word("THE","the");
			val t17_southeastern = word("MR","southeastern");
			val t18_tip = word("N","tip");
			val t19_of = word("P_R","of");
			val t20_the = word("THE","the");
			val t21_city = word("N","city");
			val t22_which = word("WHO_R","which");
			val t23_harbors = word("V_2","harbors");
			val t24_det = word("EMPTYDET","DET");
			val t25_chinampas = word("N","chinampas");
			val t26_app = word("WHO_A","APP");
			val t27_det = word("EMPTYDET","DET");
			val t28_boats = word("N","boats");
			val t29_app = word("WHO_R","APP");
			val t30_made = word("V_1","made");
			val t31_from = word("P_R","from");
			val t32_det = word("EMPTYDET","DET");
			val t33_reeds = word("N","reeds");
			val t34_which = word("WHO_R","which");
			val t35_were = word("IS","were");
			val t36_used = word("V_1","used");
			val t37_since = word("P_R","since");
			val t38_det = word("THE","DET");
			val t39_precolumbian = word("MR","preColumbian");
			val t40_times = word("N","times");

			// create the vocabulary for the hypothesis;
			val h00_a = word("A","A");
			val h01_small = word("MR","small");
			val h02_town = word("N","town");
			val h03_app = word("WHO_A","APP");
			val h04_xochimilco = word("NP_D","Xochimilco");
			val h05_is = word("IS","is");
			val h06_at = word("P_R","at");
			val h07_the = word("THE","the");
			val h08_southeastern = word("MR","southeastern");
			val h09_tip = word("N","tip");
			val h10_of = word("P_R","of");
			val h11_the = word("THE","the");
			val h12_city = word("N","city");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_xochimilco
					,
					_(
						t01_app
						,
						_(
							t02_another
							,
							_(
								t03_small
								,
								_(
									t04_town
									,
									_(
										t05_app
										,
										_(
											t06_subsumed
											,
											_(
												t07_by
												,
												_(
													t08_the
													,
													t09_city
												)
											)
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					t10_is
					,
					_(
						t11_a
						,
						_(
							t12_popular
							,
							_(
								_(
									_(
										t13_tourist
										,
										t14_destination
									)
									,
									_(
										t15_at
										,
										_(
											t16_the
											,
											_(
												t17_southeastern
												,
												_(
													t18_tip
													,
													_(
														t19_of
														,
														_(
															t20_the
															,
															t21_city
														)
													)
												)
											)
										)
									)
								)
								,
								_(
									t22_which
									,
									_(
										t23_harbors
										,
										_(
											_(
												t24_det
												,
												t25_chinampas
											)
											,
											_(
												t26_app
												,
												_(
													t27_det
													,
													_(
														t28_boats
														,
														_(
															t29_app
															,
															_(
																t30_made
																,
																_(
																	t31_from
																	,
																	_(
																		t32_det
																		,
																		_(
																			t33_reeds
																			,
																			_(
																				t34_which
																				,
																				_(
																					t35_were
																					,
																					_(
																						t36_used
																						,
																						_(
																							t37_since
																							,
																							_(
																								t38_det
																								,
																								_(
																									t39_precolumbian
																									,
																									t40_times
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
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h00_a
						,
						_(
							h01_small
							,
							h02_town
						)
					)
					,
					_(
						h03_app
						,
						h04_xochimilco
					)
				)
				,
				_(
					h05_is
					,
					_(
						h06_at
						,
						_(
							h07_the
							,
							_(
								h08_southeastern
								,
								_(
									h09_tip
									,
									_(
										h10_of
										,
										_(
											h11_the
											,
											h12_city
										)
									)
								)
							)
						)
					)
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

