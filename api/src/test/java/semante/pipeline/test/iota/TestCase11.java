package semante.pipeline.test.iota;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase11 extends AbsPipelineTest {

		@Test
		public final void TestCase11() throws Exception {

			// create the vocabulary for the text;
			val t00_bowie = word("NP_D","Bowie");
			val t01_has = word("V_2","has");
			val t02_a = word("A","a");
			val t03_new = word("MR","new");
			val t04_song = word("N","song");

			// create the vocabulary for the hypothesis;
			val h00_bowie = word("NP_D","Bowie");
			val h01_who = word("WHO_A","who");
			val h02_wrote = word("V_1","wrote");
			val h03_with = word("P_R","with");
			val h04_lou = word("NP_D","Lou");
			val h05_has = word("V_2","has");
			val h06_a = word("A","a");
			val h07_new = word("MR","new");
			val h08_song = word("N","song");

			// create the tree structure for the text;
			val tt =
			_(
				t00_bowie
				,
				_(
					t01_has
					,
					_(
						t02_a
						,
						_(
							t03_new
							,
							t04_song
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_bowie
					,
					_(
						h01_who
						,
						_(
							h02_wrote
							,
							_(
								h03_with
								,
								h04_lou
							)
						)
					)
				)
				,
				_(
					h05_has
					,
					_(
						h06_a
						,
						_(
							h07_new
							,
							h08_song
						)
					)
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertNoProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, NoProof, subs);
		}

}
