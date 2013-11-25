package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE1dev176 extends AFlattenerTest {
	
	@Before
	public final void setUp() throws Exception {
		super.doSetUp();
	}
	
	@Test
	public final void flattenTest() {
		super.flattenTest();
	}
	
	protected final void setReferences() {
		this.textReference = "EXISTS:(et)t (\\x0:e.((AND:ttt ((AND:ttt ((AND:ttt (Korean:et x0:e)) (((in:e(et)et x0:e) Korean:et) Iraq:e))) ((AND:ttt ((ate:eet x0:e) (IOTA:(et)e (\\x1:e.((AND:ttt (meatballs:et x1:e)) ((Dutch:(et)et meatballs:et) x1:e)))))) ((later:(et)et (\\x2:e.((ate:eet x2:e) (IOTA:(et)e (\\x3:e.((AND:ttt (meatballs:et x3:e)) ((Dutch:(et)et meatballs:et) x3:e))))))) x0:e)))) (prayed:et x0:e)))";
		this.hypoReference = "EXISTS:(et)t (\\x0:e.((AND:ttt ((AND:ttt (Korean:et x0:e)) (((in:e(et)et x0:e) Korean:et) Iraq:e))) ((ate:eet x0:e) (IOTA:(et)e (\\x1:e.((AND:ttt (meatballs:et x1:e)) ((Dutch:(et)et meatballs:et) x1:e)))))))";
	}
	
		protected final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_a = word("A","A");
			val t01_korean = word("N","Korean");
			val t02_in = word("P_R","in");
			val t03_iraq = word("NP_D","Iraq");
			val t04_who = word("WHO_R","who");
			val t05_later = word("MR","later");
			val t06_ate = word("V_2","ate");
			val t07_the = word("THE","the");
			val t08_dutch = word("MR","Dutch");
			val t09_meatballs = word("N","meatballs");
			val t10_prayed = word("V_1","prayed");

			// create the vocabulary for the hypothesis;
			val h00_a = word("A","A");
			val h01_korean = word("N","Korean");
			val h02_in = word("P_R","in");
			val h03_iraq = word("NP_D","Iraq");
			val h04_ate = word("V_2","ate");
			val h05_the = word("THE","the");
			val h06_dutch = word("MR","Dutch");
			val h07_meatballs = word("N","meatballs");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_a
					,
					_(
						_(
							t01_korean
							,
							_(
								t02_in
								,
								t03_iraq
							)
						)
						,
						_(
							t04_who
							,
							_(
								t05_later
								,
								_(
									t06_ate
									,
									_(
										t07_the
										,
										_(
											t08_dutch
											,
											t09_meatballs
										)
									)
								)
							)
						)
					)
				)
				,
				t10_prayed
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_a
					,
					_(
						h01_korean
						,
						_(
							h02_in
							,
							h03_iraq
						)
					)
				)
				,
				_(
					h04_ate
					,
					_(
						h05_the
						,
						_(
							h06_dutch
							,
							h07_meatballs
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
