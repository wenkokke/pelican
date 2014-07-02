package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0013 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0013() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_media = word("N","media",2);
			val t74_pounced_upon = word("V_2","pounced_upon",74);
			val t05_the = word("THE","the",5);
			val t06_religion = word("N","religion",6);
			val t07_of = word("P_R","of",7);
			val t08_jurgen = word("$NPC_1$","Jurgen",8);
			val t09_schneider = word("NP_D","Schneider",9);
			val t79_app = word("WHO_A","APP",79);
			val t11_the = word("THE","the",11);
			val t12_developer = word("N","developer",12);
			val t13_who = word("WHO_R","who",13);
			val t14_went = word("V_1","went",14);
			val t15_bankrupt = word("MR","bankrupt",15);
			val t16_and = word("AND","and",16);
			val t17_fled = word("V_2","fled",17);
			val t18_germany = word("NP_D","Germany",18);
			val t19_owing = word("GER_2","owing",19);
			val t84_det = word("A","DET",84);
			val t20_billions = word("N","billions",20);
			val t21_of = word("P_R","of",21);
			val t22_dms = word("NP_D","DMs",22);
			val t23_to = word("P_R","to",23);
			val t24_deutsche = word("$NPC_1$","Deutsche",24);
			val t25_bank = word("NP_D","Bank",25);
			val t27_as = word("P_R","as",27);
			val t28_the = word("THE","the",28);
			val t29_cause = word("N","cause",29);
			val t30_of = word("P_R","of",30);
			val t31_schneider = word("NP_D","Schneider",31);
			val t32_s = word("POSS","s",32);
			val t33_downfall = word("N","downfall",33);

			// create the vocabulary for the hypothesis;
			val h01_jurgen = word("$NPC_1$","Jurgen",1);
			val h02_schneider = word("NP_D","Schneider",2);
			val h03_went = word("V_1","went",3);
			val h04_bankrupt = word("MR","bankrupt",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_media
					,
					35
				)
				,
				_(
					_(
						t74_pounced_upon
						,
						_(
							t05_the
							,
							_(
								t06_religion
								,
								_(
									t07_of
									,
									_(
										_(
											t08_jurgen
											,
											t09_schneider
											,
											37
										)
										,
										_(
											t79_app
											,
											_(
												t11_the
												,
												_(
													t12_developer
													,
													_(
														t13_who
														,
														_(
															_(
																t14_went
																,
																t15_bankrupt
																,
																42
															)
															,
															_(
																t16_and
																,
																_(
																	_(
																		t17_fled
																		,
																		t18_germany
																		,
																		52
																	)
																	,
																	_(
																		t19_owing
																		,
																		_(
																			t84_det
																			,
																			_(
																				_(
																					t20_billions
																					,
																					_(
																						t21_of
																						,
																						t22_dms
																						,
																						46
																					)
																					,
																					47
																				)
																				,
																				_(
																					t23_to
																					,
																					_(
																						t24_deutsche
																						,
																						t25_bank
																						,
																						48
																					)
																					,
																					49
																				)
																				,
																				85
																			)
																			,
																			87
																		)
																		,
																		70
																	)
																	,
																	69
																)
																,
																83
															)
															,
															82
														)
														,
														55
													)
													,
													38
												)
												,
												81
											)
											,
											80
										)
										,
										73
									)
									,
									58
								)
								,
								36
							)
							,
							78
						)
						,
						77
					)
					,
					_(
						t27_as
						,
						_(
							t28_the
							,
							_(
								t29_cause
								,
								_(
									t30_of
									,
									_(
										_(
											t31_schneider
											,
											t32_s
											,
											62
										)
										,
										t33_downfall
										,
										63
									)
									,
									64
								)
								,
								61
							)
							,
							86
						)
						,
						66
					)
					,
					75
				)
				,
				76
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_jurgen
					,
					h02_schneider
					,
					6
				)
				,
				_(
					h03_went
					,
					h04_bankrupt
					,
					8
				)
				,
				10
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

