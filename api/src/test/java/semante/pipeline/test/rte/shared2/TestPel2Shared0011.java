package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0011 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0011() throws Exception {

			// create the vocabulary for the text;
			val t31_det = word("THE","DET",31);
			val t01_six = word("MR","Six",1);
			val t02_time = word("MR","time",2);
			val t03_prime = word("MR","prime",3);
			val t04_minister = word("N","minister",4);
			val t34_app = word("WHO_A","APP",34);
			val t05_giulio = word("$NPC_1$","Giulio",5);
			val t06_andreotti = word("NP_D","Andreotti",6);
			val t07_is = word("IS","is",7);
			val t08_a = word("A","a",8);
			val t09_casualty = word("N","casualty",9);
			val t10_stemming = word("GER_1","stemming",10);
			val t11_from = word("P_R","from",11);
			val t12_the = word("THE","the",12);
			val t13_investigations = word("N","investigations",13);

			// create the vocabulary for the hypothesis;
			val h01_giulio = word("$NPC_1$","Giulio",1);
			val h02_andreotti = word("NP_D","Andreotti",2);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_prime = word("MR","prime",5);
			val h06_minister = word("N","minister",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t31_det
						,
						_(
							t01_six
							,
							_(
								t02_time
								,
								_(
									t03_prime
									,
									t04_minister
									,
									26
								)
								,
								15
							)
							,
							30
						)
						,
						32
					)
					,
					_(
						t34_app
						,
						_(
							t05_giulio
							,
							t06_andreotti
							,
							24
						)
						,
						35
					)
					,
					28
				)
				,
				_(
					_(
						_(
							t07_is
							,
							_(
								t08_a
								,
								t09_casualty
								,
								17
							)
							,
							22
						)
						,
						t10_stemming
						,
						29
					)
					,
					_(
						t11_from
						,
						_(
							t12_the
							,
							t13_investigations
							,
							18
						)
						,
						19
					)
					,
					36
				)
				,
				33
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_giulio
					,
					h02_andreotti
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

