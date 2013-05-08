package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;



public final class TestCase04 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		System.err.println("tst:4");
		someTest(aPair);
	}

	private Entailment aPair;
	
	@Before
	public final void setUpPair() {

		// a non-entailment case
		
		// Text: 		Jan [is [a [short [Dutch man]]]]
		// Hypothesis: 	Jan [is [a [short man]]]
				
		// build the annotated words.
		val jan		= leaf(pair("NP", "jan"));
		val is		= leaf(pair("IS", "is"));
		val a		= leaf(pair("A", "a"));
		val short_a	= leaf(pair("MR", "short"));
		val dutch	= leaf(pair("MI", "dutch"));
		val man 	= leaf(pair("N", "man"));
		
		// build the annotation trees.
		val tree1	= node(jan,node(is,node(a,node(short_a,node(dutch,man)))));
		val tree2	= node(jan,node(is,node(a,node(short_a,man))));
		
		// subsumption relations
		val subs = "all x (short_dutch_man(x) -> short_man(x)).";
		
		aPair = new IEntailment(tree1, tree2, subs);		
	}

}
