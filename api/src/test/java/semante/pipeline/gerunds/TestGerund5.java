package semante.pipeline.gerunds;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestGerund5 extends APipelineTest {

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
			val john      = word("NP","John");
			val is        = word("IS", "is");
			val accused   = word("V_1","accused");
			val of        = word("P_R","of");
			val killing   = word("GER_2","killing");
			val the       = word("THE","the");
			val girl      = word("N","girl");

			// create the vocabulary for the hypothesis:
			val a         = word("A","a");

			// create the tree structure for the text;
			val tt =
			_(john,_(is,_(accused,_(of,_(killing,_(the,girl))))));

			// create the tree structure for the hypothesis;
			val th =
			_(john,_(is,_(accused,_(of,_(killing,_(a,girl))))));		

			// create the subsumption relations;
			val ss =
			new String[] {
				""
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}
