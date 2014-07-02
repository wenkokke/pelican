package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0055 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0055() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_muslim = word("MI","Muslim",2);
			val t03_fundamentalists = word("N","fundamentalists",3);
			val t04_of = word("P_R","of",4);
			val t05_hamas = word("NP_D","Hamas",5);
			val t70_app = word("WHO_A","APP",70);
			val t07_the = word("THE","the",7);
			val t08_islamic = word("MI","Islamic",8);
			val t09_resistance = word("$NC_1$","Resistance",9);
			val t10_movement = word("N","Movement",10);
			val t12_will = word("V_AUX","will",12);
			val t13_torpedo = word("V_2","torpedo",13);
			val t14_the = word("THE","the",14);
			val t15_peace = word("$NC_1$","peace",15);
			val t16_process = word("N","process",16);
			val t18_destroying = word("GER_2","destroying",18);
			val t62_det = word("THE","DET",62);
			val t19_hopes = word("N","hopes",19);
			val t20_of = word("P_R","of",20);
			val t21_a = word("A","a",21);
			val t22_resolution = word("N","resolution",22);
			val t23_to = word("P_R","to",23);
			val t24_the = word("THE","the",24);
			val t25_arabisraeli = word("MI","ArabIsraeli",25);
			val t26_conflict = word("N","conflict",26);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_fundamentalists = word("N","fundamentalists",2);
			val h03_of = word("P_R","of",3);
			val h04_islamic = word("MI","Islamic",4);
			val h05_hamas = word("NP_D","Hamas",5);
			val h06_will = word("V_AUX","will",6);
			val h07_halt = word("V_2","halt",7);
			val h08_a = word("A","a",8);
			val h09_peace = word("$NC_1$","peace",9);
			val h10_process = word("N","process",10);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_muslim
						,
						_(
							t03_fundamentalists
							,
							_(
								t04_of
								,
								_(
									t05_hamas
									,
									_(
										t70_app
										,
										_(
											t07_the
											,
											_(
												t08_islamic
												,
												_(
													t09_resistance
													,
													t10_movement
													,
													58
												)
												,
												65
											)
											,
											59
										)
										,
										71
									)
									,
									29
								)
								,
								30
							)
							,
							56
						)
						,
						67
					)
					,
					57
				)
				,
				_(
					t12_will
					,
					_(
						_(
							t13_torpedo
							,
							_(
								t14_the
								,
								_(
									t15_peace
									,
									t16_process
									,
									48
								)
								,
								49
							)
							,
							50
						)
						,
						_(
							t18_destroying
							,
							_(
								t62_det
								,
								_(
									t19_hopes
									,
									_(
										t20_of
										,
										_(
											t21_a
											,
											_(
												t22_resolution
												,
												_(
													t23_to
													,
													_(
														t24_the
														,
														_(
															t25_arabisraeli
															,
															t26_conflict
															,
															52
														)
														,
														53
													)
													,
													42
												)
												,
												38
											)
											,
											64
										)
										,
										39
									)
									,
									40
								)
								,
								63
							)
							,
							51
						)
						,
						55
					)
					,
					46
				)
				,
				69
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					_(
						h02_fundamentalists
						,
						_(
							h03_of
							,
							_(
								h04_islamic
								,
								h05_hamas
								,
								13
							)
							,
							14
						)
						,
						12
					)
					,
					23
				)
				,
				_(
					h06_will
					,
					_(
						h07_halt
						,
						_(
							h08_a
							,
							_(
								h09_peace
								,
								h10_process
								,
								20
							)
							,
							21
						)
						,
						17
					)
					,
					18
				)
				,
				22
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(13,7));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

