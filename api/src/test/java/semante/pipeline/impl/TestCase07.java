package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public final class TestCase07 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		System.err.println("tst:7");
		someTest(aPair);
	}
	
	private Entailment aPair;
	
	@Before
	public final void setUpPair() {
		
		// text:		[Dan, [who [is kind]],] [[gave Mary] [a [[brand new] book]]]
		// hypothesis:	[[Dan [[gave Mary] [a book]]]
		
		// build the annotated words.
		val dan			= leaf(pair("NP", "dan"));
		val who			= leaf(pair("WHO_A", "who"));
		val is			= leaf(pair("IS", "is"));
		val kind		= leaf(pair("MR", "kind"));
		val gave		= leaf(pair("V_3", "gave"));
		val mary		= leaf(pair("NP", "mary"));	
		val a			= leaf(pair("A", "a"));
		val brand_new	= leaf(pair("MI", "brand new"));
		val book		= leaf(pair("N", "book"));
			
		// build the annotation trees.
		val tree1	= 	node(node(dan,node(who,node(is,kind))),node(node(gave,mary),node(a,node(brand_new,book))));	
		val tree2	= 	node(dan,node(node(gave,mary),node(a,book)));
		
		aPair = new IEntailment(tree1, tree2);		
	}

}
