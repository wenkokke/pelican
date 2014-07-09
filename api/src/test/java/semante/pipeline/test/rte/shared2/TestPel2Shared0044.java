package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0044 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0044() throws Exception {

			// create the vocabulary for the text;
			val t57_qnx_software_systems_ltd = word("NP_D","QNX_Software_Systems_Ltd",57);
			val t69_app = word("WHO_A","APP",69);
			val t06_the = word("THE","the",6);
			val t07_leading = word("MR","leading",7);
			val t08_provider = word("N","provider",8);
			val t09_of = word("P_R","of",9);
			val t77_det = word("EMPTYDET","DET",77);
			val t10_realtime = word("MR","realTime",10);
			val t11_software = word("N","software",11);
			val t12_to = word("P_R","to",12);
			val t13_the = word("THE","the",13);
			val t61_computing_market = word("N","computing_market",61);
			val t17_announced = word("V_2","announced",17);
			val t18_the = word("THE","the",18);
			val t19_appointment = word("N","appointment",19);
			val t20_of = word("P_R","of",20);
			val t66_mr_sachin_lawande = word("NP_D","Mr_Sachin_Lawande",66);
			val t24_to = word("P_R","to",24);
			val t25_the = word("THE","the",25);
			val t26_position = word("N","position",26);
			val t27_of = word("P_R","of",27);
			val t76_vice_president_of_engineering_services = word("NP_D","vice_president_of_engineering_services",76);

			// create the vocabulary for the hypothesis;
			val h28_qnx_software_systems_ltd = word("NP_D","QNX_Software_Systems_Ltd",28);
			val h05_is = word("IS","is",5);
			val h06_a = word("A","a",6);
			val h07_leading = word("MR","leading",7);
			val h08_provider = word("N","provider",8);
			val h09_of = word("P_R","of",9);
			val h34_det = word("EMPTYDET","DET",34);
			val h10_realtime = word("MR","realTime",10);
			val h11_software = word("N","software",11);
			val h12_to = word("P_R","to",12);
			val h13_the = word("THE","the",13);
			val h31_computing_market = word("N","computing_market",31);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t57_qnx_software_systems_ltd
					,
					_(
						t69_app
						,
						_(
							t06_the
							,
							_(
								t07_leading
								,
								_(
									_(
										t08_provider
										,
										_(
											t09_of
											,
											_(
												t77_det
												,
												_(
													t10_realtime
													,
													t11_software
													,
													35
												)
												,
												78
											)
											,
											36
										)
										,
										58
									)
									,
									_(
										t12_to
										,
										_(
											t13_the
											,
											t61_computing_market
											,
											62
										)
										,
										38
									)
									,
									71
								)
								,
								72
							)
							,
							59
						)
						,
						70
					)
					,
					64
				)
				,
				_(
					t17_announced
					,
					_(
						t18_the
						,
						_(
							_(
								t19_appointment
								,
								_(
									t20_of
									,
									t66_mr_sachin_lawande
									,
									43
								)
								,
								41
							)
							,
							_(
								t24_to
								,
								_(
									t25_the
									,
									_(
										t26_position
										,
										_(
											t27_of
											,
											t76_vice_president_of_engineering_services
											,
											50
										)
										,
										45
									)
									,
									75
								)
								,
								52
							)
							,
							73
						)
						,
						74
					)
					,
					67
				)
				,
				54
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h28_qnx_software_systems_ltd
				,
				_(
					h05_is
					,
					_(
						h06_a
						,
						_(
							h07_leading
							,
							_(
								_(
									h08_provider
									,
									_(
										h09_of
										,
										_(
											h34_det
											,
											_(
												h10_realtime
												,
												h11_software
												,
												18
											)
											,
											35
										)
										,
										22
									)
									,
									29
								)
								,
								_(
									h12_to
									,
									_(
										h13_the
										,
										h31_computing_market
										,
										32
									)
									,
									20
								)
								,
								33
							)
							,
							36
						)
						,
						30
					)
					,
					24
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
