package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0013 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0013() throws Exception {

			// create the vocabulary for the text;
			val t30_cote_divoire = word("NP_D","Cote_dIvoire",30);
			val t03_s = word("POSS","s",3);
			val t04_president = word("N","President",4);
			val t36_app = word("WHO_A","APP",36);
			val t06_kenyan = word("MI","Kenyan",6);
			val t32_laurent_gbagbo = word("NP_D","Laurent_Gbagbo",32);
			val t10_promulgated = word("V_2","promulgated",10);
			val t38_det = word("EMPTYDET","DET",38);
			val t11_new = word("MR","new",11);
			val t26_election_laws = word("N","election_laws",26);
			val t14_in = word("P_R","in",14);
			val t15_july = word("NP_D","July",15);

			// create the vocabulary for the hypothesis;
			val h12_cote_divoire = word("NP_D","Cote_dIvoire",12);
			val h03_s = word("POSS","s",3);
			val h04_president = word("N","President",4);
			val h05_is = word("IS","is",5);
			val h06_kenyan = word("MI","Kenyan",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t30_cote_divoire
							,
							t03_s
							,
							31
						)
						,
						t04_president
						,
						18
					)
					,
					_(
						t36_app
						,
						_(
							t06_kenyan
							,
							t32_laurent_gbagbo
							,
							33
						)
						,
						37
					)
					,
					34
				)
				,
				_(
					_(
						t10_promulgated
						,
						_(
							t38_det
							,
							_(
								t11_new
								,
								t26_election_laws
								,
								27
							)
							,
							39
						)
						,
						28
					)
					,
					_(
						t14_in
						,
						t15_july
						,
						23
					)
					,
					29
				)
				,
				35
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h12_cote_divoire
						,
						h03_s
						,
						14
					)
					,
					h04_president
					,
					8
				)
				,
				_(
					h05_is
					,
					h06_kenyan
					,
					10
				)
				,
				11
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
