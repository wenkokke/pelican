package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0085 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0085() throws Exception {

			// create the vocabulary for the text;
			val t31_paul_bremer = word("NP_D","Paul_Bremer",31);
			val t67_app = word("WHO_A","APP",67);
			val t04_the = word("THE","the",4);
			val t05_top = word("MR","top",5);
			val t06_us = word("MI","US",6);
			val t07_civilian = word("MR","civilian",7);
			val t08_administrator = word("N","administrator",8);
			val t09_in = word("P_R","in",9);
			val t10_iraq = word("NP_D","Iraq",10);
			val t12_said = word("FACT","said",12);
			val t13_that = word("IGNORE","that",13);
			val t14_he = word("NP_D","he",14);
			val t15_visited = word("V_2","visited",15);
			val t16_the = word("THE","the",16);
			val t17_northern = word("MR","northern",17);
			val t18_iraqi = word("MI","Iraqi",18);
			val t19_city = word("N","city",19);
			val t20_of = word("P_R","of",20);
			val t21_kirkuk = word("NP_D","Kirkuk",21);
			val t22_with = word("P_R","with",22);
			val t23_the = word("THE","the",23);
			val t24_new = word("MR","new",24);
			val t25_iraqi = word("MI","Iraqi",25);
			val t26_president = word("N","president",26);
			val t70_app = word("WHO_A","APP",70);
			val t43_ghazi_alyawar = word("NP_D","Ghazi_alYawar",43);

			// create the vocabulary for the hypothesis;
			val h06_ghazi_alyawar = word("NP_D","Ghazi_alYawar",6);
			val h03_is = word("IS","is",3);
			val h04_iraqi = word("MI","Iraqi",4);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t31_paul_bremer
					,
					_(
						t67_app
						,
						_(
							t04_the
							,
							_(
								_(
									t05_top
									,
									_(
										t06_us
										,
										_(
											t07_civilian
											,
											t08_administrator
											,
											60
										)
										,
										61
									)
									,
									62
								)
								,
								_(
									t09_in
									,
									t10_iraq
									,
									34
								)
								,
								63
							)
							,
							66
						)
						,
						68
					)
					,
					64
				)
				,
				_(
					t12_said
					,
					_(
						t13_that
						,
						_(
							t14_he
							,
							_(
								_(
									t15_visited
									,
									_(
										t16_the
										,
										_(
											_(
												t17_northern
												,
												_(
													t18_iraqi
													,
													t19_city
													,
													51
												)
												,
												52
											)
											,
											_(
												t20_of
												,
												t21_kirkuk
												,
												40
											)
											,
											53
										)
										,
										69
									)
									,
									54
								)
								,
								_(
									t22_with
									,
									_(
										_(
											t23_the
											,
											_(
												t24_new
												,
												_(
													t25_iraqi
													,
													t26_president
													,
													55
												)
												,
												56
											)
											,
											57
										)
										,
										_(
											t70_app
											,
											t43_ghazi_alyawar
											,
											71
										)
										,
										58
									)
									,
									45
								)
								,
								59
							)
							,
							47
						)
						,
						48
					)
					,
					49
				)
				,
				65
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h06_ghazi_alyawar
				,
				_(
					h03_is
					,
					h04_iraqi
					,
					8
				)
				,
				10
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
