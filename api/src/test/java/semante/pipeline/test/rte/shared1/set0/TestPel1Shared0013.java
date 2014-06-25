package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0013 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0013() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_media = word("N","media");
			val t02_pounced_upon = word("V_2","pounced_upon");
			val t03_the = word("THE","the");
			val t04_religion = word("N","religion");
			val t05_of = word("P_R","of");
			val t06_jurgen = word("$NPC_1$","Jurgen");
			val t07_schneider = word("NP_D","Schneider");
			val t08_app = word("WHO_A","APP");
			val t09_the = word("THE","the");
			val t10_developer = word("N","developer");
			val t11_who = word("WHO_R","who");
			val t12_went = word("V_1","went");
			val t13_bankrupt = word("MR","bankrupt");
			val t14_and = word("AND","and");
			val t15_fled = word("V_2","fled");
			val t16_germany = word("NP_D","Germany");
			val t17_owing = word("GER_2","owing");
			val t18_det = word("A","DET");
			val t19_billions = word("N","billions");
			val t20_of = word("P_R","of");
			val t21_dms = word("NP_D","DMs");
			val t22_to = word("P_R","to");
			val t23_deutsche = word("$NPC_1$","Deutsche");
			val t24_bank = word("NP_D","Bank");
			val t25_as = word("P_R","as");
			val t26_the = word("THE","the");
			val t27_cause = word("N","cause");
			val t28_of = word("P_R","of");
			val t29_schneider = word("NP_D","Schneider");
			val t30_s = word("POSS","s");
			val t31_downfall = word("N","downfall");

			// create the vocabulary for the hypothesis;
			val h00_jurgen = word("$NPC_1$","Jurgen");
			val h01_schneider = word("NP_D","Schneider");
			val h02_went = word("V_1","went");
			val h03_bankrupt = word("MR","bankrupt");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					t01_media
				)
				,
				_(
					_(
						t02_pounced_upon
						,
						_(
							t03_the
							,
							_(
								t04_religion
								,
								_(
									t05_of
									,
									_(
										_(
											t06_jurgen
											,
											t07_schneider
										)
										,
										_(
											t08_app
											,
											_(
												t09_the
												,
												_(
													t10_developer
													,
													_(
														t11_who
														,
														_(
															_(
																t12_went
																,
																t13_bankrupt
															)
															,
															_(
																t14_and
																,
																_(
																	_(
																		t15_fled
																		,
																		t16_germany
																	)
																	,
																	_(
																		t17_owing
																		,
																		_(
																			t18_det
																			,
																			_(
																				_(
																					t19_billions
																					,
																					_(
																						t20_of
																						,
																						t21_dms
																					)
																				)
																				,
																				_(
																					t22_to
																					,
																					_(
																						t23_deutsche
																						,
																						t24_bank
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
					,
					_(
						t25_as
						,
						_(
							t26_the
							,
							_(
								t27_cause
								,
								_(
									t28_of
									,
									_(
										_(
											t29_schneider
											,
											t30_s
										)
										,
										t31_downfall
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
					h00_jurgen
					,
					h01_schneider
				)
				,
				_(
					h02_went
					,
					h03_bankrupt
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

