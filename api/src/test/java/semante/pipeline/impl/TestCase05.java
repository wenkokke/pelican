package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public final class TestCase05 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;
	
	@Before
	public final void setUpPair() {

		// Text: 		Jan [is [a [short [Dutch man]]]]
		// Hypothesis: 	Jan [is [a [Dutch man]]]
				
		// build the annotated words.
		val jan		= leaf(pair("NP", "jan"));
		val is		= leaf(pair("IS", "is"));
		val a		= leaf(pair("A", "a"));
		val short_a	= leaf(pair("MR", "short"));
		val dutch	= leaf(pair("MI", "dutch"));
		val man 	= leaf(pair("N", "man"));
		
		// build the annotation trees.
		val tree1	= node(jan,node(is,node(a,node(short_a,node(dutch,man)))));
		val tree2	= node(jan,node(is,node(a,node(dutch,man))));
		
		aPair = new IEntailment(tree1, tree2);		
	}
}
