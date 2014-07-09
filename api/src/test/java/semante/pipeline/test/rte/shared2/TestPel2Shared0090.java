package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0090 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0090() throws Exception {

			// create the vocabulary for the text;
			val t30_kurdistan_regional_government_prime_minister = word("NP_D","Kurdistan_Regional_Government_Prime_Minister",30);
			val t33_app = word("WHO_A","APP",33);
			val t25_dr_barham_salih = word("NP_D","Dr_Barham_Salih",25);
			val t09_was = word("IS","was",9);
			val t10_unharmed = word("MR","unharmed",10);
			val t11_after = word("P_R","after",11);
			val t12_the = word("THE","the",12);
			val t22_assassination_attempt = word("N","assassination_attempt",22);

			// create the vocabulary for the hypothesis;
			val h20_dr_barham_salih = word("NP_D","Dr_Barham_Salih",20);
			val h04_was = word("IS","was",4);
			val h05_unharmed = word("MR","unharmed",5);
			val h06_after = word("P_R","after",6);
			val h07_an = word("A","an",7);
			val h17_assassination_attempt = word("N","assassination_attempt",17);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t30_kurdistan_regional_government_prime_minister
					,
					_(
						t33_app
						,
						t25_dr_barham_salih
						,
						34
					)
					,
					31
				)
				,
				_(
					_(
						t09_was
						,
						t10_unharmed
						,
						20
					)
					,
					_(
						t11_after
						,
						_(
							t12_the
							,
							t22_assassination_attempt
							,
							23
						)
						,
						18
					)
					,
					32
				)
				,
				35
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h20_dr_barham_salih
				,
				_(
					_(
						h04_was
						,
						h05_unharmed
						,
						15
					)
					,
					_(
						h06_after
						,
						_(
							h07_an
							,
							h17_assassination_attempt
							,
							18
						)
						,
						13
					)
					,
					21
				)
				,
				22
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
