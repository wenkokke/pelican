package semante.pipeline.test.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestNouns extends AbsPipelineTest {

		@Test
		public final void TestCase01() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_is = word("IS","is",2);
			val t03_a = word("A","a",3);
			val t04_man = word("N","man",4);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_is = word("IS","is",2);
			val h03_a = word("A","a",3);
			val h04_human = word("N","human",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t02_is
					,
					_(
						t03_a
						,
						t04_man
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
					h02_is
					,
					_(
						h03_a
						,
						h04_human
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
		subs.add(new IPair<Integer,Integer>(4,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

		
		@Test
		public final void TestCase02() throws Exception {

			// create the vocabulary for the text;
			val t01_a = word("A","A",1);
			val t02_man = word("N","man",2);
			val t03_ran = word("V_1","ran",3);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_human = word("N","human",2);
			val h03_ran = word("V_1","ran",3);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_a
					,
					t02_man
					,
					4
				)
				,
				t03_ran
				,
				6
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					h02_human
					,
					4
				)
				,
				h03_ran
				,
				6
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
		public final void TestCase03() throws Exception {

			// create the vocabulary for the text;
			val t01_mary = word("NP_D","Mary",1);
			val t02_kissed = word("V_2","kissed",2);
			val t03_a = word("A","a",3);
			val t04_man = word("N","man",4);

			// create the vocabulary for the hypothesis;
			val h01_mary = word("NP_D","Mary",1);
			val h02_kissed = word("V_2","kissed",2);
			val h03_a = word("A","a",3);
			val h04_human = word("N","human",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_mary
				,
				_(
					t02_kissed
					,
					_(
						t03_a
						,
						t04_man
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
				h01_mary
				,
				_(
					h02_kissed
					,
					_(
						h03_a
						,
						h04_human
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
		subs.add(new IPair<Integer,Integer>(4,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

		@Test
		public final void TestCase04() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_is = word("IS","is",2);
			val t03_the = word("THE","the",3);
			val t04_winner = word("N","winner",4);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_is = word("IS","is",2);
			val h03_the = word("THE","the",3);
			val h04_player = word("N","player",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t02_is
					,
					_(
						t03_the
						,
						t04_winner
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
					h02_is
					,
					_(
						h03_the
						,
						h04_player
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
		subs.add(new IPair<Integer,Integer>(4,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

		@Test
		public final void TestCase05() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_man = word("N","man",2);
			val t03_ran = word("V_1","ran",3);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_human = word("N","human",2);
			val h03_ran = word("V_1","ran",3);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_man
					,
					4
				)
				,
				t03_ran
				,
				6
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					h02_human
					,
					4
				)
				,
				h03_ran
				,
				6
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
			val t01_mary = word("NP_D","Mary",1);
			val t02_kissed = word("V_2","kissed",2);
			val t03_the = word("THE","the",3);
			val t04_man = word("N","man",4);

			// create the vocabulary for the hypothesis;
			val h01_mary = word("NP_D","Mary",1);
			val h02_kissed = word("V_2","kissed",2);
			val h03_the = word("THE","the",3);
			val h04_human = word("N","human",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_mary
				,
				_(
					t02_kissed
					,
					_(
						t03_the
						,
						t04_man
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
				h01_mary
				,
				_(
					h02_kissed
					,
					_(
						h03_the
						,
						h04_human
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
		subs.add(new IPair<Integer,Integer>(4,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}
	
		@Test
		public final void TestCase07() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_is = word("IS","is",2);
			val t03_the = word("THE","the",3);
			val t04_winner = word("N","winner",4);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_is = word("IS","is",2);
			val h03_a = word("A","a",3);
			val h04_player = word("N","player",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t02_is
					,
					_(
						t03_the
						,
						t04_winner
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
					h02_is
					,
					_(
						h03_a
						,
						h04_player
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
		subs.add(new IPair<Integer,Integer>(4,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

		@Test
		public final void TestCase08() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_man = word("N","man",2);
			val t03_ran = word("V_1","ran",3);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_human = word("N","human",2);
			val h03_ran = word("V_1","ran",3);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_man
					,
					4
				)
				,
				t03_ran
				,
				6
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					h02_human
					,
					4
				)
				,
				h03_ran
				,
				6
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
		public final void TestCase09() throws Exception {

			// create the vocabulary for the text;
			val t01_mary = word("NP_D","Mary",1);
			val t02_kissed = word("V_2","kissed",2);
			val t03_the = word("THE","the",3);
			val t04_man = word("N","man",4);

			// create the vocabulary for the hypothesis;
			val h01_mary = word("NP_D","Mary",1);
			val h02_kissed = word("V_2","kissed",2);
			val h03_a = word("A","a",3);
			val h04_human = word("N","human",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_mary
				,
				_(
					t02_kissed
					,
					_(
						t03_the
						,
						t04_man
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
				h01_mary
				,
				_(
					h02_kissed
					,
					_(
						h03_a
						,
						h04_human
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
		subs.add(new IPair<Integer,Integer>(4,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}
		
		@Test
		public final void TestCase10() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_is = word("IS","is",2);
			val t03_a = word("A","a",3);
			val t04_singer = word("N","singer",4);
			val t05_in = word("P_R","in",5);
			val t06_a = word("A","a",6);
			val t07_band = word("N","band",7);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_is = word("IS","is",2);
			val h03_a = word("A","a",3);
			val h04_singer = word("N","singer",4);
			val h05_in = word("P_R","in",5);
			val h06_a = word("A","a",6);
			val h07_group = word("N","group",7);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t02_is
					,
					_(
						t03_a
						,
						_(
							t04_singer
							,
							_(
								t05_in
								,
								_(
									t06_a
									,
									t07_band
									,
									10
								)
								,
								11
							)
							,
							12
						)
						,
						13
					)
					,
					15
				)
				,
				14
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_singer
							,
							_(
								h05_in
								,
								_(
									h06_a
									,
									h07_group
									,
									10
								)
								,
								11
							)
							,
							12
						)
						,
						13
					)
					,
					15
				)
				,
				14
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(7,7));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

		@Test
		public final void TestCase11() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_is = word("IS","is",2);
			val t03_an = word("A","an",3);
			val t04_ace = word("N","ace",4);
			val t05_in = word("P_R","in",5);
			val t06_the = word("THE","the",6);
			val t07_team = word("N","team",7);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_is = word("IS","is",2);
			val h03_an = word("A","an",3);
			val h04_ace = word("N","ace",4);
			val h05_in = word("P_R","in",5);
			val h06_the = word("THE","the",6);
			val h07_group = word("N","group",7);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t02_is
					,
					_(
						t03_an
						,
						_(
							t04_ace
							,
							_(
								t05_in
								,
								_(
									t06_the
									,
									t07_team
									,
									10
								)
								,
								11
							)
							,
							12
						)
						,
						13
					)
					,
					15
				)
				,
				14
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					h02_is
					,
					_(
						h03_an
						,
						_(
							h04_ace
							,
							_(
								h05_in
								,
								_(
									h06_the
									,
									h07_group
									,
									10
								)
								,
								11
							)
							,
							12
						)
						,
						13
					)
					,
					15
				)
				,
				14
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(7,7));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}
		
}
