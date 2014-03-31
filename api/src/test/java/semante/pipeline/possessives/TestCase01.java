package semante.pipeline.possessives;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCase01 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createEntailment());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("TestCase01",createEntailment());
		}

		public final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_john   = word("NP","John");
			val t01_s      = word("GEN","'s");
			val t02_wife   = word("N_2","wife");
			val t03_adores = word("V_2","adores");
			val t04_paul   = word("NP","Paul");

			// create the vocabulary for the hypothesis;
			val h00_the    = word("THE","The");
			val h01_wife   = word("N_2","wife");
			val h02_of     = word("OF","of");
			val h03_john   = word("NP","John");
			val h04_adores = word("V_2","adores");
			val h05_paul   = word("NP","Paul");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_john
						,
						t01_s
					)
					,
					t02_wife
				)
				,
				_(
					t03_adores
					,
					t04_paul
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_the
					,
					_(
						h01_wife
						,
						_(
							h02_of
							,
							h03_john
						)
					)
				)
				,
				_(
					h04_adores
					,
					h05_paul
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
