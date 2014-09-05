package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0030 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0030() throws Exception {

			// create the vocabulary for the text;
			val t01_a = word("A","A",1);
			val t02_senior = word("MR","senior",2);
			val t61_coalition_official = word("N","coalition_official",61);
			val t05_in = word("P_R","in",5);
			val t06_iraq = word("NP_D","Iraq",6);
			val t07_said = word("FACT","said",7);
			val t08_the = word("THE","the",8);
			val t09_body = word("N","body",9);
			val t11_which = word("WHO_A","which",11);
			val t12_was = word("V_AUX","was",12);
			val t13_found = word("V_1","found",13);
			val t14_by = word("P_R","by",14);
			val t57_us_military_police = word("NP_D","US_military_police",57);
			val t18_in = word("P_R","in",18);
			val t19_the = word("THE","the",19);
			val t20_west = word("N","west",20);
			val t21_of = word("P_R","of",21);
			val t22_baghdad = word("NP_D","Baghdad",22);
			val t24_was = word("V_AUX","was",24);
			val t25_thrown = word("V_1","thrown",25);
			val t26_from = word("P_R","from",26);
			val t27_a = word("A","a",27);
			val t28_vehicle = word("N","vehicle",28);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_body = word("N","body",2);
			val h03_has = word("V_AUX","has",3);
			val h04_been = word("V_AUX","been",4);
			val h05_found = word("V_1","found",5);
			val h06_by = word("P_R","by",6);
			val h19_us_military_police = word("NP_D","US_military_police",19);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_a
					,
					_(
						_(
							t02_senior
							,
							t61_coalition_official
							,
							62
						)
						,
						_(
							t05_in
							,
							t06_iraq
							,
							32
						)
						,
						63
					)
					,
					65
				)
				,
				_(
					t07_said
					,
					_(
						_(
							_(
								t08_the
								,
								t09_body
								,
								34
							)
							,
							_(
								t11_which
								,
								_(
									_(
										_(
											t12_was
											,
											t13_found
											,
											44
										)
										,
										_(
											t14_by
											,
											t57_us_military_police
											,
											37
										)
										,
										66
									)
									,
									_(
										t18_in
										,
										_(
											t19_the
											,
											_(
												t20_west
												,
												_(
													t21_of
													,
													t22_baghdad
													,
													40
												)
												,
												38
											)
											,
											67
										)
										,
										42
									)
									,
									45
								)
								,
								46
							)
							,
							60
						)
						,
						_(
							_(
								t24_was
								,
								t25_thrown
								,
								51
							)
							,
							_(
								t26_from
								,
								_(
									t27_a
									,
									t28_vehicle
									,
									48
								)
								,
								49
							)
							,
							52
						)
						,
						68
					)
					,
					54
				)
				,
				64
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					h02_body
					,
					11
				)
				,
				_(
					_(
						h03_has
						,
						_(
							h04_been
							,
							h05_found
							,
							16
						)
						,
						22
					)
					,
					_(
						h06_by
						,
						h19_us_military_police
						,
						13
					)
					,
					21
				)
				,
				20
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
