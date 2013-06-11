package semante.pipeline.impl;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Joiner;

import semante.pipeline.Entailment;


public class TestCaseRTE2test410 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		System.err.println("tst:rte2:410");
		proveEntailment(aPair);
	}
	
	private Entailment aPair;
	
	@Before
	public final void setUpPair() {
		
		// text: 		The head of the Italian opposition, Romano Prodi, was the last president of the European Commission.
		// hypothesis: 	Romano Prodi is a former president of the European Commission.
		
		// TEXT:
    val the         = word("THE", "the");
    val head        = word("N_1", "head");
    val of          = word("P_R", "of");
    //  the
    val italian     = word("MOD_R", "Italian");
    val opposition  = word("N_1", "opposition");
    //  ,
    val who         = word("WHO_A", "APP");
    //  is
    val romano_prodi= word("NP", "Romano Prodi");
    //  ,
    val was         = word("IS", "was");
    //  the
    val last        = word("MOD_R", "last");
    val president   = word("N_1", "president");
    //  of
    //  the
    val european    = word("MOD_R", "European");
    val commission  = word("N_1", "Commission");

    // HYPOTHESIS:
    //  romano
    //  prodi
    val is          = word("IS", "is");
    val a           = word("A", "a");
    val former      = word("MOD_R", "former");
		//	president
		//  of
		//  the
		//  european
		// 	commission
		
		// ANNOTATION:
		val np =
			_(
				_(the,_(head,_(of,_(the,_(italian,opposition))))),
				_(who,romano_prodi)
			);
		val vp =
			_(was,_(the,_(last,_(president,_(of,_(the,_(european,commission)))))));
		val t1 =
			_(np,vp);
		
		val t2 = 
			_(romano_prodi,_(is,_(a,_(former,_(president,_(of,_(the,_(european,commission))))))));
			
		
		val subs = new String[] {
			"all x (last_president(x) -> former_president(x)).",
			"all x (all y (last_of_president(x,y) -> former_of_president(x,y)))."
		};
		
		aPair = new IEntailment(t1, t2, Joiner.on('\n').join(subs));
	}

}
