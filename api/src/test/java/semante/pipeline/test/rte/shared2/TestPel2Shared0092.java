package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0092 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0092() throws Exception {

			// create the vocabulary for the text;
			val t19_star_scandinavia = word("NP_D","Star_Scandinavia",19);
			val t37_app = word("WHO_A","APP",37);
			val t04_the = word("THE","the",4);
			val t05_scandinavian = word("MI","Scandinavian",5);
			val t32_broadcasting_channel = word("N","broadcasting_channel",32);
			val t09_reported = word("V_2","reported",9);
			val t10_that = word("IGNORE","that",10);
			val t11_the = word("THE","the",11);
			val t30_football_match = word("N","football_match",30);
			val t25_resulted_in = word("V_2","resulted_in",25);
			val t16_a = word("A","a",16);
			val t17_draw = word("N","draw",17);

			// create the vocabulary for the hypothesis;
			val h06_star_scandinavia = word("NP_D","Star_Scandinavia",6);
			val h03_is = word("IS","is",3);
			val h04_scandinavian = word("MI","Scandinavian",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t19_star_scandinavia
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
								t32_broadcasting_channel
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
								t30_football_match
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
				h06_star_scandinavia
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
