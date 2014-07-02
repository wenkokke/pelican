package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0060 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0060() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_soyuz = word("NP_D","Soyuz",2);
			val t51_app = word("WHO_A","APP",51);
			val t04_a = word("A","a",4);
			val t05_russian = word("MI","Russian",5);
			val t06_spacecraft = word("N","spacecraft",6);
			val t08_docked = word("V_1","docked",8);
			val t09_with = word("P_R","with",9);
			val t10_the = word("THE","the",10);
			val t11_international = word("MI","International",11);
			val t12_space = word("$NC_1$","Space",12);
			val t13_station = word("N","Station",13);
			val t15_after = word("P_R","after",15);
			val t16_a = word("A","a",16);
			val t17_twoday = word("MR","twoDay",17);
			val t18_flight = word("N","flight",18);
			val t19_from = word("P_R","from",19);
			val t20_kazakhstan = word("NP_D","Kazakhstan",20);
			val t21_s = word("POSS","s",21);
			val t22_baikonur = word("$NC_1$","Baikonur",22);
			val t23_cosmodrome = word("N","Cosmodrome",23);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_russian = word("MI","Russian",2);
			val h03_soyuz = word("NP_D","Soyuz",3);
			val h04_docked = word("V_1","docked",4);
			val h05_with = word("P_R","with",5);
			val h06_a = word("A","a",6);
			val h07_space = word("$NC_1$","space",7);
			val h08_station = word("N","station",8);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						t02_soyuz
						,
						25
					)
					,
					_(
						t51_app
						,
						_(
							t04_a
							,
							_(
								t05_russian
								,
								t06_spacecraft
								,
								47
							)
							,
							48
						)
						,
						52
					)
					,
					49
				)
				,
				_(
					_(
						t08_docked
						,
						_(
							t09_with
							,
							_(
								t10_the
								,
								_(
									t11_international
									,
									_(
										t12_space
										,
										t13_station
										,
										38
									)
									,
									39
								)
								,
								40
							)
							,
							29
						)
						,
						41
					)
					,
					_(
						t15_after
						,
						_(
							t16_a
							,
							_(
								t17_twoday
								,
								_(
									t18_flight
									,
									_(
										t19_from
										,
										_(
											_(
												t20_kazakhstan
												,
												t21_s
												,
												31
											)
											,
											_(
												t22_baikonur
												,
												t23_cosmodrome
												,
												44
											)
											,
											45
										)
										,
										33
									)
									,
									42
								)
								,
								53
							)
							,
							43
						)
						,
						35
					)
					,
					46
				)
				,
				50
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					_(
						h02_russian
						,
						h03_soyuz
						,
						17
					)
					,
					18
				)
				,
				_(
					h04_docked
					,
					_(
						h05_with
						,
						_(
							h06_a
							,
							_(
								h07_space
								,
								h08_station
								,
								15
							)
							,
							16
						)
						,
						12
					)
					,
					13
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

