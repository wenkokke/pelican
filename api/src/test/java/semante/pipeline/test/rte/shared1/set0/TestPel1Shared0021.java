package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0021 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0021() throws Exception {

			// create the vocabulary for the text;
			val t00_cote = word("$NPC_1$","Cote");
			val t01_divoire = word("NP_D","dIvoire");
			val t02_which = word("WHO_A","which");
			val t03_is = word("IS","is");
			val t04_a = word("A","a");
			val t05_haven = word("N","haven");
			val t06_of = word("P_R","of");
			val t07_det = word("THE","DET");
			val t08_stability = word("N","stability");
			val t09_in = word("P_I","in");
			val t10_west = word("MI","West");
			val t11_africa = word("NP_D","Africa");
			val t12_has = word("V_AUX","has");
			val t13_been = word("IS","been");
			val t14_split = word("V_1","split");
			val t15_in = word("P_R","in");
			val t16_two = word("NP_D","two");
			val t17_since = word("P_R","since");
			val t18_a = word("A","a");
			val t19_failed = word("MR","failed");
			val t20_coup = word("N","coup");
			val t21_against = word("P_R","against");
			val t22_gbagbo = word("NP_D","Gbagbo");
			val t23_in = word("P_R","in");
			val t24_september = word("$NPC_1$","September");
			val t25_num2002 = word("NP_D","num2002");
			val t26_pitting = word("GER_2","pitting");
			val t27_det = word("EMPTYDET","DET");
			val t28_rebels = word("N","rebels");
			val t29_from = word("P_R","from");
			val t30_the = word("THE","the");
			val t31_muslimdominated = word("MR","MuslimDominated");
			val t32_north = word("N","north");
			val t33_against = word("P_R","against");
			val t34_the = word("THE","the");
			val t35_christianpopulated = word("MR","ChristianPopulated");
			val t36_south = word("N","south");

			// create the vocabulary for the hypothesis;
			val h00_cote = word("$NPC_1$","Cote");
			val h01_divoire = word("NP_D","dIvoire");
			val h02_is = word("IS","is");
			val h03_in = word("P_I","in");
			val h04_africa = word("NP_D","Africa");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_cote
						,
						t01_divoire
					)
					,
					_(
						t02_which
						,
						_(
							t03_is
							,
							_(
								t04_a
								,
								_(
									_(
										t05_haven
										,
										_(
											t06_of
											,
											_(
												t07_det
												,
												t08_stability
											)
										)
									)
									,
									_(
										t09_in
										,
										_(
											t10_west
											,
											t11_africa
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					t12_has
					,
					_(
						t13_been
						,
						_(
							_(
								_(
									_(
										t14_split
										,
										_(
											t15_in
											,
											t16_two
										)
									)
									,
									_(
										t17_since
										,
										_(
											t18_a
											,
											_(
												t19_failed
												,
												_(
													t20_coup
													,
													_(
														t21_against
														,
														t22_gbagbo
													)
												)
											)
										)
									)
								)
								,
								_(
									t23_in
									,
									_(
										t24_september
										,
										t25_num2002
									)
								)
							)
							,
							_(
								t26_pitting
								,
								_(
									t27_det
									,
									_(
										_(
											t28_rebels
											,
											_(
												t29_from
												,
												_(
													t30_the
													,
													_(
														t31_muslimdominated
														,
														t32_north
													)
												)
											)
										)
										,
										_(
											t33_against
											,
											_(
												t34_the
												,
												_(
													t35_christianpopulated
													,
													t36_south
												)
											)
										)
									)
								)
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_cote
					,
					h01_divoire
				)
				,
				_(
					h02_is
					,
					_(
						h03_in
						,
						h04_africa
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

