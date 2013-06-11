package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;

public class TestCaseRTE1dev119 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		proveEntailment(aPair);
	}

	private Entailment aPair;

	@Before
	public final void setUpPair() {

		// Dataset=RTE1dev, id=119
		// Text=A cuban american who is accused of espionage pleads innocent.
		// Hypothesis=american accused of espionage
		// Entailment=True

		// Text part
		val a 				= leaf(pair("A","a"));
		val cuban 			= leaf(pair("MOD_R","cuban"));
		val american 		= leaf(pair("N_1","american"));
		val who 			= leaf(pair("WHO_R","who"));
		val is 				= leaf(pair("IS","is"));
		val accused 		= leaf(pair("V_1","accused"));
		val of 				= leaf(pair("P_R","of"));
		val espionage 		= leaf(pair("NP","espionage"));
		val pleads_innocent	= leaf(pair("V_1","pleads innocent"));
		
		val tree1 =
			node(
				node(a,
					node(
						node(cuban,american),
						node(who,node(is,node(accused,node(of,espionage))))
					)
				),
				pleads_innocent
			);

		// Hypothesis part
		val an = leaf(pair("A","an"));
		val is1 = leaf(pair("V_AUX","is"));
		
		// TODO ambiguity problem with "is" as COPULA, APP_EQ, APP_PRED or V_AUX.

		val tree2 =
			node(
				node(an,american),
				node(is1,node(accused,node(of,espionage))));

		// subsumption rules
		val subs = "";

		aPair = new IEntailment(tree1, tree2, subs);
	}
}
