package semante.pipeline.test.rte.shared1.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0019 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0019() throws Exception {

			// create the vocabulary for the text;
			val t35_javier_solana = word("NP_D","Javier_Solana",35);
			val t72_app = word("WHO_A","APP",72);
			val t04_the = word("THE","the",4);
			val t05_spanish = word("MI","Spanish",5);
			val t06_marxist = word("N","Marxist",6);
			val t07_who = word("WHO_A","who",7);
			val t40_serves_as = word("V_2","serves_as",40);
			val t10_nato = word("NP_D","NATO",10);
			val t11_s = word("POSS","s",11);
			val t12_secretary = word("MR","secretary",12);
			val t13_general = word("N","general",13);
			val t15_warned = word("FACT","warned",15);
			val t16_that = word("IGNORE","that",16);
			val t75_det = word("THE","DET",75);
			val t17_nato = word("MR","NATO",17);
			val t18_troops = word("N","troops",18);
			val t20_will = word("V_AUX","will",20);
			val t21_take = word("V_2","take",21);
			val t77_det = word("THE","DET",77);
			val t22_necessary = word("MR","necessary",22);
			val t23_measures = word("N","measures",23);
			val t25_including = word("GER_2","including",25);
			val t26_the = word("THE","the",26);
			val t27_use = word("N","use",27);
			val t28_of = word("P_R","of",28);
			val t29_force = word("N","force",29);
			val t31_against = word("P_R","against",31);
			val t79_det = word("THE","DET",79);
			val t32_media = word("MR","media",32);
			val t33_networks = word("N","networks",33);

			// create the vocabulary for the hypothesis;
			val h09_javier_solana = word("NP_D","Javier_Solana",9);
			val h03_is = word("IS","is",3);
			val h04_nato = word("NP_D","NATO",4);
			val h05_s = word("POSS","s",5);
			val h06_secretary = word("MR","secretary",6);
			val h07_general = word("N","general",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t35_javier_solana
					,
					_(
						t72_app
						,
						_(
							_(
								t04_the
								,
								_(
									t05_spanish
									,
									t06_marxist
									,
									63
								)
								,
								64
							)
							,
							_(
								t07_who
								,
								_(
									t40_serves_as
									,
									_(
										_(
											t10_nato
											,
											t11_s
											,
											38
										)
										,
										_(
											t12_secretary
											,
											t13_general
											,
											65
										)
										,
										66
									)
									,
									74
								)
								,
								43
							)
							,
							44
						)
						,
						73
					)
					,
					67
				)
				,
				_(
					t15_warned
					,
					_(
						t16_that
						,
						_(
							_(
								t75_det
								,
								_(
									t17_nato
									,
									t18_troops
									,
									46
								)
								,
								76
							)
							,
							_(
								t20_will
								,
								_(
									_(
										t21_take
										,
										_(
											_(
												t77_det
												,
												_(
													t22_necessary
													,
													t23_measures
													,
													48
												)
												,
												78
											)
											,
											_(
												t25_including
												,
												_(
													t26_the
													,
													_(
														t27_use
														,
														_(
															t28_of
															,
															t29_force
															,
															51
														)
														,
														49
													)
													,
													81
												)
												,
												53
											)
											,
											68
										)
										,
										69
									)
									,
									_(
										t31_against
										,
										_(
											t79_det
											,
											_(
												t32_media
												,
												t33_networks
												,
												55
											)
											,
											80
										)
										,
										56
									)
									,
									70
								)
								,
								58
							)
							,
							71
						)
						,
						60
					)
					,
					61
				)
				,
				62
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h09_javier_solana
				,
				_(
					h03_is
					,
					_(
						_(
							h04_nato
							,
							h05_s
							,
							10
						)
						,
						_(
							h06_secretary
							,
							h07_general
							,
							14
						)
						,
						15
					)
					,
					12
				)
				,
				16
			)
			;

			// create the subsumption relations;
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(40,3));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
