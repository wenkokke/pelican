package semante.pipeline.test.iota;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase08 extends AbsPipelineTest {

		@Test
		public final void TestCase08() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_tall = word("MR","tall");
			val t02_boy = word("N","boy");
			val t03_app = word("WHO_A","APP");
			val t04_john = word("NP_D","John");
			val t05_smiled = word("V_1","smiled");

			// create the vocabulary for the hypothesis;
			val h00_a = word("A","A");
			val h01_tall = word("MR","tall");
			val h02_boy = word("N","boy");
			val h03_app = word("WHO_A","APP");
			val h04_john = word("NP_D","John");
			val h05_smiled = word("V_1","smiled");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_tall
							,
							t02_boy
						)
					)
					,
					_(
						t03_app
						,
						t04_john
					)
				)
				,
				t05_smiled
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h00_a
						,
						_(
							h01_tall
							,
							h02_boy
						)
					)
					,
					_(
						h03_app
						,
						h04_john
					)
				)
				,
				h05_smiled
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
