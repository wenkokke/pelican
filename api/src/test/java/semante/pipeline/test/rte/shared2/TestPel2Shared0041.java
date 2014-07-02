package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0041 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0041() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_terror = word("$NC_1$","terror",2);
			val t03_group = word("N","group",3);
			val t04_had = word("V_AUX","had",4);
			val t05_killed = word("V_2","killed",5);
			val t06_an = word("A","an",6);
			val t07_unarmed = word("MI","unarmed",7);
			val t08_man = word("N","man",8);
			val t10_who = word("WHO_R","who",10);
			val t11_was = word("IS","was",11);
			val t12_an = word("A","an",12);
			val t13_american = word("MI","American",13);
			val t14_citizen = word("N","citizen",14);
			val t66_app = word("WHO_R","APP",66);
			val t15_working = word("V_1","working",15);
			val t16_in = word("P_R","in",16);
			val t17_riyadh = word("NP_D","Riyadh",17);
			val t19_and = word("AND","and",19);
			val t20_kidnapped = word("V_2","kidnapped",20);
			val t21_another = word("A","another",21);
			val t22_man = word("N","man",22);
			val t23_in = word("P_R","in",23);
			val t24_the = word("THE","the",24);
			val t25_same = word("MR","same",25);
			val t26_city = word("N","city",26);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_terror = word("$NC_1$","terror",2);
			val h03_group = word("N","group",3);
			val h04_had = word("V_AUX","had",4);
			val h05_killed = word("V_2","killed",5);
			val h06_an = word("A","an",6);
			val h07_unarmed = word("MI","unarmed",7);
			val h08_american = word("MI","American",8);
			val h09_man = word("N","man",9);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_terror
						,
						t03_group
						,
						59
					)
					,
					60
				)
				,
				_(
					_(
						t04_had
						,
						_(
							t05_killed
							,
							_(
								t06_an
								,
								_(
									t07_unarmed
									,
									_(
										t08_man
										,
										_(
											t10_who
											,
											_(
												t11_was
												,
												_(
													t12_an
													,
													_(
														t13_american
														,
														_(
															t14_citizen
															,
															_(
																t66_app
																,
																_(
																	t15_working
																	,
																	_(
																		t16_in
																		,
																		t17_riyadh
																		,
																		33
																	)
																	,
																	34
																)
																,
																67
															)
															,
															50
														)
														,
														65
													)
													,
													51
												)
												,
												36
											)
											,
											38
										)
										,
										48
									)
									,
									63
								)
								,
								49
							)
							,
							40
						)
						,
						64
					)
					,
					_(
						t19_and
						,
						_(
							t20_kidnapped
							,
							_(
								t21_another
								,
								_(
									t22_man
									,
									_(
										t23_in
										,
										_(
											t24_the
											,
											_(
												t25_same
												,
												t26_city
												,
												55
											)
											,
											56
										)
										,
										44
									)
									,
									42
								)
								,
								68
							)
							,
							54
						)
						,
						53
					)
					,
					69
				)
				,
				70
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_terror
						,
						h03_group
						,
						19
					)
					,
					20
				)
				,
				_(
					h04_had
					,
					_(
						h05_killed
						,
						_(
							h06_an
							,
							_(
								h07_unarmed
								,
								_(
									h08_american
									,
									h09_man
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
						13
					)
					,
					14
				)
				,
				21
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

