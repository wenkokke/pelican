package semante.pipeline.test.rte.shared1.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0022 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0022() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_theory = word("N","theory",2);
			val t81_app = word("WHO_A","APP",81);
			val t04_a = word("A","a",4);
			val t05_concept = word("N","concept",5);
			val t92_app = word("WHO_R","APP",92);
			val t06_first = word("MR","first",6);
			val t07_proposed = word("V_1","proposed",7);
			val t08_by = word("P_R","by",8);
			val t39_albert_einstein = word("NP_D","Albert_Einstein",39);
			val t11_and = word("AND","and",11);
			val t12_often = word("MR","often",12);
			val t13_reiterated = word("V_1","reiterated",13);
			val t14_by = word("P_R","by",14);
			val t83_det = word("THE","DET",83);
			val t15_british = word("MI","British",15);
			val t16_astrophysicist = word("N","astrophysicist",16);
			val t85_app = word("WHO_A","APP",85);
			val t72_stephen_hawking = word("NP_D","Stephen_Hawking",72);
			val t20_seeks = word("V_2","seeks",20);
			val t21_a = word("A","a",21);
			val t22_single = word("MR","single",22);
			val t23_set = word("N","set",23);
			val t24_of = word("P_R","of",24);
			val t89_det = word("EMPTYDET","DET",89);
			val t25_equations = word("N","equations",25);
			val t26_that = word("WHO_R","that",26);
			val t27_can = word("V_AUX","can",27);
			val t28_explain = word("V_2","explain",28);
			val t29_the = word("THE","the",29);
			val t30_fundamental = word("MI","fundamental",30);
			val t31_forces = word("N","forces",31);
			val t32_of = word("P_R","of",32);
			val t33_the = word("THE","the",33);
			val t34_universe = word("N","universe",34);

			// create the vocabulary for the hypothesis;
			val h07_stephen_hawking = word("NP_D","Stephen_Hawking",7);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_physicist = word("N","physicist",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						t02_theory
						,
						36
					)
					,
					_(
						t81_app
						,
						_(
							t04_a
							,
							_(
								t05_concept
								,
								_(
									t92_app
									,
									_(
										_(
											t06_first
											,
											_(
												t07_proposed
												,
												_(
													t08_by
													,
													t39_albert_einstein
													,
													40
												)
												,
												69
											)
											,
											70
										)
										,
										_(
											t11_and
											,
											_(
												t12_often
												,
												_(
													t13_reiterated
													,
													_(
														t14_by
														,
														_(
															_(
																t83_det
																,
																_(
																	t15_british
																	,
																	t16_astrophysicist
																	,
																	74
																)
																,
																84
															)
															,
															_(
																t85_app
																,
																t72_stephen_hawking
																,
																86
															)
															,
															44
														)
														,
														87
													)
													,
													75
												)
												,
												76
											)
											,
											71
										)
										,
										80
									)
									,
									93
								)
								,
								37
							)
							,
							94
						)
						,
						95
					)
					,
					78
				)
				,
				_(
					t20_seeks
					,
					_(
						t21_a
						,
						_(
							t22_single
							,
							_(
								t23_set
								,
								_(
									t24_of
									,
									_(
										t89_det
										,
										_(
											t25_equations
											,
											_(
												t26_that
												,
												_(
													t27_can
													,
													_(
														t28_explain
														,
														_(
															t29_the
															,
															_(
																t30_fundamental
																,
																_(
																	t31_forces
																	,
																	_(
																		t32_of
																		,
																		_(
																			t33_the
																			,
																			t34_universe
																			,
																			53
																		)
																		,
																		54
																	)
																	,
																	67
																)
																,
																91
															)
															,
															68
														)
														,
														56
													)
													,
													57
												)
												,
												59
											)
											,
											60
										)
										,
										90
									)
									,
									61
								)
								,
								65
							)
							,
							88
						)
						,
						66
					)
					,
					63
				)
				,
				79
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h07_stephen_hawking
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						h05_physicist
						,
						8
					)
					,
					9
				)
				,
				11
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(16,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
