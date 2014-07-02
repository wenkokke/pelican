package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0092 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0092() throws Exception {

			// create the vocabulary for the text;
			val t01_star = word("$NPC_1$","Star",1);
			val t02_scandinavia = word("NP_D","Scandinavia",2);
			val t37_app = word("WHO_A","APP",37);
			val t04_the = word("THE","the",4);
			val t05_scandinavian = word("MI","Scandinavian",5);
			val t06_broadcasting = word("$NC_1$","broadcasting",6);
			val t07_channel = word("N","channel",7);
			val t09_reported = word("V_2","reported",9);
			val t10_that = word("IGNORE","that",10);
			val t11_the = word("THE","the",11);
			val t12_football = word("$NC_1$","football",12);
			val t13_match = word("N","match",13);
			val t25_resulted_in = word("V_2","resulted_in",25);
			val t16_a = word("A","a",16);
			val t17_draw = word("N","draw",17);

			// create the vocabulary for the hypothesis;
			val h01_star = word("$NPC_1$","Star",1);
			val h02_scandinavia = word("NP_D","Scandinavia",2);
			val h03_is = word("IS","is",3);
			val h04_scandinavian = word("MI","Scandinavian",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_star
						,
						t02_scandinavia
						,
						19
					)
					,
					_(
						t37_app
						,
						_(
							t04_the
							,
							_(
								t05_scandinavian
								,
								_(
									t06_broadcasting
									,
									t07_channel
									,
									32
								)
								,
								33
							)
							,
							34
						)
						,
						38
					)
					,
					35
				)
				,
				_(
					t09_reported
					,
					_(
						t10_that
						,
						_(
							_(
								t11_the
								,
								_(
									t12_football
									,
									t13_match
									,
									30
								)
								,
								31
							)
							,
							_(
								t25_resulted_in
								,
								_(
									t16_a
									,
									t17_draw
									,
									23
								)
								,
								39
							)
							,
							26
						)
						,
						27
					)
					,
					28
				)
				,
				36
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_star
					,
					h02_scandinavia
					,
					6
				)
				,
				_(
					h03_is
					,
					h04_scandinavian
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

