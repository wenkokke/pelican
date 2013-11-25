package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE1test605 extends AFlattenerTest {
	
	@Before
	public final void setUp() throws Exception {
		super.doSetUp();
	}
	
	@Test
	public final void flattenTest() {
		super.flattenTest();
	}
		
		protected final void setReferences() {
			this.textReference = "(AND:ttt ((EQ:eet (IOTA:(et)e (\\x0:e.((AND:ttt ((AND:ttt (nation:et x0:e)) (((in:e(et)et x0:e) nation:et) (IOTA:(et)e world:et)))) ((largest:(et)et (\\x1:e.((AND:ttt (nation:et x1:e)) (((in:e(et)et x1:e) nation:et) (IOTA:(et)e world:et))))) x0:e))))) Indonesia:e)) (EXISTS:(et)t (\\x2:e.((AND:ttt ((AND:ttt ((AND:ttt (islands:et x2:e)) ((nice:(et)et islands:et) x2:e))) (((for:e(et)et x2:e) (\\x3:e.((AND:ttt (islands:et x3:e)) ((nice:(et)et islands:et) x3:e)))) (IOTA:(et)e retires:et)))) ((has:eet Indonesia:e) x2:e))))";
			this.hypoReference = "EXISTS:(et)t (\\x0:e.((AND:ttt (islands:et x0:e)) ((has:eet Indonesia:e) x0:e)))";
		}

		protected final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_indonesia = word("NP_D","Indonesia");
			val t01_is = word("IS","is");
			val t02_the = word("THE","the");
			val t03_largest = word("MR","largest");
			val t04_nation = word("N","nation");
			val t05_in = word("P_R","in");
			val t06_the = word("THE","the");
			val t07_world = word("N","world");
			val t08_and = word("AND","and");
			val t09_has = word("V_2","has");
			val t10_some = word("SOME","some");
			val t11_nice = word("MR","nice");
			val t12_islands = word("N","islands");
			val t13_for = word("P_R","for");
			val t14_the = word("THE","the");
			val t15_retires = word("N","retires");

			// create the vocabulary for the hypothesis;
			val h00_indonesia = word("NP_D","Indonesia");
			val h01_has = word("V_2","has");
			val h02_some = word("SOME","some");
			val h03_islands = word("N","islands");

			// create the tree structure for the text;
			val tt =
			_(
				t00_indonesia
				,
				_(
					_(
						_(
							t01_is
							,
							_(
								t02_the
								,
								_(
									t03_largest
									,
									_(
										t04_nation
										,
										_(
											t05_in
											,
											_(
												t06_the
												,
												t07_world
											)
										)
									)
								)
							)
						)
						,
						t08_and
					)
					,
					_(
						t09_has
						,
						_(
							t10_some
							,
							_(
								_(
									t11_nice
									,
									t12_islands
								)
								,
								_(
									t13_for
									,
									_(
										t14_the
										,
										t15_retires
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
				h00_indonesia
				,
				_(
					h01_has
					,
					_(
						h02_some
						,
						h03_islands
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
