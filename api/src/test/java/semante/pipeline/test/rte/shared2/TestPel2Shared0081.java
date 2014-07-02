package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0081 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0081() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_doctor = word("N","doctor",2);
			val t03_who = word("WHO_A","who",3);
			val t04_treated = word("V_2","treated",4);
			val t05_ayrton = word("$NPC_1$","Ayrton",5);
			val t06_senna = word("NP_D","Senna",6);
			val t07_in = word("P_R","in",7);
			val t08_italy = word("NP_D","Italy",8);
			val t10_after = word("P_R","after",10);
			val t11_the = word("THE","the",11);
			val t12_crash = word("N","crash",12);
			val t13_in = word("P_R","in",13);
			val t14_the = word("THE","the",14);
			val t15_san = word("$NPC_1$","San",15);
			val t16_marino = word("$NPC_1$","Marino",16);
			val t17_grand = word("$NPC_1$","Grand",17);
			val t18_prix = word("NP_D","Prix",18);
			val t20_has = word("V_AUX","has",20);
			val t21_denied = word("V_2","denied",21);
			val t63_det = word("EMPTYDET","DET",63);
			val t22_allegations = word("N","allegations",22);
			val t23_about = word("P_R","about",23);
			val t24_the = word("THE","the",24);
			val t25_driver = word("N","driver",25);
			val t26_s = word("POSS","s",26);
			val t27_death = word("N","death",27);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_doctor = word("N","doctor",2);
			val h03_treated = word("V_2","treated",3);
			val h04_ayrton = word("$NPC_1$","Ayrton",4);
			val h05_senna = word("NP_D","Senna",5);
			val h06_in = word("P_R","in",6);
			val h07_italy = word("NP_D","Italy",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						t02_doctor
						,
						29
					)
					,
					_(
						t03_who
						,
						_(
							_(
								_(
									t04_treated
									,
									_(
										t05_ayrton
										,
										t06_senna
										,
										31
									)
									,
									35
								)
								,
								_(
									t07_in
									,
									t08_italy
									,
									33
								)
								,
								36
							)
							,
							_(
								t10_after
								,
								_(
									t11_the
									,
									_(
										t12_crash
										,
										_(
											t13_in
											,
											_(
												t14_the
												,
												_(
													t15_san
													,
													_(
														t16_marino
														,
														_(
															t17_grand
															,
															t18_prix
															,
															54
														)
														,
														55
													)
													,
													56
												)
												,
												57
											)
											,
											41
										)
										,
										39
									)
									,
									62
								)
								,
								43
							)
							,
							60
						)
						,
						37
					)
					,
					38
				)
				,
				_(
					t20_has
					,
					_(
						t21_denied
						,
						_(
							t63_det
							,
							_(
								t22_allegations
								,
								_(
									t23_about
									,
									_(
										_(
											_(
												t24_the
												,
												t25_driver
												,
												52
											)
											,
											t26_s
											,
											65
										)
										,
										t27_death
										,
										46
									)
									,
									47
								)
								,
								48
							)
							,
							64
						)
						,
						49
					)
					,
					50
				)
				,
				59
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					h02_doctor
					,
					9
				)
				,
				_(
					_(
						h03_treated
						,
						_(
							h04_ayrton
							,
							h05_senna
							,
							10
						)
						,
						14
					)
					,
					_(
						h06_in
						,
						h07_italy
						,
						12
					)
					,
					16
				)
				,
				17
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

