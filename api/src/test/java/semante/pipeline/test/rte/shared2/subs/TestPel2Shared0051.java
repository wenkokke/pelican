package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0051 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0051() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_los = word("$NPC_2$","Los",2);
			val t03_angeles = word("NP_D","Angeles",3);
			val t04_lakers = word("NP_D","Lakers",4);
			val t06_who = word("WHO_A","who",6);
			val t07_won = word("V_2","won",7);
			val t08_the = word("THE","the",8);
			val t09_num3rd = word("MR","num3rd",9);
			val t10_nba = word("MR","NBA",10);
			val t11_championship = word("N","championship",11);
			val t13_beat = word("V_2","beat",13);
			val t14_the = word("THE","the",14);
			val t15_new = word("$NPC_1$","New",15);
			val t16_jersey = word("$NPC_1$","Jersey",16);
			val t17_nets = word("NP_D","Nets",17);
			val t19_sweeping = word("GER_2","sweeping",19);
			val t20_that = word("THE","that",20);
			val t21_num4game = word("MR","num4Game",21);
			val t22_championship = word("$NC_1$","championship",22);
			val t23_series = word("N","series",23);

			// create the vocabulary for the hypothesis;
			val h01_la = word("$NPC_1$","LA",1);
			val h02_lakers = word("NP_D","Lakers",2);
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
						_(
							_(
								t02_los
								,
								t03_angeles
								,
								47
							)
							,
							t04_lakers
							,
							55
						)
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
							_(
								t15_new
								,
								_(
									t16_jersey
									,
									t17_nets
									,
									40
								)
								,
								41
							)
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
								_(
									t22_championship
									,
									t23_series
									,
									43
								)
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
				_(
					h01_la
					,
					h02_lakers
					,
					6
				)
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

