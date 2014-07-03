package semante.pipeline.test.subs;

import static semante.pipeline.ResultType.Proof;

import java.util.List;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

import com.google.common.collect.Lists;

public final class TestCase02 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0042() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John",1);
			val t01_is = word("IS","is",2);
			val t02_tall = word("MR","tall",3);

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John",1);
			val h01_is = word("IS","is",2);
			val h02_vtall = word("MR","vtall",3);

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					t01_is
					,
					t02_tall
					,
					4
				)
				,
				5
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_john
				,
				_(
					h01_is
					,
					h02_vtall
					,
					4
				)
				,
				5
			)
			;

			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(3,3));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
