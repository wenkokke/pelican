package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0006 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0006() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_two = word("NUMBER","two",2);
			val t03_rescuers = word("N","rescuers",3);
			val t04_who = word("WHO_R","who",4);
			val t05_retrieved = word("V_2","retrieved",5);
			val t06_the = word("THE","the",6);
			val t07_whale = word("N","whale",7);
			val t08_are = word("IS","are",8);
			val t66_det = word("EMPTYDET","DET",66);
			val t09_firefighters = word("N","firefighters",9);
			val t10_with = word("P_R","with",10);
			val t11_californiabased = word("MI","CaliforniaBased",11);
			val t12_norfema = word("NP_D","NorFEMA",12);
			val t68_app = word("WHO_A","APP",68);
			val t14_the = word("THE","the",14);
			val t15_fema = word("$NC_1$","FEMA",15);
			val t16_team = word("N","team",16);
			val t17_which = word("WHO_R","which",17);
			val t18_protects = word("V_2","protects",18);
			val t19_the = word("THE","the",19);
			val t20_marine = word("MI","marine",20);
			val t21_ecosystem = word("N","ecosystem",21);
			val t22_by = word("P_R","by",22);
			val t23_saving = word("GER_2","saving",23);
			val t73_det = word("EMPTYDET","DET",73);
			val t24_endangered = word("MI","endangered",24);
			val t25_marine = word("MI","marine",25);
			val t26_organisms = word("N","organisms",26);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_californiabased = word("MI","CaliforniaBased",2);
			val h03_fema = word("$NC_1$","FEMA",3);
			val h04_team = word("N","team",4);
			val h05_protects = word("V_2","protects",5);
			val h06_the = word("THE","the",6);
			val h07_marine = word("MI","marine",7);
			val h08_ecosystem = word("N","ecosystem",8);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_two
						,
						_(
							t03_rescuers
							,
							_(
								t04_who
								,
								_(
									t05_retrieved
									,
									_(
										t06_the
										,
										t07_whale
										,
										30
									)
									,
									31
								)
								,
								33
							)
							,
							62
						)
						,
						65
					)
					,
					63
				)
				,
				_(
					t08_are
					,
					_(
						t66_det
						,
						_(
							t09_firefighters
							,
							_(
								t10_with
								,
								_(
									_(
										t11_californiabased
										,
										t12_norfema
										,
										36
									)
									,
									_(
										t68_app
										,
										_(
											t14_the
											,
											_(
												_(
													t15_fema
													,
													t16_team
													,
													71
												)
												,
												_(
													t17_which
													,
													_(
														_(
															t18_protects
															,
															_(
																t19_the
																,
																_(
																	t20_marine
																	,
																	t21_ecosystem
																	,
																	55
																)
																,
																56
															)
															,
															57
														)
														,
														_(
															t22_by
															,
															_(
																t23_saving
																,
																_(
																	t73_det
																	,
																	_(
																		t24_endangered
																		,
																		_(
																			t25_marine
																			,
																			t26_organisms
																			,
																			58
																		)
																		,
																		59
																	)
																	,
																	74
																)
																,
																43
															)
															,
															45
														)
														,
														60
													)
													,
													48
												)
												,
												54
											)
											,
											72
										)
										,
										69
									)
									,
									37
								)
								,
								70
							)
							,
							38
						)
						,
						67
					)
					,
					51
				)
				,
				64
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_californiabased
						,
						_(
							h03_fema
							,
							h04_team
							,
							16
						)
						,
						17
					)
					,
					18
				)
				,
				_(
					h05_protects
					,
					_(
						h06_the
						,
						_(
							h07_marine
							,
							h08_ecosystem
							,
							14
						)
						,
						15
					)
					,
					12
				)
				,
				19
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

