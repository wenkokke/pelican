package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0054 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0054() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_canadian = word("MI","Canadian",2);
			val t03_wireless = word("MR","wireless",3);
			val t04_technology = word("$NC_1$","technology",4);
			val t05_licensing = word("$NC_1$","licensing",5);
			val t06_company = word("N","company",6);
			val t50_app = word("WHO_A","APP",50);
			val t08_wilan = word("NP_D","WiLAN",8);
			val t10_has = word("V_AUX","has",10);
			val t11_begun = word("V_2","begun",11);
			val t53_det = word("A","DET",53);
			val t12_legal = word("MR","legal",12);
			val t13_action = word("N","action",13);
			val t14_against = word("P_R","against",14);
			val t15_cisco = word("NP_D","Cisco",15);
			val t17_citing = word("GER_2","citing",17);
			val t18_various = word("SOME","various",18);
			val t19_instances = word("N","instances",19);
			val t20_of = word("P_R","of",20);
			val t56_det = word("EMPTYDET","DET",56);
			val t21_stolen = word("MI","stolen",21);
			val t22_intellectual = word("MR","intellectual",22);
			val t23_property = word("N","property",23);

			// create the vocabulary for the hypothesis;
			val h01_wilan = word("NP_D","WiLAN",1);
			val h02_is = word("IS","is",2);
			val h03_canadian = word("MI","Canadian",3);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_canadian
							,
							_(
								t03_wireless
								,
								_(
									t04_technology
									,
									_(
										t05_licensing
										,
										t06_company
										,
										40
									)
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
						t50_app
						,
						t08_wilan
						,
						51
					)
					,
					45
				)
				,
				_(
					t10_has
					,
					_(
						_(
							t11_begun
							,
							_(
								t53_det
								,
								_(
									t12_legal
									,
									_(
										t13_action
										,
										_(
											t14_against
											,
											t15_cisco
											,
											29
										)
										,
										27
									)
									,
									52
								)
								,
								54
							)
							,
							46
						)
						,
						_(
							t17_citing
							,
							_(
								t18_various
								,
								_(
									t19_instances
									,
									_(
										t20_of
										,
										_(
											t56_det
											,
											_(
												t21_stolen
												,
												_(
													t22_intellectual
													,
													t23_property
													,
													47
												)
												,
												48
											)
											,
											57
										)
										,
										33
									)
									,
									31
								)
								,
								55
							)
							,
							35
						)
						,
						49
					)
					,
					38
				)
				,
				39
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_wilan
				,
				_(
					h02_is
					,
					h03_canadian
					,
					6
				)
				,
				7
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

