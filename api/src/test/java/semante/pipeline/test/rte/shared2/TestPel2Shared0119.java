package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0119 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0119() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_former = word("MR","former",2);
			val t03_world = word("MR","world",3);
			val t04_champion = word("N","champion",4);
			val t47_app = word("WHO_A","APP",47);
			val t41_alberto_tomba = word("NP_D","Alberto_Tomba",41);
			val t07_first = word("MR","first",7);
			val t08_won = word("V_2","won",8);
			val t09_the = word("THE","the",9);
			val t10_slalom = word("MR","slalom",10);
			val t11_ski = word("MR","ski",11);
			val t12_race = word("N","race",12);
			val t13_in = word("P_R","in",13);
			val t14_the = word("THE","the",14);
			val t15_late = word("MR","late",15);
			val t16_eighties = word("N","eighties",16);
			val t18_redifining = word("GER_2","redifining",18);
			val t50_det = word("THE","DET",50);
			val t19_alpine = word("MR","alpine",19);
			val t20_skiing = word("N","skiing",20);

			// create the vocabulary for the hypothesis;
			val h08_alberto_tomba = word("NP_D","Alberto_Tomba",8);
			val h03_won = word("V_2","won",3);
			val h04_a = word("A","a",4);
			val h05_ski = word("MR","ski",5);
			val h06_race = word("N","race",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_former
							,
							_(
								t03_world
								,
								t04_champion
								,
								43
							)
							,
							44
						)
						,
						45
					)
					,
					_(
						t47_app
						,
						t41_alberto_tomba
						,
						48
					)
					,
					46
				)
				,
				_(
					t07_first
					,
					_(
						_(
							_(
								t08_won
								,
								_(
									t09_the
									,
									_(
										t10_slalom
										,
										_(
											t11_ski
											,
											t12_race
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
								35
							)
							,
							_(
								t13_in
								,
								_(
									t14_the
									,
									_(
										t15_late
										,
										t16_eighties
										,
										36
									)
									,
									37
								)
								,
								26
							)
							,
							38
						)
						,
						_(
							t18_redifining
							,
							_(
								t50_det
								,
								_(
									t19_alpine
									,
									t20_skiing
									,
									27
								)
								,
								51
							)
							,
							28
						)
						,
						39
					)
					,
					40
				)
				,
				49
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h08_alberto_tomba
				,
				_(
					h03_won
					,
					_(
						h04_a
						,
						_(
							h05_ski
							,
							h06_race
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
