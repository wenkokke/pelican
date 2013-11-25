package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE1dev582 extends AFlattenerTest {
	
	@Before
	public final void setUp() throws Exception {
		super.doSetUp();
	}
	
	@Test
	public final void flattenTest() {
		super.flattenTest();
	}
	
	protected final void setReferences() {
		this.textReference = "(AND:ttt (comes:et (IOTA:(et)e visit:et))) (((amid:e(et)et (IOTA:(et)e visit:et)) comes:et) (IOTA:(et)e (\\x0:e.((AND:ttt (rumors:et x0:e)) ((AND:ttt (((about:e(et)et x0:e) rumors:et) (IOTA:(et)e (\\x1:e.((AND:ttt ((AND:ttt (leader:et x1:e)) ((senior:(et)et leader:et) x1:e))) (ninety_year_old:et x1:e)))))) ((EQ:eet Deng:e) (IOTA:(et)e (\\x2:e.((AND:ttt ((AND:ttt (leader:et x2:e)) ((senior:(et)et leader:et) x2:e))) (ninety_year_old:et x2:e))))))))))";
		this.hypoReference = "(AND:ttt T:t) (ninety_year_old:et Deng:e)";
	}
	
	protected final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_visit = word("N","visit");
			val t02_comes = word("V_1","comes");
			val t03_amid = word("P_R","amid");
			val t04_the = word("THE","the");
			val t05_rumors = word("N","rumors");
			val t06_about = word("P_R","about");
			val t07_the = word("THE","the");
			val t08_90yearold = word("MI","ninety_year_old");
			val t09_senior = word("MR","senior");
			val t10_leader = word("N","leader");
			val t11_who = word("WHO_A","who");
			val t12_is = word("IS","is");
			val t13_deng = word("NP_D","Deng");

			// create the vocabulary for the hypothesis;
			val h00_deng = word("NP_D","Deng");
			val h01_is = word("IS","is");
			val h02_90yearold = word("MI","ninety_year_old");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					t01_visit
				)
				,
				_(
					t02_comes
					,
					_(
						t03_amid
						,
						_(
							t04_the
							,
							_(
								t05_rumors
								,
								_(
									t06_about
									,
									_(
										_(
											t07_the
											,
											_(
												t08_90yearold
												,
												_(
													t09_senior
													,
													t10_leader
												)
											)
										)
										,
										_(
											t11_who
											,
											_(
												t12_is
												,
												t13_deng
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
				h00_deng
				,
				_(
					h01_is
					,
					h02_90yearold
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
