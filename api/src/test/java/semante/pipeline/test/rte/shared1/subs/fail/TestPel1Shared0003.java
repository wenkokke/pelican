package semante.pipeline.test.rte.shared1.subs.fail;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0003 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0003() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_president = word("N","president");
			val t02_of = word("P_R","of");
			val t03_congress = word("NP_D","Congress");
			val t04_app = word("WHO_A","APP");
			val t05_sonia = word("$NPC_1$","Sonia");
			val t06_gandhi = word("NP_D","Gandhi");
			val t07_has = word("V_AUX","has");
			val t08_been = word("IS","been");
			val t09_allotted = word("V_2","allotted");
			val t10_a = word("A","a");
			val t11_separate = word("MR","separate");
			val t12_office = word("N","office");
			val t13_here = word("MR","here");
			val t14_as = word("P_R","as");
			val t15_det = word("A","DET");
			val t16_head = word("N","head");
			val t17_of = word("P_R","of");
			val t18_the = word("THE","the");
			val t19_national = word("MI","national");
			val t20_advisory = word("MR","advisory");
			val t21_panel = word("N","panel");
			val t22_for = word("P_R","for");
			val t23_india = word("NP_D","India");
			val t24_s = word("GEN","s");
			val t25_ruling = word("$NC_1$","ruling");
			val t26_coalition = word("N","coalition");

			// create the vocabulary for the hypothesis;
			val h00_ms = word("$NPC_1$","Ms");
			val h01_gandhi = word("NP_D","Gandhi");
			val h02_is = word("IS","is");
			val h03_a = word("A","a");
			val h04_member = word("N","member");
			val h05_of = word("P_R","of");
			val h06_congress = word("NP_D","Congress");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_president
							,
							_(
								t02_of
								,
								t03_congress
							)
						)
					)
					,
					_(
						t04_app
						,
						_(
							t05_sonia
							,
							t06_gandhi
						)
					)
				)
				,
				_(
					t07_has
					,
					_(
						t08_been
						,
						_(
							_(
								_(
									t09_allotted
									,
									_(
										t10_a
										,
										_(
											t11_separate
											,
											t12_office
										)
									)
								)
								,
								t13_here
							)
							,
							_(
								t14_as
								,
								_(
									t15_det
									,
									_(
										t16_head
										,
										_(
											t17_of
											,
											_(
												t18_the
												,
												_(
													t19_national
													,
													_(
														t20_advisory
														,
														_(
															t21_panel
															,
															_(
																t22_for
																,
																_(
																	_(
																		t23_india
																		,
																		t24_s
																	)
																	,
																	_(
																		t25_ruling
																		,
																		t26_coalition
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
					h00_ms
					,
					h01_gandhi
				)
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_member
							,
							_(
								h05_of
								,
								h06_congress
							)
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_president = word("N","president");
			val sh000_member = word("N","member");
val st0 = 
			st000_president
			;
val sh0 = 
			sh000_member
			;
val ss0 = subs(st0, sh0);
			val st100_sonia = word("$NPC_1$","Sonia");
			val st101_gandhi = word("NP_D","Gandhi");
			val sh100_ms = word("$NPC_1$","Ms");
			val sh101_gandhi = word("NP_D","Gandhi");
val st1 = 
			_(
				st100_sonia
				,
				st101_gandhi
			)
			;
val sh1 = 
			_(
				sh100_ms
				,
				sh101_gandhi
			)
			;
val ss1 = subs(st1, sh1);
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
			ss0,
			ss1
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

