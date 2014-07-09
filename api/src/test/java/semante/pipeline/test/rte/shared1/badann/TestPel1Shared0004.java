package semante.pipeline.test.rte.shared1.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0004 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0004() throws Exception {

			// create the vocabulary for the text;
			val t01_xochimilco = word("NP_D","Xochimilco",1);
			val t84_app = word("WHO_A","APP",84);
			val t03_another = word("A","another",3);
			val t04_small = word("MR","small",4);
			val t05_town = word("N","town",5);
			val t86_app = word("WHO_R","APP",86);
			val t06_subsumed = word("V_1","subsumed",6);
			val t07_by = word("P_R","by",7);
			val t08_the = word("THE","the",8);
			val t09_city = word("N","city",9);
			val t11_is = word("IS","is",11);
			val t12_a = word("A","a",12);
			val t13_popular = word("MI","popular",13);
			val t74_tourist_destination = word("N","tourist_destination",74);
			val t16_at = word("P_I","at",16);
			val t17_the = word("THE","the",17);
			val t18_southeastern = word("MR","southeastern",18);
			val t19_tip = word("N","tip",19);
			val t20_of = word("P_R","of",20);
			val t21_the = word("THE","the",21);
			val t22_city = word("N","city",22);
			val t23_which = word("WHO_R","which",23);
			val t24_harbors = word("V_2","harbors",24);
			val t103_det = word("EMPTYDET","DET",103);
			val t25_chinampas = word("N","chinampas",25);
			val t101_app = word("WHO_A","APP",101);
			val t99_det = word("EMPTYDET","DET",99);
			val t27_boats = word("N","boats",27);
			val t93_app = word("WHO_R","APP",93);
			val t28_made = word("V_1","made",28);
			val t29_from = word("P_R","from",29);
			val t97_det = word("EMPTYDET","DET",97);
			val t30_reeds = word("N","reeds",30);
			val t31_which = word("WHO_R","which",31);
			val t32_were = word("IS","were",32);
			val t33_used = word("V_1","used",33);
			val t34_since = word("P_R","since",34);
			val t95_det = word("THE","DET",95);
			val t35_precolumbian = word("MR","preColumbian",35);
			val t36_times = word("N","times",36);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_small = word("MR","small",2);
			val h03_town = word("N","town",3);
			val h33_app = word("WHO_A","APP",33);
			val h05_xochimilco = word("NP_D","Xochimilco",5);
			val h07_is = word("IS","is",7);
			val h08_at = word("P_R","at",8);
			val h09_the = word("THE","the",9);
			val h10_southeastern = word("MR","southeastern",10);
			val h11_tip = word("N","tip",11);
			val h12_of = word("P_R","of",12);
			val h13_the = word("THE","the",13);
			val h14_city = word("N","city",14);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_xochimilco
					,
					_(
						t84_app
						,
						_(
							t03_another
							,
							_(
								t04_small
								,
								_(
									t05_town
									,
									_(
										t86_app
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
													,
													40
												)
												,
												41
											)
											,
											42
										)
										,
										87
									)
									,
									80
								)
								,
								88
							)
							,
							81
						)
						,
						85
					)
					,
					82
				)
				,
				_(
					t11_is
					,
					_(
						t12_a
						,
						_(
							t13_popular
							,
							_(
								_(
									t74_tourist_destination
									,
									_(
										t16_at
										,
										_(
											t17_the
											,
											_(
												t18_southeastern
												,
												_(
													t19_tip
													,
													_(
														t20_of
														,
														_(
															t21_the
															,
															t22_city
															,
															47
														)
														,
														68
													)
													,
													77
												)
												,
												92
											)
											,
											78
										)
										,
										70
									)
									,
									75
								)
								,
								_(
									t23_which
									,
									_(
										t24_harbors
										,
										_(
											_(
												t103_det
												,
												t25_chinampas
												,
												104
											)
											,
											_(
												t101_app
												,
												_(
													t99_det
													,
													_(
														t27_boats
														,
														_(
															t93_app
															,
															_(
																t28_made
																,
																_(
																	t29_from
																	,
																	_(
																		t97_det
																		,
																		_(
																			t30_reeds
																			,
																			_(
																				t31_which
																				,
																				_(
																					t32_were
																					,
																					_(
																						t33_used
																						,
																						_(
																							t34_since
																							,
																							_(
																								t95_det
																								,
																								_(
																									t35_precolumbian
																									,
																									t36_times
																									,
																									53
																								)
																								,
																								96
																							)
																							,
																							54
																						)
																						,
																						55
																					)
																					,
																					56
																				)
																				,
																				58
																			)
																			,
																			59
																		)
																		,
																		98
																	)
																	,
																	60
																)
																,
																61
															)
															,
															94
														)
														,
														62
													)
													,
													100
												)
												,
												102
											)
											,
											64
										)
										,
										105
									)
									,
									66
								)
								,
								90
							)
							,
							91
						)
						,
						76
					)
					,
					89
				)
				,
				83
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h01_a
						,
						_(
							h02_small
							,
							h03_town
							,
							28
						)
						,
						29
					)
					,
					_(
						h33_app
						,
						h05_xochimilco
						,
						34
					)
					,
					30
				)
				,
				_(
					h07_is
					,
					_(
						h08_at
						,
						_(
							h09_the
							,
							_(
								h10_southeastern
								,
								_(
									h11_tip
									,
									_(
										h12_of
										,
										_(
											h13_the
											,
											h14_city
											,
											20
										)
										,
										21
									)
									,
									26
								)
								,
								32
							)
							,
							27
						)
						,
						23
					)
					,
					24
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
