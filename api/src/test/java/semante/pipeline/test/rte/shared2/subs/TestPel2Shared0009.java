package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0009 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0009() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_powerful = word("MR","powerful",2);
			val t03_israeli = word("MI","Israeli",3);
			val t04_prime = word("MR","Prime",4);
			val t05_minister = word("N","Minister",5);
			val t52_app = word("WHO_A","APP",52);
			val t06_ariel = word("$NPC_1$","Ariel",6);
			val t07_sharon = word("NP_D","Sharon",7);
			val t08_will = word("V_AUX","will",8);
			val t09_dismiss = word("V_2","dismiss",9);
			val t55_det = word("THE","DET",55);
			val t10_cabinet = word("MR","Cabinet",10);
			val t11_ministers = word("N","ministers",11);
			val t12_who = word("WHO_A","who",12);
			val t13_do = word("V_AUX","do",13);
			val t14_not = word("MR","not",14);
			val t15_support = word("V_2","support",15);
			val t16_israel = word("NP_D","Israel",16);
			val t17_s = word("POSS","s",17);
			val t18_plan = word("N","plan",18);
			val t19_for = word("P_R","for",19);
			val t20_the = word("THE","the",20);
			val t21_gaza = word("$NPC_1$","Gaza",21);
			val t22_strip = word("NP_D","Strip",22);

			// create the vocabulary for the hypothesis;
			val h01_ariel = word("$NPC_1$","Ariel",1);
			val h02_sharon = word("NP_D","Sharon",2);
			val h24_app = word("WHO_A","APP",24);
			val h04_a = word("A","a",4);
			val h05_powerful = word("MR","powerful",5);
			val h06_politician = word("N","politician",6);
			val h08_will = word("V_AUX","will",8);
			val h09_fire = word("V_2","fire",9);
			val h26_det = word("SOME","DET",26);
			val h10_cabinet = word("MR","Cabinet",10);
			val h11_ministers = word("N","ministers",11);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_powerful
							,
							_(
								t03_israeli
								,
								_(
									t04_prime
									,
									t05_minister
									,
									47
								)
								,
								24
							)
							,
							51
						)
						,
						49
					)
					,
					_(
						t52_app
						,
						_(
							t06_ariel
							,
							t07_sharon
							,
							45
						)
						,
						53
					)
					,
					50
				)
				,
				_(
					t08_will
					,
					_(
						t09_dismiss
						,
						_(
							_(
								t55_det
								,
								_(
									t10_cabinet
									,
									t11_ministers
									,
									26
								)
								,
								56
							)
							,
							_(
								t12_who
								,
								_(
									t13_do
									,
									_(
										t14_not
										,
										_(
											t15_support
											,
											_(
												_(
													t16_israel
													,
													t17_s
													,
													28
												)
												,
												_(
													t18_plan
													,
													_(
														t19_for
														,
														_(
															t20_the
															,
															_(
																t21_gaza
																,
																t22_strip
																,
																41
															)
															,
															42
														)
														,
														31
													)
													,
													29
												)
												,
												57
											)
											,
											33
										)
										,
										43
									)
									,
									44
								)
								,
								36
							)
							,
							37
						)
						,
						38
					)
					,
					39
				)
				,
				54
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h01_ariel
						,
						h02_sharon
						,
						13
					)
					,
					_(
						h24_app
						,
						_(
							h04_a
							,
							_(
								h05_powerful
								,
								h06_politician
								,
								20
							)
							,
							21
						)
						,
						25
					)
					,
					22
				)
				,
				_(
					h08_will
					,
					_(
						h09_fire
						,
						_(
							h26_det
							,
							_(
								h10_cabinet
								,
								h11_ministers
								,
								16
							)
							,
							27
						)
						,
						17
					)
					,
					18
				)
				,
				23
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(9,9));
		subs.add(new IPair<Integer,Integer>(47,6));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

