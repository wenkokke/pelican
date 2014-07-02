package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0037 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0037() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_grandpa = word("N","Grandpa",2);
			val t03_of = word("P_R","of",3);
			val t04_internet = word("$NPC_1$","Internet",4);
			val t05_explorer = word("NP_D","Explorer",5);
			val t06_was = word("IS","was",6);
			val t07_the = word("THE","the",7);
			val t08_web = word("$NC_1$","web",8);
			val t09_browser = word("N","browser",9);
			val t66_app = word("WHO_A","APP",66);
			val t10_mosaic = word("NP_D","Mosaic",10);
			val t12_which = word("WHO_A","which",12);
			val t13_was = word("IS","was",13);
			val t14_developed = word("V_1","developed",14);
			val t15_at = word("P_R","at",15);
			val t16_the = word("THE","the",16);
			val t17_national = word("MI","National",17);
			val t18_center = word("N","Center",18);
			val t19_for = word("P_R","for",19);
			val t76_det = word("EMPTYDET","DET",76);
			val t20_super = word("$NC_1$","Super",20);
			val t21_computing = word("$NC_1$","Computing",21);
			val t22_applications = word("N","Applications",22);
			val t73_app = word("WHO_A","APP",73);
			val t23_ = word("IGNORE","",23);
			val t24_ncsa = word("NP_D","NCSA",24);
			val t25_ = word("IGNORE","",25);
			val t26_in = word("P_R","in",26);
			val t27_num1987 = word("NP_D","num1987",27);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_web = word("$NC_1$","web",2);
			val h03_browser = word("N","browser",3);
			val h24_app = word("WHO_A","APP",24);
			val h04_called = word("V_2","called",4);
			val h05_mosaic = word("NP_D","Mosaic",5);
			val h06_was = word("IS","was",6);
			val h07_developed = word("V_1","developed",7);
			val h08_at = word("P_R","at",8);
			val h09_the = word("THE","the",9);
			val h10_ncsa = word("NP_D","NCSA",10);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_grandpa
						,
						_(
							t03_of
							,
							_(
								t04_internet
								,
								t05_explorer
								,
								30
							)
							,
							31
						)
						,
						29
					)
					,
					65
				)
				,
				_(
					t06_was
					,
					_(
						_(
							_(
								t07_the
								,
								_(
									t08_web
									,
									t09_browser
									,
									53
								)
								,
								54
							)
							,
							_(
								t66_app
								,
								t10_mosaic
								,
								67
							)
							,
							63
						)
						,
						_(
							t12_which
							,
							_(
								t13_was
								,
								_(
									_(
										t14_developed
										,
										_(
											t15_at
											,
											_(
												_(
													t16_the
													,
													_(
														t17_national
														,
														_(
															t18_center
															,
															_(
																t19_for
																,
																_(
																	t76_det
																	,
																	_(
																		t20_super
																		,
																		_(
																			t21_computing
																			,
																			t22_applications
																			,
																			58
																		)
																		,
																		59
																	)
																	,
																	77
																)
																,
																56
															)
															,
															55
														)
														,
														78
													)
													,
													69
												)
												,
												_(
													t73_app
													,
													_(
														t23_
														,
														_(
															t24_ncsa
															,
															t25_
															,
															38
														)
														,
														60
													)
													,
													74
												)
												,
												36
											)
											,
											72
										)
										,
										57
									)
									,
									_(
										t26_in
										,
										t27_num1987
										,
										42
									)
									,
									62
								)
								,
								46
							)
							,
							48
						)
						,
						68
					)
					,
					50
				)
				,
				64
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h01_a
						,
						_(
							h02_web
							,
							h03_browser
							,
							21
						)
						,
						22
					)
					,
					_(
						h24_app
						,
						_(
							h04_called
							,
							h05_mosaic
							,
							14
						)
						,
						25
					)
					,
					15
				)
				,
				_(
					h06_was
					,
					_(
						h07_developed
						,
						_(
							h08_at
							,
							_(
								h09_the
								,
								h10_ncsa
								,
								16
							)
							,
							17
						)
						,
						18
					)
					,
					19
				)
				,
				23
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

