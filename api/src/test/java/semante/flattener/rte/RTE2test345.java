package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE2test345 extends AFlattenerTest {
	
	@Before
	public final void setUp() throws Exception {
		super.doSetUp();
	}
	
	@Test
	public final void flattenTest() {
		super.flattenTest();
	}
		
		protected final void setReferences() {
			this.textReference = "(AND:ttt ((AND:ttt (plays:et Thomas:e)) (EXISTS:(et)t (\\x0:e.((AND:ttt (((for:e(et)et Thomas:e) plays:et) (IOTA:(et)e (\\x1:e.((AND:ttt (champions:et x1:e)) ((French:(et)et champions:et) x1:e)))))) ((AND:ttt ((EQ:eet x0:e) (IOTA:(et)e (\\x2:e.((AND:ttt (champions:et x2:e)) ((French:(et)et champions:et) x2:e)))))) ((EQ:eet x0:e) Toulouse:e))))))) ((AND:ttt ((captained:eet Thomas:e) Wales:e)) ((captained:eet Thomas:e) (IOTA:(et)e (\\x3:e.((AND:ttt (side:et x3:e)) ((Welsh:(et)et side:et) x3:e))))))";
			this.hypoReference = "(captained:eet Thomas:e) (IOTA:(et)e (\\x0:e.((AND:ttt (side:et x0:e)) ((Welsh:(et)et side:et) x0:e))))";
		}

		protected final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_thomas = word("NP_D","Thomas");
			val t01_who = word("WHO_A","who");
			val t02_captained = word("V_2","captained");
			val t03_wales = word("NP_D","Wales");
			val t04_and = word("AND","and");
			val t05_captained = word("V_2","captained");
			val t06_the = word("THE","the");
			val t07_welsh = word("MR","Welsh");
			val t08_side = word("N","side");
			val t09_plays = word("V_1","plays");
			val t10_for = word("P_R","for");
			val t11_the = word("THE","the");
			val t12_french = word("MR","French");
			val t13_champions = word("N","champions");
			val t14_app = word("WHO_A","APP");
			val t15_toulouse = word("NP_D","Toulouse");

			// create the vocabulary for the hypothesis;
			val h00_thomas = word("NP_D","Thomas");
			val h01_captained = word("V_2","captained");
			val h02_the = word("THE","the");
			val h03_welsh = word("MR","Welsh");
			val h04_side = word("N","side");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_thomas
					,
					_(
						t01_who
						,
						_(
							_(
								_(
									t02_captained
									,
									t03_wales
								)
								,
								t04_and
							)
							,
							_(
								t05_captained
								,
								_(
									t06_the
									,
									_(
										t07_welsh
										,
										t08_side
									)
								)
							)
						)
					)
				)
				,
				_(
					t09_plays
					,
					_(
						t10_for
						,
						_(
							_(
								t11_the
								,
								_(
									t12_french
									,
									t13_champions
								)
							)
							,
							_(
								t14_app
								,
								t15_toulouse
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_thomas
				,
				_(
					h01_captained
					,
					_(
						h02_the
						,
						_(
							h03_welsh
							,
							h04_side
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
