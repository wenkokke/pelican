package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0086 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0086() throws Exception {

			// create the vocabulary for the text;
			val t26_valerie_plame = word("NP_D","Valerie_Plame",26);
			val t52_app = word("WHO_A","APP",52);
			val t04_the = word("THE","the",4);
			val t05_exwife = word("N","exWife",5);
			val t06_of = word("P_R","of",6);
			val t55_det = word("THE","DET",55);
			val t07_ambassador = word("N","ambassador",7);
			val t57_app = word("WHO_A","APP",57);
			val t48_joseph_wilson = word("NP_D","Joseph_Wilson",48);
			val t11_worked = word("V_1","worked",11);
			val t12_for = word("P_R","for",12);
			val t13_the = word("THE","the",13);
			val t14_cia = word("NP_D","CIA",14);
			val t16_performing = word("GER_2","performing",16);
			val t17_clandestine = word("MR","clandestine",17);
			val t63_det = word("EMPTYDET","DET",63);
			val t18_activities = word("N","activities",18);
			val t19_with = word("P_R","with",19);
			val t20_the = word("THE","the",20);
			val t21_agency = word("N","agency",21);
			val t22_for = word("P_R","for",22);
			val t23_num20 = word("MR","num20",23);
			val t65_det = word("EMPTYDET","DET",65);
			val t24_years = word("N","years",24);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h02_exwife = word("N","exWife",2);
			val h03_of = word("P_R","of",3);
			val h12_joseph_wilson = word("NP_D","Joseph_Wilson",12);
			val h06_worked = word("V_1","worked",6);
			val h07_for = word("P_R","for",7);
			val h08_the = word("THE","the",8);
			val h09_cia = word("NP_D","CIA",9);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t26_valerie_plame
					,
					_(
						t52_app
						,
						_(
							t04_the
							,
							_(
								t05_exwife
								,
								_(
									t06_of
									,
									_(
										_(
											t55_det
											,
											t07_ambassador
											,
											56
										)
										,
										_(
											t57_app
											,
											t48_joseph_wilson
											,
											58
										)
										,
										49
									)
									,
									29
								)
								,
								27
							)
							,
							54
						)
						,
						53
					)
					,
					50
				)
				,
				_(
					_(
						t11_worked
						,
						_(
							t12_for
							,
							_(
								t13_the
								,
								t14_cia
								,
								32
							)
							,
							33
						)
						,
						44
					)
					,
					_(
						t16_performing
						,
						_(
							t17_clandestine
							,
							_(
								t63_det
								,
								_(
									t18_activities
									,
									_(
										t19_with
										,
										_(
											t20_the
											,
											_(
												t21_agency
												,
												_(
													t22_for
													,
													_(
														t23_num20
														,
														_(
															t65_det
															,
															t24_years
															,
															66
														)
														,
														36
													)
													,
													37
												)
												,
												35
											)
											,
											61
										)
										,
										39
									)
									,
									64
								)
								,
								67
							)
							,
							60
						)
						,
						45
					)
					,
					47
				)
				,
				51
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_an
					,
					_(
						h02_exwife
						,
						_(
							h03_of
							,
							h12_joseph_wilson
							,
							13
						)
						,
						11
					)
					,
					20
				)
				,
				_(
					h06_worked
					,
					_(
						h07_for
						,
						_(
							h08_the
							,
							h09_cia
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
				19
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
