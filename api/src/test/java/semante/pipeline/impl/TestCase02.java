package semante.pipeline.impl;

import static semante.util.impl.IPair.pair;
import static semante.util.impl.ISimpleBinaryTree.leaf;
import static semante.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public final class TestCase02 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;
	
	@Before
	public final void setUpPair() {

		// Text: 		Jan [is [a [black [Dutch man]]]]
		// Hypothesis: 	Jan [is [a [black man]]]
				
		// build the annotated words.
		val jan		= leaf(pair("NP", "jan"));
		val is		= leaf(pair("IS", "is"));
		val a		= leaf(pair("A", "a"));
		val black	= leaf(pair("MI", "black"));
		val dutch	= leaf(pair("MI", "dutch"));
		val man 	= leaf(pair("N", "man"));
		
		// build the annotation trees.
		val tree1	= node(jan,node(is,node(a,node(black,node(dutch,man)))));
		val tree2	= node(jan,node(is,node(a,node(black,man))));
		
		aPair = new IEntailment(tree1, tree2);
	}

}
