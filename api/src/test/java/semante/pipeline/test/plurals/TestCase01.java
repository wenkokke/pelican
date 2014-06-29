package semante.pipeline.test.plurals;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase01 extends AbsPipelineTest {

		@Test
		public final void TestCase01t() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John");
			val t01_and = word("AND","and");
			val t02_mary = word("NP_D","Mary");
			val t03_ate = word("V_2","ate");
			val t04_a = word("A","a");
			val t05_sandwich = word("N","sandwich");

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John");
			val h01_ate = word("V_2","ate");
			val h02_a = word("A","a");
			val h03_sandwich = word("N","sandwich");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_john
					,
					_(
						t01_and
						,
						t02_mary
					)
				)
				,
				_(
					t03_ate
					,
					_(
						t04_a
						,
						t05_sandwich
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_john
				,
				_(
					h01_ate
					,
					_(
						h02_a
						,
						h03_sandwich
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
