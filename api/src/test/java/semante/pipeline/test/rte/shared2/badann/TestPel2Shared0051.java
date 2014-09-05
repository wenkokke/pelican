package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0051 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0051() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t55_los_angeles_lakers = word("NP_D","Los_Angeles_Lakers",55);
			val t06_who = word("WHO_A","who",6);
			val t07_won = word("V_2","won",7);
			val t08_the = word("THE","the",8);
			val t09_num3rd = word("MR","num3rd",9);
			val t10_nba = word("MR","NBA",10);
			val t11_championship = word("N","championship",11);
			val t13_beat = word("V_2","beat",13);
			val t14_the = word("THE","the",14);
			val t41_new_jersey_nets = word("NP_D","New_Jersey_Nets",41);
			val t19_sweeping = word("GER_2","sweeping",19);
			val t20_that = word("THE","that",20);
			val t21_num4game = word("MR","num4Game",21);
			val t43_championship_series = word("N","championship_series",43);

			// create the vocabulary for the hypothesis;
			val h06_la_lakers = word("NP_D","LA_Lakers",6);
			val h03_won = word("V_2","won",3);
			val h04_a = word("A","a",4);
			val h05_championship = word("N","championship",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						t55_los_angeles_lakers
						,
						48
					)
					,
					_(
						t06_who
						,
						_(
							t07_won
							,
							_(
								t08_the
								,
								_(
									t09_num3rd
									,
									_(
										t10_nba
										,
										t11_championship
										,
										49
									)
									,
									50
								)
								,
								51
							)
							,
							28
						)
						,
						30
					)
					,
					52
				)
				,
				_(
					_(
						t13_beat
						,
						_(
							t14_the
							,
							t41_new_jersey_nets
							,
							42
						)
						,
						38
					)
					,
					_(
						t19_sweeping
						,
						_(
							t20_that
							,
							_(
								t21_num4game
								,
								t43_championship_series
								,
								44
							)
							,
							35
						)
						,
						36
					)
					,
					53
				)
				,
				54
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h06_la_lakers
				,
				_(
					h03_won
					,
					_(
						h04_a
						,
						h05_championship
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
		subs.add(new IPair<Integer,Integer>(47,1));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
