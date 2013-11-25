package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE1dev1005 extends AFlattenerTest {
	
		@Before
		public final void setUp() throws Exception {
			super.doSetUp();
		}

		@Test
		public final void flattenTest() {
			super.flattenTest();
		}
		
		protected final void setReferences() {
			this.textReference = "EXISTS:(et)t (\\x0:e.((AND:ttt ((AND:ttt ((AND:ttt (specialized:et Jessica:e)) (((in:e(et)et Jessica:e) specialized:et) Claw:e))) (EXISTS:(et)t (\\x1:e.((AND:ttt (years:et x1:e)) (((for:e(et)et Jessica:e) (\\x2:e.((AND:ttt (specialized:et x2:e)) (((in:e(et)et x2:e) specialized:et) Claw:e)))) x1:e)))))) ((AND:ttt ((EQ:eet x0:e) Jessica:e)) (EXISTS:(et)t (\\x3:e.((AND:ttt ((AND:ttt (professor:et x3:e)) (((at:e(et)et x3:e) professor:et) Michigan:e))) ((EQ:eet x0:e) x3:e)))))))";
			this.hypoReference = "EXISTS:(et)t (\\x0:e.((AND:ttt (professor:et x0:e)) ((EQ:eet x0:e) Jessica:e)))";
		}

		protected final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_jessica = word("NP_D","Jessica");
			val t01_app = word("WHO_A","APP");
			val t02_a = word("A","a");
			val t03_professor = word("N","professor");
			val t04_at = word("P_R","at");
			val t05_michigan = word("NP_D","Michigan");
			val t06_has = word("V_AUX","has");
			val t07_specialized = word("V_1","specialized");
			val t08_in = word("P_R","in");
			val t09_the = word("THE","the");
			val t10_claw = word("NP_D","Claw");
			val t11_for = word("P_R","for");
			val t12_some = word("SOME","some");
			val t13_years = word("N","years");

			// create the vocabulary for the hypothesis;
			val h00_jessica = word("NP_D","Jessica");
			val h01_is = word("IS","is");
			val h02_a = word("A","a");
			val h03_professor = word("N","professor");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_jessica
					,
					_(
						t01_app
						,
						_(
							t02_a
							,
							_(
								t03_professor
								,
								_(
									t04_at
									,
									t05_michigan
								)
							)
						)
					)
				)
				,
				_(
					t06_has
					,
					_(
						_(
							t07_specialized
							,
							_(
								t08_in
								,
								_(
									t09_the
									,
									t10_claw
								)
							)
						)
						,
						_(
							t11_for
							,
							_(
								t12_some
								,
								t13_years
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_jessica
				,
				_(
					h01_is
					,
					_(
						h02_a
						,
						h03_professor
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
