package semante.pipeline.test.jan;

import lombok.val;

import org.junit.Test;

import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

public final class TestCaseJan01 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createJan01());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("Jan01",createJan01());
		}

		public final Entailment createJan01() throws Exception {

			// create the vocabulary for the text;
			val t00_jan = word("NP","jan");
			val t01_sat = word("V_1","sat");
			val t02_and = word("AND","and");
			val t03_ate = word("V_1","ate");

			// create the vocabulary for the hypothesis;
			val h00_jan = word("NP","jan");
			val h01_ate = word("V_1","ate");

			// create the tree structure for the text;
			val tt =
			_(
				t00_jan
				,
				_(
					t01_sat
					,
					_(
						t02_and
						,
						t03_ate
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_jan
				,
				h01_ate
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

