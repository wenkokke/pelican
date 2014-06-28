package semante.pipeline.test.iota;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase12 extends AbsPipelineTest {

		@Test
		public final void TestCase12() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John");
			val t01_is = word("IS","is");
			val t02_the = word("THE","the");
			val t03_man = word("N","man");
			val t04_who = word("WHO_R","who");
			val t05_kissed = word("V_2","kissed");
			val t06_sue = word("NP_D","Sue");
			val t07_and = word("AND","and");
			val t08_mary = word("NP_D","Mary");
			val t09_talked = word("V_1","talked");
			val t10_to = word("P_R","to");
			val t11_a = word("A","a");
			val t12_man = word("N","man");

			// create the vocabulary for the hypothesis;
			val h00_mary = word("NP_D","Mary");
			val h01_talked = word("V_1","talked");
			val h02_to = word("P_R","to");
			val h03_the = word("THE","the");
			val h04_man = word("N","man");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_john
					,
					_(
						t01_is
						,
						_(
							t02_the
							,
							_(
								t03_man
								,
								_(
									t04_who
									,
									_(
										t05_kissed
										,
										t06_sue
									)
								)
							)
						)
					)
				)
				,
				_(
					t07_and
					,
					_(
						t08_mary
						,
						_(
							t09_talked
							,
							_(
								t10_to
								,
								_(
									t11_a
									,
									t12_man
								)
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_mary
				,
				_(
					h01_talked
					,
					_(
						h02_to
						,
						_(
							h03_the
							,
							h04_man
						)
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
