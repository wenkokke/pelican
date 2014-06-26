package semante.pipeline.test.rte.shared1.pass;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0017 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0017() throws Exception {

			// create the vocabulary for the text;
			val t00_oqueli = word("NP_D","Oqueli");
			val t01_flies = word("V_1","flies");
			val t02_to = word("P_R","to");
			val t03_nicaragua = word("NP_D","Nicaragua");
			val t04_for = word("P_R","for");
			val t05_an = word("A","an");
			val t06_international = word("MR","international");
			val t07_socialist = word("MI","Socialist");
			val t08_delegation = word("N","delegation");
			val t09_which = word("WHO_A","which");
			val t10_will = word("V_AUX","will");
			val t11_observe = word("V_2","observe");
			val t12_the = word("THE","the");
			val t13_nicaraguan = word("MI","Nicaraguan");
			val t14_electoral = word("MR","electoral");
			val t15_campaign = word("N","campaign");

			// create the vocabulary for the hypothesis;
			val h00_an = word("A","An");
			val h01_electoral = word("MR","electoral");
			val h02_campaign = word("N","campaign");
			val h03_is = word("IS","is");
			val h04_nicaraguan = word("MI","Nicaraguan");

			// create the tree structure for the text;
			val tt =
			_(
				t00_oqueli
				,
				_(
					_(
						t01_flies
						,
						_(
							t02_to
							,
							t03_nicaragua
						)
					)
					,
					_(
						t04_for
						,
						_(
							_(
								t05_an
								,
								_(
									t06_international
									,
									_(
										t07_socialist
										,
										t08_delegation
									)
								)
							)
							,
							_(
								t09_which
								,
								_(
									t10_will
									,
									_(
										t11_observe
										,
										_(
											t12_the
											,
											_(
												t13_nicaraguan
												,
												_(
													t14_electoral
													,
													t15_campaign
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
					h00_an
					,
					_(
						h01_electoral
						,
						h02_campaign
					)
				)
				,
				_(
					h03_is
					,
					h04_nicaraguan
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

