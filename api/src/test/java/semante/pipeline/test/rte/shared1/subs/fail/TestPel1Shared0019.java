package semante.pipeline.test.rte.shared1.subs.fail;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0019 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0019() throws Exception {

			// create the vocabulary for the text;
			val t00_javier = word("$NPC_1$","Javier");
			val t01_solana = word("NP_D","Solana");
			val t02_app = word("WHO_A","APP");
			val t03_the = word("THE","the");
			val t04_spanish = word("MI","Spanish");
			val t05_marxist = word("N","Marxist");
			val t06_who = word("WHO_A","who");
			val t07_serves_as = word("V_2","serves_as");
			val t08_nato = word("NP_D","NATO");
			val t09_s = word("POSS","s");
			val t10_secretary = word("MR","secretary");
			val t11_general = word("N","general");
			val t12_warned = word("FACT","warned");
			val t13_that = word("IGNORE","that");
			val t14_det = word("THE","DET");
			val t15_nato = word("MR","NATO");
			val t16_troops = word("N","troops");
			val t17_will = word("V_AUX","will");
			val t18_take = word("V_2","take");
			val t19_det = word("THE","DET");
			val t20_necessary = word("MR","necessary");
			val t21_measures = word("N","measures");
			val t22_including = word("GER_2","including");
			val t23_the = word("THE","the");
			val t24_use = word("N","use");
			val t25_of = word("P_R","of");
			val t26_force = word("N","force");
			val t27_against = word("P_R","against");
			val t28_det = word("THE","DET");
			val t29_media = word("MR","media");
			val t30_networks = word("N","networks");

			// create the vocabulary for the hypothesis;
			val h00_javier = word("$NPC_1$","Javier");
			val h01_solana = word("NP_D","Solana");
			val h02_is = word("IS","is");
			val h03_nato = word("NP_D","NATO");
			val h04_s = word("POSS","s");
			val h05_secretary = word("MR","secretary");
			val h06_general = word("N","general");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_javier
						,
						t01_solana
					)
					,
					_(
						t02_app
						,
						_(
							_(
								t03_the
								,
								_(
									t04_spanish
									,
									t05_marxist
								)
							)
							,
							_(
								t06_who
								,
								_(
									t07_serves_as
									,
									_(
										_(
											t08_nato
											,
											t09_s
										)
										,
										_(
											t10_secretary
											,
											t11_general
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					t12_warned
					,
					_(
						t13_that
						,
						_(
							_(
								t14_det
								,
								_(
									t15_nato
									,
									t16_troops
								)
							)
							,
							_(
								t17_will
								,
								_(
									_(
										t18_take
										,
										_(
											_(
												t19_det
												,
												_(
													t20_necessary
													,
													t21_measures
												)
											)
											,
											_(
												t22_including
												,
												_(
													t23_the
													,
													_(
														t24_use
														,
														_(
															t25_of
															,
															t26_force
														)
													)
												)
											)
										)
									)
									,
									_(
										t27_against
										,
										_(
											t28_det
											,
											_(
												t29_media
												,
												t30_networks
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
					h00_javier
					,
					h01_solana
				)
				,
				_(
					h02_is
					,
					_(
						_(
							h03_nato
							,
							h04_s
						)
						,
						_(
							h05_secretary
							,
							h06_general
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_serves_as = word("V_2","serves_as");
			val sh000_is = word("IS","is");
val st0 = 
			st000_serves_as
			;
val sh0 = 
			sh000_is
			;
val ss0 = subs(st0, sh0);
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
			ss0
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

