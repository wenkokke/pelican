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
			val t02_prime = word("$NC_1$","Prime",2);
			val t03_minister = word("N","Minister",3);
			val t33_app = word("WHO_A","APP",33);
			val t04_silvio = word("$NPC_1$","Silvio",4);
			val t05_berlusconi = word("NP_D","Berlusconi",5);
			val t06_promised = word("V_2","promised",6);
			val t07_a = word("A","a",7);
			val t08_reformation = word("N","reformation",8);
			val t09_of = word("P_R","of",9);
			val t10_italy = word("NP_D","Italy",10);
			val t11_s = word("POSS","s",11);
			val t12_business = word("$NC_1$","business",12);
			val t13_regulations = word("N","regulations",13);

			// create the vocabulary for the hypothesis;
			val h01_silvio = word("$NPC_1$","Silvio",1);
			val h02_berlusconi = word("NP_D","Berlusconi",2);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_prime = word("$NC_1$","Prime",5);
			val h06_minister = word("N","Minister",6);

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
							_(
								t02_prime
								,
								t03_minister
								,
								27
							)
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
						_(
							t04_silvio
							,
							t05_berlusconi
							,
							25
						)
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
									_(
										t12_business
										,
										t13_regulations
										,
										23
									)
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
				_(
					h01_silvio
					,
					h02_berlusconi
					,
					8
				)
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						_(
							h05_prime
							,
							h06_minister
							,
							12
						)
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

