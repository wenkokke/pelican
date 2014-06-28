package semante.pipeline.test.plurals;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase04 extends AbsPipelineTest {

		@Test
		public final void TestCase04() throws Exception {

			// create the vocabulary for the text;
			val t00_bill = word("NP_D","Bill");
			val t01_kissed = word("V_2","kissed");
			val t02_john = word("NP_D","John");
			val t03_and = word("AND","and");
			val t04_mary = word("NP_D","Mary");

			// create the vocabulary for the hypothesis;
			val h00_bill = word("NP_D","Bill");
			val h01_kissed = word("V_2","kissed");
			val h02_mary = word("NP_D","Mary");

			// create the tree structure for the text;
			val tt =
			_(
				t00_bill
				,
				_(
					t01_kissed
					,
					_(
						t02_john
						,
						_(
							t03_and
							,
							t04_mary
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_bill
				,
				_(
					h01_kissed
					,
					h02_mary
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
