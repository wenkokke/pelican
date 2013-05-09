package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;

public class TestCaseRTE1dev648 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;

	@Before
	public final void setUpPair() {

		// Dataset=RTE1dev, id=648
		// Text=Yoko Ono, widow of murdered beatles star John Lennon, has plastered the small german town of langenhagen with backsides.
		// Hypothesis=Yoko Ono was John Lennon's wife.
		// Entailment=True

		// Text part
		val yoko_ono 	= leaf(pair("NP","Yoko Ono"));
		val who			= leaf(pair("WHO_A","who"));
		val is			= leaf(pair("IS","is"));
		val widow 		= leaf(pair("N","widow"));
		val of 			= leaf(pair("P_R","of"));
		val murdered 	= leaf(pair("MR","murdered"));
		val beatles 	= leaf(pair("MR","beatles"));
		val star 		= leaf(pair("N","star"));
		val john_lennon = leaf(pair("NP","John Lennon"));
		val has 		= leaf(pair("V_AUX","has"));
		val plastered 	= leaf(pair("V_2","plastered"));
		val the 		= leaf(pair("THE","the"));
		val small 		= leaf(pair("MR","small"));
		val german 		= leaf(pair("MR","german"));
		val town 		= leaf(pair("N","town"));
		val which		= leaf(pair("WHO_A","which"));
		// is
		val langenhagen = leaf(pair("N","langenhagen"));
		val with 		= leaf(pair("P_R","with"));
		val backsides 	= leaf(pair("NP","backsides"));
		
		val tree1 = 
			node(
				node(yoko_ono,
					node(who,
						node(is,node(the,node(widow,
							node(of,
								node(
									node(the,node(murdered,node(beatles,star))),
									node(who,node(is,john_lennon))
								)
							)
						)))
					)
				),
				node(
					node(
						plastered,
						node(
							node(the,node(small,node(german,town))),
							node(which,node(is,langenhagen))
						)
					),
					node(with,backsides)
				)
			);

		// Hypothesis part
		val was = leaf(pair("IS","was"));
		val s = leaf(pair("GEN","'s"));
		val wife = leaf(pair("N","wife"));

		val tree2 = 
			node(yoko_ono,node(was,node(node(john_lennon,s),wife)));

		// subsumption rules
		val subsumptionRules = "";

		aPair = new IEntailment(tree1, tree2, subsumptionRules);
	}
}