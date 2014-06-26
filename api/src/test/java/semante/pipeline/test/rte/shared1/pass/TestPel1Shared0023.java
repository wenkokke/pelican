package semante.pipeline.test.rte.shared1.pass;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0023 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0023() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_soldier = word("N","soldier");
			val t02_app = word("WHO_A","APP");
			val t03_james = word("$NPC_1$","James");
			val t04_barker = word("NP_D","Barker");
			val t05_who = word("WHO_A","who");
			val t06_raped = word("V_2","raped");
			val t07_the = word("THE","the");
			val t08_iraqi = word("MI","Iraqi");
			val t09_girl = word("N","girl");
			val t10_and = word("AND","and");
			val t11_killed = word("V_2","killed");
			val t12_the = word("THE","the");
			val t13_family = word("N","family");
			val t14_in = word("P_R","in");
			val t15_the = word("THE","the");
			val t16_march = word("MR","March");
			val t17_num2006 = word("MR","num2006");
			val t18_incident = word("N","incident");
			val t19_pleaded = word("V_1","pleaded");
			val t20_guilty = word("MR","guilty");
			val t21_to = word("P_R","to");
			val t22_det = word("THE","DET");
			val t23_murder = word("N","murder");
			val t24_on = word("P_R","on");
			val t25_wednesday = word("NP_D","Wednesday");
			val t26_in = word("P_R","in");
			val t27_fort = word("$NPC_1$","Fort");
			val t28_campbell = word("NP_D","Campbell");
			val t29_in = word("P_R","in");
			val t30_kentucky = word("NP_D","Kentucky");

			// create the vocabulary for the hypothesis;
			val h00_james = word("$NPC_1$","James");
			val h01_barker = word("NP_D","Barker");
			val h02_raped = word("V_2","raped");
			val h03_an = word("A","an");
			val h04_iraqi = word("MI","Iraqi");
			val h05_girl = word("N","girl");
			val h06_and = word("AND","and");
			val h07_killed = word("V_2","killed");
			val h08_the = word("THE","the");
			val h09_family = word("N","family");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t00_the
							,
							t01_soldier
						)
						,
						_(
							t02_app
							,
							_(
								t03_james
								,
								t04_barker
							)
						)
					)
					,
					_(
						t05_who
						,
						_(
							_(
								_(
									_(
										t06_raped
										,
										_(
											t07_the
											,
											_(
												t08_iraqi
												,
												t09_girl
											)
										)
									)
									,
									t10_and
								)
								,
								_(
									t11_killed
									,
									_(
										t12_the
										,
										t13_family
									)
								)
							)
							,
							_(
								t14_in
								,
								_(
									t15_the
									,
									_(
										t16_march
										,
										_(
											t17_num2006
											,
											t18_incident
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					_(
						_(
							_(
								_(
									t19_pleaded
									,
									t20_guilty
								)
								,
								_(
									t21_to
									,
									_(
										t22_det
										,
										t23_murder
									)
								)
							)
							,
							_(
								t24_on
								,
								t25_wednesday
							)
						)
						,
						_(
							t26_in
							,
							_(
								t27_fort
								,
								t28_campbell
							)
						)
					)
					,
					_(
						t29_in
						,
						t30_kentucky
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_james
					,
					h01_barker
				)
				,
				_(
					_(
						_(
							h02_raped
							,
							_(
								h03_an
								,
								_(
									h04_iraqi
									,
									h05_girl
								)
							)
						)
						,
						h06_and
					)
					,
					_(
						h07_killed
						,
						_(
							h08_the
							,
							h09_family
						)
					)
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

