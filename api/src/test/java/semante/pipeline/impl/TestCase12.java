package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public class TestCase12 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}
	
	private Entailment aPair;
	
	@Before
	public final void setUpPair() {
		// text: 		The head of the Italian opposition, Romano Prodi, was the last president of the European Commission.
		// hypothesis: 	Romano Prodi is a former president of the European Commission.
		
		// TEXT:
		val the  		= leaf(pair("THE", "the"));
		val head 		= leaf(pair("N", "head"));
		val of	 		= leaf(pair("P_R", "of"));
		//  the
		val italian    	= leaf(pair("MR", "Italian"));
		val opposition 	= leaf(pair("N", "opposition"));
		//  ,
		val who			= leaf(pair("WHO_A", "APP"));
		//  is
		val romano	   	= leaf(pair("NP", "Romano"));
		val prodi	   	= leaf(pair("NPC2", "Prodi"));
		//  ,
		val was 		= leaf(pair("IS", "was"));
		//  the
		val last		= leaf(pair("MR", "last"));
		val president	= leaf(pair("N", "president"));
		//  of
		//  the
		val european	= leaf(pair("MR", "European"));
		val commission	= leaf(pair("N", "Commission"));

		// HYPOTHESIS:
		// 	romano
		// 	prodi
		val is			= leaf(pair("IS", "is"));
		val a			= leaf(pair("A", "a"));
		val former		= leaf(pair("MR", "former"));
		//	president
		//  of
		//  the
		//  european
		// 	commission
		
		// ANNOTATION:
		val tree1 =
			node(
				node(
					node(the,node(head,node(of,node(the,node(italian,opposition))))),
					node(who,node(romano,prodi))
					),
				node(was,node(the,node(last,president)))
				);

		
		val tree2 = 
			node(
				node(romano,prodi),
				node(is,node(a,node(former,president)))
				);
		
		aPair = new IEntailment(tree1, tree2, "");
	}

}
