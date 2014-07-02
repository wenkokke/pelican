package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0028 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0028() throws Exception {

			// create the vocabulary for the text;
			val t01_a = word("A","A",1);
			val t02_cuban = word("MI","Cuban",2);
			val t03_american = word("N","American",3);
			val t04_who = word("WHO_A","who",4);
			val t05_is = word("V_AUX","is",5);
			val t06_accused = word("V_1","accused",6);
			val t07_of = word("P_R","of",7);
			val t08_espionage = word("NP_D","espionage",8);
			val t09_pleads = word("V_1","pleads",9);
			val t10_innocent = word("MR","innocent",10);

			// create the vocabulary for the hypothesis;
			val h11_det = word("A","DET",11);
			val h01_american = word("N","American",1);
			val h02_accused = word("V_1","accused",2);
			val h03_of = word("P_R","of",3);
			val h04_espionage = word("NP_D","espionage",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_a
						,
						_(
							t02_cuban
							,
							t03_american
							,
							23
						)
						,
						24
					)
					,
					_(
						t04_who
						,
						_(
							_(
								t05_is
								,
								t06_accused
								,
								14
							)
							,
							_(
								t07_of
								,
								t08_espionage
								,
								21
							)
							,
							15
						)
						,
						16
					)
					,
					17
				)
				,
				_(
					t09_pleads
					,
					t10_innocent
					,
					19
				)
				,
				22
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h11_det
					,
					h01_american
					,
					12
				)
				,
				_(
					h02_accused
					,
					_(
						h03_of
						,
						h04_espionage
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

