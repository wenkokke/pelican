package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0005 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0005() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_inquiries = word("N","inquiries",2);
			val t39_app = word("WHO_R","APP",39);
			val t03_spearheaded = word("V_1","spearheaded",3);
			val t04_by = word("P_R","by",4);
			val t42_det = word("A","DET",42);
			val t05_milan = word("MI","Milan",5);
			val t06_magistrate = word("N","magistrate",6);
			val t37_app = word("WHO_A","APP",37);
			val t32_antonio_di_pietro = word("NP_D","Antonio_di_Pietro",32);
			val t26_focused_on = word("V_2","focused_on",26);
			val t12_italy = word("NP_D","Italy",12);
			val t13_s = word("POSS","s",13);
			val t14_private = word("MI","private",14);
			val t28_business_sectors = word("N","business_sectors",28);

			// create the vocabulary for the hypothesis;
			val h13_antonio_di_pietro = word("NP_D","Antonio_di_Pietro",13);
			val h04_is = word("IS","is",4);
			val h05_a = word("A","a",5);
			val h06_magistrate = word("N","magistrate",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_inquiries
						,
						_(
							t39_app
							,
							_(
								t03_spearheaded
								,
								_(
									t04_by
									,
									_(
										_(
											t42_det
											,
											_(
												t05_milan
												,
												t06_magistrate
												,
												34
											)
											,
											43
										)
										,
										_(
											t37_app
											,
											t32_antonio_di_pietro
											,
											38
										)
										,
										20
									)
									,
									41
								)
								,
								21
							)
							,
							40
						)
						,
						18
					)
					,
					44
				)
				,
				_(
					t26_focused_on
					,
					_(
						_(
							t12_italy
							,
							t13_s
							,
							23
						)
						,
						_(
							t14_private
							,
							t28_business_sectors
							,
							29
						)
						,
						30
					)
					,
					36
				)
				,
				35
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h13_antonio_di_pietro
				,
				_(
					h04_is
					,
					_(
						h05_a
						,
						h06_magistrate
						,
						9
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
