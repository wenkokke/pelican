package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0066 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0066() throws Exception {

			// create the vocabulary for the text;
			val t57_det = word("EMPTYDET","DET",57);
			val t01_newspapers = word("N","Newspapers",1);
			val t02_report = word("FACT","report",2);
			val t03_that = word("IGNORE","that",3);
			val t04_the = word("THE","the",4);
			val t05_scandal = word("N","scandal",5);
			val t06_undermines = word("V_2","undermines",6);
			val t07_president = word("$NPC_1$","President",7);
			val t08_cardoso = word("NP_D","Cardoso",8);
			val t09_and = word("AND","and",9);
			val t10_could = word("V_AUX","could",10);
			val t47_force_out = word("V_2","force_out",47);
			val t13_a = word("A","a",13);
			val t14_top = word("MR","top",14);
			val t15_politician = word("N","politician",15);
			val t60_app = word("WHO_A","APP",60);
			val t17_the = word("THE","the",17);
			val t18_brazilian = word("MI","Brazilian",18);
			val t19_president = word("N","president",19);
			val t20_of = word("P_R","of",20);
			val t21_the = word("THE","the",21);
			val t22_senate = word("N","Senate",22);
			val t62_app = word("WHO_A","APP",62);
			val t24_jador = word("$NPC_1$","Jador",24);
			val t25_barbalho = word("NP_D","Barbalho",25);

			// create the vocabulary for the hypothesis;
			val h01_jador = word("$NPC_1$","Jador",1);
			val h02_barbalho = word("NP_D","Barbalho",2);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_brazilian = word("MI","Brazilian",5);
			val h06_politician = word("N","politician",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t57_det
					,
					t01_newspapers
					,
					58
				)
				,
				_(
					t02_report
					,
					_(
						t03_that
						,
						_(
							_(
								t04_the
								,
								t05_scandal
								,
								28
							)
							,
							_(
								_(
									t06_undermines
									,
									_(
										t07_president
										,
										t08_cardoso
										,
										29
									)
									,
									30
								)
								,
								_(
									t09_and
									,
									_(
										t10_could
										,
										_(
											t47_force_out
											,
											_(
												_(
													_(
														t13_a
														,
														_(
															t14_top
															,
															t15_politician
															,
															48
														)
														,
														49
													)
													,
													_(
														t60_app
														,
														_(
															t17_the
															,
															_(
																t18_brazilian
																,
																_(
																	t19_president
																	,
																	_(
																		t20_of
																		,
																		_(
																			t21_the
																			,
																			t22_senate
																			,
																			34
																		)
																		,
																		35
																	)
																	,
																	50
																)
																,
																64
															)
															,
															51
														)
														,
														61
													)
													,
													52
												)
												,
												_(
													t62_app
													,
													_(
														t24_jador
														,
														t25_barbalho
														,
														37
													)
													,
													63
												)
												,
												53
											)
											,
											54
										)
										,
										40
									)
									,
									55
								)
								,
								59
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
				56
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_jador
					,
					h02_barbalho
					,
					8
				)
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						_(
							h05_brazilian
							,
							h06_politician
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

