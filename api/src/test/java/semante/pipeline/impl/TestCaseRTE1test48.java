package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;

public class TestCaseRTE1test48 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		System.err.println("tst:rte2test:410");
		proveEntailment(aPair);
	}

	private Entailment aPair;
	
	@Before
	public final void setUpPair() {
	
	// Text part
	val clinton     = leaf(pair("NP"	, "clinton"));
	val is          = leaf(pair("IS"	, "is"));
	val a           = leaf(pair("A"		, "a"));
	val very        = leaf(pair("MOD_R"	, "very"));
	val charismatic = leaf(pair("MOD_I"	, "charismatic"));
	val person      = leaf(pair("N_1"		, "person"));
	
	val tree1 = 
		node(clinton,
			node(is,
				node(a,
					node(very,
						node(charismatic,person)))));
	
	// Hypothesis part
	//  clinton
	//  is
	val articulate  = leaf(pair("MOD_I"	, "articulate"));
	
	val tree2 =
		node(clinton,
			node(is,articulate));
	
	// subsumption rules
	val subsumptionRules = "all x (charismatic(x) -> articulate(x)).";
	
	aPair = new IEntailment(tree1, tree2, subsumptionRules);
	}
}
