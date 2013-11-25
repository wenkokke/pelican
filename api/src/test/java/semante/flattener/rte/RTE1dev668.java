package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE1dev668 extends AFlattenerTest {
	
	@Before
	public final void setUp() throws Exception {
		super.doSetUp();
	}
	
	@Test
	public final void flattenTest() {
		super.flattenTest();
	}
		
		protected final void setReferences() {
			this.textReference = "(AND:ttt ((saw:eet Dan:e) (IOTA:(et)e hall:et))) ((AND:ttt ((EQ:eet (IOTA:(et)e (\\x0:e.((AND:ttt ((AND:ttt (museum:et x0:e)) (municipal:et x0:e))) ((big:(et)et (\\x1:e.((AND:ttt (museum:et x1:e)) (municipal:et x1:e)))) x0:e))))) (IOTA:(et)e hall:et))) ((AND:ttt T:t) ((old:(et)et (\\x2:e.T:t)) (IOTA:(et)e hall:et))))";
			this.hypoReference = "EXISTS:(et)t (\\x0:e.((AND:ttt ((AND:ttt (museum:et x0:e)) ((big:(et)et museum:et) x0:e))) ((EQ:eet x0:e) (IOTA:(et)e hall:et))))";
		}

		protected final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_dan = word("NP_D","Dan");
			val t01_saw = word("V_2","saw");
			val t02_the = word("THE","the");
			val t03_hall = word("N","hall");
			val t04_which = word("WHO_A","which");
			val t05_is = word("IS","is");
			val t06_old = word("MR","old");
			val t07_and = word("AND","and");
			val t08_is = word("IS","is");
			val t09_the = word("THE","the");
			val t10_big = word("MR","big");
			val t11_municipal = word("MI","municipal");
			val t12_museum = word("N","museum");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","The");
			val h01_hall = word("N","hall");
			val h02_is = word("IS","is");
			val h03_a = word("A","a");
			val h04_big = word("MR","big");
			val h05_museum = word("N","museum");

			// create the tree structure for the text;
			val tt =
			_(
				t00_dan
				,
				_(
					t01_saw
					,
					_(
						_(
							t02_the
							,
							t03_hall
						)
						,
						_(
							t04_which
							,
							_(
								_(
									t05_is
									,
									t06_old
								)
								,
								_(
									t07_and
									,
									_(
										t08_is
										,
										_(
											t09_the
											,
											_(
												t10_big
												,
												_(
													t11_municipal
													,
													t12_museum
												)
											)
										)
									)
								)
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
					h00_the
					,
					h01_hall
				)
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_big
							,
							h05_museum
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
