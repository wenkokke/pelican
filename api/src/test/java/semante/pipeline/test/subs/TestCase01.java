package semante.pipeline.test.subs;

import static semante.pipeline.ResultType.Proof;

import java.util.List;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

import com.google.common.collect.Lists;

public final class TestCase01 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0041() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John",1);
			val t01_is = word("IS","is",2);
			val t02_a = word("A","a",3);
			val t03_man = word("N","man",4);

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John",1);
			val h01_is = word("IS","is",2);
			val h02_a = word("A","a",3);
			val h03_human = word("N","human",4);

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					t01_is
					,
					_(
						t02_a
						,
						t03_man
						,
						5
					),
					6
				),
				7
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
					_(
						h02_a
						,
						h03_human,
						5
					),
					6
				),
				7
			)
			;

			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(4,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

		@Test
		public final void TestCase05() throws Exception {

			// create the vocabulary for the text;
			val t00_john = word("NP_D","John",1);
			val t01_kissed = word("V_2","kissed",2);
			val t02_a = word("A","a",3);
			val t03_tall = word("MR","tall",4);
			val t04_boy = word("N","boy",5);

			// create the vocabulary for the hypothesis;
			val h00_john = word("NP_D","John",1);
			val h01_kissed = word("V_2","kissed",2);
			val h02_the = word("THE","the",3);
			val h03_boy = word("N","boy",4);

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					t01_kissed
					,
					_(
						t02_a
						,
						_(
							t03_tall
							,
							t04_boy
						,6)
					,7)
				,8)
			,9)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_john
				,
				_(
					h01_kissed
					,
					_(
						h02_the
						,
						h03_boy
					,5)
				,6)
			,7)
			;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

		
		
}
