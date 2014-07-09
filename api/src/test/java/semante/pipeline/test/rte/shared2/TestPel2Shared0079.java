package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0079 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0079() throws Exception {

			// create the vocabulary for the text;
			val t30_det = word("THE","DET",30);
			val t01_italian = word("MI","Italian",1);
			val t27_prime_minister = word("N","Prime_Minister",27);
			val t33_app = word("WHO_A","APP",33);
			val t25_silvio_berlusconi = word("NP_D","Silvio_Berlusconi",25);
			val t06_promised = word("V_2","promised",6);
			val t07_a = word("A","a",7);
			val t08_reformation = word("N","reformation",8);
			val t09_of = word("P_R","of",9);
			val t10_italy = word("NP_D","Italy",10);
			val t11_s = word("POSS","s",11);
			val t23_business_regulations = word("N","business_regulations",23);

			// create the vocabulary for the hypothesis;
			val h08_silvio_berlusconi = word("NP_D","Silvio_Berlusconi",8);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h12_prime_minister = word("N","Prime_Minister",12);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t30_det
						,
						_(
							t01_italian
							,
							t27_prime_minister
							,
							28
						)
						,
						31
					)
					,
					_(
						t33_app
						,
						t25_silvio_berlusconi
						,
						34
					)
					,
					29
				)
				,
				_(
					t06_promised
					,
					_(
						t07_a
						,
						_(
							t08_reformation
							,
							_(
								t09_of
								,
								_(
									_(
										t10_italy
										,
										t11_s
										,
										17
									)
									,
									t23_business_regulations
									,
									24
								)
								,
								19
							)
							,
							16
						)
						,
						35
					)
					,
					21
				)
				,
				32
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h08_silvio_berlusconi
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						h12_prime_minister
						,
						13
					)
					,
					10
				)
				,
				14
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
