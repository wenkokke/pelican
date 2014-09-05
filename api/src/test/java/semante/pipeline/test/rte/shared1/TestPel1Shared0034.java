package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0034 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0034() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_arabiclanguage = word("MI","ArabicLanguage",2);
			val t36_television_network = word("N","television_network",36);
			val t41_app = word("WHO_A","APP",41);
			val t05_aljazeera = word("NP_D","AlJazeera",5);
			val t06_reports = word("V_2","reports",6);
			val t07_it = word("NP_D","it",7);
			val t08_has = word("V_AUX","has",8);
			val t09_received = word("V_2","received",9);
			val t10_a = word("A","a",10);
			val t11_statement = word("N","statement",11);
			val t12_and = word("IGNORE","and",12);
			val t13_a = word("IGNORE","a",13);
			val t14_videotape = word("IGNORE","videotape",14);
			val t15_from = word("P_R","from",15);
			val t16_militants = word("NP_D","militants",16);

			// create the vocabulary for the hypothesis;
			val h01_aljazeera = word("NP_D","AlJazeera",1);
			val h02_is = word("IS","is",2);
			val h03_an = word("A","an",3);
			val h04_arabiclanguage = word("MI","ArabicLanguage",4);
			val h12_television_network = word("N","television_network",12);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_arabiclanguage
							,
							t36_television_network
							,
							37
						)
						,
						38
					)
					,
					_(
						t41_app
						,
						t05_aljazeera
						,
						42
					)
					,
					39
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
									,
									26
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
												,
												21
											)
											,
											31
										)
										,
										20
									)
									,
									45
								)
								,
								43
							)
							,
							_(
								t15_from
								,
								t16_militants
								,
								24
							)
							,
							27
						)
						,
						46
					)
					,
					29
				)
				,
				40
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_aljazeera
				,
				_(
					h02_is
					,
					_(
						h03_an
						,
						_(
							h04_arabiclanguage
							,
							h12_television_network
							,
							13
						)
						,
						14
					)
					,
					10
				)
				,
				15
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
