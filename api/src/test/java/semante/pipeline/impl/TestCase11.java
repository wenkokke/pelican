package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public class TestCase11 extends ATestCase {
	
	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;
	
	@Before
	public final void setUpPair() {

		// Text: 		[[The [head [of [the [Italian opposition]]]]]], [who [is Romano Prodi]],]] [is [the [former [president [of [the [European Commission]]]]]]].
		// Hypothesis: 	[Romano Prodi] [is [a [former [president [of [the [European Commission]]]]]]].

		// build the annotated words.
		val the			= leaf(pair("THE", "the"));
		val head		= leaf(pair("N", "head"));
		val of			= leaf(pair("P_R", "of"));
		val italian		= leaf(pair("MI", "italian"));
		val opposition	= leaf(pair("N", "opposition"));
		val who			= leaf(pair("WHO_A", "who"));
		val is			= leaf(pair("IS", "is"));		
		val a			= leaf(pair("A", "a"));
		val romano_prodi= leaf(pair("NP", "romano prodi"));
		val former		= leaf(pair("MR", "former"));
		val president	= leaf(pair("N", "president"));
		val euro_union	= leaf(pair("NP", "european union"));
				
		// build the annotation trees.
		val t1 		= node(node(the,node(head,node(of,node(the,node(italian,opposition))))),node(who,node(is,romano_prodi)));
		val t2 		= node(is,node(the,node(former,node(president,node(of,node(the,euro_union))))));
		val tree1	= node(t1,t2);		
		val tree2	= node(romano_prodi,node(is,node(a,node(former,node(president,node(of,node(the,euro_union)))))));
		
		aPair = new IEntailment(tree1, tree2);
	}

}
