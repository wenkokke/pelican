package semante.pipeline.test.plurals;

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
			val t00_john = word("NP_D","John");
			val t01_and = word("AND","and");
			val t02_mary = word("NP_D","Mary");
			val t03_s = word("GEN","s");
			val t04_friends = word("N","friends");
			val t05_are = word("IS","are");
			val t06_bill = word("NP_D","Bill");
			val t07_and = word("AND","and");
			val t08_sue = word("NP_D","Sue");

			// create the vocabulary for the hypothesis;
			val h00_sue = word("NP_D","Sue");
			val h01_is = word("IS","is");
			val h02_bill = word("NP_D","Bill");
			val h03_and = word("AND","and");
			val h04_sue = word("NP_D","Sue");
			val h05_is = word("IS","is");
			val h06_john = word("NP_D","John");
			
			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_john
					,
					_(
						t01_and
						,
						_(
							_(
								t02_mary
								,
								t03_s
							)
							,
							t04_friends
						)
					)
				)
				,
				_(
					t05_are
					,
					_(
						t06_bill
						,
						_(
							t07_and
							,
							t08_sue
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_sue
					,
					_(
						h01_is
						,
						h02_bill
					)
				)
				,
				_(
					h03_and
					,
					_(
						h04_sue
						,
						_(
							h05_is
							,
							h06_john
						)
					)
				)
			)
			;
			
			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertException(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
