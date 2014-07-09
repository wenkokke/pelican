package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0024 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0024() throws Exception {

			// create the vocabulary for the text;
			val t01_iraqi = word("MI","Iraqi",1);
			val t02_militants = word("NP_D","militants",2);
			val t03_said = word("V_2","said",3);
			val t04_they = word("NP_D","they",4);
			val t05_would = word("V_AUX","would",5);
			val t06_behead = word("V_2","behead",6);
			val t20_kim_sunil = word("NP_D","Kim_SunIl",20);
			val t42_app = word("WHO_A","APP",42);
			val t10_a = word("A","a",10);
			val t11_korean = word("MI","Korean",11);
			val t12_translator = word("N","translator",12);
			val t13_who = word("WHO_R","who",13);
			val t14_works = word("V_1","works",14);
			val t15_in = word("P_R","in",15);
			val t16_iraq = word("NP_D","Iraq",16);

			// create the vocabulary for the hypothesis;
			val h12_kim_sunil = word("NP_D","Kim_SunIl",12);
			val h04_who = word("WHO_A","who",4);
			val h05_is = word("IS","is",5);
			val h06_korean = word("MI","Korean",6);
			val h08_works = word("V_1","works",8);
			val h09_in = word("P_R","in",9);
			val h10_iraq = word("NP_D","Iraq",10);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_iraqi
					,
					t02_militants
					,
					18
				)
				,
				_(
					t03_said
					,
					_(
						t04_they
						,
						_(
							_(
								t05_would
								,
								t06_behead
								,
								31
							)
							,
							_(
								t20_kim_sunil
								,
								_(
									t42_app
									,
									_(
										t10_a
										,
										_(
											t11_korean
											,
											_(
												t12_translator
												,
												_(
													t13_who
													,
													_(
														t14_works
														,
														_(
															t15_in
															,
															t16_iraq
															,
															24
														)
														,
														25
													)
													,
													27
												)
												,
												36
											)
											,
											44
										)
										,
										37
									)
									,
									43
								)
								,
								38
							)
							,
							40
						)
						,
						32
					)
					,
					34
				)
				,
				39
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h12_kim_sunil
					,
					_(
						h04_who
						,
						_(
							h05_is
							,
							h06_korean
							,
							15
						)
						,
						17
					)
					,
					23
				)
				,
				_(
					h08_works
					,
					_(
						h09_in
						,
						h10_iraq
						,
						20
					)
					,
					21
				)
				,
				24
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
