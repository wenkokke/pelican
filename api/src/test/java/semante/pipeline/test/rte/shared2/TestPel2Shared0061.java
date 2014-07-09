package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0061 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0061() throws Exception {

			// create the vocabulary for the text;
			val t01_itunes = word("NP_D","iTunes",1);
			val t29_app = word("WHO_A","APP",29);
			val t03_the = word("THE","the",3);
			val t04_american = word("MI","American",4);
			val t05_software = word("N","software",5);
			val t07_has = word("V_AUX","has",7);
			val t08_seen = word("V_2","seen",8);
			val t35_det = word("EMPTYDET","DET",35);
			val t09_strong = word("MI","strong",9);
			val t10_sales = word("N","sales",10);
			val t11_in = word("P_R","in",11);
			val t12_europe = word("NP_D","Europe",12);

			// create the vocabulary for the hypothesis;
			val h01_itunes = word("NP_D","iTunes",1);
			val h02_is = word("IS","is",2);
			val h03_american = word("MI","American",3);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_itunes
					,
					_(
						t29_app
						,
						_(
							t03_the
							,
							_(
								t04_american
								,
								t05_software
								,
								25
							)
							,
							26
						)
						,
						30
					)
					,
					27
				)
				,
				_(
					t07_has
					,
					_(
						t08_seen
						,
						_(
							t35_det
							,
							_(
								t09_strong
								,
								_(
									t10_sales
									,
									_(
										t11_in
										,
										t12_europe
										,
										19
									)
									,
									17
								)
								,
								37
							)
							,
							36
						)
						,
						23
					)
					,
					21
				)
				,
				28
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_itunes
				,
				_(
					h02_is
					,
					h03_american
					,
					7
				)
				,
				9
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
