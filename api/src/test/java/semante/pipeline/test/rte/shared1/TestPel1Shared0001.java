package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0001 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0001() throws Exception {

			// create the vocabulary for the text;
			val t25_jessica_litman = word("NP_D","Jessica_Litman",25);
			val t49_app = word("WHO_A","APP",49);
			val t04_the = word("THE","the",4);
			val t05_widely = word("MR","widely",5);
			val t06_known = word("MI","known",6);
			val t41_law_professor = word("N","law_professor",41);
			val t09_at = word("P_R","at",9);
			val t10_michigan = word("NP_D","Michigan",10);
			val t11_s = word("POSS","s",11);
			val t12_wayne = word("MR","Wayne",12);
			val t13_state = word("MR","State",13);
			val t14_university = word("N","University",14);
			val t16_has = word("V_AUX","has",16);
			val t17_specialized = word("V_1","specialized",17);
			val t18_in = word("P_R","in",18);
			val t52_det = word("A","DET",52);
			val t33_copyright_law = word("N","copyright_law",33);
			val t21_for = word("P_R","for",21);
			val t55_det = word("A","DET",55);
			val t22_num20 = word("MR","num20",22);
			val t23_years = word("N","years",23);

			// create the vocabulary for the hypothesis;
			val h08_jessica_litman = word("NP_D","Jessica_Litman",8);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h12_law_professor = word("N","law_professor",12);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t25_jessica_litman
					,
					_(
						t49_app
						,
						_(
							t04_the
							,
							_(
								t05_widely
								,
								_(
									t06_known
									,
									_(
										t41_law_professor
										,
										_(
											t09_at
											,
											_(
												_(
													t10_michigan
													,
													t11_s
													,
													28
												)
												,
												_(
													t12_wayne
													,
													_(
														t13_state
														,
														t14_university
														,
														44
													)
													,
													45
												)
												,
												46
											)
											,
											30
										)
										,
										57
									)
									,
									26
								)
								,
								59
							)
							,
							50
						)
						,
						60
					)
					,
					47
				)
				,
				_(
					t16_has
					,
					_(
						_(
							t17_specialized
							,
							_(
								t18_in
								,
								_(
									t52_det
									,
									t33_copyright_law
									,
									53
								)
								,
								37
							)
							,
							38
						)
						,
						_(
							t21_for
							,
							_(
								t55_det
								,
								_(
									t22_num20
									,
									t23_years
									,
									34
								)
								,
								56
							)
							,
							35
						)
						,
						39
					)
					,
					54
				)
				,
				48
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h08_jessica_litman
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						h12_law_professor
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
