package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0035 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0035() throws Exception {

			// create the vocabulary for the text;
			val t01_a = word("A","A",1);
			val t67_houston_multimillionaire = word("N","Houston_multimillionaire",67);
			val t04_said = word("V_2","said",4);
			val t05_he = word("NP_D","he",5);
			val t06_is = word("IS","is",6);
			val t07_building = word("V_2","building",7);
			val t08_a = word("A","a",8);
			val t09_museum = word("N","museum",9);
			val t10_for = word("P_R","for",10);
			val t11_the = word("THE","the",11);
			val t12_light = word("MR","light",12);
			val t13_blue = word("MR","blue",13);
			val t73_num1975_ford_escort_gl = word("N","num1975_Ford_Escort_GL",73);
			val t18_which = word("WHO_A","which",18);
			val t19_was = word("IS","was",19);
			val t20_once = word("MR","once",20);
			val t21_owned = word("V_1","owned",21);
			val t22_by = word("P_R","by",22);
			val t64_pope_john_paul_ii = word("NP_D","Pope_John_Paul_II",64);
			val t27_in = word("P_R","in",27);
			val t28_his = word("THE","his",28);
			val t29_hometown = word("N","hometown",29);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h29_num1975_ford_escort_gl = word("N","num1975_Ford_Escort_GL",29);
			val h06_was = word("IS","was",6);
			val h07_once = word("MR","once",7);
			val h08_owned = word("V_1","owned",8);
			val h09_by = word("P_R","by",9);
			val h24_pope_john_paul_ii = word("NP_D","Pope_John_Paul_II",24);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_a
					,
					t67_houston_multimillionaire
					,
					68
				)
				,
				_(
					t04_said
					,
					_(
						t05_he
						,
						_(
							t06_is
							,
							_(
								t07_building
								,
								_(
									t08_a
									,
									_(
										t09_museum
										,
										_(
											t10_for
											,
											_(
												_(
													t11_the
													,
													_(
														t12_light
														,
														_(
															t13_blue
															,
															t73_num1975_ford_escort_gl
															,
															34
														)
														,
														74
													)
													,
													59
												)
												,
												_(
													t18_which
													,
													_(
														_(
															t19_was
															,
															_(
																t20_once
																,
																_(
																	t21_owned
																	,
																	_(
																		t22_by
																		,
																		t64_pope_john_paul_ii
																		,
																		43
																	)
																	,
																	44
																)
																,
																65
															)
															,
															66
														)
														,
														_(
															t27_in
															,
															_(
																t28_his
																,
																t29_hometown
																,
																40
															)
															,
															41
														)
														,
														46
													)
													,
													47
												)
												,
												75
											)
											,
											50
										)
										,
										33
									)
									,
									70
								)
								,
								52
							)
							,
							53
						)
						,
						54
					)
					,
					56
				)
				,
				69
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					h29_num1975_ford_escort_gl
					,
					30
				)
				,
				_(
					h06_was
					,
					_(
						h07_once
						,
						_(
							h08_owned
							,
							_(
								h09_by
								,
								h24_pope_john_paul_ii
								,
								18
							)
							,
							19
						)
						,
						25
					)
					,
					26
				)
				,
				31
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
