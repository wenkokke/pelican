package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public final class TestCase09 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		System.err.println("tst:9");
		someTest(aPair);
	}

	private Entailment aPair;
	
	@Before
	public final void setUpPair() {
	
		// text:		[[Jan [found [the book]]] [and [[every book] [is blue]]]]
		// hypothesis:	[Jan [found [the [blue block]]]]

		// build the annotated words.
		val jan		= leaf(pair("NP", "jan"));
		val found	= leaf(pair("V_2", "found"));
		val the		= leaf(pair("THE", "the"));
		val book 	= leaf(pair("N", "book"));
		val and		= leaf(pair("AND", "and"));
		val every	= leaf(pair("EVERY", "every"));
		val is		= leaf(pair("IS", "is"));
		val blue	= leaf(pair("MI", "blue"));
			
		// build the annotation trees.
		val tree1	= 	node(node(jan,node(found,node(the,book))),node(and,node(node(every,book),node(is,blue))));	
		val tree2	= 	node(jan,node(found,node(the,node(blue,book))));
		
		aPair = new IEntailment(tree1, tree2);		
	}		

}
