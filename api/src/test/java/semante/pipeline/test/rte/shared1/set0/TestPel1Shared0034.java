package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0034 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0034() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_arabiclanguage = word("MI","ArabicLanguage");
			val t02_television = word("$NC_1$","television");
			val t03_network = word("N","network");
			val t04_app = word("WHO_A","APP");
			val t05_aljazeera = word("NP_D","AlJazeera");
			val t06_reports = word("V_2","reports");
			val t07_it = word("NP_D","it");
			val t08_has = word("V_AUX","has");
			val t09_received = word("V_2","received");
			val t10_a = word("A","a");
			val t11_statement = word("N","statement");
			val t12_and = word("IGNORE","and");
			val t13_a = word("IGNORE","a");
			val t14_videotape = word("IGNORE","videotape");
			val t15_from = word("P_R","from");
			val t16_militants = word("NP_D","militants");

			// create the vocabulary for the hypothesis;
			val h00_aljazeera = word("NP_D","AlJazeera");
			val h01_is = word("IS","is");
			val h02_an = word("A","an");
			val h03_arabiclanguage = word("MI","ArabicLanguage");
			val h04_television = word("$NC_1$","television");
			val h05_network = word("N","network");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_arabiclanguage
							,
							_(
								t02_television
								,
								t03_network
							)
						)
					)
					,
					_(
						t04_app
						,
						t05_aljazeera
					)
				)
				,
				_(
					t06_reports
					,
					_(
						t07_it
						,
						_(
							_(
								_(
									t08_has
									,
									t09_received
								)
								,
								_(
									t10_a
									,
									_(
										t11_statement
										,
										_(
											t12_and
											,
											_(
												t13_a
												,
												t14_videotape
											)
										)
									)
								)
							)
							,
							_(
								t15_from
								,
								t16_militants
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_aljazeera
				,
				_(
					h01_is
					,
					_(
						h02_an
						,
						_(
							h03_arabiclanguage
							,
							_(
								h04_television
								,
								h05_network
							)
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

