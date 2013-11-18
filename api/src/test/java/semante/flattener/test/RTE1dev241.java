package semante.flattener.test;

import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

import org.junit.Test;
import lombok.val;

public final class RTE1dev241 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1dev241());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1dev241",createRTE1dev241());
		}

		public final Entailment createRTE1dev241() throws Exception {

			// create the vocabulary for the text;
			val t00_salesforce = word("NP_D","Salesforce");
			val t01_which = word("WHO_A","which");
			val t02_is = word("IS","is");
			val t03_trading = word("V_1","trading");
			val t04_under = word("P_R","under");
			val t05_the = word("THE","the");
			val t06_symbol = word("N","symbol");
			val t07_which = word("WHO_A","which");
			val t08_is = word("IS","is");
			val t09_crm = word("NP_D","CRM");
			val t10_on = word("P_R","on");
			val t11_the = word("THE","the");
			val t12_nyse = word("N","nYSE");
			val t13_is = word("IS","is");
			val t14_nice = word("MR","nice");

			// create the vocabulary for the hypothesis;
			val h00_salesforce = word("NP_D","Salesforce");
			val h01_is = word("IS","is");
			val h02_trading = word("V_1","trading");
			val h03_under = word("P_R","under");
			val h04_the = word("THE","the");
			val h05_symbol = word("N","symbol");
			val h06_which = word("WHO_A","which");
			val h07_is = word("IS","is");
			val h08_crm = word("NP_D","CRM");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_salesforce
					,
					_(
						t01_which
						,
						_(
							t02_is
							,
							_(
								_(
									t03_trading
									,
									_(
										t04_under
										,
										_(
											_(
												t05_the
												,
												t06_symbol
											)
											,
											_(
												t07_which
												,
												_(
													t08_is
													,
													t09_crm
												)
											)
										)
									)
								)
								,
								_(
									t10_on
									,
									_(
										t11_the
										,
										t12_nyse
									)
								)
							)
						)
					)
				)
				,
				_(
					t13_is
					,
					t14_nice
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_salesforce
				,
				_(
					h01_is
					,
					_(
						h02_trading
						,
						_(
							h03_under
							,
							_(
								_(
									h04_the
									,
									h05_symbol
								)
								,
								_(
									h06_which
									,
									_(
										h07_is
										,
										h08_crm
									)
								)
							)
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				""
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}
