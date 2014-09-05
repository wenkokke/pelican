package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0100 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0100() throws Exception {

			// create the vocabulary for the text;
			val t01_genesis = word("NP_D","Genesis",1);
			val t77_app = word("WHO_A","APP",77);
			val t03_the = word("THE","the",3);
			val t71_space_capsule = word("N","space_capsule",71);
			val t06_which = word("WHO_R","which",6);
			val t07_was = word("V_AUX","was",7);
			val t08_collecting = word("V_2","collecting",8);
			val t86_det = word("EMPTYDET","DET",86);
			val t09_particles = word("N","particles",9);
			val t10_from = word("P_R","from",10);
			val t11_the = word("THE","the",11);
			val t12_sun = word("N","sun",12);
			val t14_was = word("IS","was",14);
			val t15_destroyed = word("V_1","destroyed",15);
			val t16_in = word("P_R","in",16);
			val t17_the = word("THE","the",17);
			val t18_highly = word("MR","highly",18);
			val t19_publicized = word("MR","publicized",19);
			val t20_crash = word("N","crash",20);
			val t21_into = word("P_R","into",21);
			val t22_the = word("THE","the",22);
			val t64_utah_desert = word("NP_D","Utah_desert",64);
			val t25_in = word("P_R","in",25);
			val t26_a = word("A","a",26);
			val t27_lastminute = word("MR","lastMinute",27);
			val t28_bungling = word("N","bungling",28);
			val t29_of = word("P_R","of",29);
			val t30_a = word("A","a",30);
			val t31_nearflawless = word("MR","nearFlawless",31);
			val t32_scientific = word("MR","scientific",32);
			val t33_odyssey = word("N","odyssey",33);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h16_space_capsule = word("N","space_capsule",16);
			val h04_was = word("IS","was",4);
			val h05_destroyed = word("V_1","destroyed",5);
			val h06_in = word("P_R","in",6);
			val h07_a = word("A","a",7);
			val h08_crash = word("N","crash",8);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_genesis
					,
					_(
						t77_app
						,
						_(
							t03_the
							,
							_(
								t71_space_capsule
								,
								_(
									t06_which
									,
									_(
										_(
											t07_was
											,
											t08_collecting
											,
											89
										)
										,
										_(
											t86_det
											,
											_(
												t09_particles
												,
												_(
													t10_from
													,
													_(
														t11_the
														,
														t12_sun
														,
														39
													)
													,
													40
												)
												,
												87
											)
											,
											88
										)
										,
										92
									)
									,
									44
								)
								,
								72
							)
							,
							79
						)
						,
						91
					)
					,
					75
				)
				,
				_(
					_(
						_(
							t14_was
							,
							t15_destroyed
							,
							60
						)
						,
						_(
							t16_in
							,
							_(
								t17_the
								,
								_(
									_(
										t18_highly
										,
										_(
											t19_publicized
											,
											t20_crash
											,
											47
										)
										,
										82
									)
									,
									_(
										t21_into
										,
										_(
											t22_the
											,
											t64_utah_desert
											,
											65
										)
										,
										56
									)
									,
									62
								)
								,
								63
							)
							,
							58
						)
						,
						81
					)
					,
					_(
						t25_in
						,
						_(
							t26_a
							,
							_(
								_(
									t27_lastminute
									,
									t28_bungling
									,
									66
								)
								,
								_(
									t29_of
									,
									_(
										t30_a
										,
										_(
											t31_nearflawless
											,
											_(
												t32_scientific
												,
												t33_odyssey
												,
												68
											)
											,
											69
										)
										,
										70
									)
									,
									52
								)
								,
								67
							)
							,
							85
						)
						,
						54
					)
					,
					76
				)
				,
				84
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					h16_space_capsule
					,
					17
				)
				,
				_(
					_(
						h04_was
						,
						h05_destroyed
						,
						14
					)
					,
					_(
						h06_in
						,
						_(
							h07_a
							,
							h08_crash
							,
							11
						)
						,
						12
					)
					,
					19
				)
				,
				18
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
