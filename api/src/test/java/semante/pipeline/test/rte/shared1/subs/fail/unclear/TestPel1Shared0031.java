package semante.pipeline.test.rte.shared1.subs.fail.unclear;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0031 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0031() throws Exception {

			// create the vocabulary for the text;
			val t00_peter = word("$NPC_1$","Peter");
			val t01_voser = word("NP_D","Voser");
			val t02_app = word("WHO_A","APP");
			val t03_a = word("A","a");
			val t04_num46yearold = word("N","num46YearOld");
			val t05_who = word("WHO_R","who");
			val t06_joined = word("V_2","joined");
			val t07_abb = word("NP_D","ABB");
			val t08_from = word("P_R","from");
			val t09_shell = word("NP_D","Shell");
			val t10_will = word("IS","will");
			val t11_rejoin = word("V_2","rejoin");
			val t12_the = word("THE","the");
			val t13_anglodutch = word("MR","AngloDutch");
			val t14_company = word("N","company");
			val t15_on = word("P_R","on");
			val t16_october = word("$NPC_1$","October");
			val t17_num4 = word("NP_D","num4");

			// create the vocabulary for the hypothesis;
			val h00_peter = word("$NPC_1$","Peter");
			val h01_voser = word("NP_D","Voser");
			val h02_has = word("V_AUX","has");
			val h03_worked = word("V_1","worked");
			val h04_for = word("P_R","for");
			val h05_shell = word("NP_D","Shell");
			val h06_in = word("P_R","in");
			val h07_the = word("THE","the");
			val h08_past = word("N","past");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_peter
						,
						t01_voser
					)
					,
					_(
						t02_app
						,
						_(
							t03_a
							,
							_(
								t04_num46yearold
								,
								_(
									t05_who
									,
									_(
										_(
											t06_joined
											,
											t07_abb
										)
										,
										_(
											t08_from
											,
											t09_shell
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					t10_will
					,
					_(
						_(
							t11_rejoin
							,
							_(
								t12_the
								,
								_(
									t13_anglodutch
									,
									t14_company
								)
							)
						)
						,
						_(
							t15_on
							,
							_(
								t16_october
								,
								t17_num4
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
					h00_peter
					,
					h01_voser
				)
				,
				_(
					_(
						_(
							h02_has
							,
							h03_worked
						)
						,
						_(
							h04_for
							,
							h05_shell
						)
					)
					,
					_(
						h06_in
						,
						_(
							h07_the
							,
							h08_past
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_joined = word("V_2","joined");
			val st001_abb = word("NP_D","ABB");
			val st002_from = word("P_R","from");
			val st003_shell = word("NP_D","Shell");
			val sh000_has = word("V_AUX","has");
			val sh001_worked = word("V_1","worked");
			val sh002_for = word("P_R","for");
			val sh003_shell = word("NP_D","Shell");
			val sh004_in = word("P_R","in");
			val sh005_the = word("THE","the");
			val sh006_past = word("N","past");
val st0 = 
			_(
				_(
					st000_joined
					,
					st001_abb
				)
				,
				_(
					st002_from
					,
					st003_shell
				)
			)
			;
val sh0 = 
			_(
				_(
					_(
						sh000_has
						,
						sh001_worked
					)
					,
					_(
						sh002_for
						,
						sh003_shell
					)
				)
				,
				_(
					sh004_in
					,
					_(
						sh005_the
						,
						sh006_past
					)
				)
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

