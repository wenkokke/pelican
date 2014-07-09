package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0025 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0025() throws Exception {

			// create the vocabulary for the text;
			val t01_iran = word("NP_D","Iran",1);
			val t02_will = word("V_AUX","will",2);
			val t03_soon = word("MR","soon",3);
			val t04_release = word("V_2","release",4);
			val t05_the = word("THE","the",5);
			val t06_british = word("MI","British",6);
			val t07_serviceman = word("N","serviceman",7);
			val t08_who = word("WHO_R","who",8);
			val t09_was = word("IS","was",9);
			val t10_detained = word("V_1","detained",10);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_british = word("MI","British",2);
			val h03_serviceman = word("N","serviceman",3);
			val h04_was = word("IS","was",4);
			val h05_detained = word("V_1","detained",5);

			// create the tree structure for the text;
			val tt =
			_(
				t01_iran
				,
				_(
					t02_will
					,
					_(
						t03_soon
						,
						_(
							t04_release
							,
							_(
								t05_the
								,
								_(
									t06_british
									,
									_(
										t07_serviceman
										,
										_(
											t08_who
											,
											_(
												t09_was
												,
												t10_detained
												,
												17
											)
											,
											19
										)
										,
										24
									)
									,
									29
								)
								,
								25
							)
							,
							21
						)
						,
						26
					)
					,
					27
				)
				,
				28
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_british
						,
						h03_serviceman
						,
						11
					)
					,
					12
				)
				,
				_(
					h04_was
					,
					h05_detained
					,
					9
				)
				,
				13
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
