package semante.pipeline.jan;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseJan11 extends APipelineTest {

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
		val jan     = word("NP","jan");
		val quickly = word("MOD_R","quickly");
		val ate     = word("V_2","ate");
		val an      = word("A","an");
		val egg     = word("N_1","egg");
		val in      = word("P_R","in");
		val brasil  = word("NP","brasil");

		// create the tree structure for the text;
		val tt =
		_( jan , _( quickly , _( ate , _( an , egg ))))
		;

		// create the tree structure for the hypothesis;
		val th =
		_( jan , _( ate , _( an , egg )))
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

