package semante.pipeline.test.plurals;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase07 extends AbsPipelineTest {

		@Test
		public final void TestCase07() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John");
			val t01_has = word("V_AUX","has");
			val t02_been = word("IS","been");
			val t03_driving = word("V_1","driving");
			val t04_through = word("P_R","through");
			val t05_germany = word("NP_D","Germany");
			val t06_and = word("AND","and");
			val t07_france = word("NP_D","France");

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John");
			val h01_has = word("V_AUX","has");
			val h02_been = word("IS","been");
			val h03_driving = word("V_1","driving");
			val h04_through = word("P_R","through");
			val h05_germany = word("NP_D","Germany");

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					t01_has
					,
					_(
						t02_been
						,
						_(
							t03_driving
							,
							_(
								t04_through
								,
								_(
									t05_germany
									,
									_(
										t06_and
										,
										t07_france
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
				h00_john
				,
				_(
					h01_has
					,
					_(
						h02_been
						,
						_(
							h03_driving
							,
							_(
								h04_through
								,
								h05_germany
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
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
