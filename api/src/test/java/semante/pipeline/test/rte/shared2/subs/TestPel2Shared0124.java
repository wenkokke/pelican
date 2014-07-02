package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0124 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0124() throws Exception {

			// create the vocabulary for the text;
			val t01_latvia = word("NP_D","Latvia",1);
			val t52_app = word("WHO_A","APP",52);
			val t03_the = word("THE","the",3);
			val t04_lowestranked = word("MR","lowestRanked",4);
			val t05_team = word("N","team",5);
			val t07_defeated = word("V_2","defeated",7);
			val t08_world = word("MI","World",8);
			val t09_cup = word("MI","Cup",9);
			val t10_semifinalist = word("MI","semifinalist",10);
			val t11_turkey = word("NP_D","Turkey",11);
			val t12_in = word("P_R","in",12);
			val t13_a = word("A","a",13);
			val t14_playoff = word("N","playoff",14);
			val t16_qualifying = word("GER_1","qualifying",16);
			val t17_for = word("P_R","for",17);
			val t18_the = word("THE","the",18);
			val t19_final = word("MR","final",19);
			val t20_num16 = word("N","num16",20);
			val t21_of = word("P_R","of",21);
			val t22_euro = word("$NPC_1$","Euro",22);
			val t23_num2004 = word("NP_D","num2004",23);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_lowranked = word("MR","lowRanked",2);
			val h03_team = word("N","team",3);
			val h04_has = word("V_AUX","has",4);
			val h05_beaten = word("V_2","beaten",5);
			val h06_turkey = word("NP_D","Turkey",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_latvia
					,
					_(
						t52_app
						,
						_(
							t03_the
							,
							_(
								t04_lowestranked
								,
								t05_team
								,
								48
							)
							,
							49
						)
						,
						53
					)
					,
					50
				)
				,
				_(
					_(
						_(
							_(
								t07_defeated
								,
								_(
									t08_world
									,
									_(
										t09_cup
										,
										_(
											t10_semifinalist
											,
											t11_turkey
											,
											41
										)
										,
										42
									)
									,
									43
								)
								,
								44
							)
							,
							_(
								t12_in
								,
								_(
									t13_a
									,
									t14_playoff
									,
									29
								)
								,
								30
							)
							,
							47
						)
						,
						t16_qualifying
						,
						54
					)
					,
					_(
						t17_for
						,
						_(
							t18_the
							,
							_(
								t19_final
								,
								_(
									t20_num16
									,
									_(
										t21_of
										,
										_(
											t22_euro
											,
											t23_num2004
											,
											33
										)
										,
										34
									)
									,
									45
								)
								,
								55
							)
							,
							46
						)
						,
						36
					)
					,
					51
				)
				,
				56
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_lowranked
						,
						h03_team
						,
						13
					)
					,
					14
				)
				,
				_(
					h04_has
					,
					_(
						h05_beaten
						,
						h06_turkey
						,
						10
					)
					,
					11
				)
				,
				15
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(4,2));
		subs.add(new IPair<Integer,Integer>(7,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

