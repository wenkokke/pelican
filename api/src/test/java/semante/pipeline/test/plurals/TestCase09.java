package semante.pipeline.test.plurals;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestCase09 extends AbsPipelineTest {

		@Test
		public final void TestCase09() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John");
			val t01_and = word("AND","and");
			val t02_mary = word("NP_D","Mary");
			val t03_are = word("IS","are");
			val t04_the = word("THE","the");
			val t05_doctors = word("N","doctors");

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John");
			val h01_and = word("AND","and");
			val h02_mary = word("NP_D","Mary");
			val h03_are = word("IS","are");
			val h04_the = word("THE","the");
			val h05_doctors = word("N","doctors");

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
						t04_the
						,
						t05_doctors
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
						h04_the
						,
						h05_doctors
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
