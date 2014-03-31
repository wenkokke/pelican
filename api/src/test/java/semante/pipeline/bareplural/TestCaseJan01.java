package semante.pipeline.bareplural;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseJan01 extends APipelineTest {

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
			val emptydet   = word("EMPTYDET","");
			val people = word("N","people");
			val ran    = word("V_1","ran");
			val fast   = word("MOD_R","fast");

			// create the tree structure for the text;
			val tt =
			_(
				_(emptydet,people)
				,
				_(
					ran
					,
					fast
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(emptydet,people)
				,
				ran
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

