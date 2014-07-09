package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0118 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0118() throws Exception {

			// create the vocabulary for the text;
			val t01_a = word("A","A",1);
			val t02_senior = word("MR","senior",2);
			val t03_palestinian = word("MR","Palestinian",3);
			val t04_official = word("N","official",4);
			val t05_said = word("FACT","said",5);
			val t06_the = word("THE","the",6);
			val t07_palestinian = word("MI","Palestinian",7);
			val t08_leader = word("N","leader",8);
			val t64_app = word("WHO_A","APP",64);
			val t33_yasser_arafat = word("NP_D","Yasser_Arafat",33);
			val t13_has = word("V_AUX","has",13);
			val t14_lapsed = word("V_1","lapsed",14);
			val t15_into = word("P_R","into",15);
			val t16_a = word("A","a",16);
			val t17_coma = word("N","coma",17);
			val t18_in = word("P_R","in",18);
			val t19_a = word("A","a",19);
			val t20_french = word("MI","French",20);
			val t21_hospital = word("N","hospital",21);
			val t23_following = word("GER_2","following",23);
			val t24_a = word("A","a",24);
			val t25_sharp = word("MR","sharp",25);
			val t26_deterioration = word("N","deterioration",26);
			val t27_in = word("P_R","in",27);
			val t28_his = word("THE","his",28);
			val t29_health = word("N","health",29);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_palestinian = word("MI","Palestinian",2);
			val h17_yasser_arafat = word("NP_D","Yasser_Arafat",17);
			val h05_has = word("V_AUX","has",5);
			val h06_lapsed = word("V_1","lapsed",6);
			val h07_into = word("P_R","into",7);
			val h08_a = word("A","a",8);
			val h09_coma = word("N","coma",9);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_a
					,
					_(
						t02_senior
						,
						_(
							t03_palestinian
							,
							t04_official
							,
							60
						)
						,
						61
					)
					,
					62
				)
				,
				_(
					t05_said
					,
					_(
						_(
							_(
								t06_the
								,
								_(
									t07_palestinian
									,
									t08_leader
									,
									51
								)
								,
								52
							)
							,
							_(
								t64_app
								,
								t33_yasser_arafat
								,
								65
							)
							,
							53
						)
						,
						_(
							t13_has
							,
							_(
								_(
									t14_lapsed
									,
									_(
										t15_into
										,
										_(
											t16_a
											,
											_(
												t17_coma
												,
												_(
													t18_in
													,
													_(
														t19_a
														,
														_(
															t20_french
															,
															t21_hospital
															,
															54
														)
														,
														55
													)
													,
													37
												)
												,
												35
											)
											,
											69
										)
										,
										44
									)
									,
									45
								)
								,
								_(
									t23_following
									,
									_(
										t24_a
										,
										_(
											t25_sharp
											,
											_(
												t26_deterioration
												,
												_(
													t27_in
													,
													_(
														t28_his
														,
														t29_health
														,
														39
													)
													,
													40
												)
												,
												57
											)
											,
											68
										)
										,
										58
									)
									,
									42
								)
								,
								46
							)
							,
							70
						)
						,
						47
					)
					,
					49
				)
				,
				63
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					_(
						h02_palestinian
						,
						h17_yasser_arafat
						,
						18
					)
					,
					19
				)
				,
				_(
					h05_has
					,
					_(
						h06_lapsed
						,
						_(
							h07_into
							,
							_(
								h08_a
								,
								h09_coma
								,
								12
							)
							,
							13
						)
						,
						14
					)
					,
					15
				)
				,
				20
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
