package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0008 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0008() throws Exception {

			// create the vocabulary for the text;
			val t48_det = word("EMPTYDET","DET",48);
			val t01_researchers = word("N","Researchers",1);
			val t02_from = word("P_R","from",2);
			val t03_the = word("THE","the",3);
			val t04_us = word("MI","US",4);
			val t05_antarctic = word("MI","Antarctic",5);
			val t06_search = word("N","Search",6);
			val t07_for = word("P_R","for",7);
			val t08_meteorites = word("$NPC_1$","Meteorites",8);
			val t09_program = word("NP_D","Program",9);
			val t10_announced = word("FACT","announced",10);
			val t11_that = word("IGNORE","that",11);
			val t12_a = word("A","a",12);
			val t13_meteorite = word("N","meteorite",13);
			val t14_from = word("P_I","from",14);
			val t15_mars = word("NP_D","Mars",15);
			val t16_was = word("IS","was",16);
			val t17_recovered = word("V_1","recovered",17);
			val t18_in = word("P_R","in",18);
			val t19_the = word("THE","the",19);
			val t20_south = word("$NPC_1$","South",20);
			val t21_pole = word("NP_D","Pole",21);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_meteorite = word("N","meteorite",2);
			val h24_app = word("WHO_A","APP",24);
			val h03_recovered = word("V_1","recovered",3);
			val h04_in = word("P_R","in",4);
			val h05_the = word("THE","the",5);
			val h06_south = word("$NPC_1$","South",6);
			val h07_pole = word("NP_D","Pole",7);
			val h08_was = word("IS","was",8);
			val h09_from = word("P_I","from",9);
			val h10_mars = word("NP_D","Mars",10);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t48_det
					,
					_(
						t01_researchers
						,
						_(
							t02_from
							,
							_(
								t03_the
								,
								_(
									t04_us
									,
									_(
										t05_antarctic
										,
										_(
											t06_search
											,
											_(
												t07_for
												,
												_(
													t08_meteorites
													,
													t09_program
													,
													27
												)
												,
												45
											)
											,
											52
										)
										,
										51
									)
									,
									50
								)
								,
								46
							)
							,
							25
						)
						,
						26
					)
					,
					49
				)
				,
				_(
					t10_announced
					,
					_(
						t11_that
						,
						_(
							_(
								t12_a
								,
								_(
									t13_meteorite
									,
									_(
										t14_from
										,
										t15_mars
										,
										30
									)
									,
									28
								)
								,
								53
							)
							,
							_(
								t16_was
								,
								_(
									t17_recovered
									,
									_(
										t18_in
										,
										_(
											t19_the
											,
											_(
												t20_south
												,
												t21_pole
												,
												43
											)
											,
											44
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
						37
					)
					,
					38
				)
				,
				47
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h01_a
						,
						h02_meteorite
						,
						12
					)
					,
					_(
						h24_app
						,
						_(
							h03_recovered
							,
							_(
								h04_in
								,
								_(
									h05_the
									,
									_(
										h06_south
										,
										h07_pole
										,
										21
									)
									,
									22
								)
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
					23
				)
				,
				_(
					h08_was
					,
					_(
						h09_from
						,
						h10_mars
						,
						15
					)
					,
					16
				)
				,
				26
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

