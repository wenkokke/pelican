package semante.pipeline.test.rte.shared1.subs.fail.unclear;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0032 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0032() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_administration = word("N","Administration");
			val t02_is = word("IS","is");
			val t03_weary = word("NP_D","weary");
			val t04_of = word("P_R","of");
			val t05_aristide = word("NP_D","Aristide");
			val t06_app = word("WHO_A","APP");
			val t07_a = word("A","a");
			val t08_populist = word("MR","populist");
			val t09_roman = word("$NC_1$","Roman");
			val t10_catholic = word("$NC_1$","Catholic");
			val t11_priest = word("N","priest");
			val t12_who = word("WHO_A","who");
			val t13_in = word("P_R","in");
			val t14_december = word("$NPC_1$","December");
			val t15_num1990 = word("NP_D","num1990");
			val t16_won = word("V_2","won");
			val t17_an = word("A","an");
			val t18_overwhelming = word("MR","overwhelming");
			val t19_victory = word("N","victory");
			val t20_in = word("P_R","in");
			val t21_haiti = word("NP_D","Haiti");
			val t22_s = word("GEN","s");
			val t23_only = word("MR","only");
			val t24_democratic = word("MR","democratic");
			val t25_presidential = word("MR","presidential");
			val t26_election = word("N","election");

			// create the vocabulary for the hypothesis;
			val h00_aristide = word("NP_D","Aristide");
			val h01_became = word("V_2","became");
			val h02_president = word("NP_D","president");
			val h03_in = word("P_R","in");
			val h04_num1990 = word("NP_D","num1990");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					t01_administration
				)
				,
				_(
					_(
						t02_is
						,
						t03_weary
					)
					,
					_(
						t04_of
						,
						_(
							t05_aristide
							,
							_(
								t06_app
								,
								_(
									_(
										t07_a
										,
										_(
											t08_populist
											,
											_(
												t09_roman
												,
												_(
													t10_catholic
													,
													t11_priest
												)
											)
										)
									)
									,
									_(
										t12_who
										,
										_(
											_(
												t13_in
												,
												_(
													t14_december
													,
													t15_num1990
												)
											)
											,
											_(
												t16_won
												,
												_(
													t17_an
													,
													_(
														_(
															t18_overwhelming
															,
															t19_victory
														)
														,
														_(
															t20_in
															,
															_(
																_(
																	t21_haiti
																	,
																	t22_s
																)
																,
																_(
																	t23_only
																	,
																	_(
																		t24_democratic
																		,
																		_(
																			t25_presidential
																			,
																			t26_election
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
				h00_aristide
				,
				_(
					_(
						h01_became
						,
						h02_president
					)
					,
					_(
						h03_in
						,
						h04_num1990
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_won = word("V_2","won");
			val st001_an = word("A","an");
			val st002_overwhelming = word("MR","overwhelming");
			val st003_victory = word("N","victory");
			val st004_in = word("P_R","in");
			val st005_haiti = word("NP_D","Haiti");
			val st006_s = word("GEN","s");
			val st007_only = word("MR","only");
			val st008_democratic = word("MR","democratic");
			val st009_presidential = word("MR","presidential");
			val st010_election = word("N","election");
			val sh000_became = word("V_2","became");
			val sh001_president = word("NP_D","president");
val st0 = 
			_(
				st000_won
				,
				_(
					st001_an
					,
					_(
						_(
							st002_overwhelming
							,
							st003_victory
						)
						,
						_(
							st004_in
							,
							_(
								_(
									st005_haiti
									,
									st006_s
								)
								,
								_(
									st007_only
									,
									_(
										st008_democratic
										,
										_(
											st009_presidential
											,
											st010_election
										)
									)
								)
							)
						)
					)
				)
			)
			;
val sh0 = 
			_(
				sh000_became
				,
				sh001_president
			)
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

