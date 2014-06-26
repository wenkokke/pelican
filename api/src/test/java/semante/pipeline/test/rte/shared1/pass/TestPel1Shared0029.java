package semante.pipeline.test.rte.shared1.pass;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0029 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0029() throws Exception {

			// create the vocabulary for the text;
			val t00_a = word("A","A");
			val t01_syrian = word("MI","Syrian");
			val t02_american = word("MI","American");
			val t03_airman = word("N","airman");
			val t04_app = word("WHO_A","APP");
			val t05_accused = word("V_1","accused");
			val t06_of = word("P_R","of");
			val t07_espionage = word("NP_D","espionage");
			val t08_complained = word("V_1","complained");
			val t09_about = word("P_R","about");
			val t10_det = word("A","DET");
			val t11_prisoner = word("$NC_1$","prisoner");
			val t12_treatment = word("N","treatment");
			val t13_at = word("P_R","at");
			val t14_the = word("THE","the");
			val t15_us = word("$NC_1$","US");
			val t16_base = word("N","base");
			val t17_in = word("P_R","in");
			val t18_guantanamo = word("NP_D","Guantanamo");
			val t19_app = word("WHO_A","APP");
			val t20_cuba = word("NP_D","Cuba");

			// create the vocabulary for the hypothesis;
			val h00_det = word("A","DET");
			val h01_american = word("N","American");
			val h02_accused = word("V_1","accused");
			val h03_of = word("P_R","of");
			val h04_espionage = word("NP_D","espionage");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_a
						,
						_(
							t01_syrian
							,
							_(
								t02_american
								,
								t03_airman
							)
						)
					)
					,
					_(
						t04_app
						,
						_(
							t05_accused
							,
							_(
								t06_of
								,
								t07_espionage
							)
						)
					)
				)
				,
				_(
					t08_complained
					,
					_(
						t09_about
						,
						_(
							t10_det
							,
							_(
								_(
									t11_prisoner
									,
									t12_treatment
								)
								,
								_(
									t13_at
									,
									_(
										t14_the
										,
										_(
											_(
												t15_us
												,
												t16_base
											)
											,
											_(
												t17_in
												,
												_(
													t18_guantanamo
													,
													_(
														t19_app
														,
														t20_cuba
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
					h00_det
					,
					h01_american
				)
				,
				_(
					h02_accused
					,
					_(
						h03_of
						,
						h04_espionage
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

