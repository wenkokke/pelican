package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;

public class TestCaseRTE2test750 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		proveEntailment(aPair);
	}

	private Entailment aPair;

	@Before
	public final void setUpPair() {

		// Dataset=RTE2test, id=750
		// Text=The Zulu are an African ethnic group of about 11 million people who live mainly in KwaZulu-Natal Province, South Africa.
		// Hypothesis=The Zulus live in Kwazulu-Natal Province.
		// Entailment=True

		// Text part
		val the 			= leaf(pair("THE","the"));
		val zulu 			= leaf(pair("N_1","zulu"));
		val are 			= leaf(pair("IS","are"));
		val an 				= leaf(pair("A","an"));
		val african 		= leaf(pair("MOD_R","african"));
		val ethnic 			= leaf(pair("MOD_R","ethnic"));
		val group 			= leaf(pair("N_1","group"));
		val of 				= leaf(pair("P_R","of"));
		val about 			= leaf(pair("MOD_R","about"));
		val _11_million 	= leaf(pair("NUMBER","11 million"));
		val people 			= leaf(pair("N_1","people"));
		val who 			= leaf(pair("WHO_R","who"));
		val live 			= leaf(pair("V_1","live"));
		val mainly 			= leaf(pair("MOD_R","mainly"));
		val in 				= leaf(pair("P_R","in"));
		// the
		val kwazulu_natal 	= leaf(pair("MOD_R","kwazulu natal"));
		val province 		= leaf(pair("N_1","province"));
		val south_africa = leaf(pair("NP","south_africa"));

		val tree1 = 
			_(
				_(the,_(zulu,
          _(who,_(live,_(in,_(the,_(_(kwazulu_natal,province),_(of,south_africa)))))))
        ),
				_(are,
          _(an,
            _(
              _(african,_(ethnic,group)),
              _(of,_(about,_(_11_million,people)))
            )
          )
				)
			);

		// Hypothesis part
		val zulus = leaf(pair("NP","zulus"));
		
		val tree2 = _(zulus,_(live,_(in,_(the,_(kwazulu_natal,province)))));

		// subsumption rules
		val subsumptionRules = "all x ((zulu(x) & live(x)) -> live(zulus)).";

		aPair = new IEntailment(tree1, tree2, subsumptionRules);
	}
}
