package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public final class TestCase01 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		System.err.println("tst:1");
		someTest(aPair);
	}

	private Entailment aPair;
	
	@Before
	public final void setUpPair() {
		// Text: 		Jan [sat [and ate]]
		// Hypothesis: 	Jan ate
				
		// build the annotated words.
		val jan		= leaf(pair("NP", "jan"));
		val sat		= leaf(pair("V_1","sat"));
		val and		= leaf(pair("AND", "and"));
		val ate		= leaf(pair("V_1","ate"));
				
		// build the annotation trees.
		val tree1	= node(jan,node(sat,node(and,ate)));
		val tree2	= node(jan,ate);
			
		aPair = new IEntailment(tree1, tree2);
	}
}
