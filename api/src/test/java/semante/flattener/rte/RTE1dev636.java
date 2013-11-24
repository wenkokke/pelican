package semante.flattener.rte;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.flattener.AFlattenerTest;

public final class RTE1dev636 extends AFlattenerTest {
	
		@Before
		public final void setUp() throws Exception {
			super.setUp(getClass());
			this.entailment = createRTE1dev636();
		}
		
		@Test
		public final void flattenTest() {
			super.flattenTest();
		}

		public final Entailment createRTE1dev636() throws Exception {

			// create the vocabulary for the text;
			val t00_a = word("A","A");
			val t01_country = word("N","country");
			val t02_app = word("WHO_A","APP");
			val t03_the = word("THE","the");
			val t04_vaticancity = word("N","VaticanCity");
			val t05_that = word("WHO_A","that");
			val t06_is = word("IS","is");
			val t07_beautiful = word("MR","beautiful");
			val t08_attracts = word("V_2","attracts");
			val t09_some = word("SOME","some");
			val t10_attention = word("N","attention");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","The");
			val h01_vaticancity = word("N","VaticanCity");
			val h02_is = word("IS","is");
			val h03_beautiful = word("MR","beautiful");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t00_a
							,
							t01_country
						)
						,
						_(
							t02_app
							,
							_(
								t03_the
								,
								t04_vaticancity
							)
						)
					)
					,
					_(
						t05_that
						,
						_(
							t06_is
							,
							t07_beautiful
						)
					)
				)
				,
				_(
					t08_attracts
					,
					_(
						t09_some
						,
						t10_attention
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
					h01_vaticancity
				)
				,
				_(
					h02_is
					,
					h03_beautiful
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
