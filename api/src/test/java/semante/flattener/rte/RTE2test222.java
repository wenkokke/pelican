package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE2test222 extends AFlattenerTest {
	
	@Before
	public final void setUp() throws Exception {
		super.doSetUp();
	}
	
	@Test
	public final void flattenTest() {
		super.flattenTest();
	}
		
		protected final void setReferences() {
			this.textReference = "(AND:ttt (EXISTS:(et)t (\\x0:e.((AND:ttt ((AND:ttt (doctor:et x0:e)) ((Australian:(et)et doctor:et) x0:e))) ((won:eet x0:e) (IOTA:(et)e (\\x1:e.((AND:ttt ((AND:ttt (Oscar:et x1:e)) ((silver:(et)et Oscar:et) x1:e))) ((dirty:(et)et (\\x2:e.((AND:ttt (Oscar:et x2:e)) ((silver:(et)et Oscar:et) x2:e)))) x1:e))))))))) (EXISTS:(et)t (\\x3:e.((AND:ttt ((AND:ttt (doctor:et x3:e)) ((Australian:(et)et doctor:et) x3:e))) ((EQ:eet Warren:e) x3:e))))";
			this.hypoReference = "EXISTS:(et)t (\\x0:e.((AND:ttt (Oscar:et x0:e)) ((won:eet Warren:e) x0:e)))";
		}
		
		protected final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_an = word("A","An");
			val t01_australian = word("MR","Australian");
			val t02_doctor = word("N","doctor");
			val t03_who = word("WHO_A","who");
			val t04_is = word("IS","is");
			val t05_warren = word("NP_D","Warren");
			val t06_won = word("V_2","won");
			val t07_the = word("THE","the");
			val t08_dirty = word("MR","dirty");
			val t09_silver = word("MR","silver");
			val t10_oscar = word("N","Oscar");

			// create the vocabulary for the hypothesis;
			val h00_warren = word("NP_D","Warren");
			val h01_won = word("V_2","won");
			val h02_an = word("A","an");
			val h03_oscar = word("N","Oscar");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_an
						,
						_(
							t01_australian
							,
							t02_doctor
						)
					)
					,
					_(
						t03_who
						,
						_(
							t04_is
							,
							t05_warren
						)
					)
				)
				,
				_(
					t06_won
					,
					_(
						t07_the
						,
						_(
							t08_dirty
							,
							_(
								t09_silver
								,
								t10_oscar
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_warren
				,
				_(
					h01_won
					,
					_(
						h02_an
						,
						h03_oscar
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
