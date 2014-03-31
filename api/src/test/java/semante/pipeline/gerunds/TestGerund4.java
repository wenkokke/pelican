package semante.pipeline.gerunds;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestGerund4 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createEntailment());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("TestCase01",createEntailment());
		}

		public final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text:
			val t00_john      = word("NP","John");
			val t01_travelled = word("V_1","travelled");
			val t02_walking   = word("GER_1","walking");

			// create the vocabulary for the hypothesis:
			val h00_john      = word("NP","John");
			val h01_travelled = word("V_1","travelled");

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					t01_travelled
					,
					t02_walking
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_john
				,
				h01_travelled
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
