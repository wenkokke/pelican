package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0094 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0094() throws Exception {

			// create the vocabulary for the text;
			val t41_walter_r_mears = word("NP_D","Walter_R_Mears",41);
			val t46_app = word("WHO_A","APP",46);
			val t05_the = word("THE","the",5);
			val t06_wellknown = word("MI","wellKnown",6);
			val t07_columnist = word("N","columnist",7);
			val t09_has = word("V_AUX","has",9);
			val t62_reported_on = word("V_2","reported_on",62);
			val t53_det = word("EMPTYDET","DET",53);
			val t51_washington_and_national_politics = word("N","Washington_and_national_politics",51);
			val t16_for = word("P_R","for",16);
			val t17_more = word("MR","more",17);
			val t18_than = word("MR","than",18);
			val t19_num25 = word("MR","num25",19);
			val t58_det = word("A","DET",58);
			val t20_years = word("N","years",20);

			// create the vocabulary for the hypothesis;
			val h16_walter_r_mears = word("NP_D","Walter_R_Mears",16);
			val h04_is = word("IS","is",4);
			val h05_a = word("A","a",5);
			val h06_wellknown = word("MI","wellKnown",6);
			val h07_columnist = word("N","columnist",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t41_walter_r_mears
					,
					_(
						t46_app
						,
						_(
							t05_the
							,
							_(
								t06_wellknown
								,
								t07_columnist
								,
								42
							)
							,
							43
						)
						,
						47
					)
					,
					44
				)
				,
				_(
					_(
						t09_has
						,
						_(
							t62_reported_on
							,
							_(
								t53_det
								,
								t51_washington_and_national_politics
								,
								60
							)
							,
							64
						)
						,
						49
					)
					,
					_(
						t16_for
						,
						_(
							t17_more
							,
							_(
								t18_than
								,
								_(
									t19_num25
									,
									_(
										t58_det
										,
										t20_years
										,
										59
									)
									,
									29
								)
								,
								57
							)
							,
							56
						)
						,
						32
					)
					,
					63
				)
				,
				55
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h16_walter_r_mears
				,
				_(
					h04_is
					,
					_(
						h05_a
						,
						_(
							h06_wellknown
							,
							h07_columnist
							,
							13
						)
						,
						14
					)
					,
					11
				)
				,
				17
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
