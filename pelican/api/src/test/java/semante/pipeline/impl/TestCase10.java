package semante.pipeline.impl;

import static semante.util.impl.IPair.pair;
import static semante.util.impl.ISimpleBinaryTree.leaf;
import static semante.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public final class TestCase10 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;
	
	@Before
	public final void setUpPair() {
	
		// a non-entailment case		
	
		// text:		[The [man [who [killed John]]]] [loves [the [woman [who [killed Mary]]]]]
		// hypothesis:	[The man] [loves [the [woman [who [killed Mary]]]]]
		
		// build the annotated words.
		val the		= leaf(pair("THE", "the"));
		val man 	= leaf(pair("N", "man"));
		val woman 	= leaf(pair("N", "woman"));
		val who		= leaf(pair("WHO_R", "who"));
		val killed	= leaf(pair("V_2", "killed"));
		val loves	= leaf(pair("V_2", "loves"));
		val john	= leaf(pair("NP", "john"));
		val mary	= leaf(pair("NP", "mary"));
			
		// build the annotation trees.
		val tree1	= 	node(node(the,node(man,node(who,node(killed,john)))),node(loves,node(the,node(woman,node(who,node(killed,mary))))));	
		val tree2	= 	node(node(the,man),node(loves,node(the,node(woman,node(who,node(killed,mary))))));	
		
		aPair = new IEntailment(tree1, tree2);		
	}		

}

