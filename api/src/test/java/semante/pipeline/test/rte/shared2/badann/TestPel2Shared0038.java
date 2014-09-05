package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0038 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0038() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_jewish = word("MI","Jewish",2);
			val t52_agency_chairman = word("N","Agency_chairman",52);
			val t56_app = word("WHO_A","APP",56);
			val t50_zeev_bielsky = word("NP_D","Zeev_Bielsky",50);
			val t07_believes = word("V_2","believes",7);
			val t08_that = word("IGNORE","that",8);
			val t09_the = word("THE","the",9);
			val t10_stories = word("N","stories",10);
			val t11_about = word("P_R","about",11);
			val t60_det = word("EMPTYDET","DET",60);
			val t28_conversion_difficulties = word("N","conversion_difficulties",28);
			val t14_that = word("WHO_A","that",14);
			val t15_reach = word("V_2","reach",15);
			val t62_det = word("A","DET",62);
			val t16_former = word("MR","former",16);
			val t17_soviet = word("MI","Soviet",17);
			val t18_states = word("N","states",18);
			val t19_have = word("V_2","have",19);
			val t20_a = word("A","a",20);
			val t21_bad = word("MR","bad",21);
			val t22_influence = word("N","influence",22);
			val t23_on = word("P_R","on",23);
			val t24_immigration = word("N","immigration",24);

			// create the vocabulary for the hypothesis;
			val h09_zeev_bielsky = word("NP_D","Zeev_Bielsky",9);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_jewish = word("MI","Jewish",5);
			val h13_agency_official = word("N","agency_official",13);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_jewish
							,
							t52_agency_chairman
							,
							53
						)
						,
						54
					)
					,
					_(
						t56_app
						,
						t50_zeev_bielsky
						,
						57
					)
					,
					55
				)
				,
				_(
					t07_believes
					,
					_(
						t08_that
						,
						_(
							_(
								t09_the
								,
								_(
									t10_stories
									,
									_(
										t11_about
										,
										_(
											_(
												t60_det
												,
												t28_conversion_difficulties
												,
												61
											)
											,
											_(
												t14_that
												,
												_(
													t15_reach
													,
													_(
														t62_det
														,
														_(
															t16_former
															,
															_(
																t17_soviet
																,
																t18_states
																,
																46
															)
															,
															47
														)
														,
														63
													)
													,
													31
												)
												,
												33
											)
											,
											34
										)
										,
										35
									)
									,
									27
								)
								,
								59
							)
							,
							_(
								_(
									t19_have
									,
									_(
										t20_a
										,
										_(
											t21_bad
											,
											t22_influence
											,
											48
										)
										,
										64
									)
									,
									41
								)
								,
								_(
									t23_on
									,
									t24_immigration
									,
									39
								)
								,
								42
							)
							,
							65
						)
						,
						43
					)
					,
					44
				)
				,
				58
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h09_zeev_bielsky
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						_(
							h05_jewish
							,
							h13_agency_official
							,
							14
						)
						,
						15
					)
					,
					11
				)
				,
				16
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(4,7));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
