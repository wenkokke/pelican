package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0072 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0072() throws Exception {

			// create the vocabulary for the text;
			val t34_det = word("EMPTYDET","DET",34);
			val t01_ostriches = word("N","Ostriches",1);
			val t36_app = word("WHO_A","APP",36);
			val t03_the = word("THE","the",3);
			val t04_flightless = word("MI","flightless",4);
			val t05_birds = word("N","birds",5);
			val t07_hide = word("V_1","hide",7);
			val t08_in = word("P_R","in",8);
			val t09_the = word("THE","the",9);
			val t10_sand = word("N","sand",10);
			val t11_for = word("P_R","for",11);
			val t39_det = word("EMPTYDET","DET",39);
			val t12_protection = word("N","protection",12);
			val t13_against = word("P_R","against",13);
			val t14_the = word("THE","the",14);
			val t15_wind = word("N","wind",15);

			// create the vocabulary for the hypothesis;
			val h10_det = word("EMPTYDET","DET",10);
			val h01_ostriches = word("N","Ostriches",1);
			val h02_are = word("IS","are",2);
			val h03_flightless = word("MI","flightless",3);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t34_det
						,
						t01_ostriches
						,
						35
					)
					,
					_(
						t36_app
						,
						_(
							t03_the
							,
							_(
								t04_flightless
								,
								t05_birds
								,
								30
							)
							,
							31
						)
						,
						37
					)
					,
					32
				)
				,
				_(
					t07_hide
					,
					_(
						t08_in
						,
						_(
							t09_the
							,
							_(
								t10_sand
								,
								_(
									t11_for
									,
									_(
										t39_det
										,
										_(
											t12_protection
											,
											_(
												t13_against
												,
												_(
													t14_the
													,
													t15_wind
													,
													22
												)
												,
												41
											)
											,
											42
										)
										,
										24
									)
									,
									25
								)
								,
								20
							)
							,
							38
						)
						,
						27
					)
					,
					28
				)
				,
				33
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h10_det
					,
					h01_ostriches
					,
					11
				)
				,
				_(
					h02_are
					,
					h03_flightless
					,
					7
				)
				,
				9
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

