package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0021 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0021() throws Exception {

			// create the vocabulary for the text;
			val t41_cote_divoire = word("NP_D","Cote_dIvoire",41);
			val t04_which = word("WHO_A","which",4);
			val t05_once = word("MR","once",5);
			val t06_was = word("IS","was",6);
			val t07_a = word("A","a",7);
			val t08_haven = word("N","haven",8);
			val t09_of = word("P_R","of",9);
			val t92_det = word("THE","DET",92);
			val t10_stability = word("N","stability",10);
			val t11_in = word("P_I","in",11);
			val t12_west = word("MI","West",12);
			val t13_africa = word("NP_D","Africa",13);
			val t15_has = word("V_AUX","has",15);
			val t16_been = word("IS","been",16);
			val t17_split = word("V_1","split",17);
			val t18_in = word("P_R","in",18);
			val t19_two = word("NP_D","two",19);
			val t20_since = word("P_R","since",20);
			val t21_a = word("A","a",21);
			val t22_failed = word("MR","failed",22);
			val t23_coup = word("N","coup",23);
			val t24_against = word("P_R","against",24);
			val t25_gbagbo = word("NP_D","Gbagbo",25);
			val t26_in = word("P_R","in",26);
			val t62_september_num2002 = word("NP_D","September_num2002",62);
			val t30_pitting = word("GER_2","pitting",30);
			val t97_det = word("EMPTYDET","DET",97);
			val t31_rebels = word("N","rebels",31);
			val t32_from = word("P_R","from",32);
			val t33_the = word("THE","the",33);
			val t34_muslimdominated = word("MR","MuslimDominated",34);
			val t35_north = word("N","north",35);
			val t36_against = word("P_R","against",36);
			val t37_the = word("THE","the",37);
			val t38_christianpopulated = word("MR","ChristianPopulated",38);
			val t39_south = word("N","south",39);

			// create the vocabulary for the hypothesis;
			val h07_cote_divoire = word("NP_D","Cote_dIvoire",7);
			val h03_is = word("IS","is",3);
			val h04_in = word("P_I","in",4);
			val h05_africa = word("NP_D","Africa",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t41_cote_divoire
					,
					_(
						t04_which
						,
						_(
							t05_once
							,
							_(
								t06_was
								,
								_(
									t07_a
									,
									_(
										_(
											t08_haven
											,
											_(
												t09_of
												,
												_(
													t92_det
													,
													t10_stability
													,
													93
												)
												,
												49
											)
											,
											50
										)
										,
										_(
											t11_in
											,
											_(
												t12_west
												,
												t13_africa
												,
												46
											)
											,
											47
										)
										,
										90
									)
									,
									100
								)
								,
								91
							)
							,
							52
						)
						,
						53
					)
					,
					88
				)
				,
				_(
					t15_has
					,
					_(
						t16_been
						,
						_(
							_(
								_(
									_(
										t17_split
										,
										_(
											t18_in
											,
											t19_two
											,
											56
										)
										,
										76
									)
									,
									_(
										t20_since
										,
										_(
											t21_a
											,
											_(
												t22_failed
												,
												_(
													t23_coup
													,
													_(
														t24_against
														,
														t25_gbagbo
														,
														59
													)
													,
													77
												)
												,
												94
											)
											,
											78
										)
										,
										61
									)
									,
									79
								)
								,
								_(
									t26_in
									,
									t62_september_num2002
									,
									63
								)
								,
								80
							)
							,
							_(
								t30_pitting
								,
								_(
									t97_det
									,
									_(
										_(
											t31_rebels
											,
											_(
												t32_from
												,
												_(
													t33_the
													,
													_(
														t34_muslimdominated
														,
														t35_north
														,
														82
													)
													,
													83
												)
												,
												68
											)
											,
											98
										)
										,
										_(
											t36_against
											,
											_(
												t37_the
												,
												_(
													t38_christianpopulated
													,
													t39_south
													,
													84
												)
												,
												85
											)
											,
											67
										)
										,
										96
									)
									,
									64
								)
								,
								81
							)
							,
							87
						)
						,
						73
					)
					,
					74
				)
				,
				89
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h07_cote_divoire
				,
				_(
					h03_is
					,
					_(
						h04_in
						,
						h05_africa
						,
						9
					)
					,
					10
				)
				,
				12
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
