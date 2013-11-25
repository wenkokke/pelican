package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE1dev241 extends AFlattenerTest {
	
	@Before
	public final void setUp() throws Exception {
		super.doSetUp();
	}
	
	@Test
	public final void flattenTest() {
		super.flattenTest();
	}
	
	protected final void setReferences() {
		this.textReference = "(AND:ttt ((AND:ttt T:t) ((nice:(et)et (\\x0:e.T:t)) Salesforce:e))) ((AND:ttt ((AND:ttt (trading:et Salesforce:e)) ((AND:ttt (((under:e(et)et Salesforce:e) trading:et) (IOTA:(et)e symbol:et))) ((EQ:eet CRM:e) (IOTA:(et)e symbol:et))))) (((on:e(et)et Salesforce:e) (\\x1:e.((AND:ttt (trading:et x1:e)) ((AND:ttt (((under:e(et)et x1:e) trading:et) (IOTA:(et)e symbol:et))) ((EQ:eet CRM:e) (IOTA:(et)e symbol:et)))))) (IOTA:(et)e nYSE:et)))";
		this.hypoReference = "(AND:ttt (trading:et Salesforce:e)) ((AND:ttt (((under:e(et)et Salesforce:e) trading:et) (IOTA:(et)e symbol:et))) ((EQ:eet CRM:e) (IOTA:(et)e symbol:et)))";
	}
	
		protected final Entailment createEntailment() throws Exception {

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
