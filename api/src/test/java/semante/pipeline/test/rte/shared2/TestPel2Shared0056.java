package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0056 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0056() throws Exception {

			// create the vocabulary for the text;
			val t64_det = word("EMPTYDET","DET",64);
			val t01_german = word("MR","German",1);
			val t02_reporters = word("N","reporters",2);
			val t03_said = word("FACT","said",3);
			val t04_that = word("IGNORE","that",4);
			val t05_the = word("THE","the",5);
			val t06_widow = word("N","widow",6);
			val t61_app = word("WHO_A","APP",61);
			val t31_yoko_ono = word("NP_D","Yoko_Ono",31);
			val t11_who = word("WHO_A","who",11);
			val t12_is = word("IS","is",12);
			val t13_a = word("A","a",13);
			val t14_japanese = word("MI","Japanese",14);
			val t15_singer = word("N","singer",15);
			val t17_approved = word("V_2","approved",17);
			val t18_the = word("THE","the",18);
			val t19_museum = word("N","museum",19);
			val t20_as = word("P_R","as",20);
			val t21_the = word("THE","the",21);
			val t22_first = word("MR","first",22);
			val t23_museum = word("N","museum",23);
			val t24_which = word("WHO_R","which",24);
			val t25_honors = word("V_2","honors",25);
			val t42_john_lennon = word("NP_D","John_Lennon",42);

			// create the vocabulary for the hypothesis;
			val h08_yoko_ono = word("NP_D","Yoko_Ono",8);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_japanese = word("MI","Japanese",5);
			val h06_widow = word("N","widow",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t64_det
					,
					_(
						t01_german
						,
						t02_reporters
						,
						29
					)
					,
					65
				)
				,
				_(
					t03_said
					,
					_(
						t04_that
						,
						_(
							_(
								_(
									_(
										t05_the
										,
										t06_widow
										,
										30
									)
									,
									_(
										t61_app
										,
										t31_yoko_ono
										,
										62
									)
									,
									52
								)
								,
								_(
									t11_who
									,
									_(
										t12_is
										,
										_(
											t13_a
											,
											_(
												t14_japanese
												,
												t15_singer
												,
												53
											)
											,
											54
										)
										,
										34
									)
									,
									36
								)
								,
								55
							)
							,
							_(
								_(
									t17_approved
									,
									_(
										t18_the
										,
										t19_museum
										,
										38
									)
									,
									56
								)
								,
								_(
									t20_as
									,
									_(
										t21_the
										,
										_(
											t22_first
											,
											_(
												t23_museum
												,
												_(
													t24_which
													,
													_(
														t25_honors
														,
														t42_john_lennon
														,
														43
													)
													,
													44
												)
												,
												57
											)
											,
											63
										)
										,
										58
									)
									,
									46
								)
								,
								59
							)
							,
							48
						)
						,
						49
					)
					,
					50
				)
				,
				60
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h08_yoko_ono
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						_(
							h05_japanese
							,
							h06_widow
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
