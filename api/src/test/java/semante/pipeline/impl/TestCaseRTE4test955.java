package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;

public class TestCaseRTE4test955 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;

	@Before
	public final void setUpPair() {

		// Text part
		val the          = leaf(pair("THE","The"));
		val largest      = leaf(pair("MR","largest"));
		val search       = leaf(pair("N","search"));
		val engine       = leaf(pair("N","engine"));
		val on           = leaf(pair("P_R","on"));
		val web          = leaf(pair("N","web"));
		val app          = leaf(pair("WHO_A","APP"));
		val google       = leaf(pair("NP","google"));
		val receives     = leaf(pair("V_1","receives"));
		val over         = leaf(pair("P_R","over"));
		val _200_million = leaf(pair("NUMBER","200 million"));
		val queries      = leaf(pair("N","queries"));
		val each         = leaf(pair("EVERY","each"));
		val day          = leaf(pair("N","day"));
		val through      = leaf(pair("P_R","through"));
		val its          = leaf(pair("POSS","its"));
		val various      = leaf(pair("MR","various"));
		val services     = leaf(pair("N","services"));

		val tree1 = 
			node(
				node(
					node(the,
						node(largest,
							node(
								node(search,engine),
								node(on,
									node(the,web))))),
					node(app,google)),
				node(
					node(
						node(receives,
							node(over,
								node(
									_200_million,queries))),
						node(each,day)),
					node(through,
						node(its,
							node(various,services)))));

		// Hypothesis part
		val operates = leaf(pair("V_1","operates"));

		val tree2 = 
			node(google,
				node(operates,
					node(on,
						node(the,web))));

		// subsumption rules
		val subsumptionRules = "";

		aPair = new IEntailment(tree1, tree2, subsumptionRules);
	}
}