package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0001 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0001() throws Exception {

			// create the vocabulary for the text;
			val t01_jessica = word("$NPC_1$","Jessica",1);
			val t02_litman = word("NP_D","Litman",2);
			val t49_app = word("WHO_A","APP",49);
			val t04_the = word("THE","the",4);
			val t05_widely = word("MR","widely",5);
			val t06_known = word("MI","known",6);
			val t07_law = word("$NC_1$","law",7);
			val t08_professor = word("N","professor",8);
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
			val t19_copyright = word("$NC_1$","copyright",19);
			val t20_law = word("N","law",20);
			val t21_for = word("P_R","for",21);
			val t55_det = word("A","DET",55);
			val t22_num20 = word("MR","num20",22);
			val t23_years = word("N","years",23);

			// create the vocabulary for the hypothesis;
			val h01_jessica = word("$NPC_1$","Jessica",1);
			val h02_litman = word("NP_D","Litman",2);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_law = word("$NC_1$","law",5);
			val h06_professor = word("N","professor",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_jessica
						,
						t02_litman
						,
						25
					)
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
										_(
											t07_law
											,
											t08_professor
											,
											41
										)
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
									_(
										t19_copyright
										,
										t20_law
										,
										33
									)
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
				_(
					h01_jessica
					,
					h02_litman
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
							h05_law
							,
							h06_professor
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

