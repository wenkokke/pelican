package semante.pipeline.impl;

import static semante.pipeline.util.impl.IPair.pair;
import static semante.pipeline.util.impl.ISimpleBinaryTree.leaf;
import static semante.pipeline.util.impl.ISimpleBinaryTree.node;
import lombok.val;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.Entailment;
import semante.pipeline.util.Pair;
import semante.pipeline.util.SimpleBinaryTree;

public class TestCaseRTE4test955 extends ATestCase {

	@Test
	public final void testEverything() throws Exception {
		someTest(aPair);
	}

	private Entailment aPair;

	@Before
	public final void setUpPair() {

		// Text part
		val the              = word("THE"    , "The");
		val largest          = word("MOD_R"  , "largest");
		val search           = word("MOD_R"  , "search");
		val engine           = word("N"      , "engine");
		val on               = word("P_R"    , "on");
		val web              = word("N"      , "web");
		val which            = word("WHO_A"  , "which");
		val is               = word("IS"     , "is");
		val google           = word("NP"     , "google");
		val receives         = word("V_2"    , "receives");
		val over_200_million = word("NUMBER" , "over 200 million");
		val queries          = word("N"      , "queries");
		val each_day         = word("MOD_R"  , "each_day");
		val through          = word("P_R"    , "through");
		val its              = word("POSS"   , "its");
		val various          = word("MOD_R"  , "various");
		val services         = word("N"      , "services");

		val np =
			_(
				_(the,
					_(largest,
						_(
							_(search,engine),
							_(on,_(the,web))
						)
					)
				),
				_(which,_(is,google))
			);
		val vp =
			_(
//				_(
//					_(
						receives,
						_(over_200_million,queries)
//					),
//					each_day
//				),
//				_(through,_(its,_(various,services)))
			);
		val t1 = _(np,vp);

		// Hypothesis part
		val operates = word("V_1","operates");

		val t2 = 
			_(google,_(operates,_(on,_(the,web))));

		// subsumption rules
		val sr1 = "all x (all y (on_operates(x,y) -> operates(x))).";
		val sr2 = "all x (all y ((web(y) & on_search_engine_engine(x,y)) -> on_operates(x,y))).";

		aPair = new IEntailment(t1, t2, sr1+'\n'+sr2);
	}
}