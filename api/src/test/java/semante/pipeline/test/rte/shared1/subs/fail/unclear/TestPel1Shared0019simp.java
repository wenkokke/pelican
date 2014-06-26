package semante.pipeline.test.rte.shared1.subs.fail.unclear;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0019simp extends AbsPipelineTest {

		@Test
		public final void TestCaseCustom_AT37a() throws Exception {

			// create the vocabulary for the text;
			val t00_javier = word("$NPC_1$","Javier");
			val t01_solana = word("NP_D","Solana");
			val t02_who = word("WHO_A","who");
			val t03_serves_as = word("V_2","serves_as");
			val t04_nato = word("NP_D","NATO");
			val t05_s = word("GEN","s");
			val t06_general = word("MR","general");
			val t07_secretary = word("N","secretary");
			val t08_smiled = word("V_1","smiled");

			// create the vocabulary for the hypothesis;
			val h00_javier = word("$NPC_1$","Javier");
			val h01_solana = word("NP_D","Solana");
			val h02_is = word("IS","is");
			val h03_nato = word("NP_D","NATO");
			val h04_s = word("GEN","s");
			val h05_general = word("MR","general");
			val h06_secretary = word("N","secretary");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_javier
						,
						t01_solana
					)
					,
					_(
						t02_who
						,
						_(
							t03_serves_as
							,
							_(
								_(
									t04_nato
									,
									t05_s
								)
								,
								_(
									t06_general
									,
									t07_secretary
								)
							)
						)
					)
				)
				,
				t08_smiled
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_javier
					,
					h01_solana
				)
				,
				_(
					h02_is
					,
					_(
						_(
							h03_nato
							,
							h04_s
						)
						,
						_(
							h05_general
							,
							h06_secretary
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_serves_as = word("V_2","serves_as");
			val sh000_is = word("IS","is");
val st0 = 
			st000_serves_as
			;
val sh0 = 
			sh000_is
			;
val ss0 = subs(st0, sh0);
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
			ss0
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
