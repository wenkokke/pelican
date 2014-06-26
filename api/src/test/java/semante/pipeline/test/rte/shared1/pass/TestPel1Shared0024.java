package semante.pipeline.test.rte.shared1.pass;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0024 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0024() throws Exception {

			// create the vocabulary for the text;
			val t00_num2channel = word("NP_D","num2channel");
			val t01_app = word("WHO_A","APP");
			val t02_the = word("THE","the");
			val t03_largest = word("MR","largest");
			val t04_internet = word("MI","Internet");
			val t05_forum = word("N","forum");
			val t06_in = word("P_R","in");
			val t07_the = word("THE","the");
			val t08_world = word("N","world");
			val t09_has = word("V_AUX","has");
			val t10_been = word("IS","been");
			val t11_shuttered = word("V_1","shuttered");
			val t12_by = word("P_R","by");
			val t13_a = word("A","a");
			val t14_japanese = word("MI","Japanese");
			val t15_court = word("N","court");
			val t16_app = word("WHO_A","APP");
			val t17_ruling = word("GER_1","ruling");
			val t18_in = word("P_R","in");
			val t19_a = word("A","a");
			val t20_civil = word("MR","civil");
			val t21_slander = word("$NC_1$","slander");
			val t22_case = word("N","case");

			// create the vocabulary for the hypothesis;
			val h00_num2channel = word("NP_D","num2channel");
			val h01_is = word("IS","is");
			val h02_an = word("A","an");
			val h03_internet = word("MI","Internet");
			val h04_forum = word("N","forum");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_num2channel
					,
					_(
						t01_app
						,
						_(
							t02_the
							,
							_(
								t03_largest
								,
								_(
									t04_internet
									,
									_(
										t05_forum
										,
										_(
											t06_in
											,
											_(
												t07_the
												,
												t08_world
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
					t09_has
					,
					_(
						t10_been
						,
						_(
							t11_shuttered
							,
							_(
								t12_by
								,
								_(
									_(
										t13_a
										,
										_(
											t14_japanese
											,
											t15_court
										)
									)
									,
									_(
										t16_app
										,
										_(
											t17_ruling
											,
											_(
												t18_in
												,
												_(
													t19_a
													,
													_(
														t20_civil
														,
														_(
															t21_slander
															,
															t22_case
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
				h00_num2channel
				,
				_(
					h01_is
					,
					_(
						h02_an
						,
						_(
							h03_internet
							,
							h04_forum
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

