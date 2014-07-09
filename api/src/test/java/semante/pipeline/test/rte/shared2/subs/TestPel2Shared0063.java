package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0063 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0063() throws Exception {

			// create the vocabulary for the text;
			val t23_pibul_songgram = word("NP_D","Pibul_Songgram",23);
			val t46_app = word("WHO_A","APP",46);
			val t04_thailand = word("NP_D","Thailand",4);
			val t05_s = word("POSS","s",5);
			val t06_military = word("MR","military",6);
			val t07_dictator = word("N","dictator",7);
			val t08_during = word("P_R","during",8);
			val t43_world_war_num2 = word("NP_D","World_War_num2",43);
			val t13_led = word("V_2","led",13);
			val t14_a = word("A","a",14);
			val t15_campaign = word("N","campaign",15);
			val t16_against = word("P_R","against",16);
			val t49_det = word("THE","DET",49);
			val t17_antijapanese = word("MR","antiJapanese",17);
			val t18_leader = word("N","leader",18);
			val t51_app = word("WHO_A","APP",51);
			val t32_mangpo_satayahuraska = word("NP_D","Mangpo_Satayahuraska",32);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_thai = word("MR","Thai",2);
			val h03_dictator = word("N","dictator",3);
			val h04_led = word("V_2","led",4);
			val h05_a = word("A","a",5);
			val h06_campaign = word("N","campaign",6);
			val h07_against = word("P_R","against",7);
			val h13_mangpo_satayahuraska = word("NP_D","Mangpo_Satayahuraska",13);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t23_pibul_songgram
					,
					_(
						t46_app
						,
						_(
							_(
								t04_thailand
								,
								t05_s
								,
								24
							)
							,
							_(
								t06_military
								,
								_(
									t07_dictator
									,
									_(
										t08_during
										,
										t43_world_war_num2
										,
										27
									)
									,
									40
								)
								,
								48
							)
							,
							41
						)
						,
						47
					)
					,
					44
				)
				,
				_(
					_(
						t13_led
						,
						_(
							t14_a
							,
							t15_campaign
							,
							30
						)
						,
						37
					)
					,
					_(
						t16_against
						,
						_(
							_(
								t49_det
								,
								_(
									t17_antijapanese
									,
									t18_leader
									,
									31
								)
								,
								50
							)
							,
							_(
								t51_app
								,
								t32_mangpo_satayahuraska
								,
								52
							)
							,
							34
						)
						,
						53
					)
					,
					39
				)
				,
				45
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_thai
						,
						h03_dictator
						,
						19
					)
					,
					20
				)
				,
				_(
					_(
						h04_led
						,
						_(
							h05_a
							,
							h06_campaign
							,
							12
						)
						,
						17
					)
					,
					_(
						h07_against
						,
						h13_mangpo_satayahuraska
						,
						14
					)
					,
					18
				)
				,
				21
			)
			;

			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(41,20));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
