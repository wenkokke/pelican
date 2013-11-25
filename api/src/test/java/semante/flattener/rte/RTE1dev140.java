package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE1dev140 extends AFlattenerTest {
	
	@Before
	public final void setUp() throws Exception {
		super.doSetUp();
	}
	
	@Test
	public final void flattenTest() {
		super.flattenTest();
	}
	
	protected final void setReferences() {
		this.textReference = "(AND:ttt ((AND:ttt (thrown:et (IOTA:(et)e body:et))) (EXISTS:(et)t (\\x0:e.((AND:ttt (vehicle:et x0:e)) (((from:e(et)et (IOTA:(et)e body:et)) thrown:et) x0:e)))))) ((AND:ttt ((AND:ttt (found:et (IOTA:(et)e body:et))) (((by:e(et)et (IOTA:(et)e body:et)) found:et) (IOTA:(et)e (\\x1:e.((AND:ttt ((AND:ttt (police:et x1:e)) ((military:(et)et police:et) x1:e))) (US:et x1:e))))))) (((in:e(et)et (IOTA:(et)e body:et)) (\\x2:e.((AND:ttt (found:et x2:e)) (((by:e(et)et x2:e) found:et) (IOTA:(et)e (\\x3:e.((AND:ttt ((AND:ttt (police:et x3:e)) ((military:(et)et police:et) x3:e))) (US:et x3:e)))))))) (IOTA:(et)e (\\x4:e.((AND:ttt (west:et x4:e)) (((of:e(et)et x4:e) west:et) Baghdad:e))))))";
		this.hypoReference = "EXISTS:(et)t (\\x0:e.((AND:ttt (body:et x0:e)) ((AND:ttt ((AND:ttt (found:et x0:e)) (EXISTS:(et)t (\\x1:e.((AND:ttt ((AND:ttt (police:et x1:e)) ((military:(et)et police:et) x1:e))) (((by:e(et)et x0:e) found:et) x1:e)))))) (thrown:et x0:e))))";
	}
	
		protected final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_body = word("N","body");
			val t02_which = word("WHO_A","which");
			val t03_was = word("IS","was");
			val t04_found = word("V_1","found");
			val t05_by = word("P_R","by");
			val t06_the = word("THE","the");
			val t07_us = word("MI","US");
			val t08_military = word("MR","military");
			val t09_police = word("N","police");
			val t10_in = word("P_R","in");
			val t11_the = word("THE","the");
			val t12_west = word("N","west");
			val t13_of = word("P_R","of");
			val t14_baghdad = word("NP_D","Baghdad");
			val t15_was = word("IS","was");
			val t16_thrown = word("V_1","thrown");
			val t17_from = word("P_R","from");
			val t18_a = word("A","a");
			val t19_vehicle = word("N","vehicle");

			// create the vocabulary for the hypothesis;
			val h00_a = word("A","A");
			val h01_body = word("N","body");
			val h02_was = word("IS","was");
			val h03_thrown = word("V_1","thrown");
			val h04_and = word("AND","and");
			val h05_found = word("V_1","found");
			val h06_by = word("P_R","by");
			val h07_a = word("A","a");
			val h08_military = word("MR","military");
			val h09_police = word("N","police");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						t01_body
					)
					,
					_(
						t02_which
						,
						_(
							t03_was
							,
							_(
								_(
									t04_found
									,
									_(
										t05_by
										,
										_(
											t06_the
											,
											_(
												t07_us
												,
												_(
													t08_military
													,
													t09_police
												)
											)
										)
									)
								)
								,
								_(
									t10_in
									,
									_(
										t11_the
										,
										_(
											t12_west
											,
											_(
												t13_of
												,
												t14_baghdad
											)
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					t15_was
					,
					_(
						t16_thrown
						,
						_(
							t17_from
							,
							_(
								t18_a
								,
								t19_vehicle
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_a
					,
					h01_body
				)
				,
				_(
					_(
						h02_was
						,
						h03_thrown
					)
					,
					_(
						h04_and
						,
						_(
							h05_found
							,
							_(
								h06_by
								,
								_(
									h07_a
									,
									_(
										h08_military
										,
										h09_police
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
