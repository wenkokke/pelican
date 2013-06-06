package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public final class TestCase08 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		System.err.println("tst:8");
		someTest(aPair);
	}

	private Entailment aPair;
	
	@Before
	public final void setUpPair() {
		
		// text:		[Jan [is [a [tall [black [fat [Dutch man]]]]]]]
		// hypothesis:	[Jan [is [a [black man]]]]
		
		// build the annotated words.
		val jan		= leaf(pair("NP", "jan"));
		val is		= leaf(pair("IS", "is"));
		val a		= leaf(pair("A", "a"));
		val tall	= leaf(pair("MOD_R", "tall"));
		val black	= leaf(pair("MOD_I", "black"));
		val fat		= leaf(pair("MOD_R", "fat"));
		val dutch	= leaf(pair("MOD_I", "dutch"));
		val man 	= leaf(pair("N_1", "man"));
			
		// build the annotation trees.
		val tree1	= 	node(jan,node(is,node(a,node(tall,node(black,node(fat,node(dutch,man)))))));	
		val tree2	= 	node(jan,node(is,node(a,node(black,man))));
		
		aPair = new IEntailment(tree1, tree2);		
	}
}
