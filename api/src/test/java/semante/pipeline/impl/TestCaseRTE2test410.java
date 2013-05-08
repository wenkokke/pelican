package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;


public class TestCaseRTE2test410 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		System.err.println("tst:rte2:410");
		someTest(aPair);
	}
	
	private Entailment aPair;
	
	@Before
	public final void setUpPair() {
		// text: 		The head of the Italian opposition, Romano Prodi, was the last president of the European Commission.
		// hypothesis: 	Romano Prodi is a former president of the European Commission.
		
		// TEXT:
		val the  			= leaf(pair("THE", "the"));
		val head 			= leaf(pair("N", "head"));
		val of	 			= leaf(pair("P_R", "of"));
		//  the
		val italian    		= leaf(pair("MR", "Italian"));
		val opposition 		= leaf(pair("N", "opposition"));
		//  ,
		val who				= leaf(pair("WHO_A", "APP"));
		//  is
		val romano_prodi  	= leaf(pair("NP", "Romano Prodi"));
		//  ,
		val was		 		= leaf(pair("IS", "was"));
		//  the
		val last			= leaf(pair("MR", "last"));
		val president		= leaf(pair("N", "president"));
		//  of
		//  the
		val european		= leaf(pair("MR", "European"));
		val commission		= leaf(pair("N", "Commission"));

		// HYPOTHESIS:
		// 	romano
		// 	prodi
		val is				= leaf(pair("IS", "is"));
		val a				= leaf(pair("A", "a"));
		val former			= leaf(pair("MR", "former"));
		//	president
		//  of
		//  the
		//  european
		// 	commission
		
		// ANNOTATION:
		val tree1 =
			node
			(	node
				(	node
					(	node(the,head)
					,	node(of,node(the,node(italian,opposition)))
					)
				,	node(who,romano_prodi)
				)
			,	node
				(	was
				,	node
					( 	node(the,node(last,president))
					, 	node(of,node(the,node(european,commission)))
					)
				)
			);

		
		val tree2 = 
			node
			(	romano_prodi
			, 	node
				(	is
				,	node
					( 	node(a,node(former,president))	
					,	node(of,node(the,node(european,commission)))
					)
				)
			);
		
		val subs = "all x (last_president(x) -> former_president(x)).";
		
		aPair = new IEntailment(tree1, tree2, subs);
	}

}
