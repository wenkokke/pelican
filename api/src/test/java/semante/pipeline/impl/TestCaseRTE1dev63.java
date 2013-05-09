package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;

public class TestCaseRTE1dev63 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;

	@Before
	public final void setUpPair() {

		// Dataset=RTE1dev, id=63
		// Text=iran will soon release eight british servicemen detained along with three vessels
		// Hypothesis=british servicemen detained
		// Entailment=True

		// Text part
		val iran = leaf(pair("NP","iran"));
		val will = leaf(pair("V_AUX","will"));
		val soon = leaf(pair("MR","soon"));
		val release = leaf(pair("V_2","release"));
		val eight = leaf(pair("NUMBER","eight"));
		val british = leaf(pair("MR","british"));
		val servicemen = leaf(pair("N","servicemen"));
		val who = leaf(pair("WHO_R","who"));
		val were = leaf(pair("V_AUX","were"));
		val detained = leaf(pair("V_1","detained"));
		val along_with = leaf(pair("P_R","along with"));
		val three = leaf(pair("NUMBER","three"));
		val vessels = leaf(pair("N","vessels"));
		
		// TODO restrictive modification of verbs seems to be a painfull issue

		val tree1 = 
			node(iran,
				node(will,
//					node(soon,
				node(release,
					node(eight,
						node(
							node(british,servicemen),
							node(who,node(were,
								node(detained,
								node(along_with,node(three,vessels)))))
						)
					)
				)
//					)
				)
			);

		// Hypothesis part
		val some = leaf(pair("SOME","some"));
		
		val tree2 = 
			node(node(some,node(british,servicemen)),node(were,detained));

		// subsumption rules
		val subsumptionRules = "";

		aPair = new IEntailment(tree1, tree2, subsumptionRules);
	}
}

