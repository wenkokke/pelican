package semante.pipeline.test.iota;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase02 extends AbsPipelineTest {

		@Test
		public final void TestCase02() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John");
			val t01_kissed = word("V_2","kissed");
			val t02_the = word("THE","the");
			val t03_tall = word("MR","tall");
			val t04_boy = word("N","boy");

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John");
			val h01_kissed = word("V_2","kissed");
			val h02_the = word("THE","the");
			val h03_boy = word("N","boy");

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					t01_kissed
					,
					_(
						t02_the
						,
						_(
							t03_tall
							,
							t04_boy
						)
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
					h01_kissed
					,
					_(
						h02_the
						,
						h03_boy
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
