package semante.pipeline.test.plurals;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase16 extends AbsPipelineTest {

		@Test
		public final void TestCase16t() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John");
			val t01_s = word("POSS","s");
			val t02_boyfriend = word("N","boyfriend");
			val t03_and = word("AND","and");
			val t04_mary = word("NP_D","Mary");
			val t05_s = word("POSS","s");
			val t06_girlfriend = word("N","girlfriend");
			val t07_app = word("WHO_A","APP");
			val t08_bill = word("NP_D","Bill");
			val t09_and = word("AND","and");
			val t10_sue = word("NP_D","Sue");
			val t11_are = word("IS","are");
			val t12_nice = word("MR","nice");

			// create the vocabulary for the hypothesis;
			val h00_bill = word("NP_D","Bill");
			val h01_is = word("IS","is");
			val h02_sue = word("NP_D","Sue");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							_(
								t00_john
								,
								t01_s
							)
							,
							t02_boyfriend
						)
						,
						_(
							t03_and
							,
							_(
								_(
									t04_mary
									,
									t05_s
								)
								,
								t06_girlfriend
							)
						)
					)
					,
					_(
						t07_app
						,
						_(
							_(
								t08_bill
								,
								t09_and
							)
							,
							t10_sue
						)
					)
				)
				,
				_(
					t11_are
					,
					t12_nice
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_bill
				,
				_(
					h01_is
					,
					h02_sue
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
