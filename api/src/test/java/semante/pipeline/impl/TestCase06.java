package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public final class TestCase06 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		System.err.println("tst:6");
		someTest(aPair);
	}

	private Entailment aPair;
	
	@Before
	public final void setUpPair() {
	
		// Text: 		[[The [largest [[search engine] [on [the web]]]]], [which [is Google]],] [is popular]
		// Hypothesis: 	Google [is [on [the web]]]
				
		// build the annotated words.
		val the				= leaf(pair("THE", "the"));
		val largest			= leaf(pair("MR", "largest"));
		val search_engine	= leaf(pair("N", "search_engine"));
		val on_the_web		= leaf(pair("MI", "on_the_web"));
		val google			= leaf(pair("NP", "google"));	
		val is				= leaf(pair("IS", "is"));
		val popular			= leaf(pair("MR", "popular"));
		val which			= leaf(pair("WHO_A", "who"));
		
		// build the annotation trees.
		val tree1	= 	node(node(node(the,node(largest,node(search_engine,on_the_web))),node(which,node(is,google))),node(is,popular));
		val tree2	= 	node(google,node(is,on_the_web));
		
		aPair = new IEntailment(tree1, tree2);		
	}
}
