package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0042 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0042() throws Exception {

			// create the vocabulary for the text;
			val t41_det = word("SOME","DET",41);
			val t01_authorities = word("N","Authorities",1);
			val t02_say = word("FACT","say",2);
			val t20_monica_meadows = word("NP_D","Monica_Meadows",20);
			val t06_who = word("WHO_A","who",6);
			val t07_appeared = word("V_1","appeared",7);
			val t08_in = word("P_R","in",8);
			val t09_the = word("THE","the",9);
			val t10_last = word("MR","last",10);
			val t11_edition = word("N","edition",11);
			val t12_of = word("P_R","of",12);
			val t13_vogue = word("NP_D","Vogue",13);
			val t15_is = word("IS","is",15);
			val t16_in = word("P_R","in",16);
			val t44_det = word("A","DET",44);
			val t17_stable = word("MR","stable",17);
			val t18_condition = word("N","condition",18);

			// create the vocabulary for the hypothesis;
			val h09_monica_meadows = word("NP_D","Monica_Meadows",9);
			val h03_appeared = word("V_1","appeared",3);
			val h04_in = word("P_R","in",4);
			val h05_an = word("A","an",5);
			val h06_edition = word("N","edition",6);
			val h07_of = word("P_R","of",7);
			val h08_vogue = word("NP_D","Vogue",8);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t41_det
					,
					t01_authorities
					,
					42
				)
				,
				_(
					t02_say
					,
					_(
						_(
							t20_monica_meadows
							,
							_(
								t06_who
								,
								_(
									t07_appeared
									,
									_(
										t08_in
										,
										_(
											t09_the
											,
											_(
												t10_last
												,
												_(
													t11_edition
													,
													_(
														t12_of
														,
														t13_vogue
														,
														24
													)
													,
													38
												)
												,
												43
											)
											,
											39
										)
										,
										26
									)
									,
									27
								)
								,
								29
							)
							,
							40
						)
						,
						_(
							t15_is
							,
							_(
								t16_in
								,
								_(
									t44_det
									,
									_(
										t17_stable
										,
										t18_condition
										,
										31
									)
									,
									45
								)
								,
								32
							)
							,
							33
						)
						,
						34
					)
					,
					36
				)
				,
				37
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h09_monica_meadows
				,
				_(
					h03_appeared
					,
					_(
						h04_in
						,
						_(
							h05_an
							,
							_(
								h06_edition
								,
								_(
									h07_of
									,
									h08_vogue
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
					15
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
