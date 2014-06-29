package semante.pipeline.test.plurals;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase06 extends AbsPipelineTest {

		@Test
		public final void TestCase06t() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John");
			val t01_walked = word("V_1","walked");
			val t02_in = word("P_R","in");
			val t03_the = word("THE","the");
			val t04_park = word("N","park");
			val t05_and = word("AND","and");
			val t06_the = word("THE","the");
			val t07_garden = word("N","garden");

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John");
			val h01_walked = word("V_1","walked");
			val h02_in = word("P_R","in");
			val h03_the = word("THE","the");
			val h04_garden = word("N","garden");

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					t01_walked
					,
					_(
						t02_in
						,
						_(
							_(
								t03_the
								,
								t04_park
							)
							,
							_(
								t05_and
								,
								_(
									t06_the
									,
									t07_garden
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
					h01_walked
					,
					_(
						h02_in
						,
						_(
							h03_the
							,
							h04_garden
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
