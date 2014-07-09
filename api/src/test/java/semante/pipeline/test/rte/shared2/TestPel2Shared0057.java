package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0057 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0057() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t42_world_bank = word("NP_D","World_Bank",42);
			val t50_app = word("WHO_A","APP",50);
			val t05_the = word("THE","the",5);
			val t06_largest = word("MR","largest",6);
			val t07_international = word("MI","international",7);
			val t08_organization = word("N","organization",8);
			val t10_has = word("V_AUX","has",10);
			val t11_been = word("IS","been",11);
			val t12_criticized = word("V_1","criticized",12);
			val t13_for = word("P_R","for",13);
			val t52_det = word("EMPTYDET","DET",52);
			val t14_roles = word("N","roles",14);
			val t15_in = word("P_R","in",15);
			val t54_det = word("EMPTYDET","DET",54);
			val t16_projects = word("N","projects",16);
			val t17_which = word("WHO_R","which",17);
			val t18_have = word("V_AUX","have",18);
			val t19_damaged = word("V_2","damaged",19);
			val t57_det = word("EMPTYDET","DET",57);
			val t20_basic = word("MR","basic",20);
			val t21_human = word("MR","human",21);
			val t22_rights = word("N","rights",22);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h02_international = word("MI","international",2);
			val h03_organization = word("N","organization",3);
			val h04_is = word("IS","is",4);
			val h05_criticized = word("V_1","criticized",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						t42_world_bank
						,
						43
					)
					,
					_(
						t50_app
						,
						_(
							t05_the
							,
							_(
								t06_largest
								,
								_(
									t07_international
									,
									t08_organization
									,
									44
								)
								,
								45
							)
							,
							46
						)
						,
						51
					)
					,
					47
				)
				,
				_(
					t10_has
					,
					_(
						t11_been
						,
						_(
							t12_criticized
							,
							_(
								t13_for
								,
								_(
									t52_det
									,
									_(
										t14_roles
										,
										_(
											t15_in
											,
											_(
												t54_det
												,
												_(
													t16_projects
													,
													_(
														t17_which
														,
														_(
															t18_have
															,
															_(
																t19_damaged
																,
																_(
																	t57_det
																	,
																	_(
																		t20_basic
																		,
																		_(
																			t21_human
																			,
																			t22_rights
																			,
																			48
																		)
																		,
																		49
																	)
																	,
																	58
																)
																,
																30
															)
															,
															31
														)
														,
														33
													)
													,
													34
												)
												,
												56
											)
											,
											35
										)
										,
										36
									)
									,
									53
								)
								,
								37
							)
							,
							38
						)
						,
						39
					)
					,
					40
				)
				,
				41
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_an
					,
					_(
						h02_international
						,
						h03_organization
						,
						11
					)
					,
					12
				)
				,
				_(
					h04_is
					,
					h05_criticized
					,
					9
				)
				,
				13
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
