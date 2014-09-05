package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0105 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0105() throws Exception {

			// create the vocabulary for the text;
			val t01_pibul = word("NP_D","Pibul",1);
			val t26_app = word("WHO_A","APP",26);
			val t03_the = word("THE","the",3);
			val t04_military = word("MR","military",4);
			val t05_dictator = word("N","dictator",5);
			val t06_of = word("P_R","of",6);
			val t07_thailand = word("NP_D","Thailand",7);
			val t09_was = word("IS","was",9);
			val t10_anticommunist = word("MR","antiCommunist",10);

			// create the vocabulary for the hypothesis;
			val h01_pibul = word("NP_D","Pibul",1);
			val h02_was = word("IS","was",2);
			val h03_a = word("A","a",3);
			val h04_dictator = word("N","dictator",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_pibul
					,
					_(
						t26_app
						,
						_(
							t03_the
							,
							_(
								t04_military
								,
								_(
									t05_dictator
									,
									_(
										t06_of
										,
										t07_thailand
										,
										15
									)
									,
									21
								)
								,
								25
							)
							,
							22
						)
						,
						27
					)
					,
					23
				)
				,
				_(
					t09_was
					,
					t10_anticommunist
					,
					19
				)
				,
				24
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_pibul
				,
				_(
					h02_was
					,
					_(
						h03_a
						,
						h04_dictator
						,
						7
					)
					,
					8
				)
				,
				10
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
