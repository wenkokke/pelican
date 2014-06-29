package semante.pipeline.test.plurals;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase11 extends AbsPipelineTest {

		@Test
		public final void TestCase11t() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John");
			val t01_and = word("AND","and");
			val t02_mary = word("NP_D","Mary");
			val t03_are = word("IS","are");
			val t04_the = word("THE","the");
			val t05_researchers = word("N","researchers");
			val t06_from = word("P_R","from");
			val t07_pennsylvania = word("NP_D","Pennsylvania");
			val t08_who = word("WHO_A","who");
			val t09_received = word("V_2","received");
			val t10_the = word("THE","the");
			val t11_nobel = word("MR","Nobel");
			val t12_prize = word("N","prize");

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John");
			val h01_and = word("AND","and");
			val h02_mary = word("NP_D","Mary");
			val h03_are = word("IS","are");
			val h04_the = word("THE","the");
			val h05_researchers = word("N","researchers");
			val h06_from = word("P_R","from");
			val h07_pennsylvania = word("NP_D","Pennsylvania");
			val h08_who = word("WHO_A","who");
			val h09_received = word("V_2","received");
			val h10_the = word("THE","the");
			val h11_nobel = word("MR","Nobel");
			val h12_prize = word("N","prize");

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
					t03_are
					,
					_(
						_(
							t04_the
							,
							_(
								t05_researchers
								,
								_(
									t06_from
									,
									t07_pennsylvania
								)
							)
						)
						,
						_(
							t08_who
							,
							_(
								t09_received
								,
								_(
									t10_the
									,
									_(
										t11_nobel
										,
										t12_prize
									)
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
				_(
					h03_are
					,
					_(
						_(
							h04_the
							,
							_(
								h05_researchers
								,
								_(
									h06_from
									,
									h07_pennsylvania
								)
							)
						)
						,
						_(
							h08_who
							,
							_(
								h09_received
								,
								_(
									h10_the
									,
									_(
										h11_nobel
										,
										h12_prize
									)
								)
							)
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
