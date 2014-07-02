package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0038 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0038() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_jewish = word("MI","Jewish",2);
			val t03_agency = word("$NC_1$","Agency",3);
			val t04_chairman = word("N","chairman",4);
			val t56_app = word("WHO_A","APP",56);
			val t05_zeev = word("$NPC_1$","Zeev",5);
			val t06_bielsky = word("NP_D","Bielsky",6);
			val t07_believes = word("V_2","believes",7);
			val t08_that = word("IGNORE","that",8);
			val t09_the = word("THE","the",9);
			val t10_stories = word("N","stories",10);
			val t11_about = word("P_R","about",11);
			val t60_det = word("EMPTYDET","DET",60);
			val t12_conversion = word("$NC_1$","conversion",12);
			val t13_difficulties = word("N","difficulties",13);
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
			val h01_zeev = word("$NPC_1$","Zeev",1);
			val h02_bielsky = word("NP_D","Bielsky",2);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_jewish = word("MI","Jewish",5);
			val h06_agency = word("$NC_1$","agency",6);
			val h07_official = word("N","official",7);

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
							_(
								t03_agency
								,
								t04_chairman
								,
								52
							)
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
						_(
							t05_zeev
							,
							t06_bielsky
							,
							50
						)
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
												_(
													t12_conversion
													,
													t13_difficulties
													,
													28
												)
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
				_(
					h01_zeev
					,
					h02_bielsky
					,
					9
				)
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
							_(
								h06_agency
								,
								h07_official
								,
								13
							)
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

