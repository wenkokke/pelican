package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0114 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0114() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_olympic = word("$NPC_1$","Olympic",2);
			val t03_games = word("NP_D","Games",3);
			val t59_app = word("WHO_A","APP",59);
			val t05_the = word("THE","the",5);
			val t06_leading = word("MR","leading",6);
			val t07_international = word("MR","international",7);
			val t08_sporting = word("MR","sporting",8);
			val t09_event = word("N","event",9);
			val t32_took_place = word("V_1","took_place",32);
			val t12_in = word("P_R","in",12);
			val t13_atlanta = word("NP_D","Atlanta",13);
			val t14_in = word("P_R","in",14);
			val t15_num1996 = word("NP_D","num1996",15);
			val t17_disappointing = word("GER_2","disappointing",17);
			val t18_greece = word("NP_D","Greece",18);
			val t64_app = word("WHO_A","APP",64);
			val t20_the = word("THE","the",20);
			val t21_birthplace = word("N","birthplace",21);
			val t22_of = word("P_R","of",22);
			val t23_the = word("THE","the",23);
			val t24_games = word("N","games",24);

			// create the vocabulary for the hypothesis;
			val h01_olympic = word("$NPC_1$","Olympic",1);
			val h02_games = word("NP_D","Games",2);
			val h03_is = word("IS","is",3);
			val h04_an = word("A","an",4);
			val h05_international = word("MR","international",5);
			val h06_sporting = word("MR","sporting",6);
			val h07_event = word("N","event",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_olympic
							,
							t03_games
							,
							51
						)
						,
						52
					)
					,
					_(
						t59_app
						,
						_(
							t05_the
							,
							_(
								t06_leading
								,
								_(
									t07_international
									,
									_(
										t08_sporting
										,
										t09_event
										,
										29
									)
									,
									53
								)
								,
								61
							)
							,
							54
						)
						,
						60
					)
					,
					55
				)
				,
				_(
					_(
						_(
							t32_took_place
							,
							_(
								t12_in
								,
								t13_atlanta
								,
								34
							)
							,
							35
						)
						,
						_(
							t14_in
							,
							t15_num1996
							,
							37
						)
						,
						49
					)
					,
					_(
						t17_disappointing
						,
						_(
							t18_greece
							,
							_(
								t64_app
								,
								_(
									t20_the
									,
									_(
										t21_birthplace
										,
										_(
											t22_of
											,
											_(
												t23_the
												,
												t24_games
												,
												43
											)
											,
											44
										)
										,
										42
									)
									,
									67
								)
								,
								65
							)
							,
							38
						)
						,
						68
					)
					,
					50
				)
				,
				56
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_olympic
					,
					h02_games
					,
					9
				)
				,
				_(
					h03_is
					,
					_(
						h04_an
						,
						_(
							h05_international
							,
							_(
								h06_sporting
								,
								h07_event
								,
								12
							)
							,
							10
						)
						,
						17
					)
					,
					14
				)
				,
				16
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

