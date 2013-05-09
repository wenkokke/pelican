package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;

public class TestCaseRTE2test750 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;

	@Before
	public final void setUpPair() {

		// Dataset=RTE2test, id=750
		// Text=The Zulu are an African ethnic group of about 11 million people who live mainly in KwaZulu-Natal Province, South Africa.
		// Hypothesis=The Zulus live in Kwazulu-Natal Province.
		// Entailment=True

		// Text part
		val the 			= leaf(pair("THE","the"));
		val zulu 			= leaf(pair("N","zulu"));
		val are 			= leaf(pair("IS","are"));
		val an 				= leaf(pair("A","an"));
		val african 		= leaf(pair("MR","african"));
		val ethnic 			= leaf(pair("MR","ethnic"));
		val group 			= leaf(pair("N","group"));
		val of 				= leaf(pair("P_R","of"));
		val about 			= leaf(pair("MR","about"));
		val _11_million 	= leaf(pair("NUMBER","11 million"));
		val people 			= leaf(pair("N","people"));
		val who 			= leaf(pair("WHO_R","who"));
		val live 			= leaf(pair("V_1","live"));
		val mainly 			= leaf(pair("MR","mainly"));
		val in 				= leaf(pair("P_R","in"));
		val kwazulu_natal 	= leaf(pair("MR","kwazulu natal"));
		val province 		= leaf(pair("NP","province"));
		val south 			= leaf(pair("MR","south"));
		val africa 			= leaf(pair("NP","africa"));

		val tree1 = 
			node(
				node(the,
					node(
						zulu,
						node(who,
							node(
								live,
								node(in,
									node(
										node(kwazulu_natal,province),
										node(of,node(south,africa))
									)
								)
							)
						)
					)
				),
				node(are,
					node(
						node(an,
							node(african,
								node(ethnic,group))),
						node(of,
							node(about,
								node(_11_million,people))))
				)
			);

		// Hypothesis part
		val zulus = leaf(pair("NP","zulus"));
		
		val tree2 = 
			node(
				zulus,
				node(live,
					node(in,
						node(kwazulu_natal,province))));

		// subsumption rules
		val subsumptionRules = "all x ((zulu(x) & live(x)) -> live(zulus)).";

		aPair = new IEntailment(tree1, tree2, subsumptionRules);
	}
}
