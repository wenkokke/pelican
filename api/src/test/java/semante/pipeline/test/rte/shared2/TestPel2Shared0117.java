package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0117 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0117() throws Exception {

			// create the vocabulary for the text;
			val t01_wyniemko = word("NP_D","Wyniemko",1);
			val t02_who = word("WHO_A","who",2);
			val t03_now = word("MI","now",3);
			val t04_lives = word("V_1","lives",4);
			val t05_in = word("P_R","in",5);
			val t31_rochester_hills = word("NP_D","Rochester_Hills",31);
			val t61_app = word("WHO_A","APP",61);
			val t09_a = word("A","a",9);
			val t10_small = word("MR","small",10);
			val t11_city = word("N","city",11);
			val t12_in = word("P_R","in",12);
			val t13_michigan = word("NP_D","Michigan",13);
			val t15_was = word("IS","was",15);
			val t16_arrested = word("V_1","arrested",16);
			val t17_and = word("AND","and",17);
			val t18_tried = word("V_1","tried",18);
			val t19_in = word("P_R","in",19);
			val t20_num1994 = word("NP_D","num1994",20);
			val t21_for = word("P_R","for",21);
			val t22_a = word("A","a",22);
			val t23_rape = word("N","rape",23);
			val t24_in = word("P_R","in",24);
			val t44_clinton_township = word("NP_D","Clinton_Township",44);

			// create the vocabulary for the hypothesis;
			val h01_wyniemko = word("NP_D","Wyniemko",1);
			val h02_now = word("MI","now",2);
			val h03_lives = word("V_1","lives",3);
			val h04_in = word("P_R","in",4);
			val h05_a = word("A","a",5);
			val h06_small = word("MR","small",6);
			val h07_city = word("N","city",7);
			val h08_in = word("P_R","in",8);
			val h09_michigan = word("NP_D","Michigan",9);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_wyniemko
					,
					_(
						t02_who
						,
						_(
							t03_now
							,
							_(
								t04_lives
								,
								_(
									t05_in
									,
									_(
										t31_rochester_hills
										,
										_(
											t61_app
											,
											_(
												t09_a
												,
												_(
													t10_small
													,
													_(
														t11_city
														,
														_(
															t12_in
															,
															t13_michigan
															,
															39
														)
														,
														56
													)
													,
													60
												)
												,
												57
											)
											,
											62
										)
										,
										32
									)
									,
									63
								)
								,
								33
							)
							,
							34
						)
						,
						35
					)
					,
					36
				)
				,
				_(
					t15_was
					,
					_(
						_(
							_(
								_(
									t16_arrested
									,
									t17_and
									,
									53
								)
								,
								t18_tried
								,
								54
							)
							,
							_(
								t19_in
								,
								t20_num1994
								,
								49
							)
							,
							55
						)
						,
						_(
							t21_for
							,
							_(
								t22_a
								,
								_(
									t23_rape
									,
									_(
										t24_in
										,
										t44_clinton_township
										,
										45
									)
									,
									43
								)
								,
								64
							)
							,
							47
						)
						,
						51
					)
					,
					65
				)
				,
				66
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_wyniemko
				,
				_(
					h02_now
					,
					_(
						h03_lives
						,
						_(
							h04_in
							,
							_(
								h05_a
								,
								_(
									h06_small
									,
									_(
										h07_city
										,
										_(
											h08_in
											,
											h09_michigan
											,
											15
										)
										,
										20
									)
									,
									24
								)
								,
								21
							)
							,
							17
						)
						,
						18
					)
					,
					22
				)
				,
				23
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
