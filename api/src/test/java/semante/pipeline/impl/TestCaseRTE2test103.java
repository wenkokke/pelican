package semante.pipeline.impl;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Joiner;

import semante.pipeline.Entailment;

public class TestCaseRTE2test103 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		proveEntailment(aPair);
	}

	private Entailment aPair;

	@Before
	public final void setUpPair() {

		// Dataset=RTE2test, id=103
		// Text=everest summiter david Hiddleston has passed away in an avalanche of Mt. Tasman.
		// Hypothesis=A person died in an avalanche.
		// Entailment=True

		// Text part
		val an 					= word("A","an");
		val everest 			= word("MOD_R","everest");
		val summiter 			= word("N_1","summiter");
		val who					= word("WHO_A","who");
		val is					= word("IS","is");
		val david_hiddleston 	= word("NP","david hiddleston");
		val has 				= word("V_AUX","has");
		val passed_away			= word("V_1","passed away");
		val in 					= word("P_R","in");
		// an
		val avalanche 			= word("N_1","avalanche");
		val of 					= word("P_R","of");
		val mt_tasman 			= word("NP","mt tasman");
		
		val tree1 =
			_(
				_(
					_(an,_(everest,summiter)),
					_(who,_(is,david_hiddleston))
				),
				_(has,
					_(passed_away,
						_(in,_(an,_(avalanche,_(of,mt_tasman))))
					)
				)
			);

		// Hypothesis part
		val a 		= word("A","a");
		val person 	= word("N_1","person");
		val died 	= word("V_1","died");
		// in
		// an
		// avalanche

		val tree2 = 
			_(_(a,person),_(died,_(in,_(an,avalanche))));

		// subsumption rules
		val subs = new String[] {
			"all x (summiter(x) -> person(x)).",
			"all x (all y (in_died(x,y) -> died(x))).",
			"all x (all y (in_passed_away(x,y) -> in_died(x,y)))."
		};

		aPair = new IEntailment(tree1, tree2, Joiner.on('\n').join(subs));
	}
}