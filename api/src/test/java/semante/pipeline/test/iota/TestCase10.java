package semante.pipeline.test.iota;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase10 extends AbsPipelineTest {

		@Test
		public final void TestCase10() throws Exception {

			// create the vocabulary for the text;
			val t00_a = word("A","A");
			val t01_tall = word("MR","tall");
			val t02_boy = word("N","boy");
			val t03_app = word("WHO_A","APP");
			val t04_john = word("NP_D","John");
			val t05_smiled = word("V_1","smiled");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","The");
			val h01_boy = word("N","boy");
			val h02_app = word("WHO_A","APP");
			val h03_john = word("NP_D","John");
			val h04_smiled = word("V_1","smiled");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_a
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
						h00_the
						,
						h01_boy
					)
					,
					_(
						h02_app
						,
						h03_john
					)
				)
				,
				h04_smiled
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
