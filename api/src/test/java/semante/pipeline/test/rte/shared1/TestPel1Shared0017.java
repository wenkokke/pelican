package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0017 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0017() throws Exception {

			// create the vocabulary for the text;
			val t01_oqueli = word("NP_D","Oqueli",1);
			val t47_flies_to = word("V_2","flies_to",47);
			val t04_nicaragua = word("NP_D","Nicaragua",4);
			val t05_for = word("P_R","for",5);
			val t06_the = word("THE","the",6);
			val t07_international = word("MR","international",7);
			val t08_socialist = word("MI","Socialist",8);
			val t09_delegation = word("N","delegation",9);
			val t10_which = word("WHO_R","which",10);
			val t11_will = word("V_AUX","will",11);
			val t12_observe = word("V_2","observe",12);
			val t13_the = word("THE","the",13);
			val t14_nicaraguan = word("MI","Nicaraguan",14);
			val t15_electoral = word("MR","electoral",15);
			val t16_campaign = word("N","campaign",16);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_socialist = word("MI","Socialist",2);
			val h03_delegation = word("N","delegation",3);
			val h04_will = word("V_AUX","will",4);
			val h05_observe = word("V_2","observe",5);
			val h06_an = word("A","an",6);
			val h07_electoral = word("MR","electoral",7);
			val h08_campaign = word("N","campaign",8);

			// create the tree structure for the text;
			val tt =
			_(
				t01_oqueli
				,
				_(
					_(
						t47_flies_to
						,
						t04_nicaragua
						,
						49
					)
					,
					_(
						t05_for
						,
						_(
							t06_the
							,
							_(
								t07_international
								,
								_(
									t08_socialist
									,
									_(
										t09_delegation
										,
										_(
											t10_which
											,
											_(
												t11_will
												,
												_(
													t12_observe
													,
													_(
														t13_the
														,
														_(
															t14_nicaraguan
															,
															_(
																t15_electoral
																,
																t16_campaign
																,
																35
															)
															,
															36
														)
														,
														37
													)
													,
													23
												)
												,
												24
											)
											,
											26
										)
										,
										32
									)
									,
									44
								)
								,
								33
							)
							,
							45
						)
						,
						28
					)
					,
					40
				)
				,
				42
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_socialist
						,
						h03_delegation
						,
						17
					)
					,
					18
				)
				,
				_(
					h04_will
					,
					_(
						h05_observe
						,
						_(
							h06_an
							,
							_(
								h07_electoral
								,
								h08_campaign
								,
								15
							)
							,
							16
						)
						,
						12
					)
					,
					13
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
