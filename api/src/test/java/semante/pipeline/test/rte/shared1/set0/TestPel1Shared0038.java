package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0038 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0038() throws Exception {

			// create the vocabulary for the text;
			val t00_mugabe = word("NP_D","Mugabe");
			val t01_arrived = word("V_1","arrived");
			val t02_in = word("P_R","in");
			val t03_beijing = word("NP_D","Beijing");
			val t04_on = word("P_R","on");
			val t05_saturday = word("NP_D","Saturday");
			val t06_and = word("AND","and");
			val t07_toured = word("V_2","toured");
			val t08_the = word("THE","the");
			val t09_northeastern = word("MR","northeastern");
			val t10_province = word("N","province");
			val t11_of = word("P_R","of");
			val t12_jilin = word("NP_D","Jilin");
			val t13_visiting = word("GER_2","visiting");
			val t14_the = word("THE","the");
			val t15_headquarters = word("N","headquarters");
			val t16_of = word("P_R","of");
			val t17_first = word("$NPC_1$","First");
			val t18_automotive = word("$NPC_1$","Automotive");
			val t19_works = word("$NPC_1$","Works");
			val t20_group = word("NP_D","Group");
			val t21_app = word("WHO_A","APP");
			val t22_a = word("A","a");
			val t23_top = word("MR","top");
			val t24_chinese = word("MI","Chinese");
			val t25_vehicle = word("$NC_1$","vehicle");
			val t26_maker = word("N","maker");

			// create the vocabulary for the hypothesis;
			val h00_first = word("$NPC_1$","First");
			val h01_automotive = word("$NPC_1$","Automotive");
			val h02_works = word("$NPC_1$","Works");
			val h03_group = word("NP_D","Group");
			val h04_is = word("IS","is");
			val h05_chinese = word("MI","Chinese");

			// create the tree structure for the text;
			val tt =
			_(
				t00_mugabe
				,
				_(
					_(
						_(
							_(
								_(
									t01_arrived
									,
									_(
										t02_in
										,
										t03_beijing
									)
								)
								,
								_(
									t04_on
									,
									t05_saturday
								)
							)
							,
							t06_and
						)
						,
						_(
							t07_toured
							,
							_(
								t08_the
								,
								_(
									_(
										t09_northeastern
										,
										t10_province
									)
									,
									_(
										t11_of
										,
										t12_jilin
									)
								)
							)
						)
					)
					,
					_(
						t13_visiting
						,
						_(
							t14_the
							,
							_(
								t15_headquarters
								,
								_(
									t16_of
									,
									_(
										_(
											t17_first
											,
											_(
												t18_automotive
												,
												_(
													t19_works
													,
													t20_group
												)
											)
										)
										,
										_(
											t21_app
											,
											_(
												t22_a
												,
												_(
													t23_top
													,
													_(
														t24_chinese
														,
														_(
															t25_vehicle
															,
															t26_maker
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
					h00_first
					,
					_(
						h01_automotive
						,
						_(
							h02_works
							,
							h03_group
						)
					)
				)
				,
				_(
					h04_is
					,
					h05_chinese
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

