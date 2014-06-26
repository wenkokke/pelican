package semante.pipeline.test.rte.shared1.pass;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0002 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0002() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_controversyracked = word("MR","controversyRacked");
			val t02_oil = word("$NC_1$","oil");
			val t03_giant = word("N","giant");
			val t04_app = word("WHO_A","APP");
			val t05_shell = word("NP_D","Shell");
			val t06_has = word("V_AUX","has");
			val t07_named = word("V_2","named");
			val t08_a = word("A","a");
			val t09_new = word("MR","new");
			val t10_head = word("N","head");
			val t11_of = word("P_R","of");
			val t12_det = word("A","DET");
			val t13_finance = word("N","finance");
			val t14_in = word("P_R","in");
			val t15_an = word("A","an");
			val t16_effort = word("N","effort");
			val t17_which = word("WHO_R","which");
			val t18_calmed = word("V_2","calmed");
			val t19_det = word("A","DET");
			val t20_nervous = word("MR","nervous");
			val t21_shareholders = word("N","shareholders");

			// create the vocabulary for the hypothesis;
			val h00_shell = word("NP_D","Shell");
			val h01_is = word("IS","is");
			val h02_an = word("A","an");
			val h03_oil = word("$NC_1$","oil");
			val h04_giant = word("N","giant");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_controversyracked
							,
							_(
								t02_oil
								,
								t03_giant
							)
						)
					)
					,
					_(
						t04_app
						,
						t05_shell
					)
				)
				,
				_(
					t06_has
					,
					_(
						_(
							t07_named
							,
							_(
								t08_a
								,
								_(
									t09_new
									,
									_(
										t10_head
										,
										_(
											t11_of
											,
											_(
												t12_det
												,
												t13_finance
											)
										)
									)
								)
							)
						)
						,
						_(
							t14_in
							,
							_(
								t15_an
								,
								_(
									t16_effort
									,
									_(
										t17_which
										,
										_(
											t18_calmed
											,
											_(
												t19_det
												,
												_(
													t20_nervous
													,
													t21_shareholders
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
				h00_shell
				,
				_(
					h01_is
					,
					_(
						h02_an
						,
						_(
							h03_oil
							,
							h04_giant
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

