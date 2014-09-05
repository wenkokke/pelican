package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0018 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0018() throws Exception {

			// create the vocabulary for the text;
			val t01_an = word("A","An",1);
			val t02_avalanche = word("N","avalanche",2);
			val t59_app = word("WHO_A","APP",59);
			val t04_the = word("THE","the",4);
			val t05_biggest = word("MR","biggest",5);
			val t06_natural = word("MR","natural",6);
			val t07_disaster = word("N","disaster",7);
			val t08_in = word("P_R","in",8);
			val t61_det = word("THE","DET",61);
			val t09_recent = word("MR","recent",9);
			val t10_memory = word("N","memory",10);
			val t12_has = word("V_AUX","has",12);
			val t13_struck = word("V_2","struck",13);
			val t14_the = word("THE","the",14);
			val t15_popular = word("MR","popular",15);
			val t16_skiing = word("MR","skiing",16);
			val t17_resort = word("N","resort",17);
			val t63_app = word("WHO_A","APP",63);
			val t36_wanderer_resort = word("NP_D","Wanderer_Resort",36);
			val t22_in = word("P_R","in",22);
			val t23_austria = word("NP_D","Austria",23);
			val t25_killing = word("GER_2","killing",25);
			val t26_num11 = word("NUMBER","num11",26);
			val t27_people = word("N","people",27);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_biggest = word("MR","biggest",2);
			val h03_natural = word("MR","natural",3);
			val h04_disaster = word("N","disaster",4);
			val h05_in = word("P_R","in",5);
			val h26_det = word("THE","DET",26);
			val h06_recent = word("MR","recent",6);
			val h07_memory = word("N","memory",7);
			val h08_struck = word("V_2","struck",8);
			val h09_a = word("A","a",9);
			val h10_skiing = word("MR","skiing",10);
			val h11_resort = word("N","resort",11);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_an
						,
						t02_avalanche
						,
						29
					)
					,
					_(
						t59_app
						,
						_(
							t04_the
							,
							_(
								t05_biggest
								,
								_(
									t06_natural
									,
									_(
										t07_disaster
										,
										_(
											t08_in
											,
											_(
												t61_det
												,
												_(
													t09_recent
													,
													t10_memory
													,
													31
												)
												,
												62
											)
											,
											32
										)
										,
										54
									)
									,
									65
								)
								,
								55
							)
							,
							56
						)
						,
						60
					)
					,
					57
				)
				,
				_(
					t12_has
					,
					_(
						_(
							_(
								t13_struck
								,
								_(
									_(
										t14_the
										,
										_(
											t15_popular
											,
											_(
												t16_skiing
												,
												t17_resort
												,
												47
											)
											,
											48
										)
										,
										49
									)
									,
									_(
										t63_app
										,
										t36_wanderer_resort
										,
										64
									)
									,
									50
								)
								,
								52
							)
							,
							_(
								t22_in
								,
								t23_austria
								,
								38
							)
							,
							53
						)
						,
						_(
							t25_killing
							,
							_(
								t26_num11
								,
								t27_people
								,
								41
							)
							,
							42
						)
						,
						66
					)
					,
					45
				)
				,
				58
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					_(
						h02_biggest
						,
						_(
							h03_natural
							,
							_(
								h04_disaster
								,
								_(
									h05_in
									,
									_(
										h26_det
										,
										_(
											h06_recent
											,
											h07_memory
											,
											14
										)
										,
										27
									)
									,
									15
								)
								,
								22
							)
							,
							28
						)
						,
						23
					)
					,
					24
				)
				,
				_(
					h08_struck
					,
					_(
						h09_a
						,
						_(
							h10_skiing
							,
							h11_resort
							,
							20
						)
						,
						21
					)
					,
					18
				)
				,
				25
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
