package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0006 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0006() throws Exception {

			// create the vocabulary for the text;
			val t00_german = word("MI","German");
			val t01_siemens = word("NP_D","Siemens");
			val t02_may = word("V_AUX","may");
			val t03_sell = word("V_2","sell");
			val t04_det = word("THE","DET");
			val t05_relevant = word("MR","relevant");
			val t06_core = word("$NC_1$","core");
			val t07_technology = word("N","technology");
			val t08_to = word("P_R","to");
			val t09_china = word("NP_D","China");
			val t10_which = word("WHO_A","which");
			val t11_owns = word("V_2","owns");
			val t12_the = word("THE","the");
			val t13_first = word("MR","first");
			val t14_international = word("MI","international");
			val t15_maglev = word("$NC_1$","Maglev");
			val t16_line = word("N","line");

			// create the vocabulary for the hypothesis;
			val h00_a = word("A","A");
			val h01_maglev = word("$NC_1$","Maglev");
			val h02_line = word("N","line");
			val h03_is = word("IS","is");
			val h04_international = word("MI","international");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_german
					,
					t01_siemens
				)
				,
				_(
					t02_may
					,
					_(
						_(
							t03_sell
							,
							_(
								t04_det
								,
								_(
									t05_relevant
									,
									_(
										t06_core
										,
										t07_technology
									)
								)
							)
						)
						,
						_(
							t08_to
							,
							_(
								t09_china
								,
								_(
									t10_which
									,
									_(
										t11_owns
										,
										_(
											t12_the
											,
											_(
												t13_first
												,
												_(
													t14_international
													,
													_(
														t15_maglev
														,
														t16_line
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
					h00_a
					,
					_(
						h01_maglev
						,
						h02_line
					)
				)
				,
				_(
					h03_is
					,
					h04_international
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

