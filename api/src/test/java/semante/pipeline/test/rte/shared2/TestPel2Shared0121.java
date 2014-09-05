package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0121 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0121() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_baby = word("MR","Baby",2);
			val t03_bell = word("MR","Bell",3);
			val t04_company = word("N","company",4);
			val t43_app = word("WHO_A","APP",43);
			val t22_sbc_communications = word("NP_D","SBC_Communications",22);
			val t09_paid = word("V_2","paid",9);
			val t47_det = word("EMPTYDET","DET",47);
			val t10_num16 = word("MR","num16",10);
			val t11_billion = word("MR","billion",11);
			val t12_dollars = word("N","dollars",12);
			val t13_in = word("P_R","in",13);
			val t14_cash = word("N","cash",14);
			val t15_for = word("P_I","for",15);
			val t16_the = word("THE","the",16);
			val t17_deal = word("N","deal",17);
			val t18_with = word("P_R","with",18);
			val t19_att = word("NP_D","ATT",19);

			// create the vocabulary for the hypothesis;
			val h11_sbc_communications = word("NP_D","SBC_Communications",11);
			val h03_paid = word("V_2","paid",3);
			val h22_det = word("EMPTYDET","DET",22);
			val h04_num16 = word("MR","num16",4);
			val h05_billion = word("MR","billion",5);
			val h06_dollars = word("N","dollars",6);
			val h07_for = word("P_I","for",7);
			val h08_a = word("A","a",8);
			val h09_deal = word("N","deal",9);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_baby
							,
							_(
								t03_bell
								,
								t04_company
								,
								38
							)
							,
							39
						)
						,
						40
					)
					,
					_(
						t43_app
						,
						t22_sbc_communications
						,
						44
					)
					,
					41
				)
				,
				_(
					_(
						_(
							t09_paid
							,
							_(
								t47_det
								,
								_(
									t10_num16
									,
									_(
										t11_billion
										,
										t12_dollars
										,
										24
									)
									,
									46
								)
								,
								48
							)
							,
							35
						)
						,
						_(
							t13_in
							,
							t14_cash
							,
							27
						)
						,
						36
					)
					,
					_(
						t15_for
						,
						_(
							t16_the
							,
							_(
								t17_deal
								,
								_(
									t18_with
									,
									t19_att
									,
									30
								)
								,
								28
							)
							,
							45
						)
						,
						32
					)
					,
					37
				)
				,
				42
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h11_sbc_communications
				,
				_(
					_(
						h03_paid
						,
						_(
							h22_det
							,
							_(
								h04_num16
								,
								_(
									h05_billion
									,
									h06_dollars
									,
									12
								)
								,
								21
							)
							,
							23
						)
						,
						18
					)
					,
					_(
						h07_for
						,
						_(
							h08_a
							,
							h09_deal
							,
							14
						)
						,
						15
					)
					,
					19
				)
				,
				20
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
