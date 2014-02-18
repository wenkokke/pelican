package semante.pipeline.jan;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseJan12 extends APipelineTest {

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
		val t_jan     = word("NP","jan");
		val t_quickly = word("MOD_R","quickly");
		val t_ate     = word("V_2","ate");
		val t_an      = word("A","an");
		val t_egg     = word("N_1","egg");

		// create the tree structure for the text;
		val tt =
		_( t_jan , _( t_quickly , _( t_ate , _( t_an , t_egg ))))
		;
		


		// create the vocabulary for the text;
		val h_jan     = word("NP","jan");
		val h_quickly = word("MOD_R","quickly");
		val h_ate     = word("V_2","ate");
		val h_an      = word("A","an");
		val h_egg     = word("N_1","egg");

		// create the tree structure for the hypothesis;
		val th =
		_( h_jan , _( h_quickly , _( h_ate , _( h_an , h_egg ))))
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

