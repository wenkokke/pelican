package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0044 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0044() throws Exception {

			// create the vocabulary for the text;
			val t01_qnx = word("$NPC_1$","QNX",1);
			val t02_software = word("$NPC_1$","Software",2);
			val t03_systems = word("$NPC_1$","Systems",3);
			val t04_ltd = word("NP_D","Ltd",4);
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
			val t14_computing = word("$NC_1$","computing",14);
			val t15_market = word("N","market",15);
			val t17_announced = word("V_2","announced",17);
			val t18_the = word("THE","the",18);
			val t19_appointment = word("N","appointment",19);
			val t20_of = word("P_R","of",20);
			val t21_mr = word("$NPC_1$","Mr",21);
			val t22_sachin = word("$NPC_1$","Sachin",22);
			val t23_lawande = word("NP_D","Lawande",23);
			val t24_to = word("P_R","to",24);
			val t25_the = word("THE","the",25);
			val t26_position = word("N","position",26);
			val t27_of = word("P_R","of",27);
			val t28_vice = word("$NPC_1$","vice",28);
			val t29_president = word("$NPC_1$","president",29);
			val t30_of = word("$NPC_1$","of",30);
			val t31_engineering = word("$NPC_1$","engineering",31);
			val t32_services = word("NP_D","services",32);

			// create the vocabulary for the hypothesis;
			val h01_qnx = word("$NPC_1$","QNX",1);
			val h02_software = word("$NPC_1$","Software",2);
			val h03_systems = word("$NPC_1$","Systems",3);
			val h04_ltd = word("NP_D","Ltd",4);
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
			val h14_computing = word("$NC_1$","computing",14);
			val h15_market = word("N","market",15);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_qnx
						,
						_(
							t02_software
							,
							_(
								t03_systems
								,
								t04_ltd
								,
								55
							)
							,
							56
						)
						,
						57
					)
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
											_(
												t14_computing
												,
												t15_market
												,
												61
											)
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
									_(
										t21_mr
										,
										_(
											t22_sachin
											,
											t23_lawande
											,
											65
										)
										,
										66
									)
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
											_(
												t28_vice
												,
												_(
													t29_president
													,
													_(
														t30_of
														,
														_(
															t31_engineering
															,
															t32_services
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
												76
											)
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
				_(
					h01_qnx
					,
					_(
						h02_software
						,
						_(
							h03_systems
							,
							h04_ltd
							,
							26
						)
						,
						27
					)
					,
					28
				)
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
										_(
											h14_computing
											,
											h15_market
											,
											31
										)
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

