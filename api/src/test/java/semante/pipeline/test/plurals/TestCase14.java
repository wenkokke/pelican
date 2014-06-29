package semante.pipeline.test.plurals;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase14 extends AbsPipelineTest {

		@Test
		public final void TestCase14t() throws Exception {

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
			val h00_john = word("NP_D","John");
			val h01_and = word("AND","and");
			val h02_mary = word("NP_D","Mary");
			val h03_s = word("GEN","s");
			val h04_friends = word("N","friends");
			val h05_are = word("IS","are");
			val h06_bill = word("NP_D","Bill");
			val h07_and = word("AND","and");
			val h08_sue = word("NP_D","Sue");

			// create the tree structure for the text;
			val tt =
			_(
				_(
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
						t03_s
					)
					,
					t04_friends
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
					_(
						_(
							h00_john
							,
							_(
								h01_and
								,
								h02_mary
							)
						)
						,
						h03_s
					)
					,
					h04_friends
				)
				,
				_(
					h05_are
					,
					_(
						h06_bill
						,
						_(
							h07_and
							,
							h08_sue
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
			testTestCaseCreator(tt, th, Exception, subs);
		}

}
