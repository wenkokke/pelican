package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;

public class TestCaseRTE2test84 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;

	@Before
	public final void setUpPair() {

		// Dataset=RTE2test, id=84
		// Text=salvadoran reporter mauricio pineda, a sound technician for the local canal Doce television station, was shot and killed today in morazan department in the eastern part of the country.
		// Hypothesis=mauricio pineda was killed in morazan.
		// Entailment=True

		// Text part
		val salvadoran = leaf(pair("MR","salvadoran"));
		val reporter = leaf(pair("N","reporter"));
		val mauricio_pineda = leaf(pair("NP","mauricio pineda"));
		val a = leaf(pair("A","a"));
		val sound = leaf(pair("MR","sound"));
		val technician = leaf(pair("N","technician"));
		val _for = leaf(pair("P_R","for"));
		val the = leaf(pair("THE","the"));
		val local = leaf(pair("MR","local"));
		val canal = leaf(pair("N","canal"));
		val Doce = leaf(pair("NP","Doce"));
		val television = leaf(pair("N","television"));
		val station = leaf(pair("N","station"));
		val was = leaf(pair("IS","was"));
		val shot = leaf(pair("V_1","shot"));
		val and = leaf(pair("AND","and"));
		val killed = leaf(pair("V_1","killed"));
		val today = leaf(pair("N","today"));
		val in = leaf(pair("P_R","in"));
		val morazan = leaf(pair("NP","morazan"));
		val department = leaf(pair("N","department"));
		val eastern = leaf(pair("MR","eastern"));
		val part = leaf(pair("N","part"));
		val of = leaf(pair("P_R","of"));
		val country = leaf(pair("N","country"));

		val tree1 = 
			node(
				node(
					node(salvadoran,
						node(reporter,
							mauricio_pineda)),
					node(
						node(
							node(a,
								node(sound,technician)),
							node(_for,
								node(the,
									node(local,canal)))),
						node(Doce,
							node(television,station)))),
				node(was,
					node(
						node(
							node(
								node(shot,and),killed),
							node(today,
								node(in,
									node(morazan,department)))),
						node(in,
							node(
								node(the,
									node(eastern,part)),
								node(of,
									node(the,country)))))));

		// Hypothesis part

		val tree2 = 
			node(
				mauricio_pineda,
				node(was,
					node(killed,
						node(in,morazan))));

		// subsumption rules
		val subsumptionRules = "";

		aPair = new IEntailment(tree1, tree2, subsumptionRules);
	}
}

