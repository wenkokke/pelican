package semante.pipeline.test.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestIdioms extends AbsPipelineTest {

		@Test
		public final void TestCase01() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t09_ran_into = word("V_2","ran_into",9);
			val t04_virginia = word("$NPC_1$","Virginia",4);
			val t05_wolf = word("NP_D","Wolf",5);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_met = word("V_2","met",2);
			val h03_virginia = word("$NPC_1$","Virginia",3);
			val h04_wolf = word("NP_D","Wolf",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t09_ran_into
					,
					_(
						t04_virginia
						,
						t05_wolf
						,
						7
					)
					,
					11
				)
				,
				10
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					h02_met
					,
					_(
						h03_virginia
						,
						h04_wolf
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
			subs.add(new IPair<Integer,Integer>(9,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

		@Test
		public final void TestCase02() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t09_ran_into = word("V_2","ran_into",9);
			val t04_the = word("THE","the",4);
			val t05_lady = word("N","lady",5);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_met = word("V_2","met",2);
			val h03_the = word("THE","the",3);
			val h04_lady = word("N","lady",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t09_ran_into
					,
					_(
						t04_the
						,
						t05_lady
						,
						7
					)
					,
					11
				)
				,
				10
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					h02_met
					,
					_(
						h03_the
						,
						h04_lady
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
			subs.add(new IPair<Integer,Integer>(9,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

		@Test
		public final void TestCase03() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t09_ran_into = word("V_2","ran_into",9);
			val t04_a = word("A","a",4);
			val t05_lady = word("N","lady",5);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_met = word("V_2","met",2);
			val h03_a = word("A","a",3);
			val h04_lady = word("N","lady",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t09_ran_into
					,
					_(
						t04_a
						,
						t05_lady
						,
						7
					)
					,
					11
				)
				,
				10
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					h02_met
					,
					_(
						h03_a
						,
						h04_lady
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
			subs.add(new IPair<Integer,Integer>(9,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

		@Test
		public final void TestCase04() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t09_ran_into = word("V_2","ran_into",9);
			val t04_the = word("THE","the",4);
			val t05_lady = word("N","lady",5);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_met = word("V_2","met",2);
			val h03_a = word("A","a",3);
			val h04_lady = word("N","lady",4);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t09_ran_into
					,
					_(
						t04_the
						,
						t05_lady
						,
						7
					)
					,
					11
				)
				,
				10
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					h02_met
					,
					_(
						h03_a
						,
						h04_lady
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
			subs.add(new IPair<Integer,Integer>(9,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}


		@Test
		public final void TestCase05() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_kicked = word("V_2","kicked",2);
			val t03_the = word("THE","the",3);
			val t04_bucket = word("N","bucket",4);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_died = word("V_1","died",2);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					t02_kicked
					,
					_(
						t03_the
						,
						t04_bucket
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
				h02_died
				,
				5
			)
			;

			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(7,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);

		}

		@Test
		public final void TestCase06() throws Exception {

			// create the vocabulary for the text;
			val t01_john = word("NP_D","John",1);
			val t02_got = word("V_2","got",2);
			val t03_the = word("THE","the",3);
			val t04_job = word("N","job",4);
			val t05_by = word("P_R","by",5);
			val t06_pulling = word("GER_2","pulling",6);
			val t20_det = word("SOME","DET",20);
			val t07_strings = word("N","strings",7);

			// create the vocabulary for the hypothesis;
			val h01_john = word("NP_D","John",1);
			val h02_got = word("V_2","got",2);
			val h03_the = word("THE","the",3);
			val h04_job = word("N","job",4);
			val h05_by = word("P_R","by",5);
			val h06_using = word("GER_2","using",6);
			val h20_det = word("SOME","DET",20);
			val h07_connections = word("N","connections",7);

			// create the tree structure for the text;
			val tt =
			_(
				t01_john
				,
				_(
					_(
						t02_got
						,
						_(
							t03_the
							,
							t04_job
							,
							10
						)
						,
						17
					)
					,
					_(
						t05_by
						,
						_(
							t06_pulling
							,
							_(
								t20_det
								,
								t07_strings
								,
								21
							)
							,
							12
						)
						,
						14
					)
					,
					18
				)
				,
				19
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_john
				,
				_(
					_(
						h02_got
						,
						_(
							h03_the
							,
							h04_job
							,
							10
						)
						,
						17
					)
					,
					_(
						h05_by
						,
						_(
							h06_using
							,
							_(
								h20_det
								,
								h07_connections
								,
								21
							)
							,
							12
						)
						,
						14
					)
					,
					18
				)
				,
				19
			)
			;

			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(12,12));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}
}
