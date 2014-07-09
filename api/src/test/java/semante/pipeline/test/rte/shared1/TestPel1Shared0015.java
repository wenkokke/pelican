package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0015 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0015() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_young = word("MI","young",2);
			val t03_leader = word("N","leader",3);
			val t04_of = word("P_R","of",4);
			val t05_the = word("THE","the",5);
			val t06_coup = word("N","coup",6);
			val t62_app = word("WHO_A","APP",62);
			val t31_pibul_songgram = word("NP_D","Pibul_Songgram",31);
			val t64_app = word("WHO_A","APP",64);
			val t11_educated = word("V_1","educated",11);
			val t12_in = word("P_R","in",12);
			val t13_europe = word("NP_D","Europe",13);
			val t14_and = word("AND","and",14);
			val t15_influenced = word("V_1","influenced",15);
			val t16_by = word("P_R","by",16);
			val t67_det = word("EMPTYDET","DET",67);
			val t17_western = word("MR","Western",17);
			val t18_ideas = word("N","ideas",18);
			val t20_will = word("V_AUX","will",20);
			val t21_dominate = word("V_2","dominate",21);
			val t70_det = word("EMPTYDET","DET",70);
			val t22_thai = word("MI","Thai",22);
			val t23_politics = word("N","politics",23);
			val t24_in = word("P_R","in",24);
			val t25_the = word("THE","the",25);
			val t26_ensuing = word("MR","ensuing",26);
			val t27_years = word("N","years",27);

			// create the vocabulary for the hypothesis;
			val h08_pibul_songgram = word("NP_D","Pibul_Songgram",8);
			val h03_was = word("IS","was",3);
			val h04_a = word("A","a",4);
			val h05_young = word("MI","young",5);
			val h06_leader = word("N","leader",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t01_the
							,
							_(
								t02_young
								,
								_(
									t03_leader
									,
									_(
										t04_of
										,
										_(
											t05_the
											,
											t06_coup
											,
											30
										)
										,
										33
									)
									,
									52
								)
								,
								61
							)
							,
							53
						)
						,
						_(
							t62_app
							,
							t31_pibul_songgram
							,
							63
						)
						,
						55
					)
					,
					_(
						t64_app
						,
						_(
							_(
								t11_educated
								,
								_(
									t12_in
									,
									t13_europe
									,
									35
								)
								,
								36
							)
							,
							_(
								t14_and
								,
								_(
									t15_influenced
									,
									_(
										t16_by
										,
										_(
											t67_det
											,
											_(
												t17_western
												,
												t18_ideas
												,
												37
											)
											,
											68
										)
										,
										38
									)
									,
									39
								)
								,
								56
							)
							,
							66
						)
						,
						65
					)
					,
					58
				)
				,
				_(
					_(
						t20_will
						,
						_(
							t21_dominate
							,
							_(
								t70_det
								,
								_(
									t22_thai
									,
									t23_politics
									,
									42
								)
								,
								71
							)
							,
							48
						)
						,
						46
					)
					,
					_(
						t24_in
						,
						_(
							t25_the
							,
							_(
								t26_ensuing
								,
								t27_years
								,
								49
							)
							,
							50
						)
						,
						44
					)
					,
					69
				)
				,
				59
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h08_pibul_songgram
				,
				_(
					h03_was
					,
					_(
						h04_a
						,
						_(
							h05_young
							,
							h06_leader
							,
							12
						)
						,
						13
					)
					,
					10
				)
				,
				14
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
