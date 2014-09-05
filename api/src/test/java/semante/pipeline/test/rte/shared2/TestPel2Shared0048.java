package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0048 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0048() throws Exception {

			// create the vocabulary for the text;
			val t42_john_wilkes_booth = word("NP_D","John_Wilkes_Booth",42);
			val t53_app = word("WHO_A","APP",53);
			val t45_president_abraham_lincoln = word("NP_D","President_Abraham_Lincoln",45);
			val t08_s = word("POSS","s",8);
			val t09_assassin = word("N","assassin",9);
			val t11_was = word("IS","was",11);
			val t12_surrounded = word("V_1","surrounded",12);
			val t13_by = word("P_R","by",13);
			val t14_the = word("THE","the",14);
			val t15_federal = word("MR","federal",15);
			val t16_troops = word("N","troops",16);
			val t17_near = word("P_R","near",17);
			val t30_bowling_green = word("NP_D","Bowling_Green",30);
			val t55_app = word("WHO_A","APP",55);
			val t21_virginia = word("NP_D","Virginia",21);
			val t23_and = word("AND","and",23);
			val t24_killed = word("V_1","killed",24);

			// create the vocabulary for the hypothesis;
			val h12_john_wilkes_booth = word("NP_D","John_Wilkes_Booth",12);
			val h04_is = word("IS","is",4);
			val h05_an = word("A","an",5);
			val h06_assassin = word("N","assassin",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t42_john_wilkes_booth
					,
					_(
						t53_app
						,
						_(
							_(
								t45_president_abraham_lincoln
								,
								t08_s
								,
								52
							)
							,
							t09_assassin
							,
							61
						)
						,
						54
					)
					,
					46
				)
				,
				_(
					t11_was
					,
					_(
						_(
							_(
								t12_surrounded
								,
								_(
									t13_by
									,
									_(
										t14_the
										,
										_(
											t15_federal
											,
											t16_troops
											,
											47
										)
										,
										48
									)
									,
									35
								)
								,
								36
							)
							,
							_(
								t17_near
								,
								_(
									t30_bowling_green
									,
									_(
										t55_app
										,
										t21_virginia
										,
										56
									)
									,
									49
								)
								,
								33
							)
							,
							37
						)
						,
						_(
							t23_and
							,
							t24_killed
							,
							50
						)
						,
						57
					)
					,
					60
				)
				,
				59
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h12_john_wilkes_booth
				,
				_(
					h04_is
					,
					_(
						h05_an
						,
						h06_assassin
						,
						8
					)
					,
					9
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
