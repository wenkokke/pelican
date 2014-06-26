package semante.pipeline.test.rte.shared1.pass;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0028 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0028() throws Exception {

			// create the vocabulary for the text;
			val t00_a = word("A","A");
			val t01_cuban = word("MI","Cuban");
			val t02_american = word("N","American");
			val t03_who = word("WHO_A","who");
			val t04_is = word("V_AUX","is");
			val t05_accused = word("V_1","accused");
			val t06_of = word("P_R","of");
			val t07_espionage = word("NP_D","espionage");
			val t08_pleads = word("V_1","pleads");
			val t09_innocent = word("MR","innocent");

			// create the vocabulary for the hypothesis;
			val h00_det = word("A","DET");
			val h01_american = word("N","American");
			val h02_accused = word("V_1","accused");
			val h03_of = word("P_R","of");
			val h04_espionage = word("NP_D","espionage");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_a
						,
						_(
							t01_cuban
							,
							t02_american
						)
					)
					,
					_(
						t03_who
						,
						_(
							_(
								t04_is
								,
								t05_accused
							)
							,
							_(
								t06_of
								,
								t07_espionage
							)
						)
					)
				)
				,
				_(
					t08_pleads
					,
					t09_innocent
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_det
					,
					h01_american
				)
				,
				_(
					h02_accused
					,
					_(
						h03_of
						,
						h04_espionage
					)
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

