package semante.pipeline.test.subs;

import static semante.pipeline.ResultType.Proof;

import java.util.List;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

import com.google.common.collect.Lists;

public final class TestCase03 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0043() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John",1);
			val t01_pushed = word("V_2","pushed",2);
			val t02_the = word("THE","the",3);
			val t03_table = word("N","table",4);

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John",1);
			val h01_moved = word("V_2","moved",2);
			val h02_the = word("THE","the",3);
			val h03_table = word("N","table",4);

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					t01_pushed
					,
					_(
						t02_the
						,
						t03_table
						,
						5
					)
					,
					6
				)
				,
				7
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_john
				,
				_(
					h01_moved
					,
					_(
						h02_the
						,
						h03_table
						,
						5
					)
					,
					6
				)
				,
				7
			)
			;

			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(2,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
