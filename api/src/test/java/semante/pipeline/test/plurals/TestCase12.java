package semante.pipeline.test.plurals;

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
			val t00_the = word("THE","The");
			val t01_doctors = word("N","doctors");
			val t02_app = word("WHO_A","APP");
			val t03_john = word("NP_D","John");
			val t04_and = word("AND","and");
			val t05_mary = word("NP_D","Mary");
			val t06_received = word("V_2","received");
			val t07_the = word("THE","the");
			val t08_nobel = word("MR","Nobel");
			val t09_prize = word("N","prize");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","The");
			val h01_doctors = word("N","doctors");
			val h02_app = word("WHO_A","APP");
			val h03_john = word("NP_D","John");
			val h04_and = word("AND","and");
			val h05_mary = word("NP_D","Mary");
			val h06_received = word("V_2","received");
			val h07_the = word("THE","the");
			val h08_nobel = word("MR","Nobel");
			val h09_prize = word("N","prize");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						t01_doctors
					)
					,
					_(
						t02_app
						,
						_(
							t03_john
							,
							_(
								t04_and
								,
								t05_mary
							)
						)
					)
				)
				,
				_(
					t06_received
					,
					_(
						t07_the
						,
						_(
							t08_nobel
							,
							t09_prize
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
						h00_the
						,
						h01_doctors
					)
					,
					_(
						h02_app
						,
						_(
							h03_john
							,
							_(
								h04_and
								,
								h05_mary
							)
						)
					)
				)
				,
				_(
					h06_received
					,
					_(
						h07_the
						,
						_(
							h08_nobel
							,
							h09_prize
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
