package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;

public class TestCaseRTE2test103 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;

	@Before
	public final void setUpPair() {

		// Dataset=RTE2test, id=103
		// Text=everest summiter david Hiddleston has passed away in an avalanche of Mt. Tasman.
		// Hypothesis=A person died in an avalanche.
		// Entailment=True

		// Text part
		val an 					= leaf(pair("A","an"));
		val everest 			= leaf(pair("MR","everest"));
		val summiter 			= leaf(pair("N","summiter"));
		val who					= leaf(pair("WHO_A","who"));
		val is					= leaf(pair("IS","is"));
		val david_hiddleston 	= leaf(pair("NP","david hiddleston"));
		val has 				= leaf(pair("V_AUX","has"));
		val passed_away			= leaf(pair("V_1","passed away"));
		val in 					= leaf(pair("P_R","in"));
		// an
		val avalanche 			= leaf(pair("N","avalanche"));
		val of 					= leaf(pair("P_R","of"));
		val mt_tasman 			= leaf(pair("NP","mt tasman"));
		
		val tree1 =
			node(
				node(
					node(an,node(everest,summiter)),
					node(who,node(is,david_hiddleston))
				),
				node(has,
					node(passed_away,
						node(in,
							node(
								node(an,avalanche),
								node(of,mt_tasman)
							)
						)
					)
				)
			);

		// Hypothesis part
		val a 		= leaf(pair("A","a"));
		val person 	= leaf(pair("N","person"));
		val died 	= leaf(pair("V_1","died"));
		// in
		// an
		// avalanche

		val tree2 = 
			node(
				node(a,person),
				node(died,
				node(in,node(an,avalanche))));

		// subsumption rules
		val subsumptionRules = "";

		aPair = new IEntailment(tree1, tree2, subsumptionRules);
	}
}