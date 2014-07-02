package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0115 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0115() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_son = word("N","son",2);
			val t03_of = word("P_R","of",3);
			val t04_the = word("THE","the",4);
			val t05_norwegian = word("MR","Norwegian",5);
			val t06_princess = word("N","princess",6);
			val t88_app = word("WHO_A","APP",88);
			val t07_ingeborg = word("NP_D","Ingeborg",7);
			val t91_app = word("WHO_A","APP",91);
			val t09_the = word("THE","the",9);
			val t10_threeyearold = word("MI","threeYearOld",10);
			val t11_magnus = word("NP_D","Magnus",11);
			val t13_inherited = word("V_2","inherited",13);
			val t14_norway = word("NP_D","Norway",14);
			val t15_s = word("POSS","s",15);
			val t16_throne = word("N","throne",16);
			val t17_from = word("P_R","from",17);
			val t18_the = word("THE","the",18);
			val t19_king = word("N","king",19);
			val t93_app = word("WHO_A","APP",93);
			val t21_haakon = word("$NPC_1$","Haakon",21);
			val t22_v = word("NP_D","V",22);
			val t24_and = word("AND","and",24);
			val t25_was = word("IS","was",25);
			val t26_elected = word("V_2","elected",26);
			val t27_the = word("THE","the",27);
			val t28_king = word("N","King",28);
			val t29_of = word("P_R","of",29);
			val t30_sweden = word("NP_D","Sweden",30);
			val t32_by = word("P_R","by",32);
			val t33_the = word("THE","the",33);
			val t34_convention = word("N","Convention",34);
			val t35_of = word("P_R","of",35);
			val t36_oslo = word("NP_D","Oslo",36);
			val t37_in = word("P_R","in",37);
			val t38_the = word("THE","the",38);
			val t39_same = word("MR","same",39);
			val t40_year = word("N","year",40);

			// create the vocabulary for the hypothesis;
			val h01_magnus = word("NP_D","Magnus",1);
			val h02_inherited = word("V_2","inherited",2);
			val h03_norway = word("NP_D","Norway",3);
			val h04_s = word("POSS","s",4);
			val h05_throne = word("N","throne",5);
			val h06_from = word("P_R","from",6);
			val h07_haakon = word("$NPC_1$","Haakon",7);
			val h08_v = word("NP_D","V",8);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_son
							,
							_(
								t03_of
								,
								_(
									_(
										t04_the
										,
										_(
											t05_norwegian
											,
											t06_princess
											,
											81
										)
										,
										82
									)
									,
									_(
										t88_app
										,
										t07_ingeborg
										,
										89
									)
									,
									44
								)
								,
								90
							)
							,
							42
						)
						,
						87
					)
					,
					_(
						t91_app
						,
						_(
							t09_the
							,
							_(
								t10_threeyearold
								,
								t11_magnus
								,
								83
							)
							,
							84
						)
						,
						92
					)
					,
					85
				)
				,
				_(
					_(
						_(
							_(
								t13_inherited
								,
								_(
									_(
										t14_norway
										,
										t15_s
										,
										48
									)
									,
									t16_throne
									,
									49
								)
								,
								71
							)
							,
							_(
								t17_from
								,
								_(
									_(
										t18_the
										,
										t19_king
										,
										50
									)
									,
									_(
										t93_app
										,
										_(
											t21_haakon
											,
											t22_v
											,
											51
										)
										,
										94
									)
									,
									72
								)
								,
								53
							)
							,
							73
						)
						,
						t24_and
						,
						74
					)
					,
					_(
						t25_was
						,
						_(
							_(
								_(
									t26_elected
									,
									_(
										t27_the
										,
										_(
											t28_king
											,
											_(
												t29_of
												,
												t30_sweden
												,
												57
											)
											,
											55
										)
										,
										95
									)
									,
									75
								)
								,
								_(
									t32_by
									,
									_(
										t33_the
										,
										_(
											t34_convention
											,
											_(
												t35_of
												,
												t36_oslo
												,
												64
											)
											,
											59
										)
										,
										96
									)
									,
									66
								)
								,
								78
							)
							,
							_(
								t37_in
								,
								_(
									t38_the
									,
									_(
										t39_same
										,
										t40_year
										,
										76
									)
									,
									77
								)
								,
								62
							)
							,
							68
						)
						,
						97
					)
					,
					79
				)
				,
				86
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_magnus
				,
				_(
					_(
						h02_inherited
						,
						_(
							_(
								h03_norway
								,
								h04_s
								,
								11
							)
							,
							h05_throne
							,
							12
						)
						,
						17
					)
					,
					_(
						h06_from
						,
						_(
							h07_haakon
							,
							h08_v
							,
							13
						)
						,
						14
					)
					,
					18
				)
				,
				19
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

