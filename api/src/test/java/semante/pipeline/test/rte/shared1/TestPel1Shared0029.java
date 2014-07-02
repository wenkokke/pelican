package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0029 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0029() throws Exception {

			// create the vocabulary for the text;
			val t01_a = word("A","A",1);
			val t02_syrian = word("MI","Syrian",2);
			val t03_american = word("MI","American",3);
			val t04_airman = word("N","airman",4);
			val t44_app = word("WHO_A","APP",44);
			val t05_accused = word("V_1","accused",5);
			val t06_of = word("P_R","of",6);
			val t07_espionage = word("NP_D","espionage",7);
			val t08_complained = word("V_1","complained",8);
			val t09_about = word("P_R","about",9);
			val t47_det = word("A","DET",47);
			val t10_prisoner = word("$NC_1$","prisoner",10);
			val t11_treatment = word("N","treatment",11);
			val t12_at = word("P_R","at",12);
			val t13_the = word("THE","the",13);
			val t14_us = word("$NC_1$","US",14);
			val t15_base = word("N","base",15);
			val t16_in = word("P_R","in",16);
			val t17_guantanamo = word("NP_D","Guantanamo",17);
			val t49_app = word("WHO_A","APP",49);
			val t19_cuba = word("NP_D","Cuba",19);

			// create the vocabulary for the hypothesis;
			val h11_det = word("A","DET",11);
			val h01_american = word("N","American",1);
			val h02_accused = word("V_1","accused",2);
			val h03_of = word("P_R","of",3);
			val h04_espionage = word("NP_D","espionage",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_a
						,
						_(
							t02_syrian
							,
							_(
								t03_american
								,
								t04_airman
								,
								40
							)
							,
							41
						)
						,
						42
					)
					,
					_(
						t44_app
						,
						_(
							t05_accused
							,
							_(
								t06_of
								,
								t07_espionage
								,
								23
							)
							,
							24
						)
						,
						45
					)
					,
					25
				)
				,
				_(
					t08_complained
					,
					_(
						t09_about
						,
						_(
							t47_det
							,
							_(
								_(
									t10_prisoner
									,
									t11_treatment
									,
									26
								)
								,
								_(
									t12_at
									,
									_(
										t13_the
										,
										_(
											_(
												t14_us
												,
												t15_base
												,
												36
											)
											,
											_(
												t16_in
												,
												_(
													t17_guantanamo
													,
													_(
														t49_app
														,
														t19_cuba
														,
														50
													)
													,
													38
												)
												,
												30
											)
											,
											37
										)
										,
										51
									)
									,
									32
								)
								,
								48
							)
							,
							52
						)
						,
						46
					)
					,
					35
				)
				,
				43
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h11_det
					,
					h01_american
					,
					12
				)
				,
				_(
					h02_accused
					,
					_(
						h03_of
						,
						h04_espionage
						,
						7
					)
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

