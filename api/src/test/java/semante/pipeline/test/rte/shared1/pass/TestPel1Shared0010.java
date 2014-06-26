package semante.pipeline.test.rte.shared1.pass;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0010 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0010() throws Exception {

			// create the vocabulary for the text;
			val t00_foreign = word("$NPC_1$","Foreign");
			val t01_affairs = word("$NPC_1$","Affairs");
			val t02_minister = word("NP_D","Minister");
			val t03_app = word("WHO_A","APP");
			val t04_alexander = word("$NPC_1$","Alexander");
			val t05_downer = word("NP_D","Downer");
			val t06_will = word("V_AUX","will");
			val t07_host = word("V_2","host");
			val t08_the = word("THE","the");
			val t09_num20th = word("MR","num20th");
			val t10_annual = word("MR","annual");
			val t11_ausmin = word("MR","AUSMIN");
			val t12_conference = word("N","conference");
			val t13_at = word("P_R","at");
			val t14_the = word("THE","the");
			val t15_adelaide = word("$NPC_1$","Adelaide");
			val t16_town = word("$NPC_1$","Town");
			val t17_hall = word("NP_D","Hall");

			// create the vocabulary for the hypothesis;
			val h00_alexander = word("$NPC_1$","Alexander");
			val h01_downer = word("NP_D","Downer");
			val h02_will = word("V_AUX","will");
			val h03_host = word("V_2","host");
			val h04_a = word("A","a");
			val h05_conference = word("N","conference");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_foreign
						,
						_(
							t01_affairs
							,
							t02_minister
						)
					)
					,
					_(
						t03_app
						,
						_(
							t04_alexander
							,
							t05_downer
						)
					)
				)
				,
				_(
					t06_will
					,
					_(
						_(
							t07_host
							,
							_(
								t08_the
								,
								_(
									t09_num20th
									,
									_(
										t10_annual
										,
										_(
											t11_ausmin
											,
											t12_conference
										)
									)
								)
							)
						)
						,
						_(
							t13_at
							,
							_(
								t14_the
								,
								_(
									t15_adelaide
									,
									_(
										t16_town
										,
										t17_hall
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
					h00_alexander
					,
					h01_downer
				)
				,
				_(
					h02_will
					,
					_(
						h03_host
						,
						_(
							h04_a
							,
							h05_conference
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

