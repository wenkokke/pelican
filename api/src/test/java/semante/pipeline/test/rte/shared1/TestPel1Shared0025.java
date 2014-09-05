package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0025 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0025() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_famous = word("MR","famous",2);
			val t03_greek = word("MI","Greek",3);
			val t04_cinema = word("IGNORE","cinema",4);
			val t05_and = word("IGNORE","and",5);
			val t06_theater = word("IGNORE","theater",6);
			val t07_actor = word("N","actor",7);
			val t60_app = word("WHO_A","APP",60);
			val t30_nikos_kourkoulos = word("NP_D","Nikos_Kourkoulos",30);
			val t11_died = word("V_1","died",11);
			val t12_at = word("P_R","at",12);
			val t13_the = word("THE","the",13);
			val t14_ericos = word("MR","Ericos",14);
			val t15_dynan = word("MR","Dynan",15);
			val t16_hospital = word("N","Hospital",16);
			val t17_of = word("P_R","of",17);
			val t18_athens = word("NP_D","Athens",18);
			val t20_after = word("P_R","after",20);
			val t21_a = word("A","a",21);
			val t22_longstanding = word("MR","longStanding",22);
			val t23_fight = word("N","fight",23);
			val t24_with = word("P_R","with",24);
			val t25_cancer = word("N","cancer",25);

			// create the vocabulary for the hypothesis;
			val h08_nikos_kourkoulos = word("NP_D","Nikos_Kourkoulos",8);
			val h03_was = word("IS","was",3);
			val h04_a = word("A","a",4);
			val h05_greek = word("MI","Greek",5);
			val h06_actor = word("N","actor",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_famous
							,
							_(
								t03_greek
								,
								_(
									t04_cinema
									,
									_(
										t05_and
										,
										_(
											t06_theater
											,
											t07_actor
											,
											28
										)
										,
										54
									)
									,
									51
								)
								,
								59
							)
							,
							52
						)
						,
						53
					)
					,
					_(
						t60_app
						,
						t30_nikos_kourkoulos
						,
						61
					)
					,
					56
				)
				,
				_(
					_(
						t11_died
						,
						_(
							t12_at
							,
							_(
								t13_the
								,
								_(
									t14_ericos
									,
									_(
										t15_dynan
										,
										_(
											t16_hospital
											,
											_(
												t17_of
												,
												t18_athens
												,
												34
											)
											,
											44
										)
										,
										62
									)
									,
									45
								)
								,
								46
							)
							,
							36
						)
						,
						47
					)
					,
					_(
						t20_after
						,
						_(
							t21_a
							,
							_(
								t22_longstanding
								,
								_(
									t23_fight
									,
									_(
										t24_with
										,
										t25_cancer
										,
										39
									)
									,
									48
								)
								,
								63
							)
							,
							49
						)
						,
						41
					)
					,
					50
				)
				,
				57
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h08_nikos_kourkoulos
				,
				_(
					h03_was
					,
					_(
						h04_a
						,
						_(
							h05_greek
							,
							h06_actor
							,
							12
						)
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
