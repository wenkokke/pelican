package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;
import semante.pipeline.Entailment;

public class TestCaseRTE1dev121 extends ATestCase {

//	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;

//	@Before
	public final void setUpPair() {

		// Dataset=RTE1dev, id=121
		// Text=A Syrian-american airman accused of espionage complained about prisoner treatment at the US base in guantanamo, cuba.
		// Hypothesis=american accused of espionage
		// Entailment=True
		
		// TODO cannot deal with the inference syrian_american_airman(x) -> american(x). 

		// Text part
		val a = leaf(pair("A","a"));
		val syrian = leaf(pair("MR","syrian"));
		val airman = leaf(pair("N","airman"));
		val accused = leaf(pair("V_1","accused"));
		val of = leaf(pair("P_R","of"));
		val espionage = leaf(pair("N","espionage"));
		val complained = leaf(pair("V_1","complained"));
		val about = leaf(pair("P_R","about"));
		val prisoner = leaf(pair("N","prisoner"));
		val treatment = leaf(pair("N","treatment"));
		val at = leaf(pair("P_R","at"));
		val the = leaf(pair("THE","the"));
		val us = leaf(pair("NP","us"));
		val base = leaf(pair("N","base"));
		val in = leaf(pair("P_R","in"));
		val guantanamo = leaf(pair("NP","guantanamo"));
		val cuba = leaf(pair("NP","cuba"));

		val tree1 = 
			node(
				node(
					node(a,
						node(syrian,airman)),
					node(accused,
						node(of,espionage))),
				node(
					node(complained,
						node(about,
							node(prisoner,treatment))),
					node(at,
						node(
							node(the,
								node(us,base)),
							node(in,
								node(guantanamo,
                  node(in,cuba)))))));

		// Hypothesis part
		val american = leaf(pair("MR","american"));

		val tree2 = 
			node(american,
				node(accused,
					node(of,espionage)));

		// subsumption rules
		val subsumptionRules = "";

		aPair = new IEntailment(tree1, tree2, subsumptionRules);
	}
}