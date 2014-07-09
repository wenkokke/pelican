package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0021 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0021() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t33_white_house = word("N","White_House",33);
			val t04_ignored = word("V_2","ignored",4);
			val t05_the = word("THE","the",5);
			val t06_domestic = word("MR","domestic",6);
			val t07_threat = word("N","threat",7);
			val t08_from = word("P_R","from",8);
			val t09_the = word("THE","the",9);
			val t10_islamic = word("MI","Islamic",10);
			val t11_militant = word("MI","militant",11);
			val t12_organization = word("N","organization",12);
			val t37_app = word("WHO_A","APP",37);
			val t20_al_qaeda = word("NP_D","al_Qaeda",20);

			// create the vocabulary for the hypothesis;
			val h24_det = word("THE","DET",24);
			val h12_white_house = word("N","White_House",12);
			val h03_ignored = word("V_2","ignored",3);
			val h04_a = word("A","a",4);
			val h05_threat = word("N","threat",5);
			val h06_from = word("P_R","from",6);
			val h07_the = word("THE","the",7);
			val h08_islamic = word("MI","Islamic",8);
			val h19_al_qaeda = word("NP_D","al_Qaeda",19);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t33_white_house
					,
					34
				)
				,
				_(
					t04_ignored
					,
					_(
						t05_the
						,
						_(
							t06_domestic
							,
							_(
								t07_threat
								,
								_(
									t08_from
									,
									_(
										_(
											t09_the
											,
											_(
												t10_islamic
												,
												_(
													t11_militant
													,
													t12_organization
													,
													28
												)
												,
												29
											)
											,
											30
										)
										,
										_(
											t37_app
											,
											t20_al_qaeda
											,
											38
										)
										,
										31
									)
									,
									22
								)
								,
								25
							)
							,
							36
						)
						,
						26
					)
					,
					27
				)
				,
				35
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h24_det
					,
					h12_white_house
					,
					25
				)
				,
				_(
					h03_ignored
					,
					_(
						h04_a
						,
						_(
							h05_threat
							,
							_(
								h06_from
								,
								_(
									h07_the
									,
									_(
										h08_islamic
										,
										h19_al_qaeda
										,
										20
									)
									,
									21
								)
								,
								15
							)
							,
							13
						)
						,
						26
					)
					,
					18
				)
				,
				23
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
