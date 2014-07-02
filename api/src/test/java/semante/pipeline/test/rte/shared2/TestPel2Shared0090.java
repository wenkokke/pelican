package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0090 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0090() throws Exception {

			// create the vocabulary for the text;
			val t01_kurdistan = word("$NPC_1$","Kurdistan",1);
			val t02_regional = word("$NPC_1$","Regional",2);
			val t03_government = word("$NPC_1$","Government",3);
			val t04_prime = word("$NPC_1$","Prime",4);
			val t05_minister = word("NP_D","Minister",5);
			val t33_app = word("WHO_A","APP",33);
			val t06_dr = word("$NPC_1$","Dr",6);
			val t07_barham = word("$NPC_1$","Barham",7);
			val t08_salih = word("NP_D","Salih",8);
			val t09_was = word("IS","was",9);
			val t10_unharmed = word("MR","unharmed",10);
			val t11_after = word("P_R","after",11);
			val t12_the = word("THE","the",12);
			val t13_assassination = word("$NC_1$","assassination",13);
			val t14_attempt = word("N","attempt",14);

			// create the vocabulary for the hypothesis;
			val h01_dr = word("$NPC_1$","Dr",1);
			val h02_barham = word("$NPC_1$","Barham",2);
			val h03_salih = word("NP_D","Salih",3);
			val h04_was = word("IS","was",4);
			val h05_unharmed = word("MR","unharmed",5);
			val h06_after = word("P_R","after",6);
			val h07_an = word("A","an",7);
			val h08_assassination = word("$NC_1$","assassination",8);
			val h09_attempt = word("N","attempt",9);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_kurdistan
						,
						_(
							t02_regional
							,
							_(
								t03_government
								,
								_(
									t04_prime
									,
									t05_minister
									,
									27
								)
								,
								28
							)
							,
							29
						)
						,
						30
					)
					,
					_(
						t33_app
						,
						_(
							t06_dr
							,
							_(
								t07_barham
								,
								t08_salih
								,
								24
							)
							,
							25
						)
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
							_(
								t13_assassination
								,
								t14_attempt
								,
								22
							)
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
				_(
					h01_dr
					,
					_(
						h02_barham
						,
						h03_salih
						,
						19
					)
					,
					20
				)
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
							_(
								h08_assassination
								,
								h09_attempt
								,
								17
							)
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

