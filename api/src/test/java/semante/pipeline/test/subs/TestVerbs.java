package semante.pipeline.test.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestVerbs extends AbsPipelineTest<Integer> {

		@Test
		public final void TestCase01() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_jumped = word("V_1","jumped",2);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_moved = word("V_1","moved",2);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				t02_jumped
				,
				5
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				h02_moved
				,
				5
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

		@Test
		public final void TestCase02() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_jumped = word("V_1","jumped",2);
			val t03_quickly = word("MR","quickly",3);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_moved = word("V_1","moved",2);
			val h03_quickly = word("MR","quickly",3);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t02_jumped
					,
					t03_quickly
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
				h01_john
				,
				_(
					h02_moved
					,
					h03_quickly
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
			assertNoProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, NoProof, subs);
		}
		
		@Test
		public final void TestCase03() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_pushed = word("V_2","pushed",2);
			val t03_the = word("THE","the",3);
			val t04_table = word("N","table",4);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_moved = word("V_2","moved",2);
			val h03_the = word("THE","the",3);
			val h04_table = word("N","table",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t02_pushed
					,
					_(
						t03_the
						,
						t04_table
						,
						6
					)
					,
					7
				)
				,
				8
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					h02_moved
					,
					_(
						h03_the
						,
						h04_table
						,
						6
					)
					,
					7
				)
				,
				8
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
		
		
		@Test
		public final void TestCase04() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_pushed = word("V_2","pushed",2);
			val t03_the = word("THE","the",3);
			val t04_table = word("N","table",4);
			val t05_quickly = word("MR","quickly",5);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_moved = word("V_2","moved",2);
			val h03_the = word("THE","the",3);
			val h04_table = word("N","table",4);
			val h05_quickly = word("MR","quickly",5);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					_(t05_quickly,t02_pushed,6)
					,
					_(
						t03_the
						,
						t04_table
						,
						7
					)
					,
					8
				)
				,
				9
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					_(h05_quickly,h02_moved,6)
					,
					_(
						h03_the
						,
						h04_table
						,
						7
					)
					,
					8
				)
				,
				9
			)
			;

			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(2,2));

			// test for a proof;
			assertNoProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, NoProof, subs);
		}
		

		@Test
		public final void TestCase05() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_pushed = word("V_2","pushed",2);
			val t03_a = word("A","a",3);
			val t04_table = word("N","table",4);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_moved = word("V_2","moved",2);
			val h03_a = word("A","a",3);
			val h04_table = word("N","table",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t02_pushed
					,
					_(
						t03_a
						,
						t04_table
						,
						6
					)
					,
					7
				)
				,
				8
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					h02_moved
					,
					_(
						h03_a
						,
						h04_table
						,
						6
					)
					,
					7
				)
				,
				8
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
		
		
		@Test
		public final void TestCase06() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_pushed = word("V_2","pushed",2);
			val t03_a = word("A","a",3);
			val t04_table = word("N","table",4);
			val t05_quickly = word("MR","quickly",5);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_moved = word("V_2","moved",2);
			val h03_a = word("A","a",3);
			val h04_table = word("N","table",4);
			val h05_quickly = word("MR","quickly",5);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					_(t05_quickly,t02_pushed,6)
					,
					_(
						t03_a
						,
						t04_table
						,
						7
					)
					,
					8
				)
				,
				9
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					_(h05_quickly,h02_moved,6)
					,
					_(
						h03_a
						,
						h04_table
						,
						7
					)
					,
					8
				)
				,
				9
			)
			;

			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(2,2));

			// test for a proof;
			assertNoProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, NoProof, subs);
		}
		
}
