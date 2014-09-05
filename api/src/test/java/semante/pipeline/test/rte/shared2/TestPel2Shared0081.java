package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0081 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0081() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_doctor = word("N","doctor",2);
			val t03_who = word("WHO_A","who",3);
			val t04_treated = word("V_2","treated",4);
			val t31_ayrton_senna = word("NP_D","Ayrton_Senna",31);
			val t07_in = word("P_R","in",7);
			val t08_italy = word("NP_D","Italy",8);
			val t10_after = word("P_R","after",10);
			val t11_the = word("THE","the",11);
			val t12_crash = word("N","crash",12);
			val t13_in = word("P_R","in",13);
			val t14_the = word("THE","the",14);
			val t56_san_marino_grand_prix = word("NP_D","San_Marino_Grand_Prix",56);
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
			val h10_ayrton_senna = word("NP_D","Ayrton_Senna",10);
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
									t31_ayrton_senna
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
												t56_san_marino_grand_prix
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
						h10_ayrton_senna
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
