package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0043 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0043() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_first = word("MR","first",2);
			val t03_heart = word("$NC_1$","heart",3);
			val t04_transplant = word("N","transplant",4);
			val t05_in = word("P_R","in",5);
			val t06_britain = word("NP_D","Britain",6);
			val t08_which = word("WHO_A","which",8);
			val t09_is = word("IS","is",9);
			val t10_a = word("A","a",10);
			val t11_lifesaving = word("MI","lifesaving",11);
			val t12_procedure = word("N","procedure",12);
			val t14_was = word("IS","was",14);
			val t15_performed = word("V_1","performed",15);
			val t16_at = word("P_R","at",16);
			val t17_the = word("THE","the",17);
			val t18_papworth = word("MR","Papworth",18);
			val t19_hospital = word("N","Hospital",19);
			val t20_of = word("P_R","of",20);
			val t21_cambridgeshire = word("NP_D","Cambridgeshire",21);
			val t22_in = word("P_R","in",22);
			val t23_num1979 = word("NP_D","num1979",23);
			val t25_saving = word("GER_2","saving",25);
			val t26_the = word("THE","the",26);
			val t27_lives = word("N","lives",27);
			val t28_of = word("P_R","of",28);
			val t74_det = word("EMPTYDET","DET",74);
			val t29_people = word("N","people",29);
			val t30_with = word("P_R","with",30);
			val t77_det = word("EMPTYDET","DET",77);
			val t31_cardiovascular = word("MR","cardiovascular",31);
			val t32_problems = word("N","problems",32);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_heart = word("$NC_1$","heart",2);
			val h03_transplant = word("N","transplant",3);
			val h04_is = word("IS","is",4);
			val h05_lifesaving = word("MI","lifesaving",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_first
							,
							_(
								_(
									t03_heart
									,
									t04_transplant
									,
									61
								)
								,
								_(
									t05_in
									,
									t06_britain
									,
									35
								)
								,
								62
							)
							,
							80
						)
						,
						63
					)
					,
					_(
						t08_which
						,
						_(
							t09_is
							,
							_(
								t10_a
								,
								_(
									t11_lifesaving
									,
									t12_procedure
									,
									65
								)
								,
								66
							)
							,
							38
						)
						,
						40
					)
					,
					67
				)
				,
				_(
					t14_was
					,
					_(
						_(
							_(
								t15_performed
								,
								_(
									t16_at
									,
									_(
										t17_the
										,
										_(
											t18_papworth
											,
											_(
												t19_hospital
												,
												_(
													t20_of
													,
													t21_cambridgeshire
													,
													44
												)
												,
												68
											)
											,
											79
										)
										,
										69
									)
									,
									46
								)
								,
								70
							)
							,
							_(
								t22_in
								,
								t23_num1979
								,
								48
							)
							,
							71
						)
						,
						_(
							t25_saving
							,
							_(
								t26_the
								,
								_(
									t27_lives
									,
									_(
										t28_of
										,
										_(
											t74_det
											,
											_(
												t29_people
												,
												_(
													t30_with
													,
													_(
														t77_det
														,
														_(
															t31_cardiovascular
															,
															t32_problems
															,
															51
														)
														,
														78
													)
													,
													52
												)
												,
												75
											)
											,
											76
										)
										,
										54
									)
									,
									49
								)
								,
								73
							)
							,
							56
						)
						,
						72
					)
					,
					59
				)
				,
				60
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_heart
						,
						h03_transplant
						,
						10
					)
					,
					11
				)
				,
				_(
					h04_is
					,
					h05_lifesaving
					,
					8
				)
				,
				9
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

