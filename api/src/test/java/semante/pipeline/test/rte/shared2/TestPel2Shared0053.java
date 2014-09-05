package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0053 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0053() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_groundbreaking = word("MI","groundBreaking",2);
			val t03_icc = word("NP_D","ICC",3);
			val t61_app = word("WHO_A","APP",61);
			val t05_the = word("THE","the",5);
			val t06_first = word("MR","first",6);
			val t07_permanent = word("MR","permanent",7);
			val t08_global = word("MI","global",8);
			val t52_criminal_tribunal = word("N","criminal_tribunal",52);
			val t12_was = word("IS","was",12);
			val t58_set_up = word("V_1","set_up",58);
			val t15_in = word("P_R","in",15);
			val t16_paris = word("NP_D","Paris",16);
			val t18_trying = word("GER_2","trying",18);
			val t63_det = word("EMPTYDET","DET",63);
			val t19_individuals = word("N","individuals",19);
			val t20_who = word("WHO_R","who",20);
			val t21_were = word("IS","were",21);
			val t22_accused = word("V_1","accused",22);
			val t23_of = word("P_R","of",23);
			val t67_det = word("EMPTYDET","DET",67);
			val t24_crimes = word("N","crimes",24);
			val t25_against = word("P_R","against",25);
			val t65_det = word("EMPTYDET","DET",65);
			val t26_humanity = word("N","humanity",26);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_icc = word("NP_D","ICC",2);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h12_criminal_tribunal = word("N","criminal_tribunal",12);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_groundbreaking
							,
							t03_icc
							,
							50
						)
						,
						51
					)
					,
					_(
						t61_app
						,
						_(
							t05_the
							,
							_(
								t06_first
								,
								_(
									t07_permanent
									,
									_(
										t08_global
										,
										t52_criminal_tribunal
										,
										53
									)
									,
									54
								)
								,
								55
							)
							,
							56
						)
						,
						62
					)
					,
					57
				)
				,
				_(
					t12_was
					,
					_(
						_(
							t58_set_up
							,
							_(
								t15_in
								,
								t16_paris
								,
								32
							)
							,
							59
						)
						,
						_(
							t18_trying
							,
							_(
								t63_det
								,
								_(
									t19_individuals
									,
									_(
										t20_who
										,
										_(
											t21_were
											,
											_(
												t22_accused
												,
												_(
													t23_of
													,
													_(
														t67_det
														,
														_(
															t24_crimes
															,
															_(
																t25_against
																,
																_(
																	t65_det
																	,
																	t26_humanity
																	,
																	66
																)
																,
																37
															)
															,
															38
														)
														,
														68
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
										,
										43
									)
									,
									44
								)
								,
								64
							)
							,
							45
						)
						,
						60
					)
					,
					48
				)
				,
				49
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					h02_icc
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
						h12_criminal_tribunal
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
